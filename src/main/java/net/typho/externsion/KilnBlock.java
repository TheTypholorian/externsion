package net.typho.externsion;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.AbstractFurnaceBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.*;
import net.minecraft.client.gui.screen.ingame.AbstractFurnaceScreen;
import net.minecraft.client.gui.screen.recipebook.AbstractFurnaceRecipeBookScreen;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.recipe.*;
import net.minecraft.recipe.book.CookingRecipeCategory;
import net.minecraft.recipe.book.RecipeBookCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.resource.featuretoggle.FeatureFlags;
import net.minecraft.screen.*;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.StatFormatter;
import net.minecraft.stat.Stats;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.Set;

public class KilnBlock extends AbstractFurnaceBlock {
    private static Identifier stat(String id, StatFormatter formatter) {
        Identifier identifier = new Identifier(Externsion.MOD_ID, id);
        Registry.register(Registries.CUSTOM_STAT, id, identifier);
        Stats.CUSTOM.getOrCreateStat(identifier, formatter);
        return identifier;
    }
    private static <T extends net.minecraft.screen.ScreenHandler> ScreenHandlerType<T> screenHandler(String id, ScreenHandlerType.Factory<T> factory) {
        return Registry.register(Registries.SCREEN_HANDLER, new Identifier(Externsion.MOD_ID, id), new ScreenHandlerType<>(factory, FeatureFlags.VANILLA_FEATURES));
    }

    public static final RecipeType<Recipe> RECIPE_TYPE = Registry.register(Registries.RECIPE_TYPE, new Identifier(Externsion.MOD_ID, "firing"), new RecipeType<>() {
        public String toString() {
            return "kiln";
        }
    });
    public static final RecipeSerializer<Recipe> RECIPE_SERIALIZER = Registry.register(Registries.RECIPE_SERIALIZER, new Identifier(Externsion.MOD_ID, "kiln"), new CookingRecipeSerializer<>(Recipe::new, 100));
    public static final Identifier INTERACT_STAT = stat("interact_with_kiln", StatFormatter.DEFAULT);
    public static final ScreenHandlerType<ScreenHandler> SCREEN_HANDLER_TYPE = screenHandler("kiln", ScreenHandler::new);

    protected KilnBlock(Settings settings) {
        super(settings);
    }

    @Override
    public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
        return new Entity(pos, state);
    }

    @Nullable
    @Override
    public <T extends BlockEntity> BlockEntityTicker<T> getTicker(World world, BlockState state, BlockEntityType<T> type) {
        return checkType(world, type, Externsion.KILN_BLOCK_ENTITY_TYPE);
    }

    @Override
    protected void openScreen(World world, BlockPos pos, PlayerEntity player) {
        BlockEntity blockEntity = world.getBlockEntity(pos);
        if (blockEntity instanceof Entity) {
            player.openHandledScreen((NamedScreenHandlerFactory)blockEntity);
            player.incrementStat(INTERACT_STAT);
        }
    }

    @Override
    public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
        if (state.get(LIT)) {
            double d = pos.getX() + 0.5;
            double e = pos.getY();
            double f = pos.getZ() + 0.5;
            if (random.nextDouble() < 0.1) {
                world.playSound(d, e, f, SoundEvents.BLOCK_BLASTFURNACE_FIRE_CRACKLE, SoundCategory.BLOCKS, 1.0F, 1.0F, false);
            }

            Direction direction = state.get(FACING);
            Direction.Axis axis = direction.getAxis();
            double g = 0.52;
            double h = random.nextDouble() * 0.6 - 0.3;
            double i = axis == Direction.Axis.X ? direction.getOffsetX() * 0.52 : h;
            double j = random.nextDouble() * 9.0 / 16.0;
            double k = axis == Direction.Axis.Z ? direction.getOffsetZ() * 0.52 : h;
            world.addParticle(ParticleTypes.SMOKE, d + i, e + j, f + k, 0.0, 0.0, 0.0);
        }
    }

    public static class Entity extends AbstractFurnaceBlockEntity {
        public Entity(BlockPos pos, BlockState state) {
            super(Externsion.KILN_BLOCK_ENTITY_TYPE, pos, state, RECIPE_TYPE);
        }

        @Override
        protected Text getContainerName() {
            return Text.translatable("container.externsion.kiln");
        }

        @Override
        protected int getFuelTime(ItemStack fuel) {
            return super.getFuelTime(fuel) / 2;
        }

        @Override
        protected ScreenHandler createScreenHandler(int syncId, PlayerInventory playerInventory) {
            return new ScreenHandler(syncId, playerInventory, this, this.propertyDelegate);
        }
    }

    public static class Recipe extends AbstractCookingRecipe {
        public Recipe(Identifier id, String group, CookingRecipeCategory category, Ingredient input, ItemStack output, float experience, int cookTime) {
            super(RECIPE_TYPE, id, group, category, input, output, experience, cookTime);
        }

        @Override
        public ItemStack createIcon() {
            return new ItemStack(Externsion.KILN_BLOCK);
        }

        @Override
        public RecipeSerializer<?> getSerializer() {
            return RECIPE_SERIALIZER;
        }
    }

    public static class ScreenHandler extends AbstractFurnaceScreenHandler {
        public ScreenHandler(int syncId, PlayerInventory playerInventory) {
            super(SCREEN_HANDLER_TYPE, RECIPE_TYPE, RecipeBookCategory.FURNACE, syncId, playerInventory);
        }

        public ScreenHandler(int syncId, PlayerInventory playerInventory, Inventory inventory, PropertyDelegate propertyDelegate) {
            super(SCREEN_HANDLER_TYPE, RECIPE_TYPE, RecipeBookCategory.FURNACE, syncId, playerInventory, inventory, propertyDelegate);
        }
    }

    @Environment(EnvType.CLIENT)
    public static class Screen extends AbstractFurnaceScreen<ScreenHandler> {
        private static final Identifier TEXTURE = new Identifier(Externsion.MOD_ID, "textures/gui/container/kiln.png");

        public Screen(ScreenHandler container, PlayerInventory inventory, Text title) {
            super(container, new RecipeBookScreen(), inventory, title, TEXTURE);
        }
    }

    @Environment(EnvType.CLIENT)
    public static class RecipeBookScreen extends AbstractFurnaceRecipeBookScreen {
        private static final Text TOGGLE_FIREABLE_RECIPES_TEXT = Text.translatable("gui.externsion.recipebook.toggleRecipes.fireable");

        @Override
        protected Text getToggleCraftableButtonText() {
            return TOGGLE_FIREABLE_RECIPES_TEXT;
        }

        @Override
        protected Set<Item> getAllowedFuels() {
            return AbstractFurnaceBlockEntity.createFuelTimeMap().keySet();
        }
    }
}

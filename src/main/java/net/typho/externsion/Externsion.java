package net.typho.externsion;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.client.gui.screen.ingame.HandledScreens;
import net.minecraft.datafixer.TypeReferences;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemGroups;
import net.minecraft.recipe.book.RecipeBookCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

public class Externsion implements ModInitializer {
	public static final String MOD_ID = "externsion";

	public static Block block(String name, Block block) {
		return block(new Identifier(MOD_ID, name), block);
	}

	public static Block block(Identifier id, Block block) {
		Registry.register(Registries.ITEM, id, new BlockItem(block, new FabricItemSettings()));
		return Registry.register(Registries.BLOCK, id, block);
	}

	public static final Block KILN_BLOCK = block("kiln", new KilnBlock(FabricBlockSettings.copy(Blocks.BLAST_FURNACE)));

	private static <T extends BlockEntity> BlockEntityType<T> blockEntity(String id, BlockEntityType.Builder<T> builder) {
		return Registry.register(Registries.BLOCK_ENTITY_TYPE, new Identifier(Externsion.MOD_ID, id), builder.build(Util.getChoiceType(TypeReferences.BLOCK_ENTITY, id)));
	}

	public static final BlockEntityType<KilnBlock.Entity> KILN_BLOCK_ENTITY_TYPE = blockEntity("kiln", BlockEntityType.Builder.create(KilnBlock.Entity::new, KILN_BLOCK));

	public static final BlockSet OAK_BLOCK_SET = new BlockSet(BlockSet.Type.WOOD, new Identifier("oak"))
			.setSolid(Blocks.OAK_PLANKS)
			.setChiseled(block("chiseled_oak_planks", new Block(FabricBlockSettings.copy(Blocks.OAK_PLANKS))))
			.setStairs(Blocks.OAK_STAIRS)
			.setSlab(Blocks.OAK_SLAB)
			.setFence(Blocks.OAK_FENCE)
			.setFenceGate(Blocks.OAK_FENCE_GATE)
			.setDoor(Blocks.OAK_DOOR)
			.setTrapdoor(Blocks.OAK_TRAPDOOR)
			.setPressurePlate(Blocks.OAK_PRESSURE_PLATE)
			.setButton(Blocks.OAK_BUTTON)
			.setSign(Blocks.OAK_SIGN)
			.setWallSign(Blocks.OAK_WALL_SIGN);

	public static final BlockSet SPRUCE_BLOCK_SET = new BlockSet(BlockSet.Type.WOOD, new Identifier("spruce"))
			.setSolid(Blocks.SPRUCE_PLANKS)
			.setChiseled(block("chiseled_spruce_planks", new Block(FabricBlockSettings.copy(Blocks.SPRUCE_PLANKS))))
			.setStairs(Blocks.SPRUCE_STAIRS)
			.setSlab(Blocks.SPRUCE_SLAB)
			.setFence(Blocks.SPRUCE_FENCE)
			.setFenceGate(Blocks.SPRUCE_FENCE_GATE)
			.setDoor(Blocks.SPRUCE_DOOR)
			.setTrapdoor(Blocks.SPRUCE_TRAPDOOR)
			.setPressurePlate(Blocks.SPRUCE_PRESSURE_PLATE)
			.setButton(Blocks.SPRUCE_BUTTON)
			.setSign(Blocks.SPRUCE_SIGN)
			.setWallSign(Blocks.SPRUCE_WALL_SIGN);

	public static final BlockSet BIRCH_BLOCK_SET = new BlockSet(BlockSet.Type.WOOD, new Identifier("birch"))
			.setSolid(Blocks.BIRCH_PLANKS)
			.setChiseled(block("chiseled_birch_planks", new Block(FabricBlockSettings.copy(Blocks.BIRCH_PLANKS))))
			.setStairs(Blocks.BIRCH_STAIRS)
			.setSlab(Blocks.BIRCH_SLAB)
			.setFence(Blocks.BIRCH_FENCE)
			.setFenceGate(Blocks.BIRCH_FENCE_GATE)
			.setDoor(Blocks.BIRCH_DOOR)
			.setTrapdoor(Blocks.BIRCH_TRAPDOOR)
			.setPressurePlate(Blocks.BIRCH_PRESSURE_PLATE)
			.setButton(Blocks.BIRCH_BUTTON)
			.setSign(Blocks.BIRCH_SIGN)
			.setWallSign(Blocks.BIRCH_WALL_SIGN);

	public static final BlockSet JUNGLE_BLOCK_SET = new BlockSet(BlockSet.Type.WOOD, new Identifier("jungle"))
			.setSolid(Blocks.JUNGLE_PLANKS)
			.setChiseled(block("chiseled_jungle_planks", new Block(FabricBlockSettings.copy(Blocks.JUNGLE_PLANKS))))
			.setStairs(Blocks.JUNGLE_STAIRS)
			.setSlab(Blocks.JUNGLE_SLAB)
			.setFence(Blocks.JUNGLE_FENCE)
			.setFenceGate(Blocks.JUNGLE_FENCE_GATE)
			.setDoor(Blocks.JUNGLE_DOOR)
			.setTrapdoor(Blocks.JUNGLE_TRAPDOOR)
			.setPressurePlate(Blocks.JUNGLE_PRESSURE_PLATE)
			.setButton(Blocks.JUNGLE_BUTTON)
			.setSign(Blocks.JUNGLE_SIGN)
			.setWallSign(Blocks.JUNGLE_WALL_SIGN);

	public static final BlockSet ACACIA_BLOCK_SET = new BlockSet(BlockSet.Type.WOOD, new Identifier("acacia"))
			.setSolid(Blocks.ACACIA_PLANKS)
			.setChiseled(block("chiseled_acacia_planks", new Block(FabricBlockSettings.copy(Blocks.ACACIA_PLANKS))))
			.setStairs(Blocks.ACACIA_STAIRS)
			.setSlab(Blocks.ACACIA_SLAB)
			.setFence(Blocks.ACACIA_FENCE)
			.setFenceGate(Blocks.ACACIA_FENCE_GATE)
			.setDoor(Blocks.ACACIA_DOOR)
			.setTrapdoor(Blocks.ACACIA_TRAPDOOR)
			.setPressurePlate(Blocks.ACACIA_PRESSURE_PLATE)
			.setButton(Blocks.ACACIA_BUTTON)
			.setSign(Blocks.ACACIA_SIGN)
			.setWallSign(Blocks.ACACIA_WALL_SIGN);

	public static final BlockSet DARK_OAK_BLOCK_SET = new BlockSet(BlockSet.Type.WOOD, new Identifier("dark_oak"))
			.setSolid(Blocks.DARK_OAK_PLANKS)
			.setChiseled(block("chiseled_dark_oak_planks", new Block(FabricBlockSettings.copy(Blocks.DARK_OAK_PLANKS))))
			.setStairs(Blocks.DARK_OAK_STAIRS)
			.setSlab(Blocks.DARK_OAK_SLAB)
			.setFence(Blocks.DARK_OAK_FENCE)
			.setFenceGate(Blocks.DARK_OAK_FENCE_GATE)
			.setDoor(Blocks.DARK_OAK_DOOR)
			.setTrapdoor(Blocks.DARK_OAK_TRAPDOOR)
			.setPressurePlate(Blocks.DARK_OAK_PRESSURE_PLATE)
			.setButton(Blocks.DARK_OAK_BUTTON)
			.setSign(Blocks.DARK_OAK_SIGN)
			.setWallSign(Blocks.DARK_OAK_WALL_SIGN);

	public static final BlockSet MANGROVE_BLOCK_SET = new BlockSet(BlockSet.Type.WOOD, new Identifier("mangrove"))
			.setSolid(Blocks.MANGROVE_PLANKS)
			.setChiseled(block("chiseled_mangrove_planks", new Block(FabricBlockSettings.copy(Blocks.MANGROVE_PLANKS))))
			.setStairs(Blocks.MANGROVE_STAIRS)
			.setSlab(Blocks.MANGROVE_SLAB)
			.setFence(Blocks.MANGROVE_FENCE)
			.setFenceGate(Blocks.MANGROVE_FENCE_GATE)
			.setDoor(Blocks.MANGROVE_DOOR)
			.setTrapdoor(Blocks.MANGROVE_TRAPDOOR)
			.setPressurePlate(Blocks.MANGROVE_PRESSURE_PLATE)
			.setButton(Blocks.MANGROVE_BUTTON)
			.setSign(Blocks.MANGROVE_SIGN)
			.setWallSign(Blocks.MANGROVE_WALL_SIGN);

	public static final BlockSet CHERRY_BLOCK_SET = new BlockSet(BlockSet.Type.WOOD, new Identifier("cherry"))
			.setSolid(Blocks.CHERRY_PLANKS)
			.setChiseled(block("chiseled_cherry_planks", new Block(FabricBlockSettings.copy(Blocks.CHERRY_PLANKS))))
			.setStairs(Blocks.CHERRY_STAIRS)
			.setSlab(Blocks.CHERRY_SLAB)
			.setFence(Blocks.CHERRY_FENCE)
			.setFenceGate(Blocks.CHERRY_FENCE_GATE)
			.setDoor(Blocks.CHERRY_DOOR)
			.setTrapdoor(Blocks.CHERRY_TRAPDOOR)
			.setPressurePlate(Blocks.CHERRY_PRESSURE_PLATE)
			.setButton(Blocks.CHERRY_BUTTON)
			.setSign(Blocks.CHERRY_SIGN)
			.setWallSign(Blocks.CHERRY_WALL_SIGN);

	public static final BlockSet COBBLESTONE_BLOCK_SET = new BlockSet(BlockSet.Type.ROCKS, new Identifier("cobblestone"))
			.setSolid(Blocks.COBBLESTONE)
			.setStairs(Blocks.COBBLESTONE_STAIRS)
			.setSlab(Blocks.COBBLESTONE_SLAB)
			.setWall(Blocks.COBBLESTONE_WALL);

	public static final BlockSet STONE_BLOCK_SET = new BlockSet(BlockSet.Type.ROCKS, new Identifier("stone"))
			.setSolid(Blocks.STONE)
			.setStairs(Blocks.STONE_STAIRS)
			.setSlab(Blocks.STONE_SLAB)
			.setWall(block("stone_wall", new WallBlock(AbstractBlock.Settings.copy(Blocks.STONE).solid())))
			.setPressurePlate(Blocks.STONE_PRESSURE_PLATE);

	public static final BlockSet SMOOTH_STONE_BLOCK_SET = new BlockSet(BlockSet.Type.ROCKS, new Identifier("smooth_stone"))
			.setSolid(Blocks.SMOOTH_STONE)
			.setStairs(block("smooth_stone_stairs", new StairsBlock(Blocks.SMOOTH_STONE.getDefaultState(), AbstractBlock.Settings.copy(Blocks.SMOOTH_STONE).solid())))
			.setSlab(Blocks.SMOOTH_STONE_SLAB)
			.setWall(block("smooth_stone_wall", new WallBlock(AbstractBlock.Settings.copy(Blocks.SMOOTH_STONE).solid())));

	public static final BlockSet QUARTZ_BLOCK_SET = new BlockSet(BlockSet.Type.ROCKS, new Identifier("quartz"))
			.setSolid(Blocks.QUARTZ_BLOCK)
			.setPillar(Blocks.QUARTZ_PILLAR)
			.setStairs(Blocks.QUARTZ_STAIRS)
			.setSlab(Blocks.QUARTZ_SLAB)
			.setWall(block("quartz_wall", new WallBlock(AbstractBlock.Settings.copy(Blocks.QUARTZ_BLOCK).solid())));

	public static final BlockSet QUARTZ_BRICKS_BLOCK_SET = new BlockSet(BlockSet.Type.ROCKS, new Identifier("quartz_bricks"))
			.setSolid(Blocks.QUARTZ_BRICKS)
			.setStairs(block("quartz_brick_stairs", new StairsBlock(Blocks.QUARTZ_BRICKS.getDefaultState(), AbstractBlock.Settings.copy(Blocks.QUARTZ_BRICKS).solid())))
			.setSlab(block("quartz_brick_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.QUARTZ_BRICKS).solid())))
			.setWall(block("quartz_brick_wall", new WallBlock(AbstractBlock.Settings.copy(Blocks.QUARTZ_BRICKS).solid())));

	public static final BlockSet SMOOTH_QUARTZ_BLOCK_SET = new BlockSet(BlockSet.Type.ROCKS, new Identifier("smooth_quartz"))
			.setSolid(Blocks.SMOOTH_QUARTZ)
			.setStairs(Blocks.SMOOTH_QUARTZ_STAIRS)
			.setSlab(Blocks.SMOOTH_QUARTZ_SLAB)
			.setWall(block("smooth_quartz_wall", new WallBlock(AbstractBlock.Settings.copy(Blocks.SMOOTH_QUARTZ).solid())));

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS)
				.register(entries -> {
					entries.addAfter(OAK_BLOCK_SET.solid.asItem(), OAK_BLOCK_SET.chiseled);
					entries.addAfter(SPRUCE_BLOCK_SET.solid.asItem(), SPRUCE_BLOCK_SET.chiseled);
					entries.addAfter(BIRCH_BLOCK_SET.solid.asItem(), BIRCH_BLOCK_SET.chiseled);
					entries.addAfter(JUNGLE_BLOCK_SET.solid.asItem(), JUNGLE_BLOCK_SET.chiseled);
					entries.addAfter(ACACIA_BLOCK_SET.solid.asItem(), ACACIA_BLOCK_SET.chiseled);
					entries.addAfter(DARK_OAK_BLOCK_SET.solid.asItem(), DARK_OAK_BLOCK_SET.chiseled);
					entries.addAfter(MANGROVE_BLOCK_SET.solid.asItem(), MANGROVE_BLOCK_SET.chiseled);
					entries.addAfter(CHERRY_BLOCK_SET.solid.asItem(), CHERRY_BLOCK_SET.chiseled);
					entries.addAfter(STONE_BLOCK_SET.slab.asItem(), STONE_BLOCK_SET.wall);
					entries.addAfter(QUARTZ_BLOCK_SET.slab.asItem(), QUARTZ_BLOCK_SET.wall);
					entries.addAfter(QUARTZ_BRICKS_BLOCK_SET.solid.asItem(), QUARTZ_BRICKS_BLOCK_SET.stairs, QUARTZ_BRICKS_BLOCK_SET.slab, QUARTZ_BRICKS_BLOCK_SET.wall);
					entries.addAfter(SMOOTH_QUARTZ_BLOCK_SET.slab.asItem(), SMOOTH_QUARTZ_BLOCK_SET.wall);
				});
		HandledScreens.register(KilnBlock.SCREEN_HANDLER_TYPE, KilnBlock.Screen::new);
		LOGGER.info(Arrays.toString(RecipeBookCategory.values()));
	}
}
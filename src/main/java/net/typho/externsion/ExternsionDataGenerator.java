package net.typho.externsion;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.block.Block;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.item.ItemConvertible;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.Registries;
import net.minecraft.registry.RegistryWrapper;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class ExternsionDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator gen) {
		FabricDataGenerator.Pack pack = gen.createPack();

		pack.addProvider(Models::new);
		pack.addProvider(Recipes::new);
		pack.addProvider(BlockLootTables::new);
		pack.addProvider(BlockTags::new);
	}

	public static class Models extends FabricModelProvider {
		public Models(FabricDataOutput output) {
			super(output);
		}

		public static void vanillaWood(BlockStateModelGenerator gen, BlockSet set) {
			gen.registerSimpleCubeAll(set.chiseled);
		}

		@Override
		public void generateBlockStateModels(BlockStateModelGenerator gen) {
			vanillaWood(gen, Externsion.OAK_BLOCK_SET);
			vanillaWood(gen, Externsion.SPRUCE_BLOCK_SET);
			vanillaWood(gen, Externsion.BIRCH_BLOCK_SET);
			vanillaWood(gen, Externsion.JUNGLE_BLOCK_SET);
			vanillaWood(gen, Externsion.ACACIA_BLOCK_SET);
			vanillaWood(gen, Externsion.DARK_OAK_BLOCK_SET);
			vanillaWood(gen, Externsion.MANGROVE_BLOCK_SET);
			vanillaWood(gen, Externsion.CHERRY_BLOCK_SET);

			BlockStateModelGenerator.BlockTexturePool stonePool = gen.registerCubeAllModelTexturePool(Externsion.STONE_BLOCK_SET.solid);
			stonePool.wall(Externsion.STONE_BLOCK_SET.wall);

			BlockStateModelGenerator.BlockTexturePool smoothStonePool = gen.registerCubeAllModelTexturePool(Externsion.SMOOTH_STONE_BLOCK_SET.solid);
			smoothStonePool.stairs(Externsion.SMOOTH_STONE_BLOCK_SET.stairs);
			// no datagen for this wall due to custom textures
			//smoothStonePool.wall(Externsion.SMOOTH_STONE_BLOCK_SET.wall);

			// no datagen for quartz walls due to separate top/side/bottom textures

			BlockStateModelGenerator.BlockTexturePool quartzBricksPool = gen.registerCubeAllModelTexturePool(Externsion.QUARTZ_BRICKS_BLOCK_SET.solid);
			quartzBricksPool.stairs(Externsion.QUARTZ_BRICKS_BLOCK_SET.stairs);
			quartzBricksPool.slab(Externsion.QUARTZ_BRICKS_BLOCK_SET.slab);
			quartzBricksPool.wall(Externsion.QUARTZ_BRICKS_BLOCK_SET.wall);

			BlockStateModelGenerator.BlockTexturePool smoothQuartzPool = gen.registerCubeAllModelTexturePool(Externsion.SMOOTH_QUARTZ_BLOCK_SET.solid);
			smoothQuartzPool.wall(Externsion.SMOOTH_QUARTZ_BLOCK_SET.wall);
		}

		@Override
		public void generateItemModels(ItemModelGenerator gen) {
		}
	}

	public static class Recipes extends FabricRecipeProvider {
		public Recipes(FabricDataOutput output) {
			super(output);
		}

		public static void compact(Consumer<RecipeJsonProvider> gen, Block result, Block ingredient) {
			ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, result, 4)
					.pattern("AA")
					.pattern("AA")
					.input('A', ingredient)
					.criterion("has_" + Registries.BLOCK.getId(ingredient).getPath(), FabricRecipeProvider.conditionsFromItem(ingredient))
					.offerTo(gen, Registries.BLOCK.getId(result));
		}

		public static void wall(Consumer<RecipeJsonProvider> gen, Block result, Block ingredient) {
			ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, result, 6)
					.pattern("AAA")
					.pattern("AAA")
					.input('A', ingredient)
					.criterion("has_" + Registries.BLOCK.getId(ingredient).getPath(), FabricRecipeProvider.conditionsFromItem(ingredient))
					.offerTo(gen, Registries.BLOCK.getId(result));
		}

		public static void offerFiring(
				Consumer<RecipeJsonProvider> exporter,
				List<ItemConvertible> inputs,
				RecipeCategory category,
				ItemConvertible output,
				float experience,
				int cookingTime,
				String group
		) {
			offerMultipleOptions(exporter, KilnBlock.RECIPE_SERIALIZER, inputs, category, output, experience, cookingTime, group, "_from_firing");
		}

		public static void vanillaWood(Consumer<RecipeJsonProvider> gen, BlockSet set) {
			compact(gen, set.chiseled, set.solid);
		}

		public static void firingStone(Consumer<RecipeJsonProvider> gen, List<ItemConvertible> inputs, ItemConvertible output, String group) {
			offerFiring(gen, inputs, RecipeCategory.BUILDING_BLOCKS, output,
					0.1f, 100, group);
		}

		public static void firingBlockSetsHelper(Consumer<RecipeJsonProvider> gen, ItemConvertible input, ItemConvertible output, String group) {
			if (input != null && output != null) {
				firingStone(gen, List.of(input), output, group);
			}
		}

		public static void firingBlockSets(Consumer<RecipeJsonProvider> gen, BlockSet in, BlockSet out, String group) {
			firingBlockSetsHelper(gen, in.solid, out.solid, group);
			firingBlockSetsHelper(gen, in.chiseled, out.chiseled, group);
			firingBlockSetsHelper(gen, in.pillar, out.pillar, group);
			firingBlockSetsHelper(gen, in.stairs, out.stairs, group);
			firingBlockSetsHelper(gen, in.slab, out.slab, group);
			firingBlockSetsHelper(gen, in.button, out.button, group);
			firingBlockSetsHelper(gen, in.pressurePlate, out.pressurePlate, group);
			firingBlockSetsHelper(gen, in.fence, out.fence, group);
			firingBlockSetsHelper(gen, in.fenceGate, out.fenceGate, group);
			firingBlockSetsHelper(gen, in.sign, out.sign, group);
			firingBlockSetsHelper(gen, in.wallSign, out.wallSign, group);
			firingBlockSetsHelper(gen, in.wall, out.wall, group);
			firingBlockSetsHelper(gen, in.door, out.door, group);
			firingBlockSetsHelper(gen, in.trapdoor, out.trapdoor, group);
		}

		@Override
		public void generate(Consumer<RecipeJsonProvider> gen) {
			vanillaWood(gen, Externsion.OAK_BLOCK_SET);
			vanillaWood(gen, Externsion.SPRUCE_BLOCK_SET);
			vanillaWood(gen, Externsion.BIRCH_BLOCK_SET);
			vanillaWood(gen, Externsion.JUNGLE_BLOCK_SET);
			vanillaWood(gen, Externsion.ACACIA_BLOCK_SET);
			vanillaWood(gen, Externsion.DARK_OAK_BLOCK_SET);
			vanillaWood(gen, Externsion.MANGROVE_BLOCK_SET);
			vanillaWood(gen, Externsion.CHERRY_BLOCK_SET);
			wall(gen, Externsion.STONE_BLOCK_SET.wall, Externsion.STONE_BLOCK_SET.solid);
			firingBlockSets(gen, Externsion.COBBLESTONE_BLOCK_SET, Externsion.STONE_BLOCK_SET, "stone");
			firingBlockSets(gen, Externsion.STONE_BLOCK_SET, Externsion.SMOOTH_STONE_BLOCK_SET, "smooth_stone");
			firingBlockSets(gen, Externsion.QUARTZ_BLOCK_SET, Externsion.SMOOTH_QUARTZ_BLOCK_SET, "smooth_quartz");
		}
	}

	public static class BlockLootTables extends FabricBlockLootTableProvider {
		protected BlockLootTables(FabricDataOutput output) {
			super(output);
		}

		public void vanillaWood(BlockSet set) {
			addDrop(set.chiseled);
		}

		@Override
		public void generate() {
			vanillaWood(Externsion.OAK_BLOCK_SET);
			vanillaWood(Externsion.SPRUCE_BLOCK_SET);
			vanillaWood(Externsion.BIRCH_BLOCK_SET);
			vanillaWood(Externsion.JUNGLE_BLOCK_SET);
			vanillaWood(Externsion.ACACIA_BLOCK_SET);
			vanillaWood(Externsion.DARK_OAK_BLOCK_SET);
			vanillaWood(Externsion.MANGROVE_BLOCK_SET);
			vanillaWood(Externsion.CHERRY_BLOCK_SET);
			addDrop(Externsion.STONE_BLOCK_SET.wall);
			addDrop(Externsion.KILN_BLOCK);
		}
	}

	public static class BlockTags extends FabricTagProvider.BlockTagProvider {
		public BlockTags(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
			super(output, registriesFuture);
		}

		@Override
		protected void configure(RegistryWrapper.WrapperLookup lookup) {
			getOrCreateTagBuilder(net.minecraft.registry.tag.BlockTags.PICKAXE_MINEABLE)
					.add(Externsion.STONE_BLOCK_SET.wall)
					.add(Externsion.KILN_BLOCK);
			getOrCreateTagBuilder(net.minecraft.registry.tag.BlockTags.WALLS)
					.add(Externsion.STONE_BLOCK_SET.wall)
					.add(Externsion.SMOOTH_STONE_BLOCK_SET.wall)
					.add(Externsion.QUARTZ_BLOCK_SET.wall)
					.add(Externsion.QUARTZ_BRICKS_BLOCK_SET.wall)
					.add(Externsion.SMOOTH_QUARTZ_BLOCK_SET.wall);
			getOrCreateTagBuilder(net.minecraft.registry.tag.BlockTags.STAIRS)
					.add(Externsion.SMOOTH_STONE_BLOCK_SET.stairs)
					.add(Externsion.QUARTZ_BLOCK_SET.stairs)
					.add(Externsion.QUARTZ_BRICKS_BLOCK_SET.stairs);
			getOrCreateTagBuilder(net.minecraft.registry.tag.BlockTags.SLABS)
					.add(Externsion.QUARTZ_BRICKS_BLOCK_SET.slab);
		}
	}
}

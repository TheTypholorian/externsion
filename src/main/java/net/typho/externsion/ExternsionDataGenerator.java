package net.typho.externsion;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.ModelProvider;
import net.minecraft.data.server.recipe.RecipeJsonProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.util.Identifier;

import java.util.function.Consumer;

public class ExternsionDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator gen) {
		FabricDataGenerator.Pack pack = gen.createPack();

		pack.addProvider(Models::new);
		pack.addProvider(Recipes::new);
	}

	public static class Models extends FabricModelProvider {
		public Models(FabricDataOutput output) {
			super(output);
		}

		@Override
		public void generateBlockStateModels(BlockStateModelGenerator gen) {
			gen.registerSimpleCubeAll(Externsion.OAK_BLOCK_SET.chiseled);
		}

		@Override
		public void generateItemModels(ItemModelGenerator gen) {
		}
	}

	public static class Recipes extends FabricRecipeProvider {
		public Recipes(FabricDataOutput output) {
			super(output);
		}

		@Override
		public void generate(Consumer<RecipeJsonProvider> gen) {
			ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, Externsion.OAK_BLOCK_SET.chiseled, 4)
					.pattern("AA")
					.pattern("AA")
					.input('A', Externsion.OAK_BLOCK_SET.solid)
					.criterion("has_oak_planks", conditionsFromItem(Externsion.OAK_BLOCK_SET.solid))
					.offerTo(gen, new Identifier(Externsion.MOD_ID, "chiseled_oak_planks"));
		}
	}
}

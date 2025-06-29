package net.typho.externsion;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.block.MapColor;
import net.minecraft.block.enums.Instrument;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Externsion implements ModInitializer {
	public static final String MOD_ID = "externsion";

	public static Block block(String name, Block block) {
		return block(new Identifier(MOD_ID, name), block);
	}

	public static Block block(Identifier id, Block block) {
		Registry.register(Registries.ITEM, id, new BlockItem(block, new FabricItemSettings()));
		return Registry.register(Registries.BLOCK, id, block);
	}

	public static final BlockSet OAK_BLOCK_SET = new BlockSet()
			.setSolid(Blocks.OAK_PLANKS)
			.setChiseled(block("chiseled_oak_planks", new Block(AbstractBlock.Settings.create().mapColor(MapColor.OAK_TAN).instrument(Instrument.BASS).strength(2.0F, 3.0F).sounds(BlockSoundGroup.WOOD).burnable())))
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

	public static final BlockSet SPRUCE_BLOCK_SET = new BlockSet()
			.setSolid(Blocks.SPRUCE_PLANKS)
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

	public static final BlockSet BIRCH_BLOCK_SET = new BlockSet()
			.setSolid(Blocks.BIRCH_PLANKS)
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

	public static final BlockSet JUNGLE_BLOCK_SET = new BlockSet()
			.setSolid(Blocks.JUNGLE_PLANKS)
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

	public static final BlockSet ACACIA_BLOCK_SET = new BlockSet()
			.setSolid(Blocks.ACACIA_PLANKS)
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

	public static final BlockSet DARK_OAK_BLOCK_SET = new BlockSet()
			.setSolid(Blocks.DARK_OAK_PLANKS)
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

	public static final BlockSet MANGROVE_BLOCK_SET = new BlockSet()
			.setSolid(Blocks.MANGROVE_PLANKS)
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

	public static final BlockSet CHERRY_BLOCK_SET = new BlockSet()
			.setSolid(Blocks.CHERRY_PLANKS)
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

	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS)
				.register(entries -> {
					entries.addAfter(
							OAK_BLOCK_SET.solid.asItem(),
							OAK_BLOCK_SET.chiseled
					);
				});
	}
}
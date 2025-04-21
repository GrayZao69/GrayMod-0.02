package com.gray.block;

import com.gray.GrayMod.GrayMod;
import com.gray.item.GrayItem;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class GrayBlock {
    //Gera uma lista para alocar blocos associada ao id do mod
    public static final DeferredRegister.Blocks BLOCK = DeferredRegister.createBlocks(GrayMod.MOD_ID);

    //gera uma lista de items chamados refiterBockItem que recebe propiedades de qualquer tipo de bloco
    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block) {
        GrayItem.ITEMS.register(name, () -> new BlockItem(block.get(),new Item.Properties()));
    }

    //gera uma lista de bocos associados ao registerBlockItem e retorna um bloco que sera usado na lista de bloco associada ao id do mod
    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block) {
        DeferredBlock<T> toRetorn = BLOCK.register(name, block);
        registerBlockItem(name, toRetorn);
        return toRetorn;
    }

    public static final DeferredBlock<Block> ENDERITE_ORE = registerBlock("enderite_ore",
            () -> new DropExperienceBlock(UniformInt.of(2, 4),
                    BlockBehaviour.Properties.of().strength(3f).requiresCorrectToolForDrops()
                            .sound(SoundType.STONE)));
    public static final DeferredBlock<Block> ENDERITE_BLOCK = registerBlock("enderite_block",
            () -> new Block (BlockBehaviour.Properties.of().strength(4f).requiresCorrectToolForDrops()
                            .sound(SoundType.NETHERITE_BLOCK)));
    public static final DeferredBlock<Block> RAW_ENDERITE_BLOCK = registerBlock("raw_enderite_block",
            () -> new Block (BlockBehaviour.Properties.of().strength(3f).requiresCorrectToolForDrops()
                            .sound(SoundType.NETHERITE_BLOCK)));

    //garante carregamento em eventBus
    public static void register(IEventBus eventBus) {
        BLOCK.register(eventBus);
    }
}

package com.gray.item;

import com.gray.GrayMod.GrayMod;
import com.gray.block.GrayBlock;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class GrayTab {
    public static final DeferredRegister<CreativeModeTab> GRAY_TAB = DeferredRegister.create
            (Registries.CREATIVE_MODE_TAB, GrayMod.MOD_ID);

    public static final Supplier<CreativeModeTab> ENDERITE_ITENS_TAB = GRAY_TAB.register
            ("enderite_itens_tab", () -> CreativeModeTab.builder().icon(() -> new ItemStack(GrayItem.ENDERITE.get()))
                    .title(Component.translatable("crativetab.grayzzmod.enderite_item"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(GrayItem.ENDERITE);
                        output.accept(GrayItem.RAW_ENDERITE);})
                    .build());
    public static final Supplier<CreativeModeTab> ENDERITE_BLOCK_TAB = GRAY_TAB.register
            ("enderite_block_tab", () -> CreativeModeTab.builder().icon(() -> new ItemStack(GrayBlock.ENDERITE_ORE.get()))
                    .title(Component.translatable("crativetab.grayzzmod.enderite_item"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(GrayBlock.ENDERITE_ORE);
                        output.accept(GrayBlock.ENDERITE_BLOCK);
                        output.accept(GrayBlock.RAW_ENDERITE_BLOCK);})
                    .build());

    public static void resgister(IEventBus eventBus){
        GRAY_TAB.register(eventBus);
    }
}

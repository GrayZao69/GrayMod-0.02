package com.gray.item;

import com.gray.GrayMod.GrayMod;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class GrayItem {
    //concede uma lista para alocar os registros dos itens associado ao id do mod
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(GrayMod.MOD_ID);

    //registra chaves predefinidas para gerar os itens em ITEMS quando o jogo carregar
    public static final DeferredItem<Item> ENDERITE = ITEMS.register("enderite", ()-> new Item(new Item.Properties()));
    public static final DeferredItem<Item> RAW_ENDERITE = ITEMS.register("raw_enderite",() -> new Item(new Item.Properties()));

    //garante carregamento em eventBus
    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}

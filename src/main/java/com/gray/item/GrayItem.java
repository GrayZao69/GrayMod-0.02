package com.gray.item;

import com.gray.GrayMod.GrayMod;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class GrayItem {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(GrayMod.MOD_ID);

    public static final DeferredItem<Item> ENDERITA = ITEMS.register("enderita", ()-> new Item(new Item.Properties()));
    public static final DeferredItem<Item> RAW_ENDERITA = ITEMS.register("raw_enderita",() -> new Item(new Item.Properties()));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}

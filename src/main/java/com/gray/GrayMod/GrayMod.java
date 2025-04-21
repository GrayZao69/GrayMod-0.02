package com.gray.GrayMod;

import com.gray.block.GrayBlock;
import com.gray.item.GrayItem;
import com.gray.item.GrayTab;
import net.minecraft.world.item.CreativeModeTabs;
import org.slf4j.Logger;

import com.mojang.logging.LogUtils;

import net.minecraft.client.Minecraft;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLClientSetupEvent;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;
import net.neoforged.neoforge.common.NeoForge;
import net.neoforged.neoforge.event.BuildCreativeModeTabContentsEvent;
import net.neoforged.neoforge.event.server.ServerStartingEvent;

// O valor aqui deve corresponder a uma entrada no arquivo META-INF/neoforge.mods.toml
@Mod(GrayMod.MOD_ID)
public class GrayMod
{
    // Defina o mod id em um local comum para que tudo seja referenciado
    public static final String MOD_ID = "grayzzmod";
    // Fazer referência direta a um registrador slf4j
    private static final Logger LOGGER = LogUtils.getLogger();

    // O construtor da classe mod é o primeiro código que é executado quando seu mod é carregado.
    // O FML reconhecerá alguns tipos de parâmetros como IEventBus ou ModContainer e os passará automaticamente.
    public GrayMod(IEventBus modEventBus, ModContainer modContainer)
    {
        // Registre o metodo commonSetup para modloading
        modEventBus.addListener(this::commonSetup);

        GrayItem.register(modEventBus);
        GrayBlock.register(modEventBus);
        GrayTab.resgister(modEventBus);


        // Registrar- se no servidor e em outros eventos do jogo nos quais estamos interessados.
        // Observe que isso é necessário se e somente se quisermos que *essa* classe (ExampleMod) responda diretamente aos eventos.
        // Não adicione essa linha se não houver funções anotadas em @SubscribeEvent nessa classe, como onServerStarting() abaixo.
        NeoForge.EVENT_BUS.register(this);

        // Registre o item em uma guia criativa
        modEventBus.addListener(this::addCreative);

        // Registre o ModConfigSpec do nosso mod para que o FML possa criar e carregar o arquivo de configuração para nós
        modContainer.registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    private void commonSetup(final FMLCommonSetupEvent event)
    {
    }

    // Add items e blocos no inventario do criativo
    private void addCreative(BuildCreativeModeTabContentsEvent event)
    {
        //Items
        if(event.getTabKey() == CreativeModeTabs.INGREDIENTS) {
            event.accept(GrayItem.ENDERITE);
        }
        if(event.getTabKey() == CreativeModeTabs.INGREDIENTS) {
            event.accept(GrayItem.RAW_ENDERITE);
        }
        //Blocos
        if(event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS) {
            event.accept(GrayBlock.ENDERITE_BLOCK);
        }
        if(event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS) {
            event.accept(GrayBlock.RAW_ENDERITE_BLOCK);
        }
        if(event.getTabKey() == CreativeModeTabs.BUILDING_BLOCKS) {
            event.accept(GrayBlock.ENDERITE_ORE);
        }
    }

    // You can use SubscribeEvent and let the Event Bus discover methods to call
    @SubscribeEvent
    public void onServerStarting(ServerStartingEvent event)
    {
    }

    // You can use EventBusSubscriber to automatically register all static methods in the class annotated with @SubscribeEvent
    @EventBusSubscriber(modid = MOD_ID, bus = EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
    public static class ClientModEvents
    {
        @SubscribeEvent
        public static void onClientSetup(FMLClientSetupEvent event)
        {
            // Some client setup code
            LOGGER.info("HELLO FROM CLIENT SETUP");
            LOGGER.info("MINECRAFT NAME >> {}", Minecraft.getInstance().getUser().getName());
        }
    }
}

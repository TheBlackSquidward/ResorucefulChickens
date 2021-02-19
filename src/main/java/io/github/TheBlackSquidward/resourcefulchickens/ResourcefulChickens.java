package io.github.TheBlackSquidward.resourcefulchickens;

import io.github.TheBlackSquidward.resourcefulchickens.api.ChickenRegistryObject;
import io.github.TheBlackSquidward.resourcefulchickens.api.CreativeTab;
import io.github.TheBlackSquidward.resourcefulchickens.api.CustomChickenEntity;
import io.github.TheBlackSquidward.resourcefulchickens.common.items.ChickenCatcherItem;
import io.github.TheBlackSquidward.resourcefulchickens.init.BlockInit;
import io.github.TheBlackSquidward.resourcefulchickens.init.EntityInit;
import io.github.TheBlackSquidward.resourcefulchickens.init.ItemInit;
import io.github.TheBlackSquidward.resourcefulchickens.api.ChickenRegistry;
import net.minecraft.entity.ai.attributes.GlobalEntityTypeAttributes;
import net.minecraft.item.ItemGroup;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DeferredWorkQueue;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(ResourcefulChickens.MODID)
public class ResourcefulChickens {

    public static final String MODID = "resourcefulchickens";

    public static final Logger LOGGER = LogManager.getLogger();

    public static final ItemGroup creativeTab = new CreativeTab();

    public ResourcefulChickens() {
        IEventBus iEventBus = FMLJavaModLoadingContext.get().getModEventBus();
        iEventBus.addListener(this::setup);

        ItemInit.ITEMS.register(iEventBus);
        EntityInit.ENTITY_TYPES.register(iEventBus);
        BlockInit.BLOCKS.register(iEventBus);

        registerChickens();
        ChickenRegistry.initChickens();
        ChickenRegistry.registerChickens();

        MinecraftForge.EVENT_BUS.register(this);
        MinecraftForge.EVENT_BUS.register(new ChickenCatcherEvent());
        MinecraftForge.EVENT_BUS.register(new ChickenItemEvent());
    }

    private void registerChickens() {

    }

    private void setup(final FMLCommonSetupEvent e) {
        setupChickens();
    }

    private void setupChickens() {
        DeferredWorkQueue.runLater(() -> {
            for(ChickenRegistryObject chicken : ChickenRegistry.getChickenRegistry().values()) {
                GlobalEntityTypeAttributes.put(chicken.getChickenEntityRegisryObject().get(), CustomChickenEntity.setCustomAttributes().create());
            }
        });
    }

}

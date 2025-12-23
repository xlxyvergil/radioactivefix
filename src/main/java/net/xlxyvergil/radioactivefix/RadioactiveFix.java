package net.xlxyvergil.radioactivefix;

import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod("radioactivefix")
public class RadioactiveFix {

    public static final String MODID = "radioactivefix";
    private static final Logger LOGGER = LogManager.getLogger();

    public RadioactiveFix() {
        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::setup);
        LOGGER.info("RadioactiveFix mod loaded, designed to fix issues in the Radioactive mod");
    }

    private void setup(final FMLCommonSetupEvent event) {
        event.enqueueWork(() -> {
            // 检查radioactive模组是否已加载
            if (ModList.get().isLoaded("radioactive")) {
                LOGGER.info("Successfully detected Radioactive mod, ready to apply fixes");
            } else {
                LOGGER.warn("Radioactive mod not found, this mod is designed as a fix for the Radioactive mod");
            }
        });
    }
}
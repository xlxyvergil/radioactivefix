package net.xlxyvergil.radioactivefix.mixin;

import net.mcreator.radioactive.network.RadioactiveModVariables;
import net.minecraft.world.level.LevelAccessor;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Pseudo
@Mixin(targets = "net.mcreator.radioactive.network.RadioactiveModVariables$MapVariables")
public class MapVariablesMixin {

    /**
     * 修复 "Cannot use IServerWorld#getWorld in a client environment" 错误
     * 通过拦截MapVariables.get方法的调用
     */
    @Inject(
        method = "get",
        at = @At("HEAD"),
        cancellable = true
    )
    private static void get(LevelAccessor world, CallbackInfoReturnable<RadioactiveModVariables.MapVariables> cir) {
        // 首先检查是否为SchematicLevel或PonderLevel等虚拟渲染环境
        // 这种情况下，原始代码会尝试访问服务端方法导致崩溃
        String worldClassName = world.getClass().getName();
        if (worldClassName.contains("SchematicLevel") || worldClassName.contains("PonderLevel")) {
            // 在虚拟渲染环境中，返回一个新的MapVariables实例，避免访问服务端方法
            cir.setReturnValue(new RadioactiveModVariables.MapVariables());
            return;
        }
        
        // 对于其他所有情况，让原始代码正常执行
        // 只有在虚拟渲染环境（如Ponder场景）中才进行干预
    }
}
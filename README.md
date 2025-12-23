# Radioactive Fix Mod

一个用于修复 Radioactive 模组问题的 Minecraft Forge 模组。

## 问题描述

修复了以下错误：
```
java.lang.IllegalStateException: Cannot use IServerWorld#getWorld in a client environment
at net.mcreator.radioactive.network.RadioactiveModVariables$MapVariables.get(RadioactiveModVariables.java:263)
at net.mcreator.radioactive.procedures.RadiationEffectsProcedure.execute(RadiationEffectsProcedure.java:44)
```

## 修复方案

使用 Mixin 技术修复了 `RadioactiveModVariables$MapVariables.get` 方法中的客户端/服务端环境错误。

- 在客户端环境中，直接返回一个新的 MapVariables 实例，避免访问服务端特定的方法
- 在服务端环境中，安全地访问服务端世界数据
- 提供了安全回退机制，确保在各种环境下都能正常工作

## 使用方法

1. 将此模组文件与 Radioactive 模组一起放入 Minecraft 的 mods 文件夹
2. 确保 Radioactive 模组版本为 3.7.2-forge-1.20.1 或兼容版本
3. 启动游戏

## 技术细节

- Minecraft 版本: 1.20.1
- Forge 版本: 47.4.13+
- ModID: radioactivefix
- 作者: xlxyvergil
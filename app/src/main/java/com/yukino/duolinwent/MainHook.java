package com.yukino.duolinwent;

import de.robv.android.xposed.IXposedHookLoadPackage;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.callbacks.XC_LoadPackage;
import org.jetbrains.annotations.NotNull;

/**
 * Module entrypoint.
 * Responsible for distributing requests to all {@link IHook}s
 * registered in the {@link HookRegistry}.
 */
public class MainHook implements IXposedHookLoadPackage {
    @Override
    public void handleLoadPackage(XC_LoadPackage.@NotNull LoadPackageParam lpparam) {
        for (IHook hook : HookRegistry.getAllHooks()) {
            try {
                hook.hook(lpparam);
            } catch (Throwable t) {
                XposedBridge.log("Hook failed: " + hook.name() + " - " + t.getMessage());
            }
        }
    }
}
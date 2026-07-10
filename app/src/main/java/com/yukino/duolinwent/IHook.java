package com.yukino.duolinwent;

import de.robv.android.xposed.callbacks.XC_LoadPackage;

/**
 * All {@code Hook}s interface.
 * Each {@code Hook} implement this interface,
 * overwrite own logic in {@link #hook(XC_LoadPackage.LoadPackageParam)}.
 */
public interface IHook {
    /**
     * {@code Hook} name,
     * use for logs.
     */
    default String name() {
        return getClass().getSimpleName();
    }

    /**
     * Run the {@code Hook}.
     */
    void hook(XC_LoadPackage.LoadPackageParam lpparam) throws Throwable;
}
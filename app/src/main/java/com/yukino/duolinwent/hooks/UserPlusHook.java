package com.yukino.duolinwent.hooks;

import com.yukino.duolinwent.IHook;
import de.robv.android.xposed.XC_MethodHook;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.XposedHelpers;
import de.robv.android.xposed.callbacks.XC_LoadPackage;
import org.jetbrains.annotations.NotNull;

/**
 * User {@code hasPlus} attribute hook.
 */
public class UserPlusHook implements IHook {
    /**
     * Run the {@code Hook}.
     * Hook the {@code hasPlus} field in {@code User} class,
     * and set it true-forever.
     */
    @Override
    public void hook(XC_LoadPackage.@NotNull LoadPackageParam lpparam) {
        Class<?> userClass = XposedHelpers.findClass(
                "com.duolingo.data.user.User",
                lpparam.classLoader
        );

        XposedBridge.hookAllConstructors(
                userClass,
                new XC_MethodHook() {
                    @Override
                    protected void afterHookedMethod(MethodHookParam param) {
                        try {
                            XposedHelpers.setBooleanField(
                                    param.thisObject,
                                    "y",    // hasPlus field
                                    true
                            );
                        } catch (Throwable t) {
                            XposedBridge.log(t);
                        }
                    }
                }
        );
    }
}

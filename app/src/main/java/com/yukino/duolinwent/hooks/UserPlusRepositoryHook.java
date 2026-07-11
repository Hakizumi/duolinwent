package com.yukino.duolinwent.hooks;

import com.yukino.duolinwent.IHook;
import de.robv.android.xposed.XC_MethodReplacement;
import de.robv.android.xposed.XposedBridge;
import de.robv.android.xposed.callbacks.XC_LoadPackage;
import org.luckypray.dexkit.DexKitBridge;
import org.luckypray.dexkit.query.FindClass;
import org.luckypray.dexkit.query.matchers.ClassMatcher;
import org.luckypray.dexkit.result.ClassData;
import org.luckypray.dexkit.result.ClassDataList;

import java.lang.reflect.Method;

/**
 * User-subscription repository hook.
 */
public class UserPlusRepositoryHook implements IHook {
    /**
     * Run the {@code Hook}.
     * Hook the user-subscription repository method and force returns {@code true}.
     */
    @Override
    public void hook(XC_LoadPackage.LoadPackageParam lpparam) {
        try (DexKitBridge bridge = DexKitBridge.create(lpparam.appInfo.sourceDir)) {
            // 1. Find all classes implements defpaclage.oa6
            FindClass matcher = new FindClass()
                    .matcher(new ClassMatcher().addInterface("defpackage.oa6"));

            ClassDataList targetClasses = bridge.findClass(matcher);

            // 2. Hook in batch
            for (ClassData classData : targetClasses) {
                Class<?> clazz = classData.getInstance(lpparam.classLoader);

                for (Method method : clazz.getDeclaredMethods()) {
                    if (method.getName().equals("z")
                            && method.getReturnType() == boolean.class) {

                        XposedBridge.hookMethod(
                                method,
                                XC_MethodReplacement.returnConstant(true)
                        );
                    }
                }
            }
        } catch (Throwable t) {
            XposedBridge.log("DexKit scanning failed: " + t.getMessage());
        }
    }
}

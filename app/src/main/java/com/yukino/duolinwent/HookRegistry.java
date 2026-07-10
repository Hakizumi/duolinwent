package com.yukino.duolinwent;

import com.yukino.duolinwent.hooks.UserPlusHook;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Unmodifiable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Hook registry,
 * centrally manages all {@link IHook}s.
 */
public class HookRegistry {
    private static final List<IHook> HOOKS = new ArrayList<>();

    static {
        HOOKS.add(new UserPlusHook());
    }

    public static void add(IHook hook) {
        HOOKS.add(hook);
    }

    public static @Unmodifiable @NotNull List<IHook> getAllHooks() {
        return Collections.unmodifiableList(HOOKS);
    }
}
package com.yang.singleton;

import java.util.concurrent.atomic.AtomicReference;

public class CASSingleton {
    private static final AtomicReference<CASSingleton> INSTANCE = new AtomicReference<CASSingleton>();

    private CASSingleton() {
    }

    public static CASSingleton getInstance() {
        for (; ; ) {
            CASSingleton singleton = INSTANCE.get();
            if (singleton != null) {
                return singleton;
            }
            singleton = new CASSingleton();
            if (INSTANCE.compareAndSet(null, singleton)) {
                return singleton;
            }
        }
    }
}

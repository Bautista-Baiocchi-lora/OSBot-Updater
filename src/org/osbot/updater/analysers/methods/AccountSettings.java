package org.osbot.updater.analysers.methods;

import org.osbot.updater.analysers.classes.core.ClassCache;
import org.osbot.updater.analysers.methods.core.MethodAnalyserFrame;
import org.osbot.updater.misc.Hook;

import java.util.Arrays;

/**
 * Created by Ethan on 1/20/2018.
 */
public class AccountSettings extends MethodAnalyserFrame {

    public void identify() {
        this.setParent("AccountSettings", ClassCache.accountSettings);
        this.setNeededHooks(Arrays.asList("VIP", "DEV"));
        addHook(new Hook(Hook.Key.VIP.getName(), getFields(getParent(), "Z").get(0)));
        addHook(new Hook(Hook.Key.DEV.getName(), getFields(getParent(), "Z").get(1)));
    }
}

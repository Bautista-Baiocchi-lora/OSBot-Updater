package org.osbot.updater.analysers.classes;

import org.osbot.updater.analysers.classes.core.ClassAnalyserFrame;
import org.osbot.updater.analysers.classes.core.ClassCache;
import org.osbot.updater.analysers.methods.core.MethodCache;
import org.objectweb.asm.tree.ClassNode;

public class BotApplication extends ClassAnalyserFrame {

    public void identify(ClassNode c) {
        ClassCache.botApplication.setId("BotApp");
        this.setMethodAnalyser(MethodCache.botApplication);
        if (c.name.contains("BotApplication")) {
            ClassCache.botApplication.set(c);
        }
    }
}

package org.osbot.updater.analysers.classes;

import org.osbot.updater.analysers.classes.core.ClassAnalyserFrame;
import org.osbot.updater.analysers.methods.core.MethodCache;
import org.osbot.updater.searchers.FieldSearcher;
import org.objectweb.asm.tree.ClassNode;


/**
 * Created by Ethan on 1/20/2018.
 */
public class ScriptCache extends ClassAnalyserFrame {
    private int arrayListCount = 0;
    private int loggerCount = 0;
    public void identify(ClassNode c) {
        this.setId("ScriptCache");
        this.setMethodAnalyser(MethodCache.scriptCache);
        FieldSearcher fieldSearch = new FieldSearcher(c);
        arrayListCount = fieldSearch.countDesc("Ljava/util/ArrayList;");
        loggerCount = fieldSearch.countDesc("Lorg/osbot/utility/Logger;");
        if (arrayListCount == 1 && loggerCount == 1) {
            this.set(c);
        }

    }
}

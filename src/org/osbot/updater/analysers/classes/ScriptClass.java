package org.osbot.updater.analysers.classes;

import org.osbot.updater.analysers.classes.core.ClassAnalyserFrame;
import org.osbot.updater.analysers.classes.core.ClassCache;
import org.osbot.updater.searchers.FieldSearcher;
import org.objectweb.asm.tree.ClassNode;

/**
 * Created by Ethan on 1/20/2018.
 */
public class ScriptClass extends ClassAnalyserFrame {
    private int manifestCount = 0;
    private int classCount = 0;

    public void identify(ClassNode c) {
        ClassCache.scriptClass.setId("SDN script");
        FieldSearcher fieldSearch = new FieldSearcher(c);
        manifestCount = fieldSearch.countDesc("Lorg/osbot/rs07/script/ScriptManifest;");
        classCount = fieldSearch.countDesc("Ljava/lang/Class;");
        if(manifestCount == 1 && classCount == 1) {
            this.set(c);
        }

    }
}
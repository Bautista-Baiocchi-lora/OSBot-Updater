package org.osbot.updater.analysers.classes;

import org.osbot.updater.analysers.classes.core.ClassAnalyserFrame;
import org.osbot.updater.searchers.FieldSearcher;
import org.objectweb.asm.tree.ClassNode;

/**
 * Created by Ethan on 1/20/2018.
 */
public class RandomsClass extends ClassAnalyserFrame {
    private int stringCount = 0;
    private int stringArrayCount = 0;

    public void identify(ClassNode c) {
        this.setId("RandomsClass");
        FieldSearcher fieldSearch = new FieldSearcher(c);
        stringCount = fieldSearch.countDesc("Ljava/lang/String;");
        stringArrayCount = fieldSearch.countDesc("[Ljava/lang/String;");
        if (c.name.contains("osbot")) {
            if (stringCount == 3 && stringArrayCount == 1) {
               this.set(c);
            }
        }
    }
}

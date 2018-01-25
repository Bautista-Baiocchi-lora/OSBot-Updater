package org.osbot.updater.analysers.classes;

import org.osbot.updater.analysers.classes.core.ClassAnalyserFrame;
import org.osbot.updater.analysers.methods.core.MethodCache;
import org.osbot.updater.searchers.FieldSearcher;
import org.objectweb.asm.tree.ClassNode;

/**
 * Created by Ethan on 1/20/2018.
 */
public class AccountSettings extends ClassAnalyserFrame {
    private int stringCount = 0;
    private int loggerCount = 0;

    public void identify(ClassNode c) {
        this.setId("AccountSettings");
        this.setMethodAnalyser(MethodCache.accountSettings);
        if (c.superName.equals("java/lang/Object")) {
            FieldSearcher fieldSearch = new FieldSearcher(c);
            stringCount = fieldSearch.countDesc("Ljava/lang/String;");
            loggerCount = fieldSearch.countDesc("Lorg/osbot/utility/Logger;");
            if (stringCount == 3 && loggerCount == 1) {
                this.set(c);
            }
        }
    }
}

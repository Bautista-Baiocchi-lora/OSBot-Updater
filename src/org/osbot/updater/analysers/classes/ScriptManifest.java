package org.osbot.updater.analysers.classes;

import org.objectweb.asm.tree.ClassNode;


/**
 * Created by Ethan on 1/26/2018.
 */
public class ScriptManifest extends org.osbot.updater.analysers.classes.core.ClassAnalyserFrame {

    public void identify(ClassNode c) {
        this.setId("Script manifest");
        if(c.name.contains("ScriptManifest")) {
            this.set(c);
        }
    }
}

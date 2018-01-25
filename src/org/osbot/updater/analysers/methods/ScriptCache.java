package org.osbot.updater.analysers.methods;

import org.osbot.updater.analysers.classes.core.ClassCache;
import org.osbot.updater.analysers.methods.core.MethodAnalyserFrame;
import org.osbot.updater.main.Loader;
import org.osbot.updater.misc.Hook;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldNode;
import org.objectweb.asm.tree.MethodNode;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by Ethan on 1/20/2018.
 */
public class ScriptCache extends MethodAnalyserFrame {
    private Map<String, ClassNode> CLASSES = Loader.classArchive.classes;

    public void identify() {
        this.setParent("ScriptCache", ClassCache.scriptCache);
        this.setNeededHooks(Arrays.asList("StartScript", "ScriptCache"));
        List<MethodNode> methods = getMethods(getParent(), false, "Bot");
        for (MethodNode m : methods) {
             this.addHook(new Hook(Hook.Key.START_SCRIPT.getName(), m));
        }

        for (ClassNode classNode : CLASSES.values()) {
            if(classNode.name.contains("LPT8")) {
                List<FieldNode> fields = classNode.fields;
                for(FieldNode f : fields) {
                    if(f.desc.equals("Ljava/util/TreeMap;")) {
                        if(f.signature.contains(ClassCache.scriptClass.getName())) {
                            this.addHook(new Hook(Hook.Key.SCRIPT_MAP.getName(), f, classNode));
                        }
                    }
                }
            }
        }
    }
}
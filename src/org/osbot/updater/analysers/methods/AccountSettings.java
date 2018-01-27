package org.osbot.updater.analysers.methods;

import org.objectweb.asm.Opcodes;
import org.objectweb.asm.tree.AbstractInsnNode;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.MethodNode;
import org.osbot.updater.analysers.classes.core.ClassCache;
import org.osbot.updater.analysers.methods.core.MethodAnalyserFrame;
import org.osbot.updater.deob.Searcher;
import org.osbot.updater.main.Loader;
import org.osbot.updater.misc.Hook;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * Created by Ethan on 1/20/2018.
 */
public class AccountSettings extends MethodAnalyserFrame {
    private Map<String, ClassNode> CLASSES = Loader.classArchive.classes;

    public void identify() {

        this.setParent("AccountSettings", ClassCache.accountSettings);
        this.setNeededHooks(Arrays.asList("VIP", "DEV"));
        addHook(new Hook(Hook.Key.VIP.getName(), getFields(getParent(), "Z").get(0)));
        addHook(new Hook(Hook.Key.DEV.getName(), getFields(getParent(), "Z").get(1)));

        int l;
        List<MethodNode> methodList = getParent().methods;
        for (MethodNode method : methodList) {
            if (method != null && method.desc.contains("[B")) {
                Searcher search = new Searcher(method);
                AbstractInsnNode[] insnNodes = method.instructions.toArray();
                for (int i = 0; i < 100; ++i) {
                    l = search.find(new int[]{Opcodes.DUP, Opcodes.POP2, Opcodes.PUTSTATIC}, i);//ifnull or ACONST_NULL, SEARCHER.IF
                    if (l != -1) {
                        addHook(new Hook("PossibleVIP", insnNodes, l + 2));
                    }
                }
            }
        }
        for (MethodNode method : methodList) {
            if (method != null) {
                if (method.name.equals("<init>")) {
                    Searcher search = new Searcher(method);
                    AbstractInsnNode[] insnNodes = method.instructions.toArray();
                    int count = 0;
                    for (int i = 0; i < 100; ++i) {
                        l = search.find(new int[]{Opcodes.PUTFIELD}, i);
                        if (l != -1) {
                            count++;
                            if (count == 6) {
                                addHook(new Hook(Hook.Key.NAME.getName(), insnNodes, l));
                                break;
                            }
                        }
                    }
                }
            }
        }
    }
}

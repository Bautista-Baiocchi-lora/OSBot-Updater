package org.osbot.updater.analysers.methods;

import org.objectweb.asm.tree.MethodNode;
import org.osbot.updater.analysers.classes.core.ClassCache;
import org.osbot.updater.analysers.methods.core.MethodAnalyserFrame;
import org.osbot.updater.misc.Hook;

import java.util.Arrays;
import java.util.List;


/**
 * Created by Ethan on 1/20/2018.
 */
public class PreferenceClass extends MethodAnalyserFrame {

	public void identify() {
		this.setParent("PreferenceClass", ClassCache.preferenceClass);
		this.setNeededHooks(Arrays.asList("PreferenceInstance"));
		List<MethodNode> methods = getMethods(getParent(), false, ClassCache.randomsClass.getName());
		for (MethodNode m : methods) {
			if (m.desc.contains("java/lang/String")) {
				addHook(new Hook(Hook.Key.PREFERENCE_CLASS_INSTANCE.getName(), m));
			}
		}

	}
}

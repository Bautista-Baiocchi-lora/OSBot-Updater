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
public class BotApplication extends MethodAnalyserFrame {

	public void identify() {
		this.setParent("BotApp", ClassCache.botApplication);
		this.setNeededHooks(Arrays.asList("BotAppInstance", "AccountInstance", "BotInstance"));
		addHook(new Hook(Hook.Key.BOT_APP_INSTANCE.getName(), getFields(getParent(), "L" + ClassCache.botApplication.getName() + ";").get(0)));
		List<MethodNode> methodList = getParent().methods;
		for (MethodNode m : methodList) {
			if (m.desc.contains(ClassCache.accountSettings.getNodes().get(0).name)) {
				addHook(new Hook(Hook.Key.ACCOUNT_INSTACE.getName(), m));
			}
			if (m.desc.contains("rs07/Bot")) {
				addHook(new Hook(Hook.Key.BOT_INSTANCE.getName(), m));
			}
		}
	}
}

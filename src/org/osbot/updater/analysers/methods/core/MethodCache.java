package org.osbot.updater.analysers.methods.core;

import org.osbot.updater.analysers.methods.AccountSettings;
import org.osbot.updater.analysers.methods.BotApplication;
import org.osbot.updater.analysers.methods.PreferenceClass;
import org.osbot.updater.analysers.methods.ScriptCache;

public class MethodCache {

    public static BotApplication botApplication = new BotApplication();
    public static AccountSettings accountSettings = new AccountSettings();
    public static ScriptCache scriptCache = new ScriptCache();
    public static PreferenceClass preferenceClass = new PreferenceClass();
}

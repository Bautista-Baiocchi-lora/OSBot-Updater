package org.osbot.updater.analysers.classes;

import org.osbot.updater.analysers.classes.core.ClassAnalyserFrame;
import org.osbot.updater.analysers.classes.core.ClassCache;
import org.osbot.updater.analysers.methods.core.MethodCache;
import org.objectweb.asm.tree.ClassNode;

/**
 * Created by Ethan on 1/20/2018.
 */
public class PreferenceClass extends ClassAnalyserFrame {


    public void identify(ClassNode c) {
        this.setId("PreferenceClass");
        this.setMethodAnalyser(MethodCache.preferenceClass);
          if(c.name.contains("osbot")) {
              if (c.fields.size() == 1) {
                  int count = getMethods(c,false, ClassCache.randomsClass.getName()).size();
                    if(count > 1) {
                        this.set(c);
                    }
              }
          }
    }
}

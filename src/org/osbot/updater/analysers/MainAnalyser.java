package org.osbot.updater.analysers;

import org.osbot.updater.analysers.classes.core.ClassAnalyserFrame;
import org.osbot.updater.analysers.classes.core.ClassCache;
import org.osbot.updater.main.Loader;
import org.osbot.updater.misc.Hook;
import org.objectweb.asm.tree.ClassNode;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class MainAnalyser {
    private Map<String, ClassNode> CLASSES = Loader.classArchive.classes;
    private List<ClassAnalyserFrame> classAnalysers = new ArrayList();

    private void loadClasses() {
        this.classAnalysers.add(ClassCache.botApplication);
        this.classAnalysers.add(ClassCache.accountSettings);
        this.classAnalysers.add(ClassCache.randomsClass);
        this.classAnalysers.add(ClassCache.preferenceClass);
        this.classAnalysers.add(ClassCache.scriptClass);
        this.classAnalysers.add(ClassCache.scriptCache);
    }

    private void runClassAnalysers() {
        System.out.println("%% Identifying Classes..");
        for (ClassAnalyserFrame a : this.classAnalysers) {
            for (ClassNode c : CLASSES.values()) {
                a.identify(c);
            }
            if (a.getName() == null) {
                Scanner inputClass = new Scanner(System.in);
                System.out.println(a.getId() + " broke :/");
                System.out.print(a.getId() + " = ");
                if (!a.getClass(inputClass.next())) {
                    System.out.println("Class not found, terminating..");
                    System.exit(0);
                }
            }
        }
        System.out.println("    (Identified " + this.classAnalysers.size() + " out of " + this.classAnalysers.size()
                + " Classes)");

    }

    private void runMethodAnalysers() {

        System.out.println("%% Identifying Fields..");
        for (ClassAnalyserFrame a : this.classAnalysers) {
            if (a.hasMethodAnalyser) {
                a.getMethodAnalyser().identify();
            }
        }
    }


    private void logPrint() {
        for (ClassAnalyserFrame a : this.classAnalysers) {
            System.out.print(" # " + a.getId() + ": " + a.getNodes().get(0).name.replace('/','.'));
            System.out.println();
            if (a.hasMethodAnalyser) {
                for (Hook f : a.getMethodAnalyser().getHooks()) {
                    if(f.getOwner() == null) {
                        System.out.println("     ~> " + f.getId() + " : " + a.getNodes().get(0).name.replace('/','.') + "." + f.getName() + " || "+f.getDesc());
                    } else {
                        System.out.println("     ~> " + f.getId() + " : " + f.getOwner().replace('/','.') + "." + f.getName()+ " || "+f.getDesc());

                    }
                }
            }
        }
    }


    public void run() {
        loadClasses();
        runClassAnalysers();
        runMethodAnalysers();
        logPrint();
    }

}

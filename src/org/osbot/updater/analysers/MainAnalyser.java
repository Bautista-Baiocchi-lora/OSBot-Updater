package org.osbot.updater.analysers;

import org.osbot.updater.analysers.classes.core.ClassAnalyserFrame;
import org.osbot.updater.analysers.classes.core.ClassCache;
import org.osbot.updater.main.Loader;
import org.osbot.updater.misc.Hook;
import org.objectweb.asm.tree.ClassNode;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainAnalyser {
    private Map<String, ClassNode> CLASSES = Loader.classArchive.classes;
    private List<ClassAnalyserFrame> classAnalysers = new ArrayList();
    private Map<String, HookFrame> hookMap = new HashMap<>();

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
                     //   System.out.println("     ~> " + f.getId() + " : " + a.getNodes().get(0).name.replace('/','.') + "." + f.getName() + " || "+f.getDesc());
                        hookMap.put(f.getId(), new HookFrame(f.getId(), a.getNodes().get(0).name.replace("/","."), f.getName(), parseReturn(f.getDesc()), parseParamCount(f.getDesc())));
                    } else {
                     //   System.out.println("     ~> " + f.getId() + " : " + f.getOwner().replace('/','.') + "." + f.getName()+ " || "+f.getDesc());
                        hookMap.put(f.getId(), new HookFrame(f.getId(), f.getOwner().replace('/','.'), f.getName(), parseReturn(f.getDesc()), parseParamCount(f.getDesc())));

                    }
                }
            }
        }
    }
    private void printMap() {
        for(Map.Entry<String, HookFrame> entry : hookMap.entrySet()) {
            System.out.println("Key: "+entry.getKey()+ " || Class: "+ entry.getValue().getClazz() + " || Field: "
            + entry.getValue().getField() + " || Return: "+entry.getValue().getReturnType() + " || ParamCount: "+entry.getValue().getParamCount() );
        }
    }
    private int parseParamCount(String desc) {
        Matcher m = Pattern.compile("\\(([^)]+)\\)").matcher(desc);
        while(m.find()) {
            if(m.group(1).contains(";")) {
                String[] split = m.group(1).split(";");
                return split.length;
            }
        }
        return 0;
    }
    private String parseReturn(String desc) {
        if(desc.contains(")")) {
            desc = desc.substring(desc.indexOf(")") + 1);
            desc = desc.replaceAll(";", "");
            desc = desc.replace('/', '.').trim();
            if (desc.substring(0, 1).equals("L")) {
                desc = desc.replaceFirst("L", "");
                return desc;
            }
        }
            return "null";
    }
    public void run() {
        loadClasses();
        runClassAnalysers();
        runMethodAnalysers();
        logPrint();
        printMap();
    }

}

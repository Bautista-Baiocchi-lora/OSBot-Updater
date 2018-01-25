package org.osbot.updater.analysers.classes.core;


import org.osbot.updater.analysers.methods.core.MethodAnalyserFrame;
import org.osbot.updater.main.Loader;
import org.osbot.updater.misc.NodeWrapper;
import org.objectweb.asm.tree.ClassNode;

import java.util.ArrayList;
import java.util.Map;


public abstract class ClassAnalyserFrame extends NodeWrapper {
    public boolean hasMethodAnalyser = false;
    private Map<String, ClassNode> classes = Loader.classArchive.classes;
    private int count;
    private String id, name;
    private ArrayList<ClassNode> nodes = new ArrayList<>();
    private MethodAnalyserFrame methodAnalyser;

    public abstract void identify(ClassNode c);

    public String getId() {

        return this.id;
    }

    public void setId(String s) {

        this.id = s;
    }

    public ArrayList<ClassNode> getNodes() {

        return this.nodes;
    }

    public ClassNode getNode() {
        return getNodes().get(0);
    }

    public void setNode(ClassNode c) {
        nodes.add(c);
        if (nodes.size() > 1) {
            if (!c.name.equals(nodes.get(nodes.size() - 2).name))
                ++count;
        } else {
            ++count;
        }
    }

    public String getName() {
        return this.name;
    }

    public void setName(ClassNode c) {
        this.name = c.name;
    }

    public void set(String id, ClassNode c) {
        this.setId(id);
        this.setName(c);
        this.setNode(c);
    }

    public void set(ClassNode c) {
        this.setName(c);
        this.setNode(c);
    }

    public boolean getClass(String name) {
        for (ClassNode c : classes.values()) {
            if (name.equals(c.name)) {
                this.set(c);
                return true;
            }
        }
        return false;
    }

    public MethodAnalyserFrame getMethodAnalyser() {
        return this.methodAnalyser;
    }

    public void setMethodAnalyser(MethodAnalyserFrame m) {
        this.methodAnalyser = m;
        this.hasMethodAnalyser = true;
    }


}

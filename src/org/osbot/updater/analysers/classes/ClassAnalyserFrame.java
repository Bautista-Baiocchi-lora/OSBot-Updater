package org.osbot.updater.analysers.classes;



import java.util.ArrayList;
import java.util.Map;


import org.osbot.updater.analysers.methods.MethodAnalyserFrame;
import org.osbot.updater.main.Loader;
import org.osbot.updater.misc.NodeWrapper;
import org.objectweb.asm.tree.ClassNode;



public abstract class ClassAnalyserFrame extends NodeWrapper {
	private static Map<String, ClassNode> CLASSES = Loader.classArchive.classes;
    int count;
    private String id, name;
    private ArrayList<ClassNode> nodes = new ArrayList<>();
    public boolean hasMethodAnalyser = false;
    private MethodAnalyserFrame methodAnalyser;

    public abstract void identify(ClassNode c);

    public int duplicates() {

        return count - 1;
    }

    public void setId(String s) {

        this.id = s;
    }

    public String getId() {

        return this.id;
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

    public ArrayList<ClassNode> getNodes() {

        return this.nodes;
    }

    public ClassNode getNode() {
        return getNodes().get(0);
    }

    public void setName(ClassNode c) {
        this.name = c.name;
    }

    public String getName() {
        return this.name;
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
        for (ClassNode c : CLASSES.values()) {
            if (name.equals(c.name)) {
                this.set(c);
                return true;
            }
        }
        return false;
    }

    public void setMethodAnalyser(MethodAnalyserFrame m) {
        this.methodAnalyser = m;
        this.hasMethodAnalyser = true;
    }

    public MethodAnalyserFrame getMethodAnalyser() {
        return this.methodAnalyser;
    }

}

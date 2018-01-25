package org.osbot.updater.analysers.methods;


import java.util.ArrayList;
import java.util.List;


import org.osbot.updater.analysers.classes.ClassAnalyserFrame;
import org.osbot.updater.misc.Hook;
import org.osbot.updater.misc.NodeWrapper;
import org.objectweb.asm.tree.ClassNode;

/**
 * Created by Kyle on 7/26/2015.
 */
public abstract class MethodAnalyserFrame extends NodeWrapper {
    ClassNode parentClass;
    private ArrayList<Hook> fields = new ArrayList<>();
    private List<String> neededFields;

    public abstract void identify();

    public void setParent(String s, ClassAnalyserFrame _parentClass) {

        this.parentClass = _parentClass.getNodes().get(0);
    }

    public ClassNode getParent() {
        return parentClass;
    }

    public void setNeededHooks(List<String> s) {
        neededFields = s;
    }

    public ArrayList<Hook> getHooks() {
        return this.fields;
    }

    public Hook getHook(String id) {
        ArrayList<Hook> fs = this.getHooks();
        for (Hook f : fs) {
            if (f.getId().equals(id))
                return f;
        }
        return null;
    }

    public boolean containsHook(String s) {
        ArrayList<Hook> ours = this.fields;
        for (Hook ourField : ours)
            if (ourField.getId().equals(s)) {
                return true;
            }
        return false;
    }

    private boolean duplicateHook(Hook h) {
        ArrayList<Hook> ours = this.fields;
        for (Hook ourField : ours)
            if (ourField.getId().equals(h.getId()) && !ourField.getName().equals(h.getName())) {
                ourField.setDuplicate(true);
                return true;
            }
        return false;
    }

    public List<String> getNeededHooks() {
        return neededFields;
    }

    public void addHook(Hook f) {
        if (!this.containsHook(f.getId())) {
            this.fields.add(f);
            duplicateHook(f);
        }

    }

    public ArrayList<Hook> getBrokenHooks() {
        ArrayList<Hook> temp = new ArrayList<>();
        for (String f : this.getNeededHooks()) {
            if (!this.containsHook(f)) {
                temp.add(this.getHook(f));
            }
        }
        for (Hook f : this.getHooks()) {
            if (f.getName().equals("NULL"))
                temp.add(f);
        }
        return temp;
    }

    public ArrayList<Hook> getDuplicateHooks() {
        ArrayList<Hook> temp = new ArrayList<>();
        for (String f : this.getNeededHooks()) {
            if (!this.containsHook(f)) {
                temp.add(this.getHook(f));
            }
        }
        for (Hook f : this.getHooks()) {
            if (f.getName().equals("NULL"))
                temp.add(f);
        }
        return temp;
    }

}

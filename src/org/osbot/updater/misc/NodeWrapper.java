package org.osbot.updater.misc;

import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldNode;
import org.objectweb.asm.tree.MethodNode;

import java.util.ArrayList;
import java.util.List;

public class NodeWrapper {

    public List<FieldNode> getFields(ClassNode cn, String desc) {
        List<FieldNode> fields = cn.fields;
        List<FieldNode> temp = new ArrayList();
        for (FieldNode fN : fields) {
            if (fN.desc.equals(desc)) {
                temp.add(fN);
            }
        }
        return temp;
    }

    public List<FieldNode> getFields(ClassNode cn, Integer nonAccess) {
        List<FieldNode> fields = cn.fields;
        List<FieldNode> temp = new ArrayList();
        for (FieldNode fN : fields)
            if ((fN.access & nonAccess) == 0)
                temp.add(fN);
        return temp;
    }

    public List<FieldNode> getFields(ClassNode cn) {
        List<FieldNode> fields = cn.fields;
        List<FieldNode> temp = new ArrayList();
        for (FieldNode fN : fields)
            temp.add(fN);
        return temp;
    }

    public List<FieldNode> getFields(ClassNode cn, Integer nonAccess1, Integer nonAccess2) {
        List<FieldNode> fields = cn.fields;
        List<FieldNode> temp = new ArrayList();
        for (FieldNode fN : fields)
            if ((fN.access & nonAccess1) == 0 && (fN.access & nonAccess2) == 0)
                temp.add(fN);
        return temp;
    }

    public List<FieldNode> getFields(ClassNode cn, Integer none, Integer Access, Integer nonAccess2) {
        List<FieldNode> fields = cn.fields;
        List<FieldNode> temp = new ArrayList();
        for (FieldNode fN : fields)
            if ((fN.access & Access) != 0 && (fN.access & nonAccess2) == 0)
                temp.add(fN);
        return temp;
    }

    public void removeFields(ClassNode cn, String nonDesc) {
        List<FieldNode> fields = cn.fields;
        List<FieldNode> temp = new ArrayList();
        for (FieldNode fN : fields) {
            if (!fN.desc.equals(nonDesc)) {
                temp.remove(fN);
            }
        }
    }

    public MethodNode getMethod(ClassNode cn, boolean exact, String desc) {
        List<MethodNode> methods = new ArrayList<>();
        List<MethodNode> methodList = cn.methods;
        for (MethodNode m : methodList) {
            if (exact) {
                if (m.desc.equals(desc))
                    methods.add(m);
            } else if (m.desc.contains(desc))
                methods.add(m);
        }

        if (methods.size() > 1)
            return null;
        if (methods.size() == 0)
            return null;
        return methods.get(0);
    }

    public List<MethodNode> getMethods(ClassNode cn, boolean exact, String desc) {
        List<MethodNode> methods = new ArrayList<>();
        List<MethodNode> methodList = cn.methods;
        for (MethodNode m : methodList) {
            if (exact) {
                if (m.desc.equals(desc))
                    methods.add(m);
            } else if (m.desc.contains(desc))
                methods.add(m);
        }
        return methods;
    }

    public List<MethodNode> getMethods(ClassNode cn) {
        List<MethodNode> methods = new ArrayList<>();
        List<MethodNode> methodList = cn.methods;
        for (MethodNode m : methodList) {

            methods.add(m);
        }
        return methods;
    }

    public List<MethodNode> getMethodsName(ClassNode cn, boolean exact, String desc) {
        List<MethodNode> methods = new ArrayList<>();
        List<MethodNode> methodList = cn.methods;
        for (MethodNode m : methodList) {
            if (exact) {
                if (m.name.equals(desc))
                    methods.add(m);
            } else if (m.name.contains(desc))
                methods.add(m);
        }
        return methods;
    }
}

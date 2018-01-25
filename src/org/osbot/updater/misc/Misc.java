package org.osbot.updater.misc;

import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldNode;

import java.util.List;


public class Misc {

    public static int mode(int a[]) {
        int maxValue = 0, maxCount = 0;

        for (int i = 0; i < a.length; ++i) {
            int count = 0;
            for (int j = 0; j < a.length; ++j) {
                if (a[j] == a[i]) ++count;
            }
            if (count > maxCount) {
                maxCount = count;
                maxValue = a[i];
            }
        }
        return maxValue;
    }

    public static FieldNode getField(String id, ClassNode c) {
        List<FieldNode> fields = c.fields;
        for (FieldNode f : fields) {
            if (f.name.equals(id))
                return f;
        }
        return null;
    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();
        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }

    public static String get(int opcode) {
        return org.objectweb.asm.util.Printer.OPCODES[opcode];
    }

}

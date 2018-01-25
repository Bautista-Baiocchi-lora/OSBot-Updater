package org.osbot.updater.misc;


import org.osbot.updater.main.Loader;
import org.objectweb.asm.tree.*;

import java.util.List;
import java.util.Map;


public class Hook {
    private static Map<String, ClassNode> CLASSES = Loader.classArchive.classes;
    public boolean broken = false;
    public boolean duplicate = false;
    String id, name, desc, signature, owner;
    int access, multiplier;

    public Hook() {

    }


    public static enum Key {
        BOT_INSTANCE("Bot instance"), START_SCRIPT("Start script"), BOT_APP_INSTANCE("Bot app instance"),
        PREFERENCE_CLASS_INSTANCE("Preference class instance"), BOT_PREFERENCES("Bot preferences"),
        SDN_SCRIPT("SDN script"), SCRIPT_MAP("Script map"), SCRIPT_MANIFEST("Script manifest"), ACCOUNT_INSTACE("Account instance"),
        VIP("Vip"), DEV("Dev"), NAME("Name"), ACCOUNT_KEY("Account Key"), PASSWORD("Password");

        private final String name;

        Key(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }

    public Hook(String id, FieldNode f) {
        setId(id);
        setName(f.name);
        setDesc(f.desc);
        setSignature(f.signature);
        setAccess(f.access);

    }

    public Hook(String id, FieldNode f, ClassNode c) {
        setId(id);
        setName(f.name);
        setDesc(f.desc);
        setSignature(f.signature);
        setAccess(f.access);
        setOwner(c.name);
    }

    public Hook(String id, MethodNode f) {
        setId(id);
        setName(f.name);
        setDesc(f.desc);
        setSignature(f.signature);
        setAccess(f.access);
    }

    public Hook(String id, MethodNode f, ClassNode c) {
        setId(id);
        setName(f.name);
        setDesc(f.desc);
        setSignature(f.signature);
        setAccess(f.access);
        setOwner(c.name);
    }

    public Hook(String id, AbstractInsnNode[] Instructions, int L) {
        if (L != -1 && Instructions[L] instanceof FieldInsnNode) {
            for (ClassNode c : CLASSES.values()) {
                List<FieldNode> fields = c.fields;
                for (FieldNode f : fields) {
                    if ((f.name.equals(((FieldInsnNode) Instructions[L]).name)) && (f.desc.equals(((FieldInsnNode) Instructions[L]).desc))) {
                        setId(id);
                        setName(f.name);
                        setDesc(f.desc);
                        setSignature(f.signature);
                        setAccess(f.access);
                        setOwner(((FieldInsnNode) Instructions[L]).owner);
                    }
                }
            }
        } else {
            this.setId(id);
            this.setOwner("NULL");
            this.setMultiplier(0);
            this.setName("NULL");
            this.setDesc("NULL");
            this.broken = true;
        }

    }

    public String getId() {

        return id;
    }

    public void setId(String s) {

        id = s;
    }

    public String getName() {

        return name;
    }

    public void setName(String s) {

        name = s;
    }

    public boolean isDuplicate() {
        return duplicate;
    }

    public void setDuplicate(boolean duplicate) {
        this.duplicate = duplicate;
    }

    public String getDesc() {

        return desc;
    }

    public void setDesc(String s) {

        desc = s;
    }

    public String getSignature() {

        return signature;
    }

    public void setSignature(String s) {

        signature = s;
    }

    public int getAccess() {

        return access;
    }

    public void setAccess(int i) {

        access = i;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String s) {
        owner = s;
    }

    public int getMultiplier() {
        return multiplier;
    }

    public void setMultiplier(int i) {
        multiplier = i;
    }

}

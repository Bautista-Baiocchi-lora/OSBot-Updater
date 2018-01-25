package org.osbot.updater.deob;


import java.util.ArrayList;
import java.util.List;


public class Deobsfucate {
    private List<DeobFrame> Deobs = new ArrayList<DeobFrame>();

    private void loadDeobs() {
  
    	   this.Deobs.add(new Method());
           this.Deobs.add(new Multipliers());
           this.Deobs.add(new EqualSwap());
           this.Deobs.add(new RemoveExceptions());
           this.Deobs.add(new OpaquePredicates());
           this.Deobs.add(new MethodName());
           
    }


    private void runAnalysers() {
        for (DeobFrame tempDeob : this.Deobs) {
            tempDeob.Deob();
        }
   
    }

    public void Run() {
        System.out.println("Beginning Deob..");
        this.loadDeobs();
        this.runAnalysers();
        System.out.println("Deob Finished..\n");
    }
}

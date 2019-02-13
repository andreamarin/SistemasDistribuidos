/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import java.io.Serializable;

/**
 *
  * @author Andrea Marín y Luis Felipe Landa
 */
public class Task implements Serializable{
   private int taskId;
   private String requirementId;
   private int length;
   private double output;

    public Task(int taskId, String requirementId, int length) {
        this.taskId = taskId;
        this.requirementId = requirementId;
        this.length = length;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public String getRequirementId() {
        return requirementId;
    }

    public void setRequirementId(String requirementId) {
        this.requirementId = requirementId;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public double getOutput() {
        return output;
    }

    public void setOutput(double output) {
        this.output = output;
    }
    
    
   
   
   
    
}

package org.example;

public class TaskPercentageOutput {

    private int taskId;
    private String taskName;
    private String percentage;

    public TaskPercentageOutput(int taskId, String taskName, String percentage) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.percentage = percentage;
    }

    public int getTaskId() { return taskId; }
    public String getTaskName() { return taskName; }
    public String getPercentage() { return percentage; }
}

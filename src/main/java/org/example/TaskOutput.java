package org.example;

public class TaskOutput {

    private int taskId;
    private String taskName;
    private int totalMinutes;
    private String percentage;

    public TaskOutput(int taskId, String taskName, int totalMinutes, String percentage) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.totalMinutes = totalMinutes;
        this.percentage = percentage;
    }

    public int getTaskId() { return taskId; }
    public String getTaskName() { return taskName; }
    public int getTotalMinutes() { return totalMinutes; }
    public String getPercentage() { return percentage; }
}
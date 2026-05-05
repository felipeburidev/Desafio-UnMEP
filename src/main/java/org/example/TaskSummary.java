package org.example;

public class TaskSummary {
    private int taskId;
    private int totalMinutes;
    public TaskSummary(int taskId, int totalMinutes) {
        this.taskId = taskId;
        this.totalMinutes = totalMinutes;
    }

    public int getTaskId() {
        return taskId;
    }

    public int getTotalMinutes() {
        return totalMinutes;
    }
}

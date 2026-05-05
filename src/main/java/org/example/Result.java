package org.example;

import java.util.List;

public class Result {

  private int totalMinutes;
    private List<TaskOutput> tasks;  private TaskOutput mostWorkedTask;
  List<TaskPercentageOutput> top3TasksPercentage;
  List<UserSummary> top3Employees ;
  UserTaskStats mostDistinctUserOnTasks;
  int ignoredRecords;

    public int getTotalMinutes() {
        return totalMinutes;
    }

    public void setTotalMinutes(int totalMinutes) {
        this.totalMinutes = totalMinutes;
    }


    public List<TaskOutput> getTasks() {
        return tasks;
    }

    public void setTasks(List<TaskOutput> tasks) {
        this.tasks = tasks;
    }

    public TaskOutput getMostWorkedTask() {
        return mostWorkedTask;
    }

    public void setMostWorkedTask(TaskOutput mostWorkedTask) {
        this.mostWorkedTask = mostWorkedTask;
    }

    public List<TaskPercentageOutput> getTop3TasksPercentage() {
        return top3TasksPercentage;
    }

    public void setTop3TasksPercentage(List<TaskPercentageOutput> top3TasksPercentage) {
        this.top3TasksPercentage = top3TasksPercentage;
    }

    public List<UserSummary> getTop3Employees() {
        return top3Employees;
    }

    public void setTop3Employees(List<UserSummary> top3Employees) {
        this.top3Employees = top3Employees;
    }

    public UserTaskStats getMostDistinctUserOnTasks() {
        return mostDistinctUserOnTasks;
    }

    public void setMostDistinctUserOnTasks(UserTaskStats mostDistinctUserOnTasks) {
        this.mostDistinctUserOnTasks = mostDistinctUserOnTasks;
    }

    public int getIgnoredRecords() {
        return ignoredRecords;
    }

    public void setIgnoredRecords(int ignoredRecords) {
        this.ignoredRecords = ignoredRecords;
    }
}
package org.example;

import java.util.Set;

public class UserTaskStats {

    private int userId;
    private String userName;
    private int distinctTasks;
    private Set<Integer> taskIds;

    public UserTaskStats(int userId, String userName, Set<Integer> taskIds) {
        this.userId = userId;
        this.userName = userName;
        this.taskIds = taskIds;
        this.distinctTasks = taskIds.size();
    }

    public int getUserId() { return userId; }
    public String getUserName() { return userName; }
    public int getDistinctTasks() { return distinctTasks; }
    public Set<Integer> getTaskIds() { return taskIds; }
}
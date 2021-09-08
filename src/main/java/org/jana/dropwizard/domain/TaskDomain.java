package org.jana.dropwizard.domain;

import java.util.Date;

public class TaskDomain {

    private String id;
    private String taskDesc;
    private Date taskDate;
    private TaskStatus taskStatus;

    public TaskDomain() {
    }

    public TaskDomain(String id, String taskDesc, Date taskDate, TaskStatus taskStatus) {
        this.id = id;
        this.taskDesc = taskDesc;
        this.taskDate = taskDate;
        this.taskStatus = taskStatus;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTaskDesc() {
        return taskDesc;
    }

    public void setTaskDesc(String taskDesc) {
        this.taskDesc = taskDesc;
    }

    public Date getTaskDate() {
        return taskDate;
    }

    public void setTaskDate(Date taskDate) {
        this.taskDate = taskDate;
    }

    public TaskStatus getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(TaskStatus taskStatus) {
        this.taskStatus = taskStatus;
    }

    @Override
    public String toString() {
        return "TaskDomain{" +
                "id='" + id + '\'' +
                ", taskDesc='" + taskDesc + '\'' +
                ", taskDate=" + taskDate +
                ", taskStatus=" + taskStatus +
                '}';
    }

}

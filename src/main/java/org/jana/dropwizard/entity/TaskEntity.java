package org.jana.dropwizard.entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "task_tb")
@NamedQueries({
        @NamedQuery(
                name = "org.jana.dropwizard.core.TaskEntity.findAll",
                query = "select t from TaskEntity t"
        )
})
public class TaskEntity {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "task_desc")
    private String taskDesc;

    @Column(name = "task_date")
    private Date taskDate;

    @Column(name = "task_status")
    private String taskStatus;

    public TaskEntity() {
    }

    public TaskEntity(String id, String taskDesc, Date taskDate, String taskStatus) {
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

    public String getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(String taskStatus) {
        this.taskStatus = taskStatus;
    }

    @Override
    public String toString() {
        return "TaskEntity{" +
                "id='" + id + '\'' +
                ", taskDesc='" + taskDesc + '\'' +
                ", taskDate=" + taskDate +
                ", taskStatus='" + taskStatus + '\'' +
                '}';
    }

}


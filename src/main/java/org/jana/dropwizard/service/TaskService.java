package org.jana.dropwizard.service;

import org.jana.dropwizard.dao.TaskDao;
import org.jana.dropwizard.domain.TaskDomain;
import org.jana.dropwizard.domain.TaskStatus;
import org.jana.dropwizard.entity.TaskEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

public class TaskService {
    private final Logger log = LoggerFactory.getLogger(TaskService.class);

    private final TaskDao taskDao;

    public TaskService(TaskDao taskDao) {
        this.taskDao = taskDao;
    }

    public List<TaskDomain> getAll() {
        log.debug("Get all tasks");
        return taskDao.findAll()
                .stream()
                .map(e -> new TaskDomain(e.getId(), e.getTaskDesc(), e.getTaskDate(), TaskStatus.valueOf(e.getTaskStatus())))
                .collect(Collectors.toList());
    }

    public TaskDomain getById(String id) {
        log.debug("Get task by id:{}", id);
        Optional<TaskEntity> entityOpt = taskDao.findById(id);
        if (!entityOpt.isPresent()) {
            return new TaskDomain();
        }
        TaskEntity entity = entityOpt.get();
        return new TaskDomain(entity.getId(), entity.getTaskDesc(), entity.getTaskDate(), TaskStatus.valueOf(entity.getTaskStatus()));
    }

    public TaskDomain create(TaskDomain req) {
        log.debug("Create task: {}", req);
        TaskEntity entity = new TaskEntity(
                UUID.randomUUID().toString(),
                req.getTaskDesc(),
                req.getTaskDate(),
                TaskStatus.PENDING.toString()
        );
        TaskEntity savedRecord = taskDao.saveOrUpdate(entity);
        return new TaskDomain(savedRecord.getId(), savedRecord.getTaskDesc(), savedRecord.getTaskDate(), TaskStatus.valueOf(savedRecord.getTaskStatus()));
    }

    public TaskDomain update(String id, TaskDomain req) {
        log.debug("Update task id:{} req:{}", id, req);
        Optional<TaskEntity> entityOpt = taskDao.findById(id);
        if (!entityOpt.isPresent()) {
            return new TaskDomain();
        }
        TaskEntity entity = entityOpt.get();
        if (req.getTaskDesc() != null && !req.getTaskDesc().trim().isEmpty()) {
            entity.setTaskDesc(req.getTaskDesc());
        }
        if (req.getTaskDate() != null) {
            entity.setTaskDate(req.getTaskDate());
        }
        if (req.getTaskStatus() != null) {
            entity.setTaskStatus(req.getTaskStatus().toString());
        }
        TaskEntity updatedRecord = taskDao.saveOrUpdate(entity);
        return new TaskDomain(updatedRecord.getId(), updatedRecord.getTaskDesc(), updatedRecord.getTaskDate(), TaskStatus.valueOf(updatedRecord.getTaskStatus()));
    }

}

package org.jana.dropwizard.dao;

import io.dropwizard.hibernate.AbstractDAO;
import org.hibernate.SessionFactory;
import org.jana.dropwizard.entity.TaskEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

public class TaskDao extends AbstractDAO<TaskEntity> {
    private final Logger log = LoggerFactory.getLogger(TaskDao.class);

    public TaskDao(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    public List<TaskEntity> findAll() {
        log.debug("Find all tasks");
        return list(namedTypedQuery("org.jana.dropwizard.core.TaskEntity.findAll"));
    }

    public Optional<TaskEntity> findById(String id) {
        log.debug("Find task by id:{}", id);
        return Optional.ofNullable(get(id));
    }

    public TaskEntity saveOrUpdate(TaskEntity taskEntity) {
        log.debug("Save or update {}", taskEntity);
        return persist(taskEntity);
    }

}


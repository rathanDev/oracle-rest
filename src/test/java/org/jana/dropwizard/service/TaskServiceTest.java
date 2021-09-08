package org.jana.dropwizard.service;

import org.jana.dropwizard.dao.TaskDao;
import org.jana.dropwizard.domain.TaskDomain;
import org.jana.dropwizard.domain.TaskStatus;
import org.jana.dropwizard.entity.TaskEntity;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class TaskServiceTest {

    @Mock
    TaskDao taskDao;

    @InjectMocks
    TaskService taskService;

    private TaskDomain taskDomain;
    private TaskEntity taskEntity;
    Date date;
    String taskId;

    @Before
    public void setup() {
        MockitoAnnotations.openMocks(this);

        date = new Date();
        taskId = "0049ba35-2b76-43c2-a352-ee9cbccd23d2";

        taskDomain = new TaskDomain();
        taskDomain.setTaskDesc("desc");
        taskDomain.setTaskDate(date);

        taskEntity = new TaskEntity();
        taskEntity.setId(taskId);
        taskEntity.setTaskDesc("desc");
        taskEntity.setTaskDate(date);
        taskEntity.setTaskStatus(TaskStatus.PENDING.toString());
    }

    @Test
    public void testGetAll() {
        when(taskDao.findAll()).thenReturn(Collections.singletonList(taskEntity));
        List<TaskDomain> domains = taskService.getAll();
        assertEquals(taskId, domains.get(0).getId());
    }

    @Test
    public void testGetById() {
        when(taskDao.findById(taskId)).thenReturn(Optional.of(taskEntity));
        TaskDomain domain = taskService.getById(taskId);
        assertEquals(taskId, domain.getId());
    }

    @Test
    public void testCreate() {
        when(taskDao.saveOrUpdate(any())).thenReturn(taskEntity);
        TaskDomain domain = taskService.create(taskDomain);
        assertEquals(taskId, domain.getId());
    }

    @Test
    public void testUpdate() {
        when(taskDao.findById(taskId)).thenReturn(Optional.of(taskEntity));
        when(taskDao.saveOrUpdate(any())).thenReturn(taskEntity);
        TaskDomain domain = taskService.update(taskId, taskDomain);
        assertEquals(taskId, domain.getId());
    }

}
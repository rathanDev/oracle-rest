package org.jana.dropwizard.resource;

import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;
import io.dropwizard.testing.junit5.ResourceExtension;
import org.jana.dropwizard.dao.TaskDao;
import org.jana.dropwizard.domain.TaskDomain;
import org.jana.dropwizard.domain.TaskStatus;
import org.jana.dropwizard.entity.TaskEntity;
import org.jana.dropwizard.service.TaskService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(DropwizardExtensionsSupport.class)
public class TaskResourceTest {

    private static final TaskDao taskDao = mock(TaskDao.class);
    private static final TaskService taskService = new TaskService(taskDao);
    public static final ResourceExtension RESOURCES = ResourceExtension.builder()
            .addResource(new TaskResource(taskService))
            .build();
    private ArgumentCaptor<TaskEntity> taskEntityCaptor = ArgumentCaptor.forClass(TaskEntity.class);
    private TaskDomain taskDomain;
    private TaskEntity taskEntity;
    Date date;
    String taskId;

    @BeforeEach
    void setUp() {
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

    @AfterEach
    void tearDown() {
        reset(taskDao);
    }

    @Test
    public void testGetAll() {
        final List<TaskEntity> taskEntities = Collections.singletonList(taskEntity);
        when(taskDao.findAll()).thenReturn(taskEntities);

        final List<TaskDomain> response = RESOURCES.target("/task")
                .request().get(new GenericType<List<TaskDomain>>() {
                });

        verify(taskDao).findAll();
        assertThat(response.size()).isEqualTo(taskEntities.size());
        assertThat(response.get(0).getTaskDesc()).isEqualTo(taskEntities.get(0).getTaskDesc());
        assertThat(response.get(0).getTaskDate()).isEqualTo(taskEntities.get(0).getTaskDate());
    }

    @Test
    public void testGetById() {
        when(taskDao.findById(taskId)).thenReturn(Optional.of(taskEntity));

        final TaskDomain response = RESOURCES.target("/task/" + taskId)
                .request()
                .get(new GenericType<TaskDomain>() {
                });

        verify(taskDao).findById(taskId);
        assertThat(response.getId()).isEqualTo(taskEntity.getId());
        assertThat(response.getTaskDesc()).isEqualTo(taskEntity.getTaskDesc());
        assertThat(response.getTaskDate()).isEqualTo(taskEntity.getTaskDate());
    }

    @Test
    public void testCreate() {
        when(taskDao.saveOrUpdate(any(TaskEntity.class))).thenReturn(taskEntity);
        final Response response = RESOURCES.target("/task")
                .request(MediaType.APPLICATION_JSON_TYPE)
                .post(Entity.entity(taskDomain, MediaType.APPLICATION_JSON_TYPE));

        assertThat(response.getStatusInfo()).isEqualTo(Response.Status.OK);
        verify(taskDao).saveOrUpdate(taskEntityCaptor.capture());
        assertThat(taskEntityCaptor.getValue().getTaskDesc()).isEqualTo(taskDomain.getTaskDesc());
        assertThat(taskEntityCaptor.getValue().getTaskDate()).isEqualTo(taskDomain.getTaskDate());
        assertThat(taskEntityCaptor.getValue().getTaskStatus()).isEqualTo(TaskStatus.PENDING.toString());
    }

    @Test
    public void testUpdate() {
        when(taskDao.findById(taskId)).thenReturn(Optional.of(taskEntity));
        when(taskDao.saveOrUpdate(any(TaskEntity.class))).thenReturn(taskEntity);

        final TaskDomain getResponse = RESOURCES.target("/task/" + taskId)
                .request()
                .get(new GenericType<TaskDomain>() {
                });

        final Response updateResponse = RESOURCES.target("/task/" + taskId)
                .request(MediaType.APPLICATION_JSON_TYPE)
                .put(Entity.entity(taskDomain, MediaType.APPLICATION_JSON_TYPE));

        assertThat(updateResponse.getStatusInfo()).isEqualTo(Response.Status.OK);
        verify(taskDao).saveOrUpdate(taskEntityCaptor.capture());
        assertThat(taskEntityCaptor.getValue().getId()).isEqualTo(taskId);
        assertThat(taskEntityCaptor.getValue().getTaskDesc()).isEqualTo(taskDomain.getTaskDesc());
        assertThat(taskEntityCaptor.getValue().getTaskDate()).isEqualTo(taskDomain.getTaskDate());
        assertThat(taskEntityCaptor.getValue().getTaskStatus()).isEqualTo(TaskStatus.PENDING.toString());
    }

}
package org.jana.dropwizard.resource;

import io.dropwizard.hibernate.UnitOfWork;
import org.jana.dropwizard.domain.TaskDomain;
import org.jana.dropwizard.service.TaskService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/task")
@Produces(MediaType.APPLICATION_JSON)
public class TaskResource {

    private final TaskService taskService;

    public TaskResource(TaskService taskService) {
        this.taskService = taskService;
    }

    @GET
    @UnitOfWork
    public List<TaskDomain> getAll() {
        return taskService.getAll();
    }

    @GET
    @Path("/{id}")
    @UnitOfWork
    public TaskDomain getById(@PathParam("id") String id) {
        return taskService.getById(id);
    }

    @POST
    @UnitOfWork
    public TaskDomain create(TaskDomain req) {
        return taskService.create(req);
    }

    @PUT
    @Path("/{id}")
    @UnitOfWork
    public TaskDomain update(@PathParam("id") String id, TaskDomain req) {
        return taskService.update(id, req);
    }

}


package net.wikmans.batch.jobs;

import javax.batch.operations.JobOperator;
import javax.batch.runtime.BatchRuntime;
import javax.batch.runtime.JobExecution;
import javax.batch.runtime.JobInstance;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Created by dawi on 2015-12-11.
 */

@Path("/")
public class BatchResource {

    private JobOperator operator;

    BatchResource() {
        this.operator = BatchRuntime.getJobOperator();
    }

    @PUT
    @Path("start/{id}")
    public Response start(@PathParam("id") int id) {
        Properties props = new Properties();
        props.put("id", String.valueOf(id));
        try {
            long execId = this.operator.start("testB", props);
            return Response.ok().entity(execId).build();
        }
        catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @PUT
    @Path("restart/{execId}")
    public Response restart(@PathParam("execId") long execId) {
        try {
            long id = this.operator.restart(execId, new Properties());
            return Response.ok().entity(id).build();
        }
        catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @PUT
    @Path("stop/{execId}")
    public Response stop(@PathParam("execId") long execId) {
        try {
            this.operator.stop(execId);
            return Response.ok().build();
        }
        catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response list() {
        try {
            List<Execution> result = new ArrayList<>();
            for (String job: operator.getJobNames()) {
                int instances = operator.getJobInstanceCount(job);
                for (JobInstance instance: operator.getJobInstances(job, 0, instances)) {
                    for (JobExecution execution: operator.getJobExecutions(instance)) {
                        Execution e = new Execution();
                        e.setExecId(execution.getExecutionId());
                        e.setInstId(instance.getInstanceId());
                        e.setName(job);
                        e.setBatchStatus(execution.getBatchStatus());
                        e.setExitStatus(execution.getExitStatus());
                        result.add(e);
                    }
                }
            }
            return Response.ok().entity(result).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }
}

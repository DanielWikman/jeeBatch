package net.wikmans.batch.jobs;

import lombok.extern.java.Log;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.batch.operations.JobOperator;
import javax.batch.operations.NoSuchJobException;
import javax.batch.runtime.BatchRuntime;
import javax.batch.runtime.BatchStatus;
import javax.batch.runtime.JobExecution;
import javax.batch.runtime.JobInstance;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.Stateless;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * Created by dawi on 2015-12-10.
 */

@Singleton
@Stateless
@Log
public class BatchBean {

    private JobOperator jobOperator;
    private long runtimeId;

    public void startJob() {

        log.info("Starting JOB");
        jobOperator = BatchRuntime.getJobOperator();

        for (String job: jobOperator.getJobNames()) {
            log.info("Job: " + job);
        }

        log.info(" Stopped: " + getJobs(BatchStatus.STOPPED).size());
        log.info(" Failed: " + getJobs(BatchStatus.FAILED).size());

        for (long job: getJobs(BatchStatus.STOPPED)) {
            log.info("Restart: " + job);
            jobOperator.restart(job, new Properties());
            return;
        }
        runtimeId = jobOperator.start("testB", new Properties());
        log.info("Starting: " + runtimeId);

    }

    private List<Long> getJobs(BatchStatus status)  {
        List<Long> result = new ArrayList<>();
        try {
            int count = jobOperator.getJobInstanceCount("testItem");
            List<JobInstance> instances = jobOperator.getJobInstances("testItem", 0, count);
            for (JobInstance instance : instances) {
                List<JobExecution> executions = jobOperator.getJobExecutions(instance);
                for (JobExecution execution : executions) {
                    if (execution.getBatchStatus().equals(status)) {
                        result.add(execution.getExecutionId());
                    }
                }
            }
        }
        catch (NoSuchJobException e) {
            log.info("No jobs configured yet.");
        }
        return result;
    }

    public void stopJob() {
        log.info("Stopping JOB");
        jobOperator.stop(runtimeId);
    }
}

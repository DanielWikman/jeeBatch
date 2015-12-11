package net.wikmans.batch.jobs;

import lombok.extern.java.Log;

import javax.batch.api.listener.JobListener;
import javax.enterprise.context.Dependent;
import javax.inject.Named;

/**
 * Created by dawi on 2015-12-10.
 */
@Dependent
@Named
@Log
public class TestJobListener implements JobListener {

    @Override
    public void beforeJob() throws Exception {
        log.info("beforeJob");
    }

    @Override
    public void afterJob() throws Exception {
        log.info("afterJob");
    }
}

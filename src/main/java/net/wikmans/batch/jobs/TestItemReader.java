package net.wikmans.batch.jobs;

import lombok.extern.java.Log;

import javax.batch.api.chunk.ItemReader;
import javax.batch.runtime.context.JobContext;
import javax.enterprise.context.Dependent;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

/**
 * Created by dawi on 2015-12-10.
 */
@Dependent
@Named
@Log
public class TestItemReader implements ItemReader {

    @Inject
    JobContext context;

    private TestItemCheckPoint checkPoint = new TestItemCheckPoint();

    public void open(Serializable checkpoint) throws Exception {
        log.info("READER Open " + checkpoint);
        if (checkpoint != null) {
            checkPoint = ((TestItemCheckPoint) checkpoint);
        }
        else {
            try {
                int id = Integer.valueOf(context.getProperties().getProperty("id"));
                checkPoint.setAt(id);
            } catch (Exception e) {
                checkPoint.setAt(1000);
            }
        }
    }

    public void close() throws Exception {
        log.info("READER Close ");
    }

    public Object readItem() throws Exception {
        Thread.sleep(500);
        TestItem item = new TestItem();
        item.setId(checkPoint.getAt());
        log.info("READER Read " + item);
        checkPoint.setAt(item.getId() + 1);
        if (item.getId() > 10000) return null;
        return item;
    }

    public Serializable checkpointInfo() throws Exception {
        log.info("READER Checkpoint " + checkPoint);
        return checkPoint;
    }
}

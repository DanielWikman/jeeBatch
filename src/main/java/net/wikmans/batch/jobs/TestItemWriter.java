package net.wikmans.batch.jobs;

import lombok.extern.java.Log;

import javax.batch.api.chunk.ItemWriter;
import javax.enterprise.context.Dependent;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

/**
 * Created by dawi on 2015-12-10.
 */
@Dependent
@Named
@Log
public class TestItemWriter implements ItemWriter {

    private TestItemCheckPoint checkpoint = new TestItemCheckPoint();

    public void open(Serializable checkpoint) throws Exception {
        log.info("Writer OPEN " + checkpoint);
    }

    public void close() throws Exception {
        log.info("Writer CLOSE");
    }

    public void writeItems(List<Object> items) throws Exception {
        for (Object o: items) {
            TestItem i = (TestItem) o;
            checkpoint.setAt(i.getCid());
            log.info("Writer WRITE " + o.toString());
        }
    }

    public Serializable checkpointInfo() throws Exception {
        log.info("Writer CHECKPOINT " + checkpoint);
        return checkpoint;
    }
}

package net.wikmans.batch.jobs;

import lombok.extern.java.Log;

import javax.batch.api.chunk.ItemProcessor;
import javax.enterprise.context.Dependent;
import javax.inject.Named;

/**
 * Created by dawi on 2015-12-10.
 */

@Dependent
@Named
@Log
public class TestItemProcessor implements ItemProcessor {
    public Object processItem(Object item) throws Exception {
        TestItem i = (TestItem) item;
        log.info("Processing " + i.toString());
        i.setCid(i.getId() + 100000);
        return i;
    }
}

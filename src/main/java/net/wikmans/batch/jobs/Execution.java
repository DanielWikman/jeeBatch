package net.wikmans.batch.jobs;

import lombok.Data;

import javax.batch.runtime.BatchStatus;

/**
 * Created by dawi on 2015-12-11.
 */
@Data
public class Execution {
    private String name;
    private long instId;
    private long execId;
    private BatchStatus batchStatus;
    private String exitStatus;

}

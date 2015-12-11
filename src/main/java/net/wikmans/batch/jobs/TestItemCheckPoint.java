package net.wikmans.batch.jobs;

import javax.enterprise.context.Dependent;
import javax.inject.Named;
import java.io.Serializable;

/**
 * Created by dawi on 2015-12-10.
 */

@Dependent
@Named
public class TestItemCheckPoint implements Serializable {

    private int at;

    public int getAt() {
        return at;
    }

    public void setAt(int at) {
        this.at = at;
    }

    @Override
    public String toString() {
        return String.format("{%d}", at);
    }
}

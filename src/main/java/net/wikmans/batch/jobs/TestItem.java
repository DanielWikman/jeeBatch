package net.wikmans.batch.jobs;

/**
 * Created by dawi on 2015-12-10.
 */
public class TestItem {

    private int id;
    private int cid;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCid() {
        return cid;
    }

    public void setCid(int cid) {
        this.cid = cid;
    }

    @Override
    public String toString() {
        return String.format("{%d-%d}", id, cid);
    }
}

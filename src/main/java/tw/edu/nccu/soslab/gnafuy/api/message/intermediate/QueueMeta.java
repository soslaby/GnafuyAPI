package tw.edu.nccu.soslab.gnafuy.api.message.intermediate;

/**
 * Created by jjchen on 2016/5/27.
 */
public class QueueMeta {
    private String queueName;
    private boolean isForReduce;

    public QueueMeta() {
    }

    public QueueMeta(String queueName, boolean isForReduce) {
        this.queueName = queueName;
        this.isForReduce = isForReduce;
    }

    public String getQueueName() {
        return queueName;
    }

    public void setQueueName(String queueName) {
        this.queueName = queueName;
    }

    public boolean isForReduce() {
        return isForReduce;
    }

    public void setForReduce(boolean forReduce) {
        isForReduce = forReduce;
    }
}

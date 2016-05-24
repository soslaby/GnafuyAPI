package tw.edu.nccu.soslab.gnafuy.api.consumer;

import java.util.List;

/**
 * To specify the data flow.
 * Created by jjchen on 2016/5/24.
 */
public abstract class QueueConsumer {
    protected String fromQueueName;
    protected List<String> toQueueName;


    /**
     * @param fromQueueName The name of queue to take data
     * @param toQueueName queues to receive data
     */
    public QueueConsumer(String fromQueueName, List<String> toQueueName) {
        this.fromQueueName = fromQueueName;
        this.toQueueName = toQueueName;
    }

    public String getFromQueueName() {
        return fromQueueName;
    }


    public List<String> getToQueueName() {
        return toQueueName;
    }
}

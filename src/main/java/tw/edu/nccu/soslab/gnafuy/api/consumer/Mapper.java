package tw.edu.nccu.soslab.gnafuy.api.consumer;

import tw.edu.nccu.soslab.gnafuy.api.utils.Interaction;

import java.util.List;

/**
 * The programming model "MAP"
 * Created by jjchen on 2016/5/25.
 */
public abstract class Mapper<K extends Interaction<?>, V extends Interaction<?>> extends QueueConsumer {
    /**
     * @param fromQueueName The name of queue to take data
     * @param toQueueName   queues to receive data
     */
    public Mapper(String fromQueueName, List<String> toQueueName) {
        super(fromQueueName, toQueueName);
    }


    /**
     * "map" transforms element with type K to element with type V
     * @param from: K
     * @return V
     * @throws Exception
     */
    public abstract V map(K from) throws Exception;
}

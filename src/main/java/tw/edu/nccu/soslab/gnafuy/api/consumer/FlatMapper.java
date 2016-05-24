package tw.edu.nccu.soslab.gnafuy.api.consumer;

import tw.edu.nccu.soslab.gnafuy.api.utils.Interaction;

import java.util.List;

/**
 * Created by jjchen on 2016/5/25.
 */
public abstract class FlatMapper<K extends Interaction<?>, V extends Interaction<?>> extends QueueConsumer {
    /**
     * @param fromQueueName The name of queue to take data
     * @param toQueueName   queues to receive data
     */
    public FlatMapper(String fromQueueName, List<String> toQueueName) {
        super(fromQueueName, toQueueName);
    }
    /**
     * "flatMap" transforms element with type K to elements with type V
     * @param from: K
     * @return List<V>
     * @throws Exception
     */
    public abstract List<V> flatMap(K from) throws Exception;
}

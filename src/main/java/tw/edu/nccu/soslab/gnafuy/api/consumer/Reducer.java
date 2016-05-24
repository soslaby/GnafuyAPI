package tw.edu.nccu.soslab.gnafuy.api.consumer;

import tw.edu.nccu.soslab.gnafuy.api.utils.Interaction;

import java.util.List;

/**
 * Created by jjchen on 2016/5/25.
 */
public abstract class Reducer<K extends Interaction<?>> extends QueueConsumer{
    /**
     * @param fromQueueName The name of queue to take data
     * @param toQueueName   queues to receive data
     */
    public Reducer(String fromQueueName, List<String> toQueueName) {
        super(fromQueueName, toQueueName);
    }


    /**
     * "reduce" takes two elements as input then produce only one element.
     * @param left
     * @param right
     * @return left merged with right
     * @throws Exception
     */
    public abstract K reduce(K left, K right) throws Exception;
}

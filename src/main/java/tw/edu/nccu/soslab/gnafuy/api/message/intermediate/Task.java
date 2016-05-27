package tw.edu.nccu.soslab.gnafuy.api.message.intermediate;

import java.util.List;

/**
 * Created by jjchen on 2016/5/27.
 */
public class Task {
    private String taskName;
    private String taskType;
    private QueueMeta fromQueue;
    private List<QueueMeta> toQueue;

    public Task() {
    }

    public Task(String taskName, String taskType, QueueMeta fromQueue, List<QueueMeta> toQueue) {
        this.taskName = taskName;
        this.taskType = taskType;
        this.fromQueue = fromQueue;
        this.toQueue = toQueue;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskType() {
        return taskType;
    }

    public void setTaskType(String taskType) {
        this.taskType = taskType;
    }

    public QueueMeta getFromQueue() {
        return fromQueue;
    }

    public void setFromQueue(QueueMeta fromQueue) {
        this.fromQueue = fromQueue;
    }

    public List<QueueMeta> getToQueue() {
        return toQueue;
    }

    public void setToQueue(List<QueueMeta> toQueue) {
        this.toQueue = toQueue;
    }
}

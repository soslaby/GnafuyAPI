package tw.edu.nccu.soslab.gnafuy.api.message.intermediate;

import java.util.List;

/**
 * Created by jjchen on 2016/5/27.
 */
public class JobInformation {
    private String jobName;
    private String libraryBase64;
    private QueueMeta initialQueue;
    private List<Task> tasks;

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public String getLibraryBase64() {
        return libraryBase64;
    }

    public void setLibraryBase64(String libraryBase64) {
        this.libraryBase64 = libraryBase64;
    }

    public QueueMeta getInitialQueue() {
        return initialQueue;
    }

    public void setInitialQueue(QueueMeta initialQueue) {
        this.initialQueue = initialQueue;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
}

package tw.edu.nccu.soslab.gnafuy.api.job;

import tw.edu.nccu.soslab.gnafuy.api.consumer.FlatMapper;
import tw.edu.nccu.soslab.gnafuy.api.consumer.Mapper;
import tw.edu.nccu.soslab.gnafuy.api.consumer.Reducer;
import tw.edu.nccu.soslab.gnafuy.api.message.client.request.JobInitializationRequest;
import tw.edu.nccu.soslab.gnafuy.api.message.intermediate.JobInformation;
import tw.edu.nccu.soslab.gnafuy.api.message.intermediate.QueueMeta;
import tw.edu.nccu.soslab.gnafuy.api.message.intermediate.Task;
import tw.edu.nccu.soslab.gnafuy.api.utils.Base64;
import tw.edu.nccu.soslab.gnafuy.api.utils.HttpPost;
import tw.edu.nccu.soslab.gnafuy.api.utils.Interaction;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLClassLoader;
import java.net.URLConnection;
import java.util.*;

/**
 * Created by jjchen on 2016/5/27 .
 */
public class JobBuilder {
    private JobInformation jobInformation = new JobInformation();
    private ClassLoader classLoader;
    private WithInitialData withInitialData;

    public JobBuilder(String hostname, String jarPath) throws IOException {
        File file = new File(jarPath);
        URL[] urls = new URL[]{file.toURI().toURL()};
        this.classLoader = new URLClassLoader(urls);
        this.jobInformation.setLibraryBase64(JobBuilder.dexToString(jarPath));
        this.jobInformation.setJobName(UUID.randomUUID().toString());
        this.withInitialData = new WithInitialData(this.jobInformation, this.classLoader);
    }

    public WithInitialData setInitialData(QueueMeta initialQueue, Iterator<? extends Interaction<?>> dataGenerator) {
        this.jobInformation.setInitialQueue(initialQueue);
        this.withInitialData.setDataGenerator(dataGenerator);
        return this.withInitialData;
    }

    private static class WithInitialData {
        private JobInformation jobInformation;
        private ClassLoader classLoader;
        private Iterator<? extends Interaction<?>> dataGenerator;

        public WithInitialData(JobInformation jobInformation, ClassLoader classLoader) {
            this.jobInformation = jobInformation;
            this.classLoader = classLoader;
        }

        public WithInitialData appendTask(String taskName, String fromQueue, String toQueue) throws ClassNotFoundException {
            List<String> temp = new ArrayList<>();
            temp.add(toQueue);
            return appendTask(taskName, fromQueue, temp);
        }

        public WithInitialData appendTask(String taskName, String fromQueue, List<String> toQueue) throws ClassNotFoundException {
            Class<?> taskClz = classLoader.loadClass(taskName);
            boolean forReducer = false;
            String taskType;
            if (Reducer.class.isAssignableFrom(taskClz)) {
                forReducer = true;
                taskType = Reducer.class.getName();
            } else if (Mapper.class.isAssignableFrom(taskClz)) {
                taskType = Mapper.class.getName();
            } else if (FlatMapper.class.isAssignableFrom(taskClz)) {
                taskType = FlatMapper.class.getName();
            } else {
                throw new IllegalArgumentException("No such task type");
            }
            QueueMeta from = new QueueMeta(fromQueue, forReducer);
            List<QueueMeta> toQueues = new ArrayList<>();
            for (String temp : toQueue) {
                QueueMeta to = new QueueMeta(temp, forReducer);
                toQueues.add(to);
            }
            this.jobInformation.getTasks().add(new Task(taskName, taskType, from, toQueues));
            return this;
        }

        public String preBuild() {
            if (jobInformation.getTasks().size() == 0) {
                throw new IllegalStateException("should have at least one task");
            }
            return new JobInitializationRequest(jobInformation).toJson();
        }

        public void build(String host) throws Exception {
            if (jobInformation.getTasks().size() == 0) {
                throw new IllegalStateException("should have at least one task");
            }
            //figure out a way to roll dataGenerator here.
            HttpPost.post(host, new JobInitializationRequest(jobInformation));
        }

        public void setDataGenerator(Iterator<? extends Interaction<?>> dataGenerator) {
            this.dataGenerator = dataGenerator;
        }
    }

    private static String dexToString(String path) throws IOException {
        URL myUrl = new URL("file:" + path);
        URLConnection connection = myUrl.openConnection();
        InputStream input = connection.getInputStream();
        ByteArrayOutputStream buffer = new ByteArrayOutputStream();
        int data = input.read();
        while (data != -1) {
            buffer.write(data);
            data = input.read();
        }
        input.close();
        byte[] classData = buffer.toByteArray();
        return Base64.encode(classData);
    }

}

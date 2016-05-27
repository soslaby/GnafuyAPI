package tw.edu.nccu.soslab.gnafuy.api.message.client.request;

import tw.edu.nccu.soslab.gnafuy.api.message.GnafuyClientState;
import tw.edu.nccu.soslab.gnafuy.api.message.GnafuyMessageWithState;
import tw.edu.nccu.soslab.gnafuy.api.message.intermediate.JobInformation;

/**
 * Created by jjchen on 2016/5/26.
 */
public class JobInitializationRequest extends GnafuyMessageWithState {
    private JobInformation jobInformation;

    public JobInitializationRequest() {
    }

    public JobInitializationRequest(JobInformation jobInformation) {
        this.jobInformation = jobInformation;
    }

    @Override
    public GnafuyClientState getNextState() {
        return GnafuyClientState.JobInitialized;
    }

    public JobInformation getJobInformation() {
        return jobInformation;
    }

    public void setJobInformation(JobInformation jobInformation) {
        this.jobInformation = jobInformation;
    }

    @Override
    public String toString() {
        return this.toJson();
    }
}

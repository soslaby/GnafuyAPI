package tw.edu.nccu.soslab.gnafuy.api.message;

/**
 * Enum for representing states of client
 * Created by jjchen on 2016/5/25.
 *
 * JobInitialized - To ask server to launch corresponding settings which includes queue, task name and control flow
 * LibraryRequired - Client would ask for library base64 string
 * TaskRequired - Client would ask for task name (class full name) to instantiate
 */
public enum GnafuyClientState {
    JobInitialized(InnerIncreaser.getNextInt()),
    LibraryRequired(InnerIncreaser.getNextInt()),
    TaskRequired(InnerIncreaser.getNextInt()),
    DataRequired(InnerIncreaser.getNextInt()),
    DataProcessing(InnerIncreaser.getNextInt()),
    Stop(InnerIncreaser.getNextInt()),
    UnexpectedState(InnerIncreaser.getNextInt()),;

    private int statusCode;

    GnafuyClientState(int statusCode) {
        this.statusCode = statusCode;
    }

    public static GnafuyClientState fromStatusCode(int code) {
        for (GnafuyClientState req : GnafuyClientState.values()) {
            if (req.getStatusCode() == code) {
                return req;
            }
        }
        return UnexpectedState;
    }

    public int getStatusCode() {
        return statusCode;
    }

    private static class InnerIncreaser {
        private static int i = 0;

        static int getNextInt() {
            return (int) Math.pow(2, i++);
        }
    }


}
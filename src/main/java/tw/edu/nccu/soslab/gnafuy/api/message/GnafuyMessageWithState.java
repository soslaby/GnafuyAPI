package tw.edu.nccu.soslab.gnafuy.api.message;

import com.google.gson.Gson;

/**
 * Created by jjchen on 2016/5/26.
 */
public abstract class GnafuyMessageWithState {
    private int nextStateCode;

    public int getNextStateCode() {
        return nextStateCode;
    }

    public void setNextStateCode(int nextStateCode) {
        this.nextStateCode = nextStateCode;
    }

    public abstract GnafuyClientState getNextState();

    public String toJson() {
        return new Gson().toJson(this);
    }

    public static <T> T fromJson(String json, Class<T> cls) {
        return new Gson().fromJson(json, cls);
    }
}

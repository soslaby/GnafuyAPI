package tw.edu.nccu.soslab.gnafuy.api.message.server.response;

import tw.edu.nccu.soslab.gnafuy.api.message.GnafuyClientState;
import tw.edu.nccu.soslab.gnafuy.api.message.GnafuyMessageWithState;

/**
 * Created by jjchen on 2016/5/27.
 */
public class GnafuyServerResponse extends GnafuyMessageWithState {
    private GnafuyClientState gnafuyClientState;

    public GnafuyServerResponse() {
    }

    public GnafuyServerResponse(GnafuyClientState gnafuyClientState) {
        this.gnafuyClientState = gnafuyClientState;
    }

    @Override
    public GnafuyClientState getNextState() {
        return gnafuyClientState;
    }

    public GnafuyClientState getGnafuyClientState() {
        return gnafuyClientState;
    }

    public void setGnafuyClientState(GnafuyClientState gnafuyClientState) {
        this.gnafuyClientState = gnafuyClientState;
    }
}

package tw.edu.nccu.soslab.gnafuy.api.utils;

import tw.edu.nccu.soslab.gnafuy.api.message.GnafuyMessageWithState;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by jjchen on 2016/5/27.
 */
public class HttpPost {
    public static String post(String host, GnafuyMessageWithState msg) throws Exception {
        StringBuffer html = new StringBuffer();
        OutputStreamWriter w = null;
        BufferedReader in = null;
        HttpURLConnection conn = null;
        try {
            URL obj = new URL(host + "/" + msg.getNextState().name());
            conn = (HttpURLConnection) obj.openConnection();
            conn.setReadTimeout(600000);
            conn.addRequestProperty("Accept-Language", "en-US,en;q=0.8");
            conn.addRequestProperty("User-Agent", "Mozilla");
            conn.addRequestProperty("Referer", "google.com");
            conn.setDoOutput(true);
            w = new OutputStreamWriter(conn.getOutputStream(), "UTF-8");
            w.write(msg.toJson());
            w.flush();
            w.close();
            in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                html.append(inputLine);
            }
        } catch (Exception e) {
            throw e;
        } finally {
            conn.disconnect();
            if(in != null){
                in.close();
            }
        }
        return html.toString();
    }
}

package Appclasses;

/**
 * Created by dealwis on 3/27/16.
 */
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;


import android.content.Context;
import android.os.AsyncTask;
import android.widget.TextView;

public class ServerConManager {

    private String ServerURL = "http://192.168.8.100:80/comi_server/?";
    private HttpURLConnection connection = null;
    /**
     * function get_server_connection_UP
     * @return TYPE HttpURLConnection
     */
    public HttpURLConnection get_server_connection_UP(){

        try {
            URL url;

            url = new URL(this.ServerURL);
            connection = (HttpURLConnection)url.openConnection();
            connection.setRequestMethod("POST");
            connection.setRequestProperty("Content-Type",
                    "application/x-www-form-urlencoded");

        } catch(Exception e){
            return null;
        }

        if(this.connection != null){
            return connection;
        }else return connection;

    }

    public String send_POST_HTTP_request(HttpURLConnection conn ,String data){

        try{
        conn.setRequestProperty("Content-Length", "" +
                Integer.toString(data.getBytes().length));
        conn.setRequestProperty("Content-Language", "en-US");

        conn.setUseCaches (false);
        conn.setDoInput(true);
        conn.setDoOutput(true);
        //Send request
        DataOutputStream wr = new DataOutputStream (
                conn.getOutputStream ());
        wr.writeBytes (data);
        wr.flush ();
        wr.close ();

        //Get Response
        InputStream is = conn.getInputStream();
        BufferedReader rd = new BufferedReader(new InputStreamReader(is,"iso-8859-1"),8);
        String line;
        StringBuffer response = new StringBuffer();
        while((line = rd.readLine()) != null) {
            response.append(line);
            response.append('\r');
        }
        rd.close();
        return response.toString();

                } catch (Exception e) {

                    e.printStackTrace();
                    return null;

                } finally {

                    if(conn != null) {
                        conn.disconnect();
                    }
                }


    }




}

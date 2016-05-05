package Appclasses;


import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;
import Appclasses.ServerConManager;
/**
 * Created by dealwis on 4/1/16.
 */
public class Intrest {


    private String image = "";
    private ServerConManager SerConObject = new ServerConManager();
    private HttpURLConnection ser_conn = null;





    public String intrest_id = "";
    public String intrest_name = "";
    public String intrest_caption = "";
    public String intrest_type = "";
    public String intrest_category = "";
    public String intrest_shared_date = "";

    public String upload_user_shared_intrest(String image,String name,String caption,String Intrest,String Intrest_type,String capacity){


        String Ser_Response = "";


        this.ser_conn = this.SerConObject.get_server_connection_UP();

        try {

            String urlParameters ;
            String Server_API_method = "upload_shared_intrest";

            urlParameters = "image=" + URLEncoder.encode(image, "UTF-8")
                    + "&method=" + URLEncoder.encode(Server_API_method, "UTF-8")
                    + "&name=" + URLEncoder.encode(name, "UTF-8")
                    + "&caption=" + URLEncoder.encode(caption, "UTF-8")
                    + "&intrest=" + URLEncoder.encode(Intrest, "UTF-8")
                    + "&intrest_type=" + URLEncoder.encode(Intrest_type, "UTF-8")
                    + "&capacity=" + URLEncoder.encode(capacity, "UTF-8");

            Ser_Response = this.SerConObject.send_POST_HTTP_request(ser_conn, urlParameters);


            JSONArray jsonArray = new JSONArray((Ser_Response));

            JSONObject jsonobject = jsonArray.getJSONObject(0);
            String Response = jsonobject.getString("response");


            return Response;



        }catch(Exception e) {


            return "error in upload_user_shared_intrest";
        }



    }

    public int get_all_intrest_details(String int_id){

        String Ser_Response = "";
        this.ser_conn = this.SerConObject.get_server_connection_UP();


        try {

            String urlParameters ;
            String Server_API_method = "get_all_intrest_details";

            urlParameters = "intrest_id=" + URLEncoder.encode(int_id, "UTF-8")
                    + "&method=" + URLEncoder.encode(Server_API_method, "UTF-8");

            Ser_Response = this.SerConObject.send_POST_HTTP_request(ser_conn, urlParameters);


            JSONArray jsonArray = new JSONArray((Ser_Response));

            JSONObject jsonobject = jsonArray.getJSONObject(0);

            String Response = jsonobject.getString("response");

            if(Response.equals("1")){


                JSONObject jsonobject1 = jsonArray.getJSONObject(1);
                JSONObject jsonobject2 = jsonArray.getJSONObject(2);
                JSONObject jsonobject3 = jsonArray.getJSONObject(3);
                JSONObject jsonobject4 = jsonArray.getJSONObject(4);
                JSONObject jsonobject5 = jsonArray.getJSONObject(5);
                JSONObject jsonobject6 = jsonArray.getJSONObject(6);


                intrest_id = jsonobject1.getString("id");
                intrest_name = jsonobject2.getString("name");
                intrest_caption = jsonobject3.getString("caption");
                intrest_shared_date = jsonobject4.getString("shared_date");
                intrest_type = jsonobject5.getString("intrest_type");
                intrest_category = jsonobject6.getString("intrest_category");


                return 1;
            }else{

                return 0;
            }



        }catch(Exception e) {

            return -1;
        }

    }




    public int make_intrest_like(String int_id,String usr_id){

        String Ser_Response = "";
        this.ser_conn = this.SerConObject.get_server_connection_UP();


        try {

            String urlParameters ;
            String Server_API_method = "make_intrest_like";

            urlParameters = "intrest_id=" + URLEncoder.encode(int_id, "UTF-8")
                    + "&method=" + URLEncoder.encode(Server_API_method, "UTF-8")
                    + "&user_id=" + URLEncoder.encode(usr_id, "UTF-8");

            Ser_Response = this.SerConObject.send_POST_HTTP_request(ser_conn, urlParameters);


            JSONArray jsonArray = new JSONArray((Ser_Response));

            JSONObject jsonobject = jsonArray.getJSONObject(0);

            String Response = jsonobject.getString("response");

            if(Response.equals("1")){


                return 1;
            }else{

                return 0;
            }



        }catch(Exception e) {

            return -1;
        }

    }



    public int join_activity_action(String int_id,String usr_id){

        String Ser_Response = "";
        this.ser_conn = this.SerConObject.get_server_connection_UP();


        try {

            String urlParameters ;
            String Server_API_method = "join_activity_action";

            urlParameters = "intrest_id=" + URLEncoder.encode(int_id, "UTF-8")
                    + "&method=" + URLEncoder.encode(Server_API_method, "UTF-8")
                    + "&user_id=" + URLEncoder.encode(usr_id, "UTF-8");

            Ser_Response = this.SerConObject.send_POST_HTTP_request(ser_conn, urlParameters);


            JSONArray jsonArray = new JSONArray((Ser_Response));

            JSONObject jsonobject = jsonArray.getJSONObject(0);

            String Response = jsonobject.getString("response");

            if(Response.equals("1")){


                return 1;
            }else{

                return 0;
            }



        }catch(Exception e) {

            return -1;
        }

    }


    public String get_all_like_count(String int_id){

        String Ser_Response = "";
        this.ser_conn = this.SerConObject.get_server_connection_UP();


        try {

            String urlParameters ;
            String Server_API_method = "get_like_count";

            urlParameters = "intrest_id=" + URLEncoder.encode(int_id, "UTF-8")
                    + "&method=" + URLEncoder.encode(Server_API_method, "UTF-8");

            Ser_Response = this.SerConObject.send_POST_HTTP_request(ser_conn, urlParameters);


            JSONArray jsonArray = new JSONArray((Ser_Response));

            JSONObject jsonobject = jsonArray.getJSONObject(0);

            String Response = jsonobject.getString("response");


            if(Response.equals("1")){

                String count = jsonobject.getString("likecount");
                return count;


            }else{

                return "-1";
            }



        }catch(Exception e) {

            return "-2";
        }

    }




    public String get_intrest_type(String int_id){

        String Ser_Response = "";
        this.ser_conn = this.SerConObject.get_server_connection_UP();


        try {

            String urlParameters ;
            String Server_API_method = "get_intrest_type";

            urlParameters = "intrest_id=" + URLEncoder.encode(int_id, "UTF-8")
                    + "&method=" + URLEncoder.encode(Server_API_method, "UTF-8");

            Ser_Response = this.SerConObject.send_POST_HTTP_request(ser_conn, urlParameters);


            JSONArray jsonArray = new JSONArray((Ser_Response));

            JSONObject jsonobject = jsonArray.getJSONObject(0);

            String Response = jsonobject.getString("response");


            if(Response.equals("1")){

                String type = jsonobject.getString("type");
                return type;


            }else{

                return "-1";
            }



        }catch(Exception e) {

            return "-2";
        }

    }


    public String get_like_status(String int_id,String username){

        String Ser_Response = "";
        this.ser_conn = this.SerConObject.get_server_connection_UP();


        try {

            String urlParameters ;
            String Server_API_method = "get_like_status";

            urlParameters = "intrest_id=" + URLEncoder.encode(int_id, "UTF-8")
                    + "&method=" + URLEncoder.encode(Server_API_method, "UTF-8")
                    + "&username=" + URLEncoder.encode(username, "UTF-8");

            Ser_Response = this.SerConObject.send_POST_HTTP_request(ser_conn, urlParameters);


            JSONArray jsonArray = new JSONArray((Ser_Response));

            JSONObject jsonobject = jsonArray.getJSONObject(0);

            String Response = jsonobject.getString("response");


            if(Response.equals("1")){

                String status = jsonobject.getString("status");
                return status;


            }
            if(Response.equals("0")){

                String status = jsonobject.getString("status");
                return status;


            }
            else{

                return "-1";
            }



        }catch(Exception e) {

            return "-2";
        }

    }



    public String get_join_status(String int_id,String username){

        String Ser_Response = "";
        this.ser_conn = this.SerConObject.get_server_connection_UP();


        try {

            String urlParameters ;
            String Server_API_method = "get_join_status";

            urlParameters = "intrest_id=" + URLEncoder.encode(int_id, "UTF-8")
                    + "&method=" + URLEncoder.encode(Server_API_method, "UTF-8")
                    + "&username=" + URLEncoder.encode(username, "UTF-8");

            Ser_Response = this.SerConObject.send_POST_HTTP_request(ser_conn, urlParameters);


            JSONArray jsonArray = new JSONArray((Ser_Response));

            JSONObject jsonobject = jsonArray.getJSONObject(0);

            String Response = jsonobject.getString("response");


            if(Response.equals("1")){

                String status = jsonobject.getString("status");
                return status;


            }
            if(Response.equals("0")){

                String status = jsonobject.getString("status");
                return status;


            }
            else{

                return "-1";
            }



        }catch(Exception e) {

            return "-2";
        }

    }


    public String sendGetRequest(String uri) {
        try {
            URL url = new URL(uri);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(con.getInputStream()));

            String result;

            StringBuilder sb = new StringBuilder();

            while((result = bufferedReader.readLine())!=null){
                sb.append(result);
            }

            return sb.toString();
        } catch (Exception e) {
            return null;
        }
    }

    public String sendPostRequest(String requestURL,
                                  HashMap<String, String> postDataParams) {

        URL url;
        String response = "";
        try {
            url = new URL(requestURL);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(15000);
            conn.setConnectTimeout(15000);
            conn.setRequestMethod("POST");
            conn.setDoInput(true);
            conn.setDoOutput(true);


            OutputStream os = conn.getOutputStream();
            BufferedWriter writer = new BufferedWriter(
                    new OutputStreamWriter(os, "UTF-8"));
            writer.write(getPostDataString(postDataParams));

            writer.flush();
            writer.close();
            os.close();
            int responseCode = conn.getResponseCode();

            if (responseCode == HttpsURLConnection.HTTP_OK) {
                BufferedReader br = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                response = br.readLine();
            } else {
                response = "Error Registering";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return response;
    }

    private String getPostDataString(HashMap<String, String> params) throws UnsupportedEncodingException {
        StringBuilder result = new StringBuilder();
        boolean first = true;
        for (Map.Entry<String, String> entry : params.entrySet()) {
            if (first)
                first = false;
            else
                result.append("&");

            result.append(URLEncoder.encode(entry.getKey(), "UTF-8"));
            result.append("=");
            result.append(URLEncoder.encode(entry.getValue(), "UTF-8"));
        }

        return result.toString();
    }




}

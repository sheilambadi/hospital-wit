import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by sheilambadi on 2/28/18.
 */

public class TaskBackground extends AsyncTask<String, String, String> {

    HttpURLConnection conn;
    URL url = null;
    Context context;

    @Override
    protected String doInBackground(String... strings) {
        //set the url
        try {
            url = new URL("http://10.0.2.2:80/wit/register.php");
        } catch (MalformedURLException e){
            e.printStackTrace();
            return "exception";
        }

        //try to connect
        //setup HttpURLConnection class to send and receive data from php and mysql
        //conn = (HttpURLConnection) url.openConnection();
        try{
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");

            //handle send and receive
            conn.setDoInput(true);
            conn.setDoOutput(true);
        } catch (IOException e){
            String k = e.toString();
            return  k;
        }

        //append parameter to URL
        String[] params={};
        Uri.Builder builder = new Uri.Builder().appendQueryParameter("username", params[0])
                                                .appendQueryParameter("password", params[1]);
        String query = builder.build().getEncodedQuery();

        //open connection for sending data
        OutputStream os = null;
        try {
            os = conn.getOutputStream();
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
            writer.write(query);
            writer.flush();
            writer.close();
            os.close();
            conn.connect();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //check for the server response
        try{
            int responseode = conn.getResponseCode();

            //check if connection successful
            if(responseode == HttpURLConnection.HTTP_OK){
                //read data sent from server
                InputStream input = conn.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                StringBuilder result = new StringBuilder();
                String line;

                while ((line = reader.readLine()) != null){
                    result.append(line);
                }

                //pass data to onPostExecute method
                return  (result.toString());
            } else {
                return ("Unsuccessful");
            }
        }catch (IOException e){
            e.printStackTrace();;
        }

        return null;
    }
}


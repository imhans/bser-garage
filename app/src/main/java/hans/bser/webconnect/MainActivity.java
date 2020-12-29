package hans.bser.webconnect;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    final static String BESR_ITEM_URL = "https://open-api.bser.io/v1/data/";
    String urlString = BESR_ITEM_URL + "Character";
    String str, result;
    TextView txt;
    String name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt = findViewById(R.id.txtView);

        AsyncTask.execute(new Runnable() {
            @Override
            public void run() {
                //Create URL
                try {
                    URL aesop = new URL(urlString);
                    txt.setText("tried");

                    //Create Connection
                    HttpURLConnection mConnection = (HttpURLConnection) aesop.openConnection();
                    //Add Headers
                    mConnection.setRequestProperty("x-api-key", "agFxyGgRR38dzOKU73vzU1AvG9oDovLf1wfFhQxa");

                    //Check Connection
                    if ( mConnection.getResponseCode() == 200 ) {
                        BufferedReader reader = new BufferedReader(new InputStreamReader(mConnection.getInputStream()));
                        StringBuffer buffer = new StringBuffer();
                        while ( (str = reader.readLine()) != null) {
                            buffer.append(str);
                        }

                        result = buffer.toString();
                        //Log.d("result", result);
                        reader.close();

                        //Get JSON Array
                        JSONObject jsonObject = new JSONObject(result);
                        JSONArray jsonArray = jsonObject.getJSONArray("data");

                        List<String> itemList = new ArrayList<String>();
                        for (int i = 0; i < jsonArray.length(); i++) {
                            itemList.add(jsonArray.getJSONObject(i).getString("name"));
                        }
                        name = itemList.toString();
                        Log.d("name", name);
                    }
                    else {
                        //Toast.makeText(getApplicationContext(), "fail", Toast.LENGTH_SHORT).show();
                        Log.d("test", "connection fail");
                    }

                } catch (MalformedURLException e) {
                    System.err.println("Malformed URL");
                    e.printStackTrace();
                } catch (IOException e) {
                    System.err.println("URL Connection failed");
                    e.printStackTrace();
                } catch (JSONException e) {
                    System.err.println("JSON Parsing error");
                    e.printStackTrace();
                }
            }
        });
        txt.setText(name);
    }

//    private class GetItems extends AsyncTask<String, String, String> {
//
//        @Override
//        protected String doInBackground(Object[] objects) {
//            return null;
//        }
//    }
}
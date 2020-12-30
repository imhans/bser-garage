package hans.bser.garage;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import web.WebTask;

public class MainActivity extends AppCompatActivity {

    final static String BESR_ITEM_URL = "https://open-api.bser.io/v1/data/";
    Button btnCharacter;
    Button btnItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        WebTask webTask = new WebTask(BESR_ITEM_URL, "Character");
        webTask.execute();

        btnCharacter = findViewById(R.id.btnCharacter);
        btnItem = findViewById(R.id.btnItem);

        btnCharacter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentCharacter = new Intent(getApplicationContext(), CharacterActivity.class);
                startActivity(intentCharacter);
            }
        });

        /** Test **/
//        AsyncTask.execute(new Runnable() {
//            @Override
//            public void run() {
//                HttpURLConnection mConnection = null;
//                StringBuffer params = new StringBuffer();
//
//                /**
//                 * 2. Get data via HttpsURLConnection
//                 * */
//                try {
//                    URL url = new URL(urlString);
//
//                    //Create Connection
//                    mConnection = (HttpURLConnection) url.openConnection();
//                    //Add Headers
//                    mConnection.setRequestProperty(getString(R.string.XAPIKEY), getString(R.string.APIKEY));
//
//                    //Check Connection
//                    if ( mConnection.getResponseCode() == 200 ) {
//                        BufferedReader reader = new BufferedReader(new InputStreamReader(mConnection.getInputStream()));
//                        StringBuffer buffer = new StringBuffer();
//                        while ( (str = reader.readLine()) != null) {
//                            buffer.append(str);
//                        }
//
//                        result = buffer.toString();
//                        //Log.d("result", result);
//                        reader.close();
//
//                        //Get JSON Array
//                        JSONObject jsonObject = new JSONObject(result);
//                        JSONArray jsonArray = jsonObject.getJSONArray("data");
//
//                        /**
//                         Customize Below
//                         **/
//                        //Get Objects
//                        obj = jsonArray.get(0).toString();
//                        Log.d("obj", obj);
//                    }
//                    else {
//                        Log.d("test", "connection fail");
//                    }
//
//                } catch (MalformedURLException e) {
//                    System.err.println("Malformed URL");
//                    e.printStackTrace();
//                } catch (IOException e) {
//                    System.err.println("URL Connection failed");
//                    e.printStackTrace();
//                } catch (JSONException e) {
//                    System.err.println("JSON Parsing error");
//                    e.printStackTrace();
//                }
//
//            }
//        });
    }
}
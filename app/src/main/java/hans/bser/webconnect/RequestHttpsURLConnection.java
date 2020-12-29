package hans.bser.webconnect;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class RequestHttpsURLConnection {

    String str, strUrl, result;
    String obj, name;

    public String request(String _url, String _params) {

        HttpURLConnection mConnection = null;

        /**
         * 1. Append the parameter
         * */
        if ( _params != null )
            strUrl = _url + _params;
        else
            strUrl = _url;

        /**
         * 2. Get data via HttpsURLConnection
         * */
        try {
            URL url = new URL(strUrl);

            //Create Connection
            mConnection = (HttpURLConnection) url.openConnection();
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
                Log.d("result", result);
                reader.close();

                //Get JSON Array
                JSONObject jsonObject = new JSONObject(result);
                JSONArray jsonArray = jsonObject.getJSONArray("data");

                /**
                 Customize Below
                 **/
                //Get Objects
                obj = jsonArray.get(0).toString();
                Log.d("obj", obj);

                /**
                     e.g. - Fetch Characters Name into an ArrayList
                     List<String> itemList = new ArrayList<String>();
                     for (int i = 0; i < jsonArray.length(); i++) {
                         itemList.add(jsonArray.getJSONObject(i).getString("name"));
                     }
                     name = itemList.get(0).toString();
                     Log.d("name", name);
                 *
                 * **/

            }
            else {
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

        return obj;
    }
}

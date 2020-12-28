package hans.bser.webconnect;

import android.content.ContentValues;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.Buffer;
import java.util.Map;

public class RequestHTTPConnection {

    String str, result;

    public String request(String _url) {
        HttpURLConnection mConnection = null;
//        StringBuffer params = new StringBuffer();

//        if ( _params == null ) {
//            params.append("");
//        }
//        //In case _params has more then two values and so it needs to append "&"
//        else {
//            boolean isAnd = false;
//            String key;
//            String value;
//
//            for (Map.Entry<String, Object> parameter : _params.valueSet()) {
//                key = parameter.getKey();
//                value = parameter.getValue().toString();
//
//                if (isAnd) {
//                    params.append("&");
//                }
//            }
//        }


        //Create URL
        try {
            URL aesop = new URL(_url);

            //Create Connection
            mConnection = (HttpURLConnection) aesop.openConnection();

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

                JSONObject jsonObject = new JSONObject(result);
                Log.d("test", "connection success");

                return result;
            }
            else {
                //Toast.makeText(getApplicationContext(), "fail", Toast.LENGTH_SHORT).show();
                Log.d("test", "connection fail");
                return null;
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
        return null;
    }

}

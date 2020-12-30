package web;

import android.os.AsyncTask;

public class WebTask extends AsyncTask<Void, Void, String> {

    private String url;
    private String values;

    public WebTask(String url, String values) {
        this.url = url; this.values = values;
    }

    @Override
    protected String doInBackground(Void... voids) {
        String result;
        RequestHttpsURLConnection requestHttpsURLConnection = new RequestHttpsURLConnection();
        result = requestHttpsURLConnection.request(url, values);

        return result;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
    }
}

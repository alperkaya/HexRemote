package com.example.alperkaya.hexremote;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private ListView mCanView;
    private CanAdapter mCanAdapter;
    private ProgressDialog pDialog;

    // API urls
    // Url to get all CAN messages
    private String URL_GET_CAN_MSG = "http://192.168.17.2/hex_api/get_canmsg.php";

    private ArrayList<CanMessage> mCanList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mCanList = new ArrayList<CanMessage>();

        mCanView = (ListView) findViewById(R.id.listView);


        new GetCanMessages().execute();
    }

    private void populateListView() {
        mCanAdapter = new CanAdapter(getApplicationContext(), mCanList);
        mCanView.setAdapter(mCanAdapter);

    }

    private class GetCanMessages extends AsyncTask<Void, Void, Void> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(MainActivity.this);
            pDialog.setMessage("Fetching CAN messages..");
            pDialog.setCancelable(false);
            pDialog.show();

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            ServiceHandler jsonParser = new ServiceHandler(getApplicationContext());
            String json = jsonParser.makeServiceCall(URL_GET_CAN_MSG, ServiceHandler.GET);

            Log.e("Response: ", "> " + json);

            if (json != null) {
                try {
                    JSONObject jsonObj = new JSONObject(json);
                    if (jsonObj != null) {

                        JSONArray jsonArray = jsonObj
                                .getJSONArray("CANmsg");

                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject canObj = (JSONObject) jsonArray.get(i);
                            CanMessage data = new CanMessage(canObj.getInt("extID"),
                                    canObj.getInt("data0"),
                                    canObj.getInt("data1"),
                                    canObj.getInt("data2"),
                                    canObj.getInt("data3"),
                                    canObj.getInt("data4"),
                                    canObj.getInt("data5"),
                                    canObj.getInt("data6"),
                                    canObj.getInt("data7"));
                            mCanList.add(data);
                        }
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            } else {
                Log.e("JSON Data", "Didn't receive any data from server!");
            }

            return null;
        }


        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            if (pDialog.isShowing())
                pDialog.dismiss();

            populateListView();
        }
    }

}

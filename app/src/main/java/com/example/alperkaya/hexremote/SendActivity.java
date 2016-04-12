package com.example.alperkaya.hexremote;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

public class SendActivity extends Activity {

    private ProgressDialog pDialog;

    // API urls
    // Url to update CAN messages
    private String URL_POST_CAN_MSG = "http://alperkaya.duckdns.org/hex_api/create_canmsg.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send);
    }


    private class CreateCanMessages extends AsyncTask<Void, Void, Void> {
        boolean isNewCANmsgCreated = false;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pDialog = new ProgressDialog(SendActivity.this);
            pDialog.setMessage("Creating new category..");
            pDialog.setCancelable(false);
            pDialog.show();

        }


        @Override
        protected Void doInBackground(Void... arg0) {
            ServiceHandler jsonParser = new ServiceHandler(getApplicationContext());
            String json = jsonParser.makeServiceCall(URL_POST_CAN_MSG, ServiceHandler.POST);

            Log.e("Response: ", "> " + json);

            if (json != null) {
                /*try {
                    JSONObject jsonObj = new JSONObject(json);
                    if (jsonObj != null) {

                        *//*JSONArray jsonArray = jsonObj
                                .getJSONArray("CANmsg");

                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject canObj = (JSONObject) jsonArray.get(i);
                            CanMessage data = new CanMessage(
                                    canObj.getInt("extID"),
                                    canObj.getInt("data0"),
                                    canObj.getInt("data1"),
                                    canObj.getInt("data2"),
                                    canObj.getInt("data3"),
                                    canObj.getInt("data4"),
                                    canObj.getInt("data5"),
                                    canObj.getInt("data6"),
                                    canObj.getInt("data7"));
                            mCanList.add(data);*//*
                        }
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }*/

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

        }
    }

}

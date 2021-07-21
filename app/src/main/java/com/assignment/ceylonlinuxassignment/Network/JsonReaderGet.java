package com.assignment.ceylonlinuxassignment.Network;

import android.content.Context;
import android.util.Log;

import com.assignment.ceylonlinuxassignment.R;
import com.assignment.ceylonlinuxassignment.Utils.Utility;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;

public class JsonReaderGet {
    private static final String TAG = "JsonReaderGet";
    static InputStream is = null;
    private static JSONObject jsonObject = null;
    private static String json = "";

    public static String[] getAPI(Context mContext) {
        String[] responseArray = new String[2];
        if (Utility.isNetworkNotAvailable(mContext)) {
            try {

                String reqURL = "https://app.ceylonlinux.lk/jsonFileWrite/testapi.php" ;

                String response = HttpClient.executeHttpGet(reqURL);

                if(!response.equalsIgnoreCase("")) {
                    JSONObject jsonObj = new JSONObject(response);
                    if (jsonObj.get("success").toString().equals("true")) {
                        responseArray[0] = "true";
                        responseArray[1] = response;
                        Log.d(TAG, "getAPI: " + jsonObj.getString("data"));
                    } else {
                        responseArray[0] = "false";
                        if (jsonObj.has("code")) {
                            if (jsonObj.getString("code").equalsIgnoreCase("401")) {
                                responseArray[1] = "Unauthorized";
                            } else {
                                responseArray[1] = jsonObj.getString("message");
                            }
                        } else {
                            responseArray[1] = jsonObj.getString("message");
                        }
                        Log.d(TAG, "getAPI: " + jsonObj.getString("message"));
                    }
                }else {
                    responseArray[0] = "false";
                    responseArray[1] = "";
                    Log.d(TAG, "getAPI: empty" + response);
                }
            } catch (IOException e) {
                responseArray[0] = "false";
                responseArray[1] = mContext.getResources().getString(R.string.http_failed_message);
                Log.d(TAG, "getAPI: IOException : " + e.getMessage());
            } catch (JSONException e) {
                responseArray[0] = "false";
                responseArray[1] = mContext.getResources().getString(R.string.http_failed_message);
                Log.d(TAG, "getAPI: JSONException : " + e.getMessage());
            } catch (Exception e) {
                responseArray[0] = "false";
                responseArray[1] = mContext.getResources().getString(R.string.http_failed_message);
                Log.d(TAG, "getAPI: Exception : " + e.getMessage());
            }
        } else {
            responseArray[0] = "false";
            responseArray[1] = "";
        }
        return responseArray;
    }

}

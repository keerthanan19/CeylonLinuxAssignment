package com.assignment.ceylonlinuxassignment.Network;

import android.content.Context;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

public class HttpClient {

    public static String executeHttpGet(String ReqUrl) throws IOException {

        InputStream is = null;
        int len;
        try {
            URL url = new URL(ReqUrl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestProperty("Accept-Encoding", "UTF-8");
            conn.setReadTimeout(10000);
            conn.setConnectTimeout(20000);
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "*/*");
            conn.setDoInput(true);
            conn.connect();
            int response = conn.getResponseCode();
            is = conn.getInputStream();
//            len = conn.getContentLength();
            return readIt(is);
        } catch (UnsupportedEncodingException ex) {
            Log.e("UnsupportedEncoding", ex.toString());
        } catch (MalformedURLException ex) {
            Log.e("MalformedURLException", ex.toString());
        } catch (ProtocolException ex) {
            Log.e("ProtocolException", ex.toString());
        } catch (IOException ex) {
            Log.e("IOException", ex.toString());
        } catch (Exception e) {
            Log.e("ExceptionHttpGet", e.toString());
        } finally {
            if (is != null) {
                is.close();
            }
        }
        return null;
    }

    private static String readIt(InputStream stream) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        StringBuilder sb = new StringBuilder();
        String line;
        try {
            line = "";
            while ((line = reader.readLine()) != null) {
                sb.append(line).append('\n');
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                stream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

}

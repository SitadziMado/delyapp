package com.example.adrax.dely.core;

import android.os.AsyncTask;
import android.support.annotation.NonNull;

import org.jetbrains.annotations.Contract;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Created by Максим on 09.07.2017.
 */

class InternetTask extends AsyncTask<String, Void, String> {
    public InternetTask(String address, InternetCallback<String> callable) {
        if (callable == null || address == null || address.equals("")) {
            throw new NullPointerException();
        }

        m_address = address;
        m_callable = callable;
    }

    @Override
    protected String doInBackground(String... params) {
        String result = null;
        HttpURLConnection urlConnection = null;
        try {
            URL url = new URL(m_address);
            urlConnection = (HttpURLConnection)url.openConnection();

            String data = concatenateParameters(params);

            urlConnection.setRequestMethod("POST");
            urlConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            urlConnection.setRequestProperty( "Content-Length", String.valueOf(data.length()));

            urlConnection.setDoOutput(true);
            urlConnection.setChunkedStreamingMode(0);

            OutputStream out = new BufferedOutputStream(urlConnection.getOutputStream());
            out.write(data.getBytes(Charset.forName("UTF-8")));
            out.flush();

            InputStream in = new BufferedInputStream(urlConnection.getInputStream());
            result = readStream(in);
        } catch (MalformedURLException e) {
            result = null;
        } catch (IOException e) {
            result = null;
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }

        return result;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        m_callable.call(s);
    }

    private String readStream(InputStream in)
            throws IOException {
        ByteArrayOutputStream result = new ByteArrayOutputStream();
        try {
            byte[] buffer = new byte[1024];
            int length;
            while ((length = in.read(buffer)) != -1)
                result.write(buffer, 0, length);
            return result.toString("UTF-8");
        }
        catch (IOException e) {
            throw e;
        }
    }

    @NonNull
    private static String concatenateParameters(@NonNull String... params)
            throws UnsupportedEncodingException {
        StringBuilder sb = new StringBuilder();

        // Если параметров больше двух, то парсим их
        if (params.length >= 2) {
            try {
                sb.append(URLEncoder.encode(params[0], "UTF-8"));
                sb.append("=");
                sb.append(URLEncoder.encode(params[1], "UTF-8"));

                // Преобразуем всё к виду "param1=value1&param2=value2..."
                for (int i = 2; i < params.length; i += 2) {
                    sb.append("&");
                    sb.append(URLEncoder.encode(params[i], "UTF-8"));
                    sb.append("=");
                    sb.append(URLEncoder.encode(params[i + 1], "UTF-8"));
                }
            }
            catch (UnsupportedEncodingException e) {
                throw e;
            }
        }
        return sb.toString();
    }

    private String m_address = null;
    private InternetCallback<String> m_callable = null;
}

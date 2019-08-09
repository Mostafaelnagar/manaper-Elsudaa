package app.manaper.base.volleyutils;

import android.util.Log;
import android.widget.ImageView;

import com.android.volley.AuthFailureError;
import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.google.gson.Gson;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import org.json.JSONObject;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import app.manaper.R;
import app.manaper.base.UserPreferenceHelper;
import app.manaper.base.filesutils.VolleyFileObject;
import app.manaper.common.Common;


public class ConnectionHelper {

    private static DisplayImageOptions options = new DisplayImageOptions.Builder()
            .showImageForEmptyUri(R.color.colorWhite)
            .showImageOnLoading(R.color.colorWhite)
            .showImageOnFail(R.color.colorWhite)
            .cacheInMemory(true)
            .cacheOnDisk(true).build();

    private static ImageLoader imageLoader = ImageLoader.getInstance();
    private ConnectionListener connectionListener;
    private RequestQueue queue;
    private static final int TIME_OUT = 10000;
    private Gson gson;

    public ConnectionHelper(ConnectionListener connectionListener) {
        this.connectionListener = connectionListener;
        queue = MyApplication.getInstance().getRequestQueue();
        gson = new Gson();
    }


    public void requestJsonObject(int method, String url, Object requestData, final Class<?> responseType) {
        final Gson gson = new Gson();
        String link = url;

        link = link.replaceAll(" ", "%20");
        JSONObject jsonObject = null;

        try {
            jsonObject = new JSONObject(gson.toJson(requestData));
        } catch (Exception e) {
            e.getStackTrace();
        }

        Log.e("Url :", url);
        if (jsonObject != null) {
            Log.e("Request :", jsonObject.toString());
        } else {
            Log.e("Request :", "Make sure that you added request correctly");
        }


        JsonObjectRequest jsonObjReq = new JsonObjectRequest(method, link, jsonObject,

                response -> {
                    Log.e("Response Success:", response.toString());
                    parseData(response, responseType);
                }

                , new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError volleyError) {
                showErrorDetails(volleyError);
                connectionListener.onRequestError(volleyError);
            }


        }) {
            @Override
            public Map getHeaders() throws AuthFailureError {

                return getCustomHeaders();
            }
        };
        jsonObjReq.setRetryPolicy(new DefaultRetryPolicy(TIME_OUT,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(jsonObjReq);

    }


    public void multiPartConnect(String url, final Object requestData, final List<VolleyFileObject> volleyFileObjects, final Class<?> responseType) {

        System.out.println(url);
        String link = url;
        link = link.replaceAll(" ", "%20");


        final VolleyMultipartRequest multipartRequest = new VolleyMultipartRequest(Request.Method.POST, link, new Response.Listener<NetworkResponse>() {
            @Override
            public void onResponse(NetworkResponse networkResponse) {
                String responseString = new String(networkResponse.data);
                JSONObject response = null;
                try {
                    response = new JSONObject(responseString);
                } catch (Exception e) {
                    e.getStackTrace();
                }
                parseData(response, responseType);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError volleyError) {
                showErrorDetails(volleyError);
                connectionListener.onRequestError(volleyError);
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                return getParameters(requestData);
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                return getCustomHeaders();
            }

            @Override
            protected Map<String, DataPart> getByteData() {

                return getFileParameters(volleyFileObjects);
            }

        };

        multipartRequest.setRetryPolicy(new DefaultRetryPolicy(TIME_OUT,
                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        queue.add(multipartRequest);
    }

    public HashMap getCustomHeaders() {
        HashMap headers = new HashMap();
        //header
        if (UserPreferenceHelper.getInstance(MyApplication.getInstance().getApplicationContext()).getUserData() != null) {
            headers.put("jwt", UserPreferenceHelper.getInstance(MyApplication.getInstance().getApplicationContext()).getUserData().getJwt());
        } else if (Common.userItem !=null){
            headers.put("jwt", Common.userItem.getJwt());
        }else {
            headers.put("jwt", "DgSX73mbIMn1vkTYDLzO");
        }

        headers.put("Lang", UserPreferenceHelper.getInstance(MyApplication.getInstance().getApplicationContext()).getCurrentLanguage(MyApplication.getInstance().getApplicationContext()));

         return headers;
    }


    public static void loadImage(final ImageView image, String imageUrl) {
         imageLoader.displayImage(imageUrl, image, options);
    }


    private void showErrorDetails(VolleyError volleyError) {
        String body;

        try {
            final String statusCode = String.valueOf(volleyError.networkResponse.statusCode);
            body = new String(volleyError.networkResponse.data, "UTF-8");
            Log.e("TAG", "Error Body " + body + " StatusCode " + statusCode);
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    private void parseData(JSONObject response, final Class<?> responseType) {

        try {
            System.out.println("Response " + response.toString());
            if (response.toString().equals("")) {
                connectionListener.onRequestError(null);
            } else {
                connectionListener.onRequestSuccess(gson.fromJson(response.toString(), responseType));
            }
        } catch (Exception e) {
            connectionListener.onRequestError(null);
        }

    }

    private Map<String, String> getParameters(final Object requestData) {
        Map<String, String> params = new HashMap<>();
        try {
            JSONObject jsonObject = new JSONObject(gson.toJson(requestData));
            for (int i = 0; i < jsonObject.names().length(); i++) {
                params.put(jsonObject.names().getString(i), jsonObject.get(jsonObject.names().getString(i)) + "");
                Log.e("PARAMS", jsonObject.get(jsonObject.names().getString(i)) + "");
            }
            Log.e("PARAMS", params.size() + "");
        } catch (Exception e) {
            Log.e("PARAMS", e.getStackTrace() + "");
            e.getStackTrace();
        }
        return params;
    }

    private Map<String, VolleyMultipartRequest.DataPart> getFileParameters(List<VolleyFileObject> volleyFileObjects) {
        Map<String, VolleyMultipartRequest.DataPart> filesParams = new HashMap<>();
        if (volleyFileObjects == null) {
            return filesParams;
        }

        for (int i = 0; i < volleyFileObjects.size(); i++) {
            final File filePath = new File(volleyFileObjects.get(i).getFilePath());
            filesParams.put(volleyFileObjects.get(i).getParamName(), new VolleyMultipartRequest.DataPart(filePath.getName(), volleyFileObjects.get(i).getCompressObject().getBytes()));
        }
        Log.e("PARAMS", filesParams.size() + "");
        volleyFileObjects.clear();
        return filesParams;
    }


}
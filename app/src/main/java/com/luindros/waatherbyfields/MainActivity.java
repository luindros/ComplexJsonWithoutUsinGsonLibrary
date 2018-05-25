package com.luindros.waatherbyfields;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
public class MainActivity extends AppCompatActivity {
    private String JsonURL = "http://api.openweathermap.org/data/2.5/find?q=London,uk&appid=62c44155efb775337d698e0089a05319&units=metric";
    private RequestQueue requestQueue;
    private Weather weather;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        requestQueue = Volley.newRequestQueue(this);
        weather=new Weather();

        requestWeather();
    }

    private void requestWeather() {

        JsonObjectRequest rq = new JsonObjectRequest(Request.Method.GET, JsonURL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                int weather_id;

                /*With this code we can run a JSONArray and interpret by key.
                int weather_id;
                JSONArray list= null;
                list = response.getJSONArray("list");
                for (int i = 0; i <list.length() ; i++) {
                    JSONObject object = list.getJSONObject(i);
                    String key = object.keys().next();
                    if (key.equalsIgnoreCase("weather")) {
                        weather_id=object.getInt("id");
                        Toast.makeText(MainActivity.this, "Weather id = "+weather_id, Toast.LENGTH_SHORT).show();
                    }
                }*/

                try {
                    //Splitting the JSONObject response in different JsonElements (JsonObjects, JsonArrays, etc)
                    //with the intention to collect the exact value we want.
                    
                    JSONArray list= response.getJSONArray("list");
                    JSONObject objectList=list.getJSONObject(0);
                    JSONArray weatherList=objectList.getJSONArray("weather");
                    JSONObject weatherObject=weatherList.getJSONObject(0);

                    weather_id=weatherObject.getInt("id");
                    Toast.makeText(MainActivity.this, "weatherid"+weather_id, Toast.LENGTH_SHORT).show();

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(rq);
    }
}

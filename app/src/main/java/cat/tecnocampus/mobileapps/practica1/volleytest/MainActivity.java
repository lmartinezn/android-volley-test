package cat.tecnocampus.mobileapps.practica1.volleytest;

import android.app.DownloadManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        text= findViewById(R.id.text_json);

        RequestQueue queue= Volley.newRequestQueue(getApplicationContext());
        String url = "https://jsonploacholder.typicode.com/users/";

        getStringRequest(queue, url);
        //getJSONRequest(queue,url);

    }

    private void getStringRequest(RequestQueue queue, String url){
        StringRequest stringRequest=new StringRequest(Request.Method.GET, url,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            text.setText(response);
                        }
                    },
                    new Response.ErrorListener(){
                        @Override
                        public void onErrorResponse(VolleyError e){
                            text.setText("Error in volley string request");
                        }
                    }
        );
        queue.add(stringRequest);
    }

    private void getJSONRequest(RequestQueue queue, String url){
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                        text.setText(getAllNames(response));
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        text.setText("Error i volley json request");
                    }
                }
        );
        queue.add(jsonArrayRequest);
    }

    private String getAllNames(JSONArray users){
        try{
            String result="";
            for(int i=0; i<users.length(); i++){
                JSONObject user= users.getJSONObject(i);
                result+=user.getString("name")+ "\n";
            }
            return result;
        }catch(Exception e){
            Log.d("S11", "Error in getAllNames");
        }
        return "";
    }
}

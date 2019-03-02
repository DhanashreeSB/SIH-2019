package com.example.shyam.fly;

import android.annotation.SuppressLint;
import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class LoginActivity extends AppCompatActivity {

    public SharedPreferenceConfig preferenceConfig;
    public EditText textusername, textpassword;
    public String usernameString, passwordString, loginflag="";
    ProgressDialog progressDialog;
    private static final String USER_URL = "http://100.192.0.199/fly/login.php";

    @SuppressLint("LongLogTag")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        preferenceConfig = new SharedPreferenceConfig(getApplicationContext());
        textusername = findViewById(R.id.username);
        textpassword = findViewById(R.id.password);
        progressDialog = new ProgressDialog(LoginActivity.this);
        if(preferenceConfig.readLoginStatus())
        {
            Log.w("Inside shared Preferences :","true");
            startActivity(new Intent(this,FirstActivity.class));
            finish();
        }
    }

    public void loginUser(View view)
    {
        usernameString = textusername.getText().toString();
        passwordString = textpassword.getText().toString();
        if(!usernameString.isEmpty()&&!passwordString.isEmpty())
        {
            loadusers();
        }
        else
        {
            validateLogin();
        }

    }

    public void loadusers()
    {
        progressDialog.setMessage("Please Wait");
        progressDialog.show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, USER_URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // Hiding the progress dialog after all task complete.
                progressDialog.dismiss();

                // Matching server responce message to our text.
                if(response.equalsIgnoreCase("Data Matched")) {

                    // If response matched then show the toast.
                    Toast.makeText(LoginActivity.this, "Logged In Successfully", Toast.LENGTH_LONG).show();

                    // Finish the current Login activity.
                    finish();

                    // Opening the user profile activity using intent.
                    preferenceConfig.writeLoginStatus(true);
                    Intent intent = new Intent(LoginActivity.this, FirstActivity.class);

                    // Sending User Email to another activity using intent.
                    intent.putExtra("UserEmailTAG", textusername.getText().toString());

                    startActivity(intent);
                }
                else {

                    // Showing Echo Response Message Coming From Server.
                    Toast.makeText(LoginActivity.this, response, Toast.LENGTH_LONG).show();

                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(LoginActivity.this,error.getMessage(),Toast.LENGTH_LONG).show();
                // Hiding the progress dialog after all task complete.
                progressDialog.dismiss();

                // Showing error message if something goes wrong.
                Toast.makeText(LoginActivity.this, error.toString(), Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() {

                // Creating Map String Params.
                Map<String, String> params = new HashMap<String, String>();

                // Adding All values to Params.
                // The first argument should be same sa your MySQL database table columns.
                params.put("username", textusername.getText().toString());
                params.put("password", textpassword.getText().toString());
                return params;
            }
        };

        //Volley.newRequestQueue(this).add(stringRequest);
        // Creating RequestQueue.
        RequestQueue requestQueue = Volley.newRequestQueue(LoginActivity.this);
        // Adding the StringRequest object into requestQueue.
        requestQueue.add(stringRequest);
    }

    public void register(View view)
    {
        Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
        startActivity(intent);
    }

    public void validateLogin()
    {
        if(usernameString.isEmpty())
        {
            textusername.setError("Enter Username");
        }
        if(passwordString.isEmpty())
        {
            textpassword.setError("Enter Password");
        }
    }
}
/*
try {
                    JSONArray jsonArray = new JSONArray(response);

                    for (int i=0; i<jsonArray.length();i++)
                    {
                        JSONObject jsonObject = jsonArray.getJSONObject(i);
                        String username = jsonObject.getString("username");
                        String password = jsonObject.getString("password");
                        String email = jsonObject.getString("email");
                        String phone_no = jsonObject.getString("phone_no");
                        System.out.print("username : "+username+" password :"+password);

                        if(usernameString.equals(username) && passwordString.equals(password))
                        {
                            loginflag = "exist";
                            break;
                        }
                        else
                        {
                            loginflag = "notexist";
                        }
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

 */
package com.example.shyam.fly;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.HashMap;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    EditText usernametext, passwordtext, repasswordtext, emailtext, phonetext;
    public static String usernameString, emailString, phoneString, repass, passwordString;
    Button registerbutton;
    String SERVER_URL = "http://192.168.43.221/fly/register.php";
    AlertDialog.Builder builder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        usernametext = findViewById(R.id.username1);
        passwordtext = findViewById(R.id.password1);
        repasswordtext = findViewById(R.id.repassword);
        emailtext = findViewById(R.id.email);
        phonetext = findViewById(R.id.phonenumber);
        registerbutton = findViewById(R.id.registerb);
        builder = new AlertDialog.Builder(RegisterActivity.this);
    }

    public void setRegisterResponse(View view) {
        usernameString = usernametext.getText().toString();
        emailString = emailtext.getText().toString();
        phoneString = phonetext.getText().toString();
        repass = repasswordtext.getText().toString();
        passwordString = passwordtext.getText().toString();

        if (!usernameString.isEmpty()&&!emailString.isEmpty()&&!phoneString.isEmpty()&&!repass.isEmpty()&&!passwordString.isEmpty()&&passwordString.equals(repass)&&Patterns.EMAIL_ADDRESS.matcher(emailString).matches()&&phoneString.length()==10) {
            StringRequest stringRequest = new StringRequest(Request.Method.POST, SERVER_URL,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            builder.setTitle("Server Response");
                            builder.setMessage("Response : " + response);
                            builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    usernametext.setText("");
                                    emailtext.setText("");
                                    passwordtext.setText("");
                                    repasswordtext.setText("");
                                    phonetext.setText("");
                                }
                            });

                            AlertDialog alertDialog = builder.create();
                            alertDialog.show();
                        }
                    }, new Response.ErrorListener() {
                @Override
                public void onErrorResponse(VolleyError error) {

                    Toast.makeText(RegisterActivity.this, error.getMessage(), Toast.LENGTH_LONG).show();
                    error.printStackTrace();
                }
            }) {
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<String, String>();
                    params.put("username", usernameString);
                    params.put("email", emailString);
                    params.put("password", passwordString);
                    params.put("phone", phoneString);
                    return params;
                }

            };

            MySingleton.getInstance(RegisterActivity.this).addToRequestQueue(stringRequest);
            startActivity(new Intent(RegisterActivity.this,LoginActivity.class));

        }

        else
        {
            validate();
        }

    }

    public void validate()
    {
        if(usernameString.isEmpty())
        {
            usernametext.setError("Enter Username");
        }

        if(passwordString.isEmpty())
        {
            passwordtext.setError("Enter Password");
        }

        if(repass.isEmpty())
        {
            repasswordtext.setError("Re-enter Password");
        }

        if(emailString.isEmpty())
        {
            emailtext.setError("Enter Email");
        }

        if(phoneString.isEmpty())
        {
            phonetext.setError("Enter Phone No.");
        }

        if(!passwordString.equals(repass))
        {
            //Toast.makeText(RegisterActivity.this,"Re-entered password is incorrect",Toast.LENGTH_LONG).show();
            repasswordtext.setError("Enter same password");
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(emailString).matches())
        {
            emailtext.setError("Invalid Email");
        }

        if(phoneString.length()<10||phoneString.length()==11)
        {
            phonetext.setError("Invalid Phone No.");
        }
    }
}

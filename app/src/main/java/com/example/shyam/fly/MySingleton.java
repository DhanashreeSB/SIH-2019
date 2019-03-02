package com.example.shyam.fly;

import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class MySingleton {
    private static MySingleton mySingleton;
    private RequestQueue requestQueue;
    private static Context context;

    private MySingleton(Context context)
    {
        this.context = context;
        requestQueue = getRequestQueue();
    }

    //method that returns instance of this class
    public static synchronized MySingleton getInstance(Context context)
    {
        if(mySingleton==null)
        {
            mySingleton = new MySingleton(context);
        }
        return mySingleton;
    }

    public RequestQueue getRequestQueue()
    {
        if(requestQueue == null)
        {
            requestQueue = Volley.newRequestQueue(context.getApplicationContext());

        }
        return requestQueue;
    }

    public <T>void addToRequestQueue(Request<T> request)
    {
        requestQueue.add(request);

    }
}

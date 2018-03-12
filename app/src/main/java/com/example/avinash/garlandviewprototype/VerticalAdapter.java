package com.example.avinash.garlandviewprototype;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.error.AuthFailureError;
import com.android.volley.error.VolleyError;
import com.android.volley.request.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by avinash on 26/1/18.
 */

public class VerticalAdapter extends RecyclerView.Adapter<VerticalAdapter.MyViewHolder> {

   private Context context;
   private String[] craftinfo;
//    public ArrayList<String> craftinfo;




    final String [] who = {
            "Actor",
            "Actress",
            "Art Department",
            "Assistant Director",
            "Child Artist",
            "Choreographer",
            "Costumer",
            "Dancer",
            "Designer",
            "DI",
            "Dialogue Writer",
            "Director of Photography",
            "Dubbing Artist",
            "Editor",
            "Focus Puller",
            "Hair Dresser",
            "Location Manager",
            "Lyric Writer",
            "Makeup Man",
            "Mic Department",
            "Music Director",
            "Pet Supplier",
            "PRO",
            "Production Food",
            "Production Manager",
            "Script Writer",
            "Set Department",
            "SFX",
            "Side Artist",
            "Singer",
            "Sound Mixing Engineer",
            "Sound Recording Engineer",
            "Still Photographer",
            "Story Board Artist",
            "Stuntman",
            "Vehicle Driver",
            "VFX"
    };




    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView txtView;

        public MyViewHolder(View view) {
            super(view);
            txtView = (TextView) view.findViewById(R.id.txtView);

        }
    }


    public VerticalAdapter(Context context, String[] craftinfo) {
        this.context = context;
        this.craftinfo = craftinfo;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.vertical_item_view, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {

        try {
            JSONObject jsonObject = new JSONObject(craftinfo[position]);


            jsonObject.optString("category");
            jsonObject.optString("gender");
            jsonObject.optString("email");
            jsonObject.optString("mobileNumber");

            String s = "Name: " + jsonObject.optString("name") + "\n" +
                    "Category: " + jsonObject.optString("category") + "\n" +
                    "Gender: " + jsonObject.optString("gender") + "\n" +
                    "Email: " + jsonObject.optString("email") + "\n" +
                    "Mobile Number: " + jsonObject.optString("mobileNumber");

            holder.txtView.setText(s);

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

    @Override
    public int getItemCount() {
        return craftinfo.length;
    }
}
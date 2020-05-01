package com.akinseye.ndif_yemmanuel.handout;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
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

import java.text.DecimalFormat;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link World.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link World#newInstance} factory method to
 * create an instance of this fragment.
 */
public class World extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    String noOfCases;
    String noOfDeath;
    String noOfRecovery;
    String world_api;

    private OnFragmentInteractionListener mListener;

    public World() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment World.
     */
    // TODO: Rename and change types and number of parameters
    public static World newInstance(String param1, String param2) {
        World fragment = new World();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View v = inflater.inflate(R.layout.fragment_world, container, false);
        // Inflate the layout for this fragment

        final ProgressBar progressBar = v.findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);

        //get api from my DB first
        RequestQueue queue2 = Volley.newRequestQueue(getContext());
        String url = "http://54.71.22.155/covid/apis";
        StringRequest stringRequest2 = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        progressBar.setVisibility(View.VISIBLE);
                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            JSONObject world_data = jsonArray.getJSONObject(0);
                            world_api = world_data.getString("api");


                            //get the data's first
                            RequestQueue queue = Volley.newRequestQueue(getContext());
                            StringRequest stringRequest = new StringRequest(Request.Method.GET, world_api,
                                    new Response.Listener<String>() {
                                        @Override
                                        public void onResponse(String response) {
                                            //array is saved in here
                                            try{
                                                JSONObject jsonObject = new JSONObject(response);

                                                noOfCases = jsonObject.getString("cases");
                                                noOfDeath = jsonObject.getString("deaths");
                                                noOfRecovery = jsonObject.getString("recovered");
                                            }
                                            catch (JSONException e){
                                                e.printStackTrace();
                                                Toast.makeText(getContext(), "Error in connection", Toast.LENGTH_LONG).show();
                                            }

                                            TextView confirmedcased = v.findViewById(R.id.confirmedcase);
                                            TextView deathcase = v.findViewById(R.id.deathcase);
                                            TextView recoverycase = v.findViewById(R.id.recoverycase);

                                            //adding commas to the string
                                            DecimalFormat df = new DecimalFormat("#,###");
                                            confirmedcased.setText(df.format(Long.parseLong(noOfCases)));
                                            deathcase.setText(df.format(Long.parseLong(noOfDeath)));
                                            recoverycase.setText(df.format(Long.parseLong(noOfRecovery)));

                                            progressBar.setVisibility(View.GONE);


                                        }
                                    }, new Response.ErrorListener() {
                                @Override
                                public void onErrorResponse(VolleyError error) {
                                    progressBar.setVisibility(View.GONE);
                                    Toast.makeText(getActivity(), "Error in connection", Toast.LENGTH_LONG).show();
                                }
                            });
                            queue.add(stringRequest);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressBar.setVisibility(View.GONE);
                Toast.makeText(getActivity(), "Network connectivity problem", Toast.LENGTH_LONG).show();
            }
        });
        queue2.add(stringRequest2);

        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}

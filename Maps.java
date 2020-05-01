package com.akinseye.ndif_yemmanuel.handout;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.richpath.RichPath;
import com.richpath.RichPathView;
import com.richpathanimator.AnimationListener;
import com.richpathanimator.RichPathAnimator;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Maps.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Maps#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Maps extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    String richName;

    int TotalNoOfCases;
    int TotalNoOfDeath;


    private OnFragmentInteractionListener mListener;

    public Maps() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Maps.
     */
    // TODO: Rename and change types and number of parameters
    public static Maps newInstance(String param1, String param2) {
        Maps fragment = new Maps();
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
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_maps, container, false);


        final RichPathView androidRichPathView = v.findViewById(R.id.ic_nigeria);

        final RichPath[] allPaths = androidRichPathView.findAllRichPaths();


        //to color the path of a state affected
        final int[] numArray2 = new int[1];
        //get the data from the DB
        RequestQueue queue = Volley.newRequestQueue(getContext());
        String url = "http://54.71.22.155/covid/getAllData";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //array is saved in here
                        try{
                            JSONArray jsonArray = new JSONArray(response);
                            numArray2[0] = jsonArray.length();
                            //System.out.println("JSONarray length = "+numArray[0]);
                            //Toast.makeText(getContext(), "JSONArray length= "+numArray[0], Toast.LENGTH_LONG).show();

                            for(int i=0; i<numArray2[0]; i++){
                                JSONObject counter = jsonArray.getJSONObject(i);

                                final String state = counter.getString("dstate");
                                final String cases = counter.getString("cases");
                                String death = counter.getString("deaths");
                                String recovery = counter.getString("recovered");

                                RichPathAnimator.animate(allPaths)
                                        .animationListener(new AnimationListener() {
                                            @Override
                                            public void onStart() {

                                                if(Integer.parseInt(cases) > 0 && Integer.parseInt(cases) <= 100){
                                                    //color the path for this state
                                                    RichPath st = androidRichPathView.findRichPathByName(state);
                                                    st.setFillColor(Color.parseColor("#cece21"));
                                                }
                                                if(Integer.parseInt(cases) > 100){
                                                    //color the path for this state
                                                    RichPath st = androidRichPathView.findRichPathByName(state);
                                                    st.setFillColor(Color.parseColor("#dd480e"));
                                                }
                                                else{
                                                    //leave the default color
                                                }
                                            }

                                            @Override
                                            public void onStop() {

                                            }
                                        })
                                .start();


                            }
                        }
                        catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(stringRequest);







        androidRichPathView.setOnPathClickListener(new RichPath.OnPathClickListener() {
            @Override
            public void onClick(RichPath richPath) {
//                if (richPath.getName().equals("Abuja")){
//                    Toast.makeText(getContext(), "FCT Abuja", Toast.LENGTH_LONG).show();
//                }

                richName = richPath.getName();
                showDialog(getActivity());
            }
        });

        return v;
    }







    public void showDialog(Activity activity) {

        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.custompopupcovid);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        final TextView stateName = dialog.findViewById(R.id.nameOfState);
        final TextView totalConfirmed = dialog.findViewById(R.id.totalConfirmed);
        final TextView totalDeath = dialog.findViewById(R.id.totalDeath);
        final TextView totalRecoveries = dialog.findViewById(R.id.totalRecoveries);

        //get the data's first
        final int[] numArray = new int[1];
        RequestQueue queue = Volley.newRequestQueue(getContext());
        String url = "http://54.71.22.155/covid/getAllData";
        StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        //array is saved in here
                        try{
                            JSONArray jsonArray = new JSONArray(response);
                            numArray[0] = jsonArray.length();
                            //System.out.println("JSONarray length = "+numArray[0]);
                            //Toast.makeText(getContext(), "JSONArray length= "+numArray[0], Toast.LENGTH_LONG).show();

                            for(int i=0; i<numArray[0]; i++){
                                JSONObject counter = jsonArray.getJSONObject(i);

                                String state = counter.getString("dstate");
                                String cases = counter.getString("cases");
                                String death = counter.getString("deaths");
                                String recovery = counter.getString("recovered");

//                                TotalNoOfCases = TotalNoOfCases + Integer.parseInt(cases);
//                                TotalNoOfDeath = TotalNoOfDeath + Integer.parseInt(death);

                                TotalNoOfCases = TotalNoOfCases + Integer.parseInt(cases);
                                TotalNoOfDeath = TotalNoOfDeath + Integer.parseInt(death);

                                if(richName.equals(state)){
                                    stateName.setText(state);
                                    totalConfirmed.setText(cases);
                                    totalDeath.setText(death);
                                    totalRecoveries.setText(recovery);
                                }
                                else{
                                    //do nothing and skip
                                }
                            }
                        }
                        catch (JSONException e){
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        queue.add(stringRequest);
        dialog.show();


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

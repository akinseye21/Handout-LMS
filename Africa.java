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
import android.widget.Toast;

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

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Africa.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Africa#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Africa extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    ArrayList<String> Country = new ArrayList<>();
    ArrayList<String> NumberOfCases = new ArrayList<>();
    ArrayList<String> NumberOfDeath = new ArrayList<>();
    ArrayList<String> NumberOfRecovery = new ArrayList<>();
    ArrayList<String> NumberOfActive = new ArrayList<>();

    String richName;
    String countries_api;
    int i;

    private OnFragmentInteractionListener mListener;

    public Africa() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Africa.
     */
    // TODO: Rename and change types and number of parameters
    public static Africa newInstance(String param1, String param2) {
        Africa fragment = new Africa();
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
        View v = inflater.inflate(R.layout.fragment_africa, container, false);
        // Inflate the layout for this fragment


        final RichPathView androidRichPathView = v.findViewById(R.id.ic_africa);

        final RichPath[] allPaths = androidRichPathView.findAllRichPaths();


        //get api from my DB first
        RequestQueue queue2 = Volley.newRequestQueue(getContext());
        String url = "http://54.71.22.155/covid/apis";
        StringRequest stringRequest2 = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            JSONObject world_data = jsonArray.getJSONObject(2);
                            countries_api = world_data.getString("api");


                            //to color the path of a state affected
                            final int[] numArray2 = new int[1];
                            //get the data from the DB
                            RequestQueue queue = Volley.newRequestQueue(getContext());
                            StringRequest stringRequest = new StringRequest(Request.Method.GET, countries_api,
                                    new Response.Listener<String>() {
                                        @Override
                                        public void onResponse(String response) {
                                            //array is saved in here
                                            try{
                                                JSONArray jsonArray = new JSONArray(response);
                                                numArray2[0] = jsonArray.length();

                                                for(int i=0; i<numArray2[0]; i++){
                                                    JSONObject counter = jsonArray.getJSONObject(i);

                                                    final String country = counter.getString("country");
                                                    final String cases = counter.getString("cases");
                                                    final String death = counter.getString("deaths");
                                                    final String recovery = counter.getString("recovered");
                                                    final String active = counter.getString("active");
                                                    //if country is a name of any africa country, save each String in an array list
                                                    if( country.equals("Zimbabwe") ||
                                                            country.equals("Zambia") ||
                                                            country.equals("Western Sahara") ||
                                                            country.equals("Uganda") ||
                                                            country.equals("Tunisia") ||
                                                            country.equals("Togo") ||
                                                            country.equals("Tanzania") ||
                                                            country.equals("Swaziland") ||
                                                            country.equals("Sudan") ||
                                                            country.equals("South Sudan") ||
                                                            country.equals("South Africa") ||
                                                            country.equals("Somalia") ||
                                                            country.equals("Sierra Leone") ||
                                                            country.equals("Senegal") ||
                                                            country.equals("Rwanda") ||
                                                            country.equals("Congo") ||
                                                            country.equals("Nigeria") ||
                                                            country.equals("Niger") ||
                                                            country.equals("Namibia") ||
                                                            country.equals("Mozambique") ||
                                                            country.equals("Morocco") ||
                                                            country.equals("Mauritania") ||
                                                            country.equals("Mali") ||
                                                            country.equals("Malawi") ||
                                                            country.equals("Madagascar") ||
                                                            country.equals("Libyan Arab Jamahiriya") ||
                                                            country.equals("Liberia") ||
                                                            country.equals("Lesotho") ||
                                                            country.equals("Kenya") ||
                                                            country.equals("Guinea-Bissau") ||
                                                            country.equals("Guinea") ||
                                                            country.equals("Ghana") ||
                                                            country.equals("Gambia") ||
                                                            country.equals("Gabon") ||
                                                            country.equals("Ethiopia") ||
                                                            country.equals("Eritrea") ||
                                                            country.equals("Equatorial Guinea") ||
                                                            country.equals("Egypt") ||
                                                            country.equals("Djibouti") ||
                                                            country.equals("Côte d'Ivoire") ||
                                                            country.equals("Chad") ||
                                                            country.equals("Central African Republic") ||
                                                            country.equals("Cameroon") ||
                                                            country.equals("Burundi") ||
                                                            country.equals("Burkina Faso") ||
                                                            country.equals("Botswana") ||
                                                            country.equals("Benin") ||
                                                            country.equals("Angola") ||
                                                            country.equals("Algeria"))
                                                    {
                                                        Country.add(country);
                                                        NumberOfCases.add(cases);
                                                        NumberOfDeath.add(death);
                                                        NumberOfRecovery.add(recovery);
                                                        NumberOfActive.add(active);

                                                        RichPathAnimator.animate(allPaths)
                                                                .animationListener(new AnimationListener() {
                                                                    @Override
                                                                    public void onStart() {

                                                                        if(Integer.parseInt(cases) > 0 && Integer.parseInt(cases) <= 100) {
                                                                            //color the path for this state
                                                                            RichPath st = androidRichPathView.findRichPathByName(country);
                                                                            st.setFillColor(Color.parseColor("#cece21"));
                                                                        }
                                                                        if(Integer.parseInt(cases) > 100) {
                                                                            //color the path for this state
                                                                            RichPath st = androidRichPathView.findRichPathByName(country);
                                                                            st.setFillColor(Color.parseColor("#dd480e"));
                                                                        }
                                                                        else{
                                                                            //leave color the same
                                                                        }


                                                                    }

                                                                    @Override
                                                                    public void onStop() {

                                                                    }
                                                                })
                                                                .start();


                                                    }else{
                                                        //ignore the remaining data
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


                            androidRichPathView.setOnPathClickListener(new RichPath.OnPathClickListener() {
                                @Override
                                public void onClick(RichPath richPath) {

                                    richName = richPath.getName();
                                    showDialog(getActivity());
                                }
                            });



                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getActivity(), "Network connectivity problem", Toast.LENGTH_LONG).show();
            }
        });
        queue2.add(stringRequest2);





        return v;
    }


    public void showDialog(Activity activity) {

        final Dialog dialog = new Dialog(activity);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.custompopupcovidafrica);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

        final TextView countryName = dialog.findViewById(R.id.nameOfCountry);
        final TextView totalConfirmed = dialog.findViewById(R.id.totalConfirmed);
        final TextView totalDeath = dialog.findViewById(R.id.totalDeath);
        final TextView totalRecoveries = dialog.findViewById(R.id.totalRecoveries);
        final TextView confirmedCases = dialog.findViewById(R.id.confirmedCases);


        for(int i=0; i<Country.size(); i++){
            if (richName.equals(Country.get(i))){
                countryName.setText(richName);
                totalConfirmed.setText(NumberOfCases.get(i));
                totalDeath.setText(NumberOfDeath.get(i));
                totalRecoveries.setText(NumberOfRecovery.get(i));
                confirmedCases.setText(NumberOfActive.get(i));
            }
        }

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

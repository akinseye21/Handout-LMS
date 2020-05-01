package com.akinseye.ndif_yemmanuel.handout;

import android.content.Context;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Charts.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Charts#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Charts extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public Charts() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Charts.
     */
    // TODO: Rename and change types and number of parameters
    public static Charts newInstance(String param1, String param2) {
        Charts fragment = new Charts();
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
        View v = inflater.inflate(R.layout.fragment_charts, container, false);

        final BarChart barChart = v.findViewById(R.id.bargraph);


        final ArrayList<BarEntry> barEntryCases = new ArrayList<>();
        final ArrayList<BarEntry> barEntryDeaths = new ArrayList<>();
        final ArrayList<BarEntry> barEntryRecovery = new ArrayList<>();
        //array list for states in X axis
        final ArrayList<String> states = new ArrayList<>();

        //get the data set
        final int[] numArray2 = new int[1];
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

                            for(int i=0; i<numArray2[0]; i++){
                                JSONObject counter = jsonArray.getJSONObject(i);

                                String state = counter.getString("dstate");
                                String cases = counter.getString("cases");
                                String death = counter.getString("deaths");
                                String recovery = counter.getString("recovered");

                                barEntryCases.add(new BarEntry(Float.parseFloat(cases), i));
                                barEntryDeaths.add(new BarEntry(Float.parseFloat(death), i));
                                barEntryRecovery.add(new BarEntry(Float.parseFloat(recovery), i));

                                states.add(state);

                            }
                        }
                        catch (JSONException e){
                            e.printStackTrace();
                        }



                        BarDataSet barDataSet = new BarDataSet(barEntryCases, "Cases");
                        barDataSet.setColor(Color.parseColor("#f60300"));


                        BarDataSet barDataSet1 = new BarDataSet(barEntryDeaths, "Death");
                        barDataSet1.setColor(Color.parseColor("#9C27B0"));

                        BarDataSet barDataSet2 = new BarDataSet(barEntryRecovery, "Recovery");
                        barDataSet2.setColor(Color.parseColor("#e241f4"));

                        BarData theData = new BarData(states,barDataSet);




                        barChart.setDescription("Covid-19 cases per states in Nigeria");
                        barChart.setHorizontalScrollBarEnabled(true);
                        barChart.setDescriptionColor(Color.WHITE);

                        theData.setValueTextColor(Color.parseColor("#ffffff"));

                        XAxis xAxis = barChart.getXAxis();
                        //xAxis.setValueFormatter(new IndexAxisValueFormatter(states));
                        //xAxis.setCenterAxisLabels(true);
                        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
                        //xAxis.setGranularity(1);
                        //xAxis.setGranularityEnabled(true);
                        xAxis.setGridColor(Color.WHITE);
                        xAxis.setTextColor(Color.WHITE);
                        xAxis.setDrawGridLines(true);


                        YAxis left = barChart.getAxisLeft();
                        YAxis right = barChart.getAxisRight();
                        left.setDrawGridLines(true); // no grid lines
                        left.setTextColor(Color.WHITE);
                        right.setDrawGridLines(true);
                        right.setTextColor(Color.WHITE);

                        barChart.setData(theData);
                        barChart.setTouchEnabled(true);
                        barChart.setDragEnabled(true);
                        barChart.setScaleEnabled(true);
                        barChart.setVisibleXRangeMaximum(10);
                        barChart.isDrawBarShadowEnabled();
                        barChart.setScrollBarStyle(View.SCROLLBARS_OUTSIDE_OVERLAY);
                        barChart.setScrollBarSize(100);
                        barChart.setHorizontalScrollBarEnabled(true);


                        barChart.invalidate();

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        queue.add(stringRequest);

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

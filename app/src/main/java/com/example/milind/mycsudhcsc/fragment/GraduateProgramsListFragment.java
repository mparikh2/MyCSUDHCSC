package com.example.milind.mycsudhcsc.fragment;

import android.content.ClipData;
import android.os.Bundle;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.milind.mycsudhcsc.R;
import com.example.milind.mycsudhcsc.activity.Programs;
import com.example.milind.mycsudhcsc.activity.Upload;
import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.GenericTypeIndicator;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;


public class GraduateProgramsListFragment extends Fragment {

    private DatabaseReference mDatabase;
    private ListView mUserList;
    ArrayAdapter adapter;
    private String[] items;
    private SearchView searchView;


    //list to store uploads data
    List<Programs> programsList;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        final View view = inflater.inflate(R.layout.fragment_notifications,
                container, false);

        programsList = new ArrayList<>();

        mDatabase = FirebaseDatabase.getInstance().getReferenceFromUrl("https://mycsudhcsc.firebaseio.com/GraduateProgramsList");
        //mDatabase = FirebaseDatabase.getInstance().getReference("mycsudhcsc");
        mUserList = (ListView) view.findViewById(R.id.user_list);

        FirebaseListAdapter<String> firebaseListAdapter = new FirebaseListAdapter<String>(
                getActivity(),
                String.class,
                        android.R.layout.simple_list_item_1,
                        mDatabase
                ) {
            @Override
            protected void populateView(View v, String model, int position) {

                TextView textView = (TextView) v.findViewById(android.R.id.text1);
                textView.setText(model);
            }
        };

        mUserList.setAdapter(firebaseListAdapter);

//        mDatabase.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
//                    Programs programs = postSnapshot.getValue(Programs.class);
//                    programsList.add(programs);
//                }
//
//                String[] GraduateProgramsList = new String[programsList.size()];
//
//                for (int i = 0; i < GraduateProgramsList.length; i++) {
//                    GraduateProgramsList[i] = programsList.get(i).getId();
//                }
//
//                //displaying it to list
//                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity().getApplicationContext(), android.R.layout.simple_list_item_1, GraduateProgramsList);
//                mUserList.setAdapter(adapter);
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//                System.out.println("The read failed: " + databaseError.getMessage());
//            }
//        });

        return view;
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getActivity().getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        MenuItem item = menu.findItem(R.id.action_search);
        searchView = (SearchView) item.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);

                return false;
            }
        });
        super.onCreateOptionsMenu(menu,inflater);
        return false;
    }
}


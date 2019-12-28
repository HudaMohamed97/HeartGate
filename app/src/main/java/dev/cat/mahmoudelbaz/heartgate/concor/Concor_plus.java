package dev.cat.mahmoudelbaz.heartgate.concor;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import dev.cat.mahmoudelbaz.heartgate.R;
import dev.cat.mahmoudelbaz.heartgate.home.Child_item;
import dev.cat.mahmoudelbaz.heartgate.home.ListAdapter;
import dev.cat.mahmoudelbaz.heartgate.home.Menu_item;

public class Concor_plus extends AppCompatActivity {
    ListAdapter listAdapter;
    RecyclerView expListView;
    List<Menu_item> listDataHeader;
    SharedPreferences shared;
    String userID;
    private int lastExpandedPosition = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_concor_plus);
        shared = getSharedPreferences("id", Context.MODE_PRIVATE);
        userID = shared.getString("id", "0");
        expListView = findViewById(R.id.lvExp);
        prepareListData();
        expListView.setHasFixedSize(true);
        expListView.setLayoutManager(new LinearLayoutManager(this));
        listAdapter = new ListAdapter(this, listDataHeader);
        expListView.setAdapter(listAdapter);
        /*expListView.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {
            @Override
            public boolean onChildClick(ExpandableListView expandableListView, View view, int groupPosition, int childPosition, long id) {
                Intent i;
                if (groupPosition == 0) {
                    switch (childPosition) {
                        case 0:
                            i = new Intent(Concor_plus.this, MyProfile.class);
                            startActivity(i);
                            break; // optional

                        case 1:
                            i = new Intent(Concor_plus.this, NearByDrs.class);
                            startActivity(i);
                            break; // optional


                        case 2:
                            i = new Intent(Concor_plus.this, ConnectionsTabs.class);
                            startActivity(i);
                            break; // optional

                        case 3:
                            // i = new Intent(Concor_plus.this, Favourites.class);
                            i = new Intent(Concor_plus.this, ConnectionsTabs.class);
                            startActivity(i);
                            break; // optional

                        case 4:
                            i = new Intent(Concor_plus.this, Calender.class);
                            startActivity(i);
                            break; // optional

                        default: // Optional
                            // Statements
                    }

                } else if (groupPosition == 1) {

                    switch (childPosition) {
                        case 0:
                            i = new Intent(Concor_plus.this, Concor_plus.class);
                            startActivity(i);
                            break; // optional

                        case 1:
                            i = new Intent(Concor_plus.this, Concor_plus.class);
                            startActivity(i);
                            break; // optional

                        case 2:
                            i = new Intent(Concor_plus.this, ConcorPrice.class);
                            startActivity(i);
                            break; // optional

                        default: // Optional
                            // Statements
                    }

                } else if (groupPosition == 2) {

                    switch (childPosition) {
                        case 0:
                            i = new Intent(Concor_plus.this, CardioUpdates.class);
                            startActivity(i);
                            break; // optional

                        case 1:
                            i = new Intent(Concor_plus.this, OnlineLibrary.class);
                            startActivity(i);
                            break; // optional

                        default: // Optional
                            // Statements
                    }

                } else if (groupPosition == 3) {

                    switch (childPosition) {
                        case 0:
                            i = new Intent(Concor_plus.this, BMI.class);
                            startActivity(i);
                            break; // optional

                        case 1:
                            i = new Intent(Concor_plus.this, CardioRiskFactor.class);
                            startActivity(i);
                            break; // optional

                        default: // Optional
                            // Statements
                    }

                } else if (groupPosition == 4) {

                    switch (childPosition) {
                        case 0:
                            i = new Intent(Concor_plus.this, Questions.class);
                            startActivity(i);
                            break; // optional

                        default: // Optional
                            // Statements
                    }

                } else if (groupPosition == 5) {

                    switch (childPosition) {
                        case 0:
                            i = new Intent(Concor_plus.this, DrugInteractions.class);
                            startActivity(i);
                            break; // optional

                        default: // Optional
                            // Statements
                    }

                } else if (groupPosition == 6) {

                    switch (childPosition) {
                        case 0:
                            i = new Intent(Concor_plus.this, Survey.class);
                            startActivity(i);
                            break; // optional

                        default: // Optional
                            // Statements
                    }

                } *//*else if (groupPosition == 7) {

                    i = new Intent(Concor_plus.this, LoginActivity.class);
                    startActivity(i);
                    finish();

                }*//*


                return true;
            }
        });

*/
    }

    /*
     * Preparing the list data
     */
    private void prepareListData() {
        listDataHeader = new ArrayList<>();
        listDataHeader.add(new Menu_item("Composition And Pharmaceutical Form", R.drawable.white_frame, R.color.transparent));
        listDataHeader.add(new Menu_item("Indication", R.drawable.white_frame, R.color.transparent));
        listDataHeader.add(new Menu_item("Dosage / Administration", R.drawable.white_frame, R.color.transparent));
        listDataHeader.add(new Menu_item("Contraindications / Interaction", R.drawable.white_frame, R.color.transparent));
        listDataHeader.add(new Menu_item("Composition And Pharmaceutical Form", R.drawable.white_frame, R.color.transparent));
        listDataHeader.add(new Menu_item("Undesirable Effects / Effects On Ability To Drive And Use Machines", R.drawable.white_frame, R.color.transparent));
        listDataHeader.add(new Menu_item("Overdose", R.drawable.white_frame, R.color.transparent));

        // Adding child data
        List<Child_item> Composition = new ArrayList<>();
        Composition.add(new Child_item(getString(R.string.Composition_plus), R.drawable.accountbg));


        List<Child_item> Indication = new ArrayList<Child_item>();
        Composition.add(new Child_item(getString(R.string.Indication_plus), R.drawable.accountbg));


        List<Child_item> Dosage = new ArrayList<Child_item>();
        Dosage.add(new Child_item(getString(R.string.Dosage_plus), R.drawable.heartpressbg));


        List<Child_item> Contraindications = new ArrayList<>();
        Contraindications.add(new Child_item(getString(R.string.Contraindications_plus), R.drawable.medicalstaticsbg));


        List<Child_item> Warnings = new ArrayList<Child_item>();
        Warnings.add(new Child_item(getString(R.string.Composition_plus), R.drawable.advisebg));


        List<Child_item> Effects = new ArrayList<Child_item>();
        Effects.add(new Child_item(getString(R.string.Effects_plus), R.drawable.pullbg));

        List<Child_item> Overdose = new ArrayList<Child_item>();
        Overdose.add(new Child_item(getString(R.string.Overdose_plus), R.drawable.pullbg));


    }
}


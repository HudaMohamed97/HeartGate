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
import dev.cat.mahmoudelbaz.heartgate.home.ListAdapter;
import dev.cat.mahmoudelbaz.heartgate.home.Menu_item;

public class Concor extends AppCompatActivity {

    ListAdapter listAdapter;
    RecyclerView expListView;
    List<Menu_item> listDataHeader;
    SharedPreferences shared;
    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_concor);
        shared = getSharedPreferences("id", Context.MODE_PRIVATE);
        userID = shared.getString("id", "0");
        expListView = findViewById(R.id.lvExp);
        prepareListData();
        expListView.setHasFixedSize(true);
        expListView.setLayoutManager(new LinearLayoutManager(this));
        listAdapter = new ListAdapter(this, listDataHeader);
        expListView.setAdapter(listAdapter);
    }

    private void prepareListData() {
        listDataHeader = new ArrayList<>();
        listDataHeader.add(new Menu_item("Composition And Pharmaceutical Form", R.drawable.white_frame, R.color.transparent, "file:///android_asset/composition_pharma.html"));
        listDataHeader.add(new Menu_item("Indication", R.drawable.white_frame, R.color.transparent, "file:///android_asset/indication.html"));
        listDataHeader.add(new Menu_item("Dosage / Administration", R.drawable.white_frame, R.color.transparent, "file:///android_asset/dosage.html"));
        listDataHeader.add(new Menu_item("Contraindications / Interaction", R.drawable.white_frame, R.color.transparent, "file:///android_asset/contra.html"));
        listDataHeader.add(new Menu_item("Warnings And Precautions", R.drawable.white_frame, R.color.transparent, "file:///android_asset/warnings.html"));
        listDataHeader.add(new Menu_item("Pregnancy And Lactation", R.drawable.white_frame, R.color.transparent, "file:///android_asset/warnings.html"));
        listDataHeader.add(new Menu_item("Undesirable Effects / Effects On Ability To Drive And Use Machines", R.drawable.white_frame, R.color.transparent, "file:///android_asset/composition_pharma.html"));
        listDataHeader.add(new Menu_item("Overdose", R.drawable.white_frame, R.color.transparent, "file:///android_asset/overdose.html"));
        listDataHeader.add(new Menu_item("Properties/ Effects", R.drawable.white_frame, R.color.transparent, "file:///android_asset/composition_pharma.html"));
        listDataHeader.add(new Menu_item("Pharmacokinetics", R.drawable.white_frame, R.color.transparent, "file:///android_asset/composition_pharma.html"));
        listDataHeader.add(new Menu_item("Preclinical Data", R.drawable.white_frame, R.color.transparent, "file:///android_asset/composition_pharma.html"));


        /*
        listDataChild.put(listDataHeader.get(0), Composition); // Header, Child data
        listDataChild.put(listDataHeader.get(1), Indication);
        listDataChild.put(listDataHeader.get(2), Dosage);
        listDataChild.put(listDataHeader.get(3), Contraindications);
        listDataChild.put(listDataHeader.get(4), Warnings);
        listDataChild.put(listDataHeader.get(5), Pregnancy);
        listDataChild.put(listDataHeader.get(6), Effects);
        listDataChild.put(listDataHeader.get(7), Overdose);
        listDataChild.put(listDataHeader.get(8), Properties);
        listDataChild.put(listDataHeader.get(9), Pharmacokinetics);
        listDataChild.put(listDataHeader.get(10), Preclinical);
*/


    }
}

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

public class Concor_plus extends AppCompatActivity {
    ListAdapter listAdapter;
    RecyclerView expListView;
    List<Menu_item> listDataHeader;
    SharedPreferences shared;
    String userID;

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
    }

    private void prepareListData() {
        listDataHeader = new ArrayList<>();
        listDataHeader.add(new Menu_item("Composition And Pharmaceutical Form", R.drawable.white_frame, R.color.transparent, "file:///android_asset/composition_pharma.html"));
        listDataHeader.add(new Menu_item("Indication", R.drawable.white_frame, R.color.transparent, "file:///android_asset/indication.html"));
        listDataHeader.add(new Menu_item("Dosage / Administration", R.drawable.white_frame, R.color.transparent, "file:///android_asset/dosage.html"));
        listDataHeader.add(new Menu_item("Contraindications / Interaction", R.drawable.white_frame, R.color.transparent, "file:///android_asset/contra.html"));
        listDataHeader.add(new Menu_item("Composition And Pharmaceutical Form", R.drawable.white_frame, R.color.transparent, "file:///android_asset/composition_pharma.html"));
        listDataHeader.add(new Menu_item("Undesirable Effects / Effects On Ability To Drive And Use Machines", R.drawable.white_frame, R.color.transparent, "file:///android_asset/undesirable_fx.html"));
        listDataHeader.add(new Menu_item("Overdose", R.drawable.white_frame, R.color.transparent, "file:///android_asset/overdose.html"));
    }
}

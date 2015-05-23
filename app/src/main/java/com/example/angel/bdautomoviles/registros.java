package com.example.angel.bdautomoviles;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import com.example.angel.bdautomoviles.adaptadores.autosAdaptador;
import com.example.angel.bdautomoviles.modelos.AdminSQLiteOpenHelper;
import com.example.angel.bdautomoviles.modelos.autos;

import java.util.ArrayList;
import java.util.List;


public class registros extends ActionBarActivity {

    private RecyclerView recycler;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager lmanager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registros);

        List<autos> items = new ArrayList<>();
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "autos", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();

        Cursor fila = bd.rawQuery("select n_serie, marca, modelo, color from autos", null);

        for (fila.moveToFirst(); !fila.isAfterLast(); fila.moveToNext()){
            items.add(new autos(fila.getString(0), fila.getString(1), fila.getString(2), (fila.getString(3))));
        }

        recycler = (RecyclerView) findViewById(R.id.r_autos);
        recycler.setHasFixedSize(true);

        lmanager = new LinearLayoutManager(this);
        recycler.setLayoutManager(lmanager);

        adapter = new autosAdaptador(items);
        recycler.setAdapter(adapter);
    }
}

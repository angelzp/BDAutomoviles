package com.example.angel.bdautomoviles;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.angel.bdautomoviles.modelos.AdminSQLiteOpenHelper;

public class MainActivity extends ActionBarActivity {

    EditText et_serie, et_marca, et_modelo, et_color;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_serie = (EditText) findViewById(R.id.et_serie);
        et_marca = (EditText) findViewById(R.id.et_marca);
        et_modelo = (EditText) findViewById(R.id.et_modelo);
        et_color = (EditText) findViewById(R.id.et_color);
    }

    public void alta(View v) {
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "autos", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();

        String serie = et_serie.getText().toString();
        String marca = et_marca.getText().toString();
        String modelo = et_modelo.getText().toString();
        String color = et_color.getText().toString();

        Cursor fila = bd.rawQuery("select marca, modelo, color from autos where n_serie=" + serie, null);
        if (fila.getCount() >= 1){
            Toast.makeText(this, "No se puede agregar. Existe la serie", Toast.LENGTH_SHORT).show();
        } else {

            ContentValues registro = new ContentValues();

            registro.put("n_serie", serie);
            registro.put("marca", marca);
            registro.put("modelo", modelo);
            registro.put("color", color);

            bd.insert("autos", null, registro);
            bd.close();

            et_serie.setText("");
            et_marca.setText("");
            et_modelo.setText("");
            et_color.setText("");

            Toast.makeText(this, "Se agrego un nuevo auto", Toast.LENGTH_SHORT).show();
        }
    }

    public void consulta(View v){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "autos", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();

        String serie = et_serie.getText().toString();

        Cursor fila = bd.rawQuery("select marca, modelo, color from autos where n_serie=" + serie, null);

        if (fila.moveToFirst()){
            et_marca.setText(fila.getString(0));
            et_modelo.setText(fila.getString(1));
            et_color.setText(fila.getString(2));

        } else {
            Toast.makeText(this, "No existe el auto", Toast.LENGTH_SHORT).show();
        }
        bd.close();
    }

    public void baja(View v){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "autos", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();

        String serie = et_serie.getText().toString();

        int cant = bd.delete("autos", "n_serie=" + serie, null);
        bd.close();

        et_serie.setText("");
        et_marca.setText("");
        et_modelo.setText("");
        et_color.setText("");

        if (cant == 1){
            Toast.makeText(this, "Se borro el auto", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "No existe el auto", Toast.LENGTH_SHORT).show();
        }
    }

    public void modificacion(View v){
        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this, "autos", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();

        String serie = et_serie.getText().toString();
        String marca = et_marca.getText().toString();
        String modelo = et_modelo.getText().toString();
        String color = et_color.getText().toString();

        ContentValues registro = new ContentValues();

        registro.put("n_serie", serie);
        registro.put("marca", marca);
        registro.put("modelo", modelo);
        registro.put("color", color);

        int cant = bd.update("autos", registro, "n_serie=" + serie, null);
        bd.close();

        if (cant == 1){
            Toast.makeText(this, "Se modificaron los datos del auto", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "No existe el auto", Toast.LENGTH_SHORT).show();
        }
    }

    public void limpia(View v){
        et_serie.setText("");
        et_marca.setText("");
        et_modelo.setText("");
        et_color.setText("");
    }

    public void buscar(View v){
        Intent a = new Intent(this, registros.class);
        startActivity(a);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}

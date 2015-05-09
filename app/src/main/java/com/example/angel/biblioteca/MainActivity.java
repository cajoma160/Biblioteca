package com.example.angel.biblioteca;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;

import android.widget.EditText;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import android.view.View;

import android.widget.Toast;

public class MainActivity extends ActionBarActivity {

    private EditText tex1, tex2, tex3,tex4,tex5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tex1 = (EditText) findViewById(R.id.et_dni);
        tex2 = (EditText) findViewById(R.id.et_nombre);
        tex3 = (EditText) findViewById(R.id.et_numero);
        tex4= (EditText) findViewById(R.id.et_libro);
        tex5= (EditText) findViewById(R.id.et_fecha);
    }

    public void alta(View v) {
        AdmiSQLiteOpenHelper admin = new AdmiSQLiteOpenHelper(this,"administracion", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();

        String dni = tex1.getText().toString();
        String nombre = tex2.getText().toString();
        String numero = tex3.getText().toString();
        String nombre_li = tex4.getText().toString();
        String fecha = tex5.getText().toString();

        ContentValues registro = new ContentValues();  //es una clase para guardar datos
        registro.put("dni", dni);
        registro.put("nombre", nombre);
        registro.put("numero", numero);
        registro.put("nombre_li", nombre_li);
        registro.put("fecha", fecha);

        bd.insert("biblioteca", null, registro);
        bd.close();
        tex1.setText("");
        tex2.setText("");
        tex3.setText("");
        tex4.setText("");
        tex5.setText("");


        Toast.makeText(this, "Registro Guardado",
                Toast.LENGTH_SHORT).show();

    }

    public void consulta(View v) {
        AdmiSQLiteOpenHelper admin = new AdmiSQLiteOpenHelper(this,
                "administracion", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase(); //Create and/or open a database that will be used for reading and writing.
        String dni = tex1.getText().toString();

        Cursor fila = bd.rawQuery(  //devuelve 0 o 1 fila //es una consulta
                "select nombre,numero,nombre_li,fecha  from biblioteca where dni=" + dni, null);
        if (fila.moveToFirst()) {  //si ha devuelto 1 fila, vamos al primero (que es el unico)
            tex2.setText(fila.getString(0));
            tex3.setText(fila.getString(1));
            tex4.setText(fila.getString(2));
            tex5.setText(fila.getString(3));

        } else
            Toast.makeText(this, "No existe el libro" ,
                    Toast.LENGTH_SHORT).show();
        bd.close();

    }

    public void baja(View v) {
        AdmiSQLiteOpenHelper admin = new AdmiSQLiteOpenHelper(this,
                "administracion", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String dni = tex1.getText().toString();
        int cant = bd.delete("biblioteca", "dni=" + dni, null); // (votantes es la nombre de la tabla, condición)
        bd.close();
        tex1.setText("");
        tex2.setText("");
        tex3.setText("");
        tex4.setText("");
        tex5.setText("");

        if (cant == 1)
            Toast.makeText(this, "Se borró el libro",
                    Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(this, "No existe el libro",
                    Toast.LENGTH_SHORT).show();
    }

    public void modificacion(View v) {
        AdmiSQLiteOpenHelper admin = new AdmiSQLiteOpenHelper(this,
                "administracion", null, 1);
        SQLiteDatabase bd = admin.getWritableDatabase();
        String dni = tex1.getText().toString();
        String nombre = tex2.getText().toString();
        String numero = tex3.getText().toString();
        String nombre_li = tex4.getText().toString();
        String fecha = tex4.getText().toString();

        ContentValues registro = new ContentValues();
        registro.put("nombre", nombre);
        registro.put("numero", numero);
        registro.put("nombre_li", nombre_li);
        registro.put("fecha", fecha);

        int cant = bd.update("biblioteca", registro, "dni=" + dni, null);
        bd.close();
        if (cant == 1)
            Toast.makeText(this, "se modificaron los datos", Toast.LENGTH_SHORT)
                    .show();
        else
            Toast.makeText(this, "no existe el libro",
                    Toast.LENGTH_SHORT).show();




    }
}






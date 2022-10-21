package com.cdp.agenda;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.cdp.agenda.db.DbCliente;
import com.cdp.agenda.entidades.CLiente;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class VerCliente extends AppCompatActivity  {

    EditText txtnombre, txtfechaNacimiento, txtapellido, txttelefono, txtcorreo, txtedad, txtcedula;
    Button btnEditar;
    FloatingActionButton fabEditar, fabEliminar;
    CLiente cliente ;
    int id = 0;
    boolean correcto = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ver_cliente);

        txtnombre = findViewById(R.id.txtNombre);
        txtapellido = findViewById(R.id.txtApellido);
        txttelefono = findViewById(R.id.txtTelefono);
        txtcorreo = findViewById(R.id.txtCorreoElectronico);
        txtfechaNacimiento = findViewById(R.id.txtfechaNacimiento);
        txtcedula = findViewById(R.id.txtCedula);
        txtedad = findViewById(R.id.txtEdad);

        btnEditar = findViewById(R.id.btnGuarda);
        btnEditar.setVisibility(View.INVISIBLE);
        fabEditar = findViewById(R.id.fabmodificar);
        fabEliminar = findViewById(R.id.fabEliminar);

        if (savedInstanceState == null) {
            Bundle extras = getIntent().getExtras();
            if (extras == null) {
                id = Integer.parseInt(null);
            } else {
                id = extras.getInt("cli_id");
            }
        } else {
            id = (int) savedInstanceState.getSerializable("cli_id");
        }
        DbCliente dbCliente = new DbCliente(VerCliente.this);
        cliente = dbCliente.verclientes(id);

        if (cliente != null) {

            txtnombre.setText(cliente.getNombre());
            txtapellido.setText(cliente.getApellido());
            txtcedula.setText(cliente.getCedula());
            txttelefono.setText(cliente.getTelefono());
            txtcorreo.setText(cliente.getCorreo_electornico());
            txtfechaNacimiento.setText(cliente.getFechaNacimiento());
            txtedad.setInputType(cliente.getEdad());

            txtnombre.setInputType(InputType.TYPE_NULL);
            txtapellido.setInputType(InputType.TYPE_NULL);
            txtcedula.setInputType(InputType.TYPE_NULL);
            txttelefono.setInputType(InputType.TYPE_NULL);
            txtcorreo.setInputType(InputType.TYPE_NULL);
            txtfechaNacimiento.setInputType(InputType.TYPE_NULL);
            txtedad.setInputType(InputType.TYPE_NULL);

            btnEditar.setVisibility(View.INVISIBLE);
            correcto = true;
        }

        fabEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(VerCliente.this, modificar.class);
                intent.putExtra("cli_id", id);
                startActivity(intent);
            }
        });


        fabEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(VerCliente.this);
                builder.setMessage("¿Desea eliminar este contaco?").setPositiveButton("SI", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if (dbCliente.Eliminar(id)) {
                            regresar();
                        }
                    }
                }).setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        regresar();
                    }
                }).show();
            }
        });
    }

    private void regresar() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
package com.cdp.agenda;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.cdp.agenda.db.DbCliente;
import com.cdp.agenda.entidades.CLiente;

public class modificar extends AppCompatActivity {

    EditText txtnombre, txtfechaNacimiento, txtapellido, txttelefono, txtcorreo, txtedad, txtcedula;
    TextView viewNombre;
    Button btnEditar;
    CLiente cliente;
    boolean correcto = false;
    int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modificar);

        txtnombre = findViewById(R.id.txtnombre);
        txtapellido = findViewById(R.id.txtApellido);
        txtcedula = findViewById(R.id.txtcedula);
        txttelefono = findViewById(R.id.txttelefono);
        txtcorreo = findViewById(R.id.txtcorreo);
        txtfechaNacimiento = findViewById(R.id.txtfecha);
        txtedad = findViewById(R.id.txtedad);
        btnEditar = findViewById(R.id.btn_editar);

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

        final DbCliente dbCliente = new DbCliente(modificar.this);
        cliente = dbCliente.verclientes(id);

        if (cliente != null) {

            txtnombre.setText(cliente.getNombre());
            txtapellido.setText(cliente.getApellido());
            txtcedula.setText(cliente.getCedula());
            txttelefono.setText(cliente.getTelefono());
            txtcorreo.setText(cliente.getCorreo_electornico());
            txtfechaNacimiento.setText(cliente.getFechaNacimiento());
            txtedad.setInputType(cliente.getEdad());
        }

        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!txtnombre.getText().toString().equals("") && !txtapellido.getText().toString().equals("")  && !txtfechaNacimiento.getText().toString().equals("")) {
                    DbCliente cliente = new DbCliente(modificar.this);
                    correcto = dbCliente.editarContacto(id, txtnombre.getText().toString(), txtapellido.getText().toString(), txtedad.getText().toString(), txtcedula.getText().toString(), txtcorreo.getText().toString(), txttelefono.getText().toString(), txtfechaNacimiento.getText().toString());

                    if (correcto) {
                        Toast.makeText(modificar.this, "DATO MODIFICADO CORRECTAMENTE", Toast.LENGTH_SHORT).show();
                        menuprincipal();
                    } else {
                        Toast.makeText(modificar.this, "ERROR AL MODIFICAR DATO", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(modificar.this, "LLENA LOS DATOS COMPLETAMENTE", Toast.LENGTH_SHORT).show();
                }
            }

            private void menuprincipal() {
                Intent intent = new Intent(modificar.this, VerCliente.class);
                intent.putExtra("cli_id", id);
                startActivity(intent);
            }
        });
    }
}
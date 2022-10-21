package com.cdp.agenda;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.cdp.agenda.db.DbCliente;

public class NuevoActivity extends AppCompatActivity {

     TextView viewCedula, viewNombre, viewApellido, viewTelefono, viewfechaNacimiento;
     EditText txtNombre, txtCedula, txtApellido, txtTelefono, txtCorreoElectronico, txtEdad, txtfechaNacimiento;
     Button btnGuarda;
     String html = "";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nuevo);
    /*
        html = "<span style='color:red;'>*</span>";
        viewCedula.setText(Html.fromHtml(viewCedula.getText().toString() + html));
        viewNombre.setText(Html.fromHtml(viewNombre.getText().toString() + html));
        viewTelefono.setText(Html.fromHtml(viewTelefono.getText().toString() + html));
        viewApellido.setText(Html.fromHtml(viewApellido.getText().toString() + html));

        viewCedula = findViewById(R.id.viewCedula);
        viewNombre = findViewById(R.id.viewNombre);
        viewTelefono = findViewById(R.id.viewTelefono);
        viewApellido = findViewById(R.id.viewApellido);
*/
        txtCedula = findViewById(R.id.txtCedula);
        txtNombre = findViewById(R.id.txtNombre);
        txtApellido = findViewById(R.id.txtApellido);
        txtTelefono = findViewById(R.id.txtTelefono);
        txtEdad = findViewById(R.id.txtEdad);
        txtCorreoElectronico = findViewById(R.id.txtCorreoElectronico);
        txtfechaNacimiento = findViewById(R.id.txtfechaNacimiento);

        btnGuarda = findViewById(R.id.btnGuarda);

        btnGuarda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!txtCedula.getText().toString().equals("") && !txtNombre.getText().toString().equals("") && !txtTelefono.getText().toString().equals("") && !txtApellido.getText().toString().equals("") && !txtfechaNacimiento.getText().toString().equals("")) {
                    DbCliente dbclientes = new DbCliente(NuevoActivity.this);
                    long id = dbclientes.insertarcliente(txtNombre.getText().toString(), txtApellido.getText().toString(), txtCedula.getText().toString(), txtTelefono.getText().toString(), txtCorreoElectronico.getText().toString(), txtfechaNacimiento.getText().toString(),Integer.parseInt(txtEdad.getText().toString()));

                    if (id > 0) {
                        Toast.makeText(NuevoActivity.this, "REGISTRO GUARDADO", Toast.LENGTH_LONG).show();
                        limpiar();
                        regresar();
                    } else {
                        Toast.makeText(NuevoActivity.this, "ERROR AL GUARDAR REGISTRO", Toast.LENGTH_LONG).show();
                    }
                } else {
                    Toast.makeText(NuevoActivity.this, "DEBE LLENAR LOS CAMPOS OBLIGATORIOS", Toast.LENGTH_LONG).show();

                }
            }
        });
    }

    private void limpiar() {
        setResult(RESULT_OK);
        finish();
        txtCedula.setText("");
        txtNombre.setText("");
        txtApellido.setText("");
        txtTelefono.setText("");
        txtCorreoElectronico.setText("");
        txtfechaNacimiento.setText("");
        txtEdad.setText("");
    }
    private void regresar(){
        Intent intent = new Intent(NuevoActivity.this, MainActivity.class);
        startActivity(intent);
    }
}
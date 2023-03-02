package xyz.abelgomez.truekshop;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class activity_sign_up extends AppCompatActivity {
    TextView nuevoUsuario, continuarLabel;
    ImageView signUpImageView;
    TextInputLayout usuarioSignUpTextField, contrasenaTextField;
    MaterialButton inicioSesion;





    TextInputEditText emailEditText, passwordEditText, cofirmarPasswordEditText;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);



        signUpImageView = findViewById(R.id.signUpImageView);
//        bienvenidoLabel = findViewById(R.id.bienvenidoLabel);
        continuarLabel = findViewById(R.id.continuarLabel);
        usuarioSignUpTextField = findViewById(R.id.usuarioSignUpTextField);
        contrasenaTextField = findViewById(R.id.contrasenaTextField);
        inicioSesion = findViewById(R.id.inicioSesion);
        nuevoUsuario = findViewById(R.id.nuevoUsuario);


        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        cofirmarPasswordEditText = findViewById(R.id.comfirmarPasswordEditText);




        nuevoUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                transitionBack();
            }
        });

        ////spinner

        Spinner spinner = (Spinner) findViewById(R.id.my_spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.spinner_options, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                String selectedItem = adapterView.getItemAtPosition(position).toString();
                // hacer algo con el elemento seleccionado
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                // hacer algo cuando no hay nada seleccionado
            }
        });






    }









    public void transitionBack() {
        Intent intent = new Intent(activity_sign_up.this, LoginActivity.class);

        Pair[] pairs = new Pair[6];
        pairs[0] = new Pair<View, String>(signUpImageView, "logoImageTrans");
//        pairs[1] = new Pair<View, String>(bienvenidoLabel, "textTrans");
        pairs[1] = new Pair<View, String>(continuarLabel, "iniciaSesionTextTrans");
        pairs[2] = new Pair<View, String>(usuarioSignUpTextField, "emailInputTextTrans");
        pairs[3] = new Pair<View, String>(contrasenaTextField, "passwordInputTextTrans");
        pairs[4] = new Pair<View, String>(inicioSesion, "buttonSignInTrans");
        pairs[5] = new Pair<View, String>(nuevoUsuario, "newUserTrans");

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(activity_sign_up.this, pairs);
            startActivity(intent, options.toBundle());
        } else {
            startActivity(intent);
            finish();
        }
    }
}
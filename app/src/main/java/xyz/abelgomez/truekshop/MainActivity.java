package xyz.abelgomez.truekshop;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);


        //agregar animaciones xd

        Animation animation1 = AnimationUtils.loadAnimation(this,R.anim.desplazamiento_derecha);
        Animation animation2 = AnimationUtils.loadAnimation(this,R.anim.desplazamiento_abajo);
        Animation animation3 = AnimationUtils.loadAnimation(this,R.anim.desplazamiento_arriba);


        TextView detextTextView = findViewById(R.id.detextView);
        TextView ventacompraTextView = findViewById(R.id.ventacompratextView);
        TextView prodectosagricolasTextView = findViewById(R.id.prodectosagricolastextView);
        ImageView logoImageView = findViewById(R.id.logoimageView);

        detextTextView.setAnimation(animation2);
        prodectosagricolasTextView.setAnimation(animation2);
        logoImageView.setAnimation(animation1);
        ventacompraTextView.setAnimation(animation3);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this,LoginActivity.class);
              startActivity(intent);
              finish();

            }
        },5000);


    }
}
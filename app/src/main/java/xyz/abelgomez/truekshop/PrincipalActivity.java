package xyz.abelgomez.truekshop;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import xyz.abelgomez.truekshop.databinding.ActivityMainBinding;
import xyz.abelgomez.truekshop.databinding.ActivityPrincipalBinding;

public class PrincipalActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private DrawerLayout drawerLayout;
//    @SuppressLint("NonConstantResourceId")
//    @BindView(R.id.mRecyclerView)
//    RecyclerView mRecyclerView;
//    MovieAdapter mMovieAdapter;
//
//    LinearLayoutManager mLayoutManager;


   // ActivityMainBinding binding;
    ActivityPrincipalBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityPrincipalBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());








        replaceFragment(new PrincipalFragment());
        binding.bottomNavigationView.setBackground(null);
        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.home:
                    replaceFragment(new PrincipalFragment());
                    break;
                case R.id.Compras:
                    replaceFragment(new ComprasFragment());
                    break;
                case R.id.datosusuario:
                    replaceFragment(new DatosUsuarioFragment());
                    break;
                case R.id.Ajustes:
                    replaceFragment(new AjustesFragment());
                    break;
            }
            return true;

        });




        ////
        // Inicializar Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);

        // Establecer Toolbar como la action bar de la actividad
        setSupportActionBar(toolbar);
        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_nav,
                R.string.close_nav);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();






    }





    private void replaceFragment(Fragment fragment) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, fragment);
        fragmentTransaction.commit();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.nav_crearprodecto:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new NewProducto()).commit();
                break;
            case R.id.nav_sobrenosotros:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new DatosUsuarioFragment()).commit();
                break;

            case R.id.nav_logout:
                Toast.makeText(this, "Logout!", Toast.LENGTH_SHORT).show();
                break;
        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }



}
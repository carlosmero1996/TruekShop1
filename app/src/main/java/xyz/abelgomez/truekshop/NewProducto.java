package xyz.abelgomez.truekshop;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.NumberPicker;
import android.widget.Spinner;
import android.widget.TextView;


public class NewProducto extends Fragment implements NumberPicker.OnValueChangeListener{

    private ImageView imageView;
    private TextView textView;
    private NumberPicker numberPicker;
    private Spinner opcionescategoria;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        View view = inflater.inflate(R.layout.fragment_new_producto, container, false);

        imageView = view.findViewById(R.id.iv_visor);
        numberPicker = view.findViewById(R.id.numberPicker);
        numberPicker.setMaxValue(9999);
        numberPicker.setMinValue(0);
        numberPicker.setValue(5);




        opcionescategoria = view.findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter= ArrayAdapter.createFromResource(getActivity(),R.array.opcionescategoria, android.R.layout.simple_spinner_item);
        opcionescategoria.setAdapter(adapter);


        numberPicker.setOnValueChangedListener(this);

        view.findViewById(R.id.bsubirimagen).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cargarimagen();
            }
        });

        return view;
//        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_new_producto, container, false);
    }

    @SuppressWarnings("deprecation")
    private void cargarimagen() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        intent.setType("image/");
        startActivityForResult(Intent.createChooser(intent, "Seleccione en la aplicacion"), 10);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            Uri path = data.getData();
            imageView.setImageURI(path);
        }
    }

    @Override
    public void onValueChange(NumberPicker numberPicker, int oldValue, int newValue) {

    }
}
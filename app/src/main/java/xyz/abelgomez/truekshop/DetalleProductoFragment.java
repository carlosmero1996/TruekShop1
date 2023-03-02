package xyz.abelgomez.truekshop;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import xyz.abelgomez.truekshop.model.Producto;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DetalleProductoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DetalleProductoFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public DetalleProductoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment DetalleProductoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DetalleProductoFragment newInstance(String param1, String param2) {
        DetalleProductoFragment fragment = new DetalleProductoFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detalle_producto, container, false);

        // Obtener el producto seleccionado del argumento de bundle
        Producto selectedProduct = (Producto) getArguments().getSerializable("producto");

        // Obtener referencias a los elementos de la vista

        ImageView imageView = view.findViewById(R.id.imageView);
        // Cargar la imagen con Glide
        Glide.with(this)
                .load(selectedProduct.getImagenBytes())
                .into(imageView);
        TextView nombreTextView = view.findViewById(R.id.nombreTextView);
        TextView precioTextView = view.findViewById(R.id.precioTextView);
        TextView descripcionTextView = view.findViewById(R.id.descripcionTextView);







        nombreTextView.setText(selectedProduct.getNombre());
        precioTextView.setText(Double.toString(selectedProduct.getPrecio()));
        descripcionTextView.setText(selectedProduct.getDescripcion());

        return view;
    }
}
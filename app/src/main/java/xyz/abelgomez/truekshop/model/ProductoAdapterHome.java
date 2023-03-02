package xyz.abelgomez.truekshop.model;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.Locale;

import xyz.abelgomez.truekshop.R;

public class ProductoAdapterHome extends BaseAdapter {

    private Context context;
    private ArrayList<Producto> productos;

    public ProductoAdapterHome(Context context, ArrayList<Producto> productos) {
        this.context = context;
        this.productos = productos;
    }

    @Override
    public int getCount() {
        return productos.size();
    }

    @Override
    public Object getItem(int position) {
        return productos.get(position);
    }

//    @Override
//    public long getItemId(int position) {
//        return position;
//    }
@Override
public long getItemId(int position) {
    return productos.get(position).getId();
}

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view;

        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.item_producto, null);
        } else {
            view = convertView;
        }

        ImageView imageView = (ImageView) view.findViewById(R.id.imagen);
        TextView nombreTextView = (TextView) view.findViewById(R.id.nombre);
     //   TextView descripcionTextView = (TextView) view.findViewById(R.id.descripcion_text_view);
        TextView precioTextView = (TextView) view.findViewById(R.id.precio);

        Producto producto = productos.get(position);

        Glide.with(context)
                .load(producto.getImagenBytes())
                .centerCrop()
                .placeholder(R.drawable.cremal)
                .into(imageView);

        nombreTextView.setText(producto.getNombre());
       // descripcionTextView.setText(producto.getDescripcion());
        precioTextView.setText(String.format(Locale.getDefault(), "$ %.2f", producto.getPrecio()));

        return view;
    }


//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
//
//        if (convertView == null) {
//            convertView = inflater.inflate(R.layout.item_producto, null);
//        }
//
//        ImageView imagen = convertView.findViewById(R.id.imagen);
//        TextView nombre = convertView.findViewById(R.id.nombre);
//        TextView precio = convertView.findViewById(R.id.precio);
//
//        Producto producto = productos.get(position);
////        imagen.setImageResource(producto.getImagenBytes());
//        // Convertir los bytes de la imagen a un objeto Bitmap
//        byte[] imagenBytes = producto.getImagenBytes();
//        Bitmap bitmap = BitmapFactory.decodeByteArray(imagenBytes, 0, imagenBytes.length);
//
//        // Establecer el objeto Bitmap en el ImageView
//        imagen.setImageBitmap(bitmap);
//        nombre.setText(producto.getNombre());
//        precio.setText("Precio :"+Double.toString(producto.getPrecio()));
//
//        return convertView;
//    }
}

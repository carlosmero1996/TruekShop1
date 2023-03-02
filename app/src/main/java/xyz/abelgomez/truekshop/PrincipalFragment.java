package xyz.abelgomez.truekshop;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import xyz.abelgomez.truekshop.model.DBHelper;
import xyz.abelgomez.truekshop.model.Producto;
import xyz.abelgomez.truekshop.model.ProductoAdapterHome;

import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


public class PrincipalFragment extends Fragment {

    private static final String API_BASE_URL = "http://192.168.18.4:8080/api/";
    private static final String FIRST_TIME_KEY = "firstTime";

    private Context mContext;
    private List<Producto> mProductos = null;
    private DBHelper mDBHelper;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_principal, container, false);

        mContext = getActivity();

        // Obtener referencia a la vista del GridView
        GridView gridView = (GridView) rootView.findViewById(R.id.grid_view);

        // Inicializar el DBHelper
        mDBHelper = new DBHelper(mContext);
        // Obtener los productos desde la base de datos SQLite
        mProductos = mDBHelper.getProductos();

        // Si la lista de productos en la base de datos SQLite está vacía, obtenerlos desde el API REST de Spring
        if (mProductos == null || mProductos.size() == 0) {
            getProductsFromApi();
        } else {
            // Si la lista de productos en la base de datos SQLite no está vacía, mostrarla en el GridView
            ProductoAdapterHome mAdapter = new ProductoAdapterHome(mContext, (ArrayList<Producto>) mProductos);
            gridView.setAdapter(mAdapter);
        }




        return rootView;
    }

    private void getProductsFromApi() {
        StringRequest stringRequest = new StringRequest(
                Request.Method.GET,
                API_BASE_URL + "listarProductos",
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Procesar la respuesta del API REST de Spring
                        try {
                            JSONArray jsonArray = new JSONArray(response);
                            mProductos = new ArrayList<>();
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject jsonObject = jsonArray.getJSONObject(i);
                                Producto producto = new Producto();
                                producto.setId(jsonObject.getInt("id"));
                                producto.setNombre(jsonObject.getString("nombre"));
                                producto.setDescripcion(jsonObject.getString("descripcion"));
                                producto.setPrecio((float) jsonObject.getDouble("precio"));
                                mProductos.add(producto);

                                // Insertar el producto en la base de datos SQLite
                                mDBHelper.insertProducto(producto);
                            }

                            // Mostrar los productos en el GridView
                            ProductoAdapterHome mAdapter = new ProductoAdapterHome(mContext, (ArrayList<Producto>) mProductos);
                            GridView gridView = (GridView) getView().findViewById(R.id.grid_view);
                            gridView.setAdapter(mAdapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // Aquí manejas los errores que puedan ocurrir durante la llamada al API REST de Spring
                        Toast.makeText(mContext, "Error al obtener los productos desde el servidor", Toast.LENGTH_SHORT).show();
                        error.printStackTrace();
                    }
                }
        );
        Volley.newRequestQueue(mContext).add(stringRequest);


    }

    private void clearSQLiteData() {
        mDBHelper.clearProductos();
    }
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        clearSQLiteData();
    }
}















//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        View rootView = inflater.inflate(R.layout.fragment_principal, container, false);
//
//        mContext = getActivity();
//
//
//
//        GridView gridView = (GridView) rootView.findViewById(R.id.grid_view);
//        ProductoAdapterHome mAdapter = new ProductoAdapterHome(mContext, (ArrayList<Producto>) mProductos);
//        gridView.setAdapter(mAdapter);
//
//
//
//
//        ///////////////////////////////////////////////////////////////////
//
//        // Agregar un OnItemClickListener al GridView
//        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
//                // Obtener el producto seleccionado
//                Producto selectedProduct = mProductos.get(position);
//
//                // Crear el fragmento de detalle y pasar el producto seleccionado como argumento
//                DetalleProductoFragment detalleProductoFragment = new DetalleProductoFragment();
//                Bundle args = new Bundle();
//                args.putSerializable("producto", selectedProduct);
//                detalleProductoFragment.setArguments(args);
//
//                // Reemplazar el fragmento actual con el fragmento de detalle
//                FragmentTransaction transaction = getParentFragmentManager().beginTransaction();
//                transaction.replace(R.id.fragment_container, detalleProductoFragment);
//                transaction.addToBackStack(null);
//                transaction.commit();
//            }
//        });
//////////////////////////////////////////////////////////////////
//        return rootView;
//    }


/////////////////////////







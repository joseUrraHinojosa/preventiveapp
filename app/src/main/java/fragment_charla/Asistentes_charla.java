package fragment_charla;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.jose.preventiveapp.R;

import java.util.ArrayList;
import java.util.List;

import dao.IdActualDAO;
import dao.TrabajadoresDAO;
import dto.AdapterAsistentesCharla;
import dto.AdapterRiesgos;
import dto.Trabajadores;


public class Asistentes_charla extends Fragment {

    public Asistentes_charla() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    private List<Trabajadores> traList = new ArrayList<>();
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private AdapterAsistentesCharla adapterAsistentesCharla;
    private ImageView irAtras;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_asistentes_charla, container, false);
        irAtras =(ImageView)root.findViewById(R.id.iv_atras_dg);

        recyclerView =(RecyclerView)root.findViewById(R.id.recyclerAsistentes);

        int id = new IdActualDAO().mostrarIdActual(getContext());
        traList = new TrabajadoresDAO().mostrarTrabajadoresActual(id,getContext());
        layoutManager = new LinearLayoutManager(getContext());
        adapterAsistentesCharla = new AdapterAsistentesCharla(traList,getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapterAsistentesCharla);


        irAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.ct_charla, new Resumen_temas_tratados_charla()).commit();
            }
        });

        return  root;
    }
}
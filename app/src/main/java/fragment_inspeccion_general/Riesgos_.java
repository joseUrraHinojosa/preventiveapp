package fragment_inspeccion_general;

import android.annotation.SuppressLint;
import android.media.Image;
import android.os.Build;
import android.os.Bundle;

import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;

import com.jose.preventiveapp.R;

import java.util.ArrayList;

import dto.AdapterRiesgos;

public class Riesgos_ extends Fragment {

    public Riesgos_() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    private ImageView imgAtras;
    private ArrayList<CheckBox> list_riesgosCheck ;
    private  String[] list_string;
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private AdapterRiesgos adapterRiesgos;
    private CheckBox checkBox;


    @SuppressLint("NewApi")
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_riesgos, container, false);

        imgAtras =(ImageView)root.findViewById(R.id.iv_atras_ce);

        imgAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.ct_inspeccion_general, new Etapas_tarea()).commit();
            }
        });


        recyclerView = root.findViewById(R.id.recycler_riesgos);
        list_string = getActivity().getResources().getStringArray(R.array.riegos_string);

        list_riesgosCheck = new ArrayList<CheckBox>();

        for(int i =0; i<list_string.length; i++){

            list_riesgosCheck.add(checkBox = new CheckBox(getContext()));
            list_riesgosCheck.get(i).setId(i);
            list_riesgosCheck.get(i).setText(list_string[i]);

        }


        layoutManager = new LinearLayoutManager(getContext());
        adapterRiesgos = new AdapterRiesgos(list_riesgosCheck,getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapterRiesgos);



        return root;
    }
}
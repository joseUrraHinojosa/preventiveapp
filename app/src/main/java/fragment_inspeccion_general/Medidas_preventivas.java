package fragment_inspeccion_general;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.jose.preventiveapp.R;
import java.util.ArrayList;
import java.util.List;
import dto.AdapterMedidasPreventivas;
import dto.MedidasPreventivas;

public class Medidas_preventivas extends Fragment  {

    private List<MedidasPreventivas> mPreventivasList = new ArrayList<>();
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;
    private ImageView imgAtras;

    public Medidas_preventivas() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
         mPreventivasList = (List<MedidasPreventivas>) getArguments().getSerializable("mePreventivas");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_medidas_preventivas, container, false);
        recyclerView =(RecyclerView)root.findViewById(R.id.RecyclerMedidas);
        imgAtras =(ImageView)root.findViewById(R.id.iv_atras_ce);

        imgAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.ct_inspeccion_general, new Riesgos_()).commit();
            }
        });


        layoutManager = new LinearLayoutManager(getContext());
        AdapterMedidasPreventivas adapterMedidasPreventivas = new AdapterMedidasPreventivas(mPreventivasList,getContext());
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapterMedidasPreventivas);
        return  root;
    }


}
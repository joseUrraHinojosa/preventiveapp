package fragment_inspeccion_general;


import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.jose.preventiveapp.Inspeccion_general_dt;
import com.jose.preventiveapp.R;

import java.util.ArrayList;
import java.util.List;


public class Seleccion_epp extends Fragment {

    public Seleccion_epp() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private ImageView imgAtras;
    private Button btn_sig;
    private CheckBox epp1,epp2,epp3,epp4,epp5,epp6,epp7,epp8,epp9,epp10,epp11,eppOtros;
    private List<CheckBox> checkBoxList = new ArrayList<>();
    private List<String> epp_list = new ArrayList<>();
    private boolean checadoOtrosEpp =false;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_seleccion_epp, container, false);


        imgAtras = (ImageView)root.findViewById(R.id.iv_atras_dg);
        btn_sig =(Button)root.findViewById(R.id.btn_siguiente);

        checkBoxList.add(root.findViewById(R.id.epp1));
        checkBoxList.add(root.findViewById(R.id.epp2));
        checkBoxList.add(root.findViewById(R.id.epp3));
        checkBoxList.add(root.findViewById(R.id.epp4));
        checkBoxList.add(root.findViewById(R.id.epp5));
        checkBoxList.add(root.findViewById(R.id.epp6));
        checkBoxList.add(root.findViewById(R.id.epp7));
        checkBoxList.add(root.findViewById(R.id.epp8));
        checkBoxList.add(root.findViewById(R.id.epp9));
        checkBoxList.add(root.findViewById(R.id.epp10));
        checkBoxList.add(root.findViewById(R.id.epp11));
        checkBoxList.add(root.findViewById(R.id.eppOtros));


        imgAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.ct_inspeccion_general, new Equipos_herramientas_otros()).commit();
            }
        });

        //recorre todos los checkbox y si se checa se agrea la lista eppList si no lo quita de la lista
        for(CheckBox actual: checkBoxList){
            actual.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(isChecked==true){
                        epp_list.add(actual.getText().toString());
                        if(actual.getText().toString().equals("Otros")){
                            checadoOtrosEpp=true;
                        }

                    }else if(isChecked==false){
                        epp_list.remove(actual.getText().toString());
                        if(actual.getText().toString().equals("Otros")){
                            checadoOtrosEpp=false;
                        }
                    }

                }
            });


        }

        btn_sig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean validado =true;

                if(epp_list.isEmpty()){
                    validado=false;
                }


                Activity activity = getActivity();
                if(validado){

                    ((Inspeccion_general_dt)activity).seleccionEPP(epp_list);

                    if(checadoOtrosEpp==true){
                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.ct_inspeccion_general, new Seleccion_eppOtros()).commit();

                    }else{
                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.ct_inspeccion_general, new Nomina_trabajadores()).commit();

                    }

                }else{
                    Toast.makeText(getContext(),"No ha seleccionado ningún EPP",Toast.LENGTH_LONG).show();
                }

            }
        });

        return root;
    }



}
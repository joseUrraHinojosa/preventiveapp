package fragment_charla;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.Toast;

import com.jose.preventiveapp.Charla_dt;
import com.jose.preventiveapp.Menu;
import com.jose.preventiveapp.R;

import java.util.ArrayList;
import java.util.List;

import dao.IdActualDAO;
import dao.TemasCharlaDAO;

public class Clasificacion_temas_charla extends Fragment {

    public Clasificacion_temas_charla() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private Button btn_sign;
    private List<CheckBox> checkBoxList = new ArrayList<>();
    private List<String> temasCharlaList = new ArrayList<>();
    private ImageView irAtras;
    private boolean     otros=false;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_clasificacion_temas_charla, container, false);

        btn_sign = root.findViewById(R.id.btn_siguiente);
        irAtras =(ImageView)root.findViewById(R.id.iv_atras_ce);
        checkBoxList.add(root.findViewById(R.id.cbxtcharla1));
        checkBoxList.add(root.findViewById(R.id.cbxtcharla2));
        checkBoxList.add(root.findViewById(R.id.cbxtcharla3));
        checkBoxList.add(root.findViewById(R.id.cbxtcharla4));
        checkBoxList.add(root.findViewById(R.id.cbxtcharla5));
        checkBoxList.add(root.findViewById(R.id.cbxtcharla6));
        checkBoxList.add(root.findViewById(R.id.cbxtcharla7));
        checkBoxList.add(root.findViewById(R.id.cbxtcharla8));
        checkBoxList.add(root.findViewById(R.id.cbxtcharla9));
        checkBoxList.add(root.findViewById(R.id.cbxtcharla10));
        checkBoxList.add(root.findViewById(R.id.cbxtcharla11));
        checkBoxList.add(root.findViewById(R.id.cbxtcharla12));
        checkBoxList.add(root.findViewById(R.id.cbxtcharla13));
        checkBoxList.add(root.findViewById(R.id.cbxtcharla14));
        checkBoxList.add(root.findViewById(R.id.cbxtcharla15));
        checkBoxList.add(root.findViewById(R.id.cbxtcharla16));


        irAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), Menu.class);
                startActivity(intent);
                getActivity().finish();
            }
        });

        btn_sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                for(CheckBox actual: checkBoxList){

                    if(actual.isChecked()){

                        temasCharlaList.add(actual.getText().toString());

                        if(actual.getText().toString().equals("Otros")){
                            otros = true;
                        }
                    }
                }

                if(temasCharlaList.isEmpty()){
                    Toast.makeText(getContext(),"Aun no selecciona ningún tema",Toast.LENGTH_SHORT).show();
                }else{
                    ((Charla_dt)getContext()).temasCharla(temasCharlaList);

                    if(otros == true){
                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.ct_charla, new Clasificacion_OtrosTemas()).commit();

                    }else{
                        getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.ct_charla, new Resumen_temas_tratados_charla()).commit();

                    }


                }


            }
        });

        return root;
    }
}
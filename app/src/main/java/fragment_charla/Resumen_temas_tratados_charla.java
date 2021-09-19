package fragment_charla;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.jose.preventiveapp.Charla_dt;
import com.jose.preventiveapp.R;

import java.util.ArrayList;
import java.util.List;

import fragment_inspeccion_general.Factores_ambientales;

public class Resumen_temas_tratados_charla extends Fragment {

    public Resumen_temas_tratados_charla() {

    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }


    private SeekBar seekBar;
    private TextInputLayout t1,t2,t3,t4,t5,t6,t7,t8,t9,t10;
    private Button btn_sig;
    private List<TextInputLayout> inputLayoutList = new ArrayList<>();
    private List<String> temasTratadoslist = new ArrayList<>();
    private ImageView irAtras;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_resumen_temas_tratados_charla, container, false);

        btn_sig =(Button)root.findViewById(R.id.btn_siguiente);
        irAtras =(ImageView)root.findViewById(R.id.iv_atras_ce);

        seekBar = root.findViewById(R.id.seekBar_tTratados);
        t1= root.findViewById(R.id.tema1);
        t2= root.findViewById(R.id.tema2);
        t3= root.findViewById(R.id.tema3);
        t4= root.findViewById(R.id.tema4);
        t5= root.findViewById(R.id.tema5);
        t6= root.findViewById(R.id.tema6);
        t7= root.findViewById(R.id.tema7);
        t8= root.findViewById(R.id.tema8);
        t9= root.findViewById(R.id.tema9);
        t10= root.findViewById(R.id.tema10);
        inputLayoutList.add(t1);
        inputLayoutList.add(t2);
        inputLayoutList.add(t3);
        inputLayoutList.add(t4);
        inputLayoutList.add(t5);
        inputLayoutList.add(t6);
        inputLayoutList.add(t7);
        inputLayoutList.add(t8);
        inputLayoutList.add(t9);
        inputLayoutList.add(t10);



        btn_sig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean validado=true;

                for(TextInputLayout actual: inputLayoutList){
                    actual.setErrorEnabled(false);

                    if(actual.getEditText().getText().toString().isEmpty() && actual.getVisibility()==View.VISIBLE){
                        actual.setErrorEnabled(true);
                        actual.setError("Debe completar este campo");
                        validado=false;
                    }else if(actual.getVisibility()==View.VISIBLE){
                        temasTratadoslist.add(actual.getEditText().getText().toString());
                    }
                }

                if(temasTratadoslist.isEmpty()){
                    Toast.makeText(getContext(),"Aun no ha registrado ningún tema",Toast.LENGTH_SHORT).show();
                    validado=false;
                }

                if(validado==true){

                    ((Charla_dt)getContext()).temasTratdos(temasTratadoslist);
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.ct_charla, new Asistentes_charla()).commit();

                }else{
                    Toast.makeText(getContext(),"Debe completar todos los campos",Toast.LENGTH_LONG).show();
                }



            }
        });

        irAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.ct_charla, new Clasificacion_temas_charla()).commit();
            }
        });

         seekBar.setProgress(0);

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                switch (seekBar.getProgress()){

                    case 0: t2.setVisibility(View.GONE);
                            t3.setVisibility(View.GONE);
                            t4.setVisibility(View.GONE);
                            t5.setVisibility(View.GONE);
                            t6.setVisibility(View.GONE);
                            t7.setVisibility(View.GONE);
                            t8.setVisibility(View.GONE);
                            t9.setVisibility(View.GONE);
                            t10.setVisibility(View.GONE);
                            break;

                    case 1: t2.setVisibility(View.VISIBLE);
                            t3.setVisibility(View.GONE);
                            t4.setVisibility(View.GONE);
                            t5.setVisibility(View.GONE);
                            t6.setVisibility(View.GONE);
                            t7.setVisibility(View.GONE);
                            t8.setVisibility(View.GONE);
                            t9.setVisibility(View.GONE);
                            t10.setVisibility(View.GONE);
                            break;

                    case 2: t2.setVisibility(View.VISIBLE);
                            t3.setVisibility(View.VISIBLE);
                            t4.setVisibility(View.GONE);
                            t5.setVisibility(View.GONE);
                            t6.setVisibility(View.GONE);
                            t7.setVisibility(View.GONE);
                            t8.setVisibility(View.GONE);
                            t9.setVisibility(View.GONE);
                            t10.setVisibility(View.GONE);
                             break;

                    case 3: t2.setVisibility(View.VISIBLE);
                            t3.setVisibility(View.VISIBLE);
                            t4.setVisibility(View.VISIBLE);
                            t5.setVisibility(View.GONE);
                            t6.setVisibility(View.GONE);
                            t7.setVisibility(View.GONE);
                            t8.setVisibility(View.GONE);
                            t9.setVisibility(View.GONE);
                            t10.setVisibility(View.GONE);
                             break;

                    case 4: t2.setVisibility(View.VISIBLE);
                            t3.setVisibility(View.VISIBLE);
                            t4.setVisibility(View.VISIBLE);
                            t5.setVisibility(View.VISIBLE);
                            t6.setVisibility(View.GONE);
                            t7.setVisibility(View.GONE);
                            t8.setVisibility(View.GONE);
                            t9.setVisibility(View.GONE);
                            t10.setVisibility(View.GONE);
                             break;
                    case 5: t2.setVisibility(View.VISIBLE);
                            t3.setVisibility(View.VISIBLE);
                            t4.setVisibility(View.VISIBLE);
                            t5.setVisibility(View.VISIBLE);
                            t6.setVisibility(View.VISIBLE);
                            t7.setVisibility(View.GONE);
                            t8.setVisibility(View.GONE);
                            t9.setVisibility(View.GONE);
                            t10.setVisibility(View.GONE);
                            break;

                    case 6: t2.setVisibility(View.VISIBLE);
                            t3.setVisibility(View.VISIBLE);
                            t4.setVisibility(View.VISIBLE);
                            t5.setVisibility(View.VISIBLE);
                            t6.setVisibility(View.VISIBLE);
                            t7.setVisibility(View.VISIBLE);
                            t8.setVisibility(View.GONE);
                            t9.setVisibility(View.GONE);
                            t10.setVisibility(View.GONE);
                            break;

                    case 7: t2.setVisibility(View.VISIBLE);
                            t3.setVisibility(View.VISIBLE);
                            t4.setVisibility(View.VISIBLE);
                            t5.setVisibility(View.VISIBLE);
                            t6.setVisibility(View.VISIBLE);
                            t7.setVisibility(View.VISIBLE);
                            t8.setVisibility(View.VISIBLE);
                            t9.setVisibility(View.GONE);
                            t10.setVisibility(View.GONE);
                            break;

                    case 8: t2.setVisibility(View.VISIBLE);
                            t3.setVisibility(View.VISIBLE);
                            t4.setVisibility(View.VISIBLE);
                            t5.setVisibility(View.VISIBLE);
                            t6.setVisibility(View.VISIBLE);
                            t7.setVisibility(View.VISIBLE);
                            t8.setVisibility(View.VISIBLE);
                            t9.setVisibility(View.VISIBLE);
                            t10.setVisibility(View.GONE);
                            break;

                    case 9: t2.setVisibility(View.VISIBLE);
                            t3.setVisibility(View.VISIBLE);
                            t4.setVisibility(View.VISIBLE);
                            t5.setVisibility(View.VISIBLE);
                            t6.setVisibility(View.VISIBLE);
                            t7.setVisibility(View.VISIBLE);
                            t8.setVisibility(View.VISIBLE);
                            t9.setVisibility(View.VISIBLE);
                            t10.setVisibility(View.VISIBLE);
                            break;


                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        return root;
    }

}
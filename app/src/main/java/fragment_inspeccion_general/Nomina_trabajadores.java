package fragment_inspeccion_general;

import android.os.Bundle;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.jose.preventiveapp.Inspeccion_general_dt;
import com.jose.preventiveapp.R;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import dto.CommonFn;
import dto.Trabajadores;


public class Nomina_trabajadores extends Fragment {

    public Nomina_trabajadores(){

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    private ImageView imgAtras;
    private SeekBar sb_NominaTra;
    private List<LinearLayout> linearLayoutList = new ArrayList<>();
    private List<TextInputLayout> inputLayoutNomList = new ArrayList<>();
    private List<TextInputLayout> inputLayoutDocuList = new ArrayList<>();
    private List<Switch> switchList = new ArrayList<>();
    private Button btn;
    private List<Trabajadores> trabajadoresList = new ArrayList<>();
    private CommonFn cf = new CommonFn();


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_nomina_trabajadores, container, false);
        btn =(Button)root.findViewById(R.id.btn_siguiente1);
        imgAtras = (ImageView) root.findViewById(R.id.iv_atras_dg);
        sb_NominaTra = (SeekBar) root.findViewById(R.id.sb_nominaTra);

        linearLayoutList.add(root.findViewById(R.id.linear_tra1));
        linearLayoutList.add(root.findViewById(R.id.linear_tra2));
        linearLayoutList.add(root.findViewById(R.id.linear_tra3));
        linearLayoutList.add(root.findViewById(R.id.linear_tra4));
        linearLayoutList.add(root.findViewById(R.id.linear_tra5));
        linearLayoutList.add(root.findViewById(R.id.linear_tra6));
        linearLayoutList.add(root.findViewById(R.id.linear_tra7));
        linearLayoutList.add(root.findViewById(R.id.linear_tra8));
        linearLayoutList.add(root.findViewById(R.id.linear_tra9));
        linearLayoutList.add(root.findViewById(R.id.linear_tra10));
        linearLayoutList.add(root.findViewById(R.id.linear_tra11));
        linearLayoutList.add(root.findViewById(R.id.linear_tra12));
        linearLayoutList.add(root.findViewById(R.id.linear_tra13));
        linearLayoutList.add(root.findViewById(R.id.linear_tra14));
        linearLayoutList.add(root.findViewById(R.id.linear_tra15));
        linearLayoutList.add(root.findViewById(R.id.linear_tra16));
        linearLayoutList.add(root.findViewById(R.id.linear_tra17));
        linearLayoutList.add(root.findViewById(R.id.linear_tra18));

        inputLayoutNomList.add(root.findViewById(R.id.tl_nom1));
        inputLayoutNomList.add(root.findViewById(R.id.tl_nom2));
        inputLayoutNomList.add(root.findViewById(R.id.tl_nom3));
        inputLayoutNomList.add(root.findViewById(R.id.tl_nom4));
        inputLayoutNomList.add(root.findViewById(R.id.tl_nom5));
        inputLayoutNomList.add(root.findViewById(R.id.tl_nom6));
        inputLayoutNomList.add(root.findViewById(R.id.tl_nom7));
        inputLayoutNomList.add(root.findViewById(R.id.tl_nom8));
        inputLayoutNomList.add(root.findViewById(R.id.tl_nom9));
        inputLayoutNomList.add(root.findViewById(R.id.tl_nom10));
        inputLayoutNomList.add(root.findViewById(R.id.tl_nom11));
        inputLayoutNomList.add(root.findViewById(R.id.tl_nom12));
        inputLayoutNomList.add(root.findViewById(R.id.tl_nom13));
        inputLayoutNomList.add(root.findViewById(R.id.tl_nom14));
        inputLayoutNomList.add(root.findViewById(R.id.tl_nom15));
        inputLayoutNomList.add(root.findViewById(R.id.tl_nom16));
        inputLayoutNomList.add(root.findViewById(R.id.tl_nom17));
        inputLayoutNomList.add(root.findViewById(R.id.tl_nom18));

        inputLayoutDocuList.add(root.findViewById(R.id.tl_docu1));
        inputLayoutDocuList.add(root.findViewById(R.id.tl_docu2));
        inputLayoutDocuList.add(root.findViewById(R.id.tl_docu3));
        inputLayoutDocuList.add(root.findViewById(R.id.tl_docu4));
        inputLayoutDocuList.add(root.findViewById(R.id.tl_docu5));
        inputLayoutDocuList.add(root.findViewById(R.id.tl_docu6));
        inputLayoutDocuList.add(root.findViewById(R.id.tl_docu7));
        inputLayoutDocuList.add(root.findViewById(R.id.tl_docu8));
        inputLayoutDocuList.add(root.findViewById(R.id.tl_docu9));
        inputLayoutDocuList.add(root.findViewById(R.id.tl_docu10));
        inputLayoutDocuList.add(root.findViewById(R.id.tl_docu11));
        inputLayoutDocuList.add(root.findViewById(R.id.tl_docu12));
        inputLayoutDocuList.add(root.findViewById(R.id.tl_docu13));
        inputLayoutDocuList.add(root.findViewById(R.id.tl_docu14));
        inputLayoutDocuList.add(root.findViewById(R.id.tl_docu15));
        inputLayoutDocuList.add(root.findViewById(R.id.tl_docu16));
        inputLayoutDocuList.add(root.findViewById(R.id.tl_docu17));
        inputLayoutDocuList.add(root.findViewById(R.id.tl_docu18));

        switchList.add(root.findViewById(R.id.sw1));
        switchList.add(root.findViewById(R.id.sw2));
        switchList.add(root.findViewById(R.id.sw3));
        switchList.add(root.findViewById(R.id.sw4));
        switchList.add(root.findViewById(R.id.sw5));
        switchList.add(root.findViewById(R.id.sw6));
        switchList.add(root.findViewById(R.id.sw7));
        switchList.add(root.findViewById(R.id.sw8));
        switchList.add(root.findViewById(R.id.sw9));
        switchList.add(root.findViewById(R.id.sw10));
        switchList.add(root.findViewById(R.id.sw11));
        switchList.add(root.findViewById(R.id.sw12));
        switchList.add(root.findViewById(R.id.sw13));
        switchList.add(root.findViewById(R.id.sw14));
        switchList.add(root.findViewById(R.id.sw15));
        switchList.add(root.findViewById(R.id.sw16));
        switchList.add(root.findViewById(R.id.sw17));
        switchList.add(root.findViewById(R.id.sw18));


        imgAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.ct_inspeccion_general, new Seleccion_epp()).commit();

            }
        });


        //funcionalidad seekbar entrega cantidad de trabajadores a ingresar
        sb_NominaTra.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                for(int i =1; i < linearLayoutList.size(); i++){
                    if(linearLayoutList.get(i).getVisibility()==View.VISIBLE){
                        linearLayoutList.get(i).setVisibility(View.GONE);
                        switchList.get(i).setChecked(false);
                        inputLayoutNomList.get(i).setErrorEnabled(false);
                        inputLayoutDocuList.get(i).setErrorEnabled(false);
                    }

                }

                for(int i=0; i< sb_NominaTra.getProgress(); i++){
                    linearLayoutList.get(i).setVisibility(View.VISIBLE);
                }

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        for(int i=0; i< switchList.size(); i++) {
            switchList.get(i).setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    if(isChecked==true){
                        for(int i =0; i< switchList.size(); i++){
                            if(switchList.get(i).isChecked()){
                                inputLayoutDocuList.get(i).setHint("Pasaporte");
                            }
                        }
                    }else{

                        for(int i =0; i< switchList.size(); i++){
                            if(switchList.get(i).isChecked()){

                            }else{
                                inputLayoutDocuList.get(i).setHint("Run");
                            }
                        }
                    }
                }
            });
        }





        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean validado =true;


                for(int i =0; i< linearLayoutList.size(); i++){

                    if(linearLayoutList.get(i).getVisibility() == View.VISIBLE){
                        inputLayoutNomList.get(i).setErrorEnabled(false);
                        inputLayoutDocuList.get(i).setErrorEnabled(false);

                        if(inputLayoutNomList.get(i).getEditText().getText().toString().equals("")){
                            inputLayoutNomList.get(i).setErrorEnabled(true);
                            inputLayoutNomList.get(i).setError("Debe completar este campo");
                             validado=false;
                        }

                        if(inputLayoutDocuList.get(i).getEditText().getText().toString().equals("")){
                            inputLayoutDocuList.get(i).setErrorEnabled(true);
                            inputLayoutDocuList.get(i).setError("Debe completar este campo");
                            validado=false;
                        }else
                        if(cf.validaRut(inputLayoutDocuList.get(i).getEditText().getText().toString().trim())==false && inputLayoutDocuList.get(i).getHint().equals("Run")){
                            inputLayoutDocuList.get(i).setErrorEnabled(true);
                            inputLayoutDocuList.get(i).setError("Run inválido");
                            validado=false;

                        }

                    }
                }


                if(validado==true){


                    for(int i =0 ; i<linearLayoutList.size(); i++){
                        if(linearLayoutList.get(i).getVisibility()==View.VISIBLE){
                            Trabajadores tra = new Trabajadores();
                           String nombre = inputLayoutNomList.get(i).getEditText().getText().toString().trim();
                           String docu = inputLayoutDocuList.get(i).getEditText().getText().toString().trim();
                           tra.setNombre(nombre);
                            if( inputLayoutDocuList.get(i).getHint().equals("Pasaporte")){
                                tra.setPasaporte(docu);

                            }else{
                                tra.setRun(docu);
                            }

                            trabajadoresList.add(tra);
                        }
                    }

                    ((Inspeccion_general_dt)getActivity()).trabajadoresInterface(trabajadoresList);
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.ct_inspeccion_general, new Etapas_tarea()).commit();


                }else{
                    Toast.makeText(getContext(),"Error en el registro, verifique campos!",Toast.LENGTH_LONG).show();
                }

            }
        });

        return root;
    }



}
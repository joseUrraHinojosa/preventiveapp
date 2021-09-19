package fragment_charla;

import android.app.Activity;
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
import com.jose.preventiveapp.Inspeccion_general_dt;
import com.jose.preventiveapp.R;

import java.util.ArrayList;
import java.util.List;

import fragment_inspeccion_general.Nomina_trabajadores;
import fragment_inspeccion_general.Seleccion_epp;


public class Clasificacion_OtrosTemas extends Fragment {

    private ImageView imgAtras;
    private Button btnSig;
    private SeekBar seekbarOtrosTemas;
    private List<TextInputLayout> textInputLayoutList = new ArrayList<>();
    private List<String> ListOtrosTemas = new ArrayList<>();

    public Clasificacion_OtrosTemas() {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_clasificacion__otros_temas, container, false);

        imgAtras =(ImageView) root.findViewById(R.id.iv_atras_ce);
        btnSig = (Button)root.findViewById(R.id.btn_siguiente);
        seekbarOtrosTemas =(SeekBar)root.findViewById(R.id.seekBarOtrosTemas);

        textInputLayoutList.add(root.findViewById(R.id.otroTemas1));
        textInputLayoutList.add(root.findViewById(R.id.otroTema2));
        textInputLayoutList.add(root.findViewById(R.id.otroTema3));
        textInputLayoutList.add(root.findViewById(R.id.otroTema4));
        textInputLayoutList.add(root.findViewById(R.id.otroTema5));
        textInputLayoutList.add(root.findViewById(R.id.otroTema6));
        textInputLayoutList.add(root.findViewById(R.id.otroTema7));
        textInputLayoutList.add(root.findViewById(R.id.otroTema8));


        imgAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.ct_charla, new Clasificacion_temas_charla()).commit();
            }
        });



        seekbarOtrosTemas.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                switch (seekbarOtrosTemas.getProgress()) {

                    case 0:
                        textInputLayoutList.get(1).setVisibility(View.GONE);
                        break;

                    case 1:
                        for (int i = 0; i < textInputLayoutList.size(); i++) {
                            if (textInputLayoutList.get(i).equals(textInputLayoutList.get(1))) {
                                textInputLayoutList.get(0).setVisibility(View.VISIBLE);
                                textInputLayoutList.get(1).setVisibility(View.VISIBLE);
                            } else {
                                textInputLayoutList.get(i).setVisibility(View.GONE);
                            }
                        }
                        break;

                    case 2:
                        for (int i = 0; i < textInputLayoutList.size(); i++) {
                            if (textInputLayoutList.get(i).equals(textInputLayoutList.get(2))) {
                                textInputLayoutList.get(0).setVisibility(View.VISIBLE);
                                textInputLayoutList.get(1).setVisibility(View.VISIBLE);
                                textInputLayoutList.get(2).setVisibility(View.VISIBLE);
                            } else {
                                textInputLayoutList.get(i).setVisibility(View.GONE);
                            }
                        }
                        break;

                    case 3:
                        for (int i = 0; i < textInputLayoutList.size(); i++) {
                            if (textInputLayoutList.get(i).equals(textInputLayoutList.get(3))) {
                                textInputLayoutList.get(0).setVisibility(View.VISIBLE);
                                textInputLayoutList.get(1).setVisibility(View.VISIBLE);
                                textInputLayoutList.get(2).setVisibility(View.VISIBLE);
                                textInputLayoutList.get(3).setVisibility(View.VISIBLE);
                            } else {
                                textInputLayoutList.get(i).setVisibility(View.GONE);
                            }
                        }
                        break;

                    case 4:
                        for (int i = 0; i < textInputLayoutList.size(); i++) {
                            if (textInputLayoutList.get(i).equals(textInputLayoutList.get(4))) {
                                textInputLayoutList.get(0).setVisibility(View.VISIBLE);
                                textInputLayoutList.get(1).setVisibility(View.VISIBLE);
                                textInputLayoutList.get(2).setVisibility(View.VISIBLE);
                                textInputLayoutList.get(3).setVisibility(View.VISIBLE);
                                textInputLayoutList.get(4).setVisibility(View.VISIBLE);
                            } else {
                                textInputLayoutList.get(i).setVisibility(View.GONE);
                            }
                        }
                        break;

                    case 5:
                        for (int i = 0; i < textInputLayoutList.size(); i++) {
                            if (textInputLayoutList.get(i).equals(textInputLayoutList.get(5))) {
                                textInputLayoutList.get(0).setVisibility(View.VISIBLE);
                                textInputLayoutList.get(1).setVisibility(View.VISIBLE);
                                textInputLayoutList.get(2).setVisibility(View.VISIBLE);
                                textInputLayoutList.get(3).setVisibility(View.VISIBLE);
                                textInputLayoutList.get(4).setVisibility(View.VISIBLE);
                                textInputLayoutList.get(5).setVisibility(View.VISIBLE);
                            } else {
                                textInputLayoutList.get(i).setVisibility(View.GONE);
                            }
                        }
                        break;

                    case 6:
                        for (int i = 0; i < textInputLayoutList.size(); i++) {
                            if (textInputLayoutList.get(i).equals(textInputLayoutList.get(6))) {
                                textInputLayoutList.get(0).setVisibility(View.VISIBLE);
                                textInputLayoutList.get(1).setVisibility(View.VISIBLE);
                                textInputLayoutList.get(2).setVisibility(View.VISIBLE);
                                textInputLayoutList.get(3).setVisibility(View.VISIBLE);
                                textInputLayoutList.get(4).setVisibility(View.VISIBLE);
                                textInputLayoutList.get(5).setVisibility(View.VISIBLE);
                                textInputLayoutList.get(6).setVisibility(View.VISIBLE);
                            } else {
                                textInputLayoutList.get(i).setVisibility(View.GONE);
                            }
                        }
                        break;

                    case 7:
                        for (int i = 0; i < textInputLayoutList.size(); i++) {
                            if (textInputLayoutList.get(i).equals(textInputLayoutList.get(7))) {
                                textInputLayoutList.get(0).setVisibility(View.VISIBLE);
                                textInputLayoutList.get(1).setVisibility(View.VISIBLE);
                                textInputLayoutList.get(2).setVisibility(View.VISIBLE);
                                textInputLayoutList.get(3).setVisibility(View.VISIBLE);
                                textInputLayoutList.get(4).setVisibility(View.VISIBLE);
                                textInputLayoutList.get(5).setVisibility(View.VISIBLE);
                                textInputLayoutList.get(6).setVisibility(View.VISIBLE);
                                textInputLayoutList.get(7).setVisibility(View.VISIBLE);
                            } else {
                                textInputLayoutList.get(i).setVisibility(View.GONE);
                            }
                        }
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


        btnSig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean validado =true;

                for(TextInputLayout actual: textInputLayoutList){
                    actual.setErrorEnabled(false);

                    if(actual.getVisibility() == View.VISIBLE){
                        if(actual.getEditText().getText().toString().equals("")){
                            actual.setErrorEnabled(true);
                            actual.setError("Debe completar este campo");
                            validado=false;
                        }else{
                            ListOtrosTemas.add(actual.getEditText().getText().toString());
                        }
                    }
                }

                if(ListOtrosTemas.isEmpty()){
                    validado=false;
                    Toast.makeText(getContext(),"No ha registrado ningún tema",Toast.LENGTH_LONG).show();
                }

                if(validado){
                    Activity activity = getActivity();
                    ((Charla_dt)activity).otrosTemasCharla(ListOtrosTemas);
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.ct_charla, new Resumen_temas_tratados_charla()).commit();


                }
            }
        });





        return root;
    }
}
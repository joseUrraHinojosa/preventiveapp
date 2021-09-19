package fragment_inspeccion_general;

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
import com.jose.preventiveapp.Inspeccion_general_dt;
import com.jose.preventiveapp.R;

import java.util.ArrayList;
import java.util.List;

public class Seleccion_eppOtros extends Fragment {

    public Seleccion_eppOtros() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
    private ImageView imgAtras;
    private Button btnSig;
    private SeekBar seekbarEpp;
    private List<TextInputLayout> textInputLayoutList = new ArrayList<>();
    private List<String> eppListOtros = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {



        View root = inflater.inflate(R.layout.fragment_seleccion_epp_otros, container, false);
        imgAtras =(ImageView) root.findViewById(R.id.iv_atras_ce);
        btnSig = (Button)root.findViewById(R.id.btn_siguiente);
        seekbarEpp =(SeekBar)root.findViewById(R.id.seekBarOtrosEpp);

        textInputLayoutList.add(root.findViewById(R.id.otroEpp1));
        textInputLayoutList.add(root.findViewById(R.id.otroEpp2));
        textInputLayoutList.add(root.findViewById(R.id.otroEpp3));
        textInputLayoutList.add(root.findViewById(R.id.otroEpp4));
        textInputLayoutList.add(root.findViewById(R.id.otroEpp5));
        textInputLayoutList.add(root.findViewById(R.id.otroEpp6));
        textInputLayoutList.add(root.findViewById(R.id.otroEpp7));
        textInputLayoutList.add(root.findViewById(R.id.otroEpp8));


        imgAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.ct_inspeccion_general, new Seleccion_epp()).commit();
            }
        });



        seekbarEpp.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

                switch (seekbarEpp.getProgress()) {

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
                            eppListOtros.add(actual.getEditText().getText().toString());
                        }
                    }
                }

                if(eppListOtros.isEmpty()){
                    validado=false;
                    Toast.makeText(getContext(),"No ha registrado ningún epp",Toast.LENGTH_LONG).show();
                }

                if(validado){
                    Activity activity = getActivity();
                    ((Inspeccion_general_dt)activity).seleccionOtrosEPP(eppListOtros);
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.ct_inspeccion_general, new Nomina_trabajadores()).commit();


                }
            }
        });





        return root;
    }
}
package fragment_revision_epp;

import android.graphics.Color;
import android.os.Bundle;

import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.textview.MaterialTextView;
import com.jose.preventiveapp.R;
import com.jose.preventiveapp.Revision_epp_dt;
import java.util.ArrayList;
import java.util.List;

import dao.IdActualDAO;
import dao.SeleccionEppDAO;
import dto.EstadoEpp;


public class Estado_epp extends Fragment {

    private Button btnSig;
    private RadioButton radioButton;
    private RadioGroup rg1,rg2,rg3,rg4,rg5,rg6,rg7,rg8,rg9,rg10,rg11,rg12,rg13,rg14;
    private RadioGroup rg15,rg16,rg17,rg18,rg19,rg20,rg21,rg22,rg23,rg24;
    private MaterialCardView cv1,cv2,cv3,cv4,cv5,cv6,cv7,cv8,cv9,cv10,cv11,cv12,cv13,cv14;
    private MaterialCardView cv15,cv16,cv17,cv18,cv19,cv20,cv21,cv22,cv23,cv24;
    private List<EstadoEpp> estadoEppList = new ArrayList<>();
    private List<String> otrosEppList = new ArrayList<>();
    private List<String> otrosEppList2 = new ArrayList<>();
    private List<MaterialTextView> textViewList  = new ArrayList<>();
    private TextView tvEpp;

    private List<MaterialCardView> cardViewList =new ArrayList<>();


    public Estado_epp() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_estado_epp, container, false);

        rg1 =(RadioGroup)root.findViewById(R.id.rgroup1);
        rg2 =(RadioGroup)root.findViewById(R.id.rgroup2);
        rg3 =(RadioGroup)root.findViewById(R.id.rgroup3);
        rg4 =(RadioGroup)root.findViewById(R.id.rgroup4);
        rg5 =(RadioGroup)root.findViewById(R.id.rgroup5);
        rg6 =(RadioGroup)root.findViewById(R.id.rgroup6);
        rg7 =(RadioGroup)root.findViewById(R.id.rgroup7);
        rg8 =(RadioGroup)root.findViewById(R.id.rgroup8);
        rg9 =(RadioGroup)root.findViewById(R.id.rgroup9);
        rg10 =(RadioGroup)root.findViewById(R.id.rgroup10);
        rg11 =(RadioGroup)root.findViewById(R.id.rgroup11);
        rg12 =(RadioGroup)root.findViewById(R.id.rgroup12);
        rg13 =(RadioGroup)root.findViewById(R.id.rgroup13);
        rg14 =(RadioGroup)root.findViewById(R.id.rgroup14);

        rg15 =(RadioGroup)root.findViewById(R.id.rgroup15);
        rg16 =(RadioGroup)root.findViewById(R.id.rgroup16);
        rg17 =(RadioGroup)root.findViewById(R.id.rgroup17);
        rg18 =(RadioGroup)root.findViewById(R.id.rgroup18);
        rg19 =(RadioGroup)root.findViewById(R.id.rgroup19);
        rg20 =(RadioGroup)root.findViewById(R.id.rgroup20);
        rg21 =(RadioGroup)root.findViewById(R.id.rgroup21);
        rg22 =(RadioGroup)root.findViewById(R.id.rgroup22);
        rg23 =(RadioGroup)root.findViewById(R.id.rgroup23);
        rg24 =(RadioGroup)root.findViewById(R.id.rgroup24);


        cv1 =(MaterialCardView) root.findViewById(R.id.carview1);
        cv2 =(MaterialCardView) root.findViewById(R.id.carview2);
        cv3 =(MaterialCardView) root.findViewById(R.id.carview3);
        cv4 =(MaterialCardView) root.findViewById(R.id.carview4);
        cv5 =(MaterialCardView) root.findViewById(R.id.carview5);
        cv6 =(MaterialCardView) root.findViewById(R.id.carview6);
        cv7 =(MaterialCardView) root.findViewById(R.id.carview7);
        cv8 =(MaterialCardView) root.findViewById(R.id.carview8);
        cv9 =(MaterialCardView) root.findViewById(R.id.carview9);
        cv10 =(MaterialCardView) root.findViewById(R.id.carview10);
        cv11 =(MaterialCardView) root.findViewById(R.id.carview11);
        cv12 =(MaterialCardView) root.findViewById(R.id.carview12);
        cv13 =(MaterialCardView) root.findViewById(R.id.carview13);
        cv14 =(MaterialCardView) root.findViewById(R.id.carview14);

        cv15 =(MaterialCardView) root.findViewById(R.id.carview15);
        cv16 =(MaterialCardView) root.findViewById(R.id.carview16);
        cv17 =(MaterialCardView) root.findViewById(R.id.carview17);
        cv18 =(MaterialCardView) root.findViewById(R.id.carview18);
        cv19 =(MaterialCardView) root.findViewById(R.id.carview19);
        cv20 =(MaterialCardView) root.findViewById(R.id.carview20);
        cv21 =(MaterialCardView) root.findViewById(R.id.carview21);
        cv22 =(MaterialCardView) root.findViewById(R.id.carview22);
        cv23 =(MaterialCardView) root.findViewById(R.id.carview23);
        cv24 =(MaterialCardView) root.findViewById(R.id.carview24);

        cardViewList.add(root.findViewById(R.id.carview15));
        cardViewList.add(root.findViewById(R.id.carview16));
        cardViewList.add(root.findViewById(R.id.carview17));
        cardViewList.add(root.findViewById(R.id.carview18));
        cardViewList.add(root.findViewById(R.id.carview19));
        cardViewList.add(root.findViewById(R.id.carview20));
        cardViewList.add(root.findViewById(R.id.carview21));
        cardViewList.add(root.findViewById(R.id.carview22));
        cardViewList.add(root.findViewById(R.id.carview23));
        cardViewList.add(root.findViewById(R.id.carview24));



        textViewList.add(root.findViewById(R.id.tvEstado15));
        textViewList.add(root.findViewById(R.id.tvEstado16));
        textViewList.add(root.findViewById(R.id.tvEstado17));
        textViewList.add(root.findViewById(R.id.tvEstado18));
        textViewList.add(root.findViewById(R.id.tvEstado19));
        textViewList.add(root.findViewById(R.id.tvEstado20));
        textViewList.add(root.findViewById(R.id.tvEstado21));
        textViewList.add(root.findViewById(R.id.tvEstado22));
        textViewList.add(root.findViewById(R.id.tvEstado23));
        textViewList.add(root.findViewById(R.id.tvEstado24));

        btnSig =(Button)root.findViewById(R.id.btn_sig1);


        try{
            int indice = -1;
            int id = new IdActualDAO().mostrarIdActual(getContext());
            otrosEppList = new SeleccionEppDAO().mostrarSeleccionEpp(id,getContext());

            for(int i =0; i< otrosEppList.size(); i++){
                if(otrosEppList.get(i).equals("Otros")){
                    indice=(i+1);
                }
            }

            for(int i =indice; i< otrosEppList.size(); i++){
               otrosEppList2.add(otrosEppList.get(i));
            }


            for(int i =0; i< otrosEppList2.size(); i++){
                cardViewList.get(i).setVisibility(View.VISIBLE);
                textViewList.get(i).setText(otrosEppList2.get(i));
            }


        }catch (Exception e){

        }


        btnSig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean validado = true;
                int  radioButtonID = (-1);

                estadoEppList.clear();

                cv1.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
                cv2.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
                cv3.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
                cv4.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
                cv5.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
                cv6.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
                cv7.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
                cv8.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
                cv9.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
                cv10.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
                cv11.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
                cv12.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
                cv13.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
                cv14.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
                cv15.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
                cv16.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
                cv17.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
                cv18.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
                cv19.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
                cv20.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
                cv21.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
                cv22.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
                cv23.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
                cv24.setBackgroundColor(Color.parseColor("#FFFFFFFF"));

                //RADIOGROUP 1
                    radioButtonID = rg1.getCheckedRadioButtonId();
                    if(radioButtonID != -1){
                        radioButton = root.findViewById(radioButtonID);
                        estadoEppList.add(new EstadoEpp(null,"Casco de seguridad",radioButton.getText().toString()));
                    }else {
                        cv1.setBackgroundColor(Color.parseColor("#16D50000"));
                        validado = false;
                    }

                //RADIOGROUP 2
                radioButtonID = rg2.getCheckedRadioButtonId();
                if(radioButtonID != -1){
                    radioButton = root.findViewById(radioButtonID);
                    estadoEppList.add(new EstadoEpp(null,"Barbiquejo",radioButton.getText().toString()));
                }else {
                    cv2.setBackgroundColor(Color.parseColor("#16D50000"));
                    validado = false;
                }

                //RADIOGROUP 3
                radioButtonID = rg3.getCheckedRadioButtonId();
                if(radioButtonID != -1){
                    radioButton = root.findViewById(radioButtonID);
                    estadoEppList.add(new EstadoEpp(null,"Zapatos de seguridad",radioButton.getText().toString()));
                }else {
                    cv3.setBackgroundColor(Color.parseColor("#16D50000"));
                    validado = false;
                }


                //RADIOGROUP 4
                radioButtonID = rg4.getCheckedRadioButtonId();
                if(radioButtonID != -1){
                    radioButton = root.findViewById(radioButtonID);
                    estadoEppList.add(new EstadoEpp(null,"Arnés de seguridad",radioButton.getText().toString()));
                }else {
                    cv4.setBackgroundColor(Color.parseColor("#16D50000"));
                    validado = false;
                }

                //RADIOGROUP 5
                radioButtonID = rg5.getCheckedRadioButtonId();
                if(radioButtonID != -1){
                    radioButton = root.findViewById(radioButtonID);
                    estadoEppList.add(new EstadoEpp(null,"Estrobo de posicionamiento",radioButton.getText().toString()));
                }else {
                    cv5.setBackgroundColor(Color.parseColor("#16D50000"));
                    validado = false;
                }

                //RADIOGROUP 6
                radioButtonID = rg6.getCheckedRadioButtonId();
                if(radioButtonID != -1){
                    radioButton = root.findViewById(radioButtonID);
                    estadoEppList.add(new EstadoEpp(null,"Doble cabo de vida con amortiguador de impacto",radioButton.getText().toString()));
                }else {
                    cv6.setBackgroundColor(Color.parseColor("#16D50000"));
                    validado = false;
                }

                //RADIOGROUP 7
                radioButtonID = rg7.getCheckedRadioButtonId();
                if(radioButtonID != -1){
                    radioButton = root.findViewById(radioButtonID);
                    estadoEppList.add(new EstadoEpp(null,"Carro de ascenso",radioButton.getText().toString()));
                }else {
                    cv7.setBackgroundColor(Color.parseColor("#16D50000"));
                    validado = false;
                }

                //RADIOGROUP 8
                radioButtonID = rg8.getCheckedRadioButtonId();
                if(radioButtonID != -1){
                    radioButton = root.findViewById(radioButtonID);
                    estadoEppList.add(new EstadoEpp(null,"Guantes dieléctricos",radioButton.getText().toString()));
                }else {
                    cv8.setBackgroundColor(Color.parseColor("#16D50000"));
                    validado = false;
                }

                //RADIOGROUP 9
                radioButtonID = rg9.getCheckedRadioButtonId();
                if(radioButtonID != -1){
                    radioButton = root.findViewById(radioButtonID);
                    estadoEppList.add(new EstadoEpp(null,"Guantes tácticos",radioButton.getText().toString()));
                }else {
                    cv9.setBackgroundColor(Color.parseColor("#16D50000"));
                    validado = false;
                }

                //RADIOGROUP 10
                radioButtonID = rg10.getCheckedRadioButtonId();
                if(radioButtonID != -1){
                    radioButton = root.findViewById(radioButtonID);
                    estadoEppList.add(new EstadoEpp(null,"Guantes de cabritilla",radioButton.getText().toString()));
                }else {
                    cv10.setBackgroundColor(Color.parseColor("#16D50000"));
                    validado = false;
                }

                //RADIOGROUP 11
                radioButtonID = rg11.getCheckedRadioButtonId();
                if(radioButtonID != -1){
                    radioButton = root.findViewById(radioButtonID);
                    estadoEppList.add(new EstadoEpp(null,"Chaleco geólogo",radioButton.getText().toString()));
                }else {
                    cv11.setBackgroundColor(Color.parseColor("#16D50000"));
                    validado = false;
                }

                //RADIOGROUP 12
                radioButtonID = rg12.getCheckedRadioButtonId();
                if(radioButtonID != -1){
                    radioButton = root.findViewById(radioButtonID);
                    estadoEppList.add(new EstadoEpp(null,"Overol de trabajo",radioButton.getText().toString()));
                }else {
                    cv12.setBackgroundColor(Color.parseColor("#16D50000"));
                    validado = false;
                }

                //RADIOGROUP 13
                radioButtonID = rg13.getCheckedRadioButtonId();
                if(radioButtonID != -1){
                    radioButton = root.findViewById(radioButtonID);
                    estadoEppList.add(new EstadoEpp(null,"Gafas de seguridad",radioButton.getText().toString()));
                }else {
                    cv13.setBackgroundColor(Color.parseColor("#16D50000"));
                    validado = false;
                }

                //RADIOGROUP 14
                radioButtonID = rg14.getCheckedRadioButtonId();
                if(radioButtonID != -1){
                    radioButton = root.findViewById(radioButtonID);
                    estadoEppList.add(new EstadoEpp(null,"Protector Solar",radioButton.getText().toString()));
                }else {
                    cv14.setBackgroundColor(Color.parseColor("#16D50000"));
                    validado = false;
                }

                //RADIOGROUP 15
                if(cv15.getVisibility() == View.VISIBLE){
                    radioButtonID = rg15.getCheckedRadioButtonId();
                    if(radioButtonID != -1){
                        radioButton = root.findViewById(radioButtonID);
                        estadoEppList.add(new EstadoEpp(null,textViewList.get(0).getText().toString(),radioButton.getText().toString()));
                    }else {
                        cv15.setBackgroundColor(Color.parseColor("#16D50000"));
                        validado = false;
                    }
                }

                //RADIOGROUP 16
                if(cv16.getVisibility() == View.VISIBLE){
                    radioButtonID = rg16.getCheckedRadioButtonId();
                    if(radioButtonID != -1){
                        radioButton = root.findViewById(radioButtonID);
                        estadoEppList.add(new EstadoEpp(null,textViewList.get(1).getText().toString(),radioButton.getText().toString()));
                    }else {
                        cv16.setBackgroundColor(Color.parseColor("#16D50000"));
                        validado = false;
                    }
                }

                //RADIOGROUP 17
                if(cv17.getVisibility() == View.VISIBLE){
                    radioButtonID = rg17.getCheckedRadioButtonId();
                    if(radioButtonID != -1){
                        radioButton = root.findViewById(radioButtonID);
                        estadoEppList.add(new EstadoEpp(null,textViewList.get(2).getText().toString(),radioButton.getText().toString()));
                    }else {
                        cv17.setBackgroundColor(Color.parseColor("#16D50000"));
                        validado = false;
                    }
                }

                //RADIOGROUP 18
                if(cv18.getVisibility() == View.VISIBLE){
                    radioButtonID = rg18.getCheckedRadioButtonId();
                    if(radioButtonID != -1){
                        radioButton = root.findViewById(radioButtonID);
                        estadoEppList.add(new EstadoEpp(null,textViewList.get(3).getText().toString(),radioButton.getText().toString()));
                    }else {
                        cv18.setBackgroundColor(Color.parseColor("#16D50000"));
                        validado = false;
                    }
                }

                //RADIOGROUP 19
                if(cv19.getVisibility() == View.VISIBLE){
                    radioButtonID = rg19.getCheckedRadioButtonId();
                    if(radioButtonID != -1){
                        radioButton = root.findViewById(radioButtonID);
                        estadoEppList.add(new EstadoEpp(null,textViewList.get(4).getText().toString(),radioButton.getText().toString()));
                    }else {
                        cv19.setBackgroundColor(Color.parseColor("#16D50000"));
                        validado = false;
                    }
                }

                //RADIOGROUP 20
                if(cv20.getVisibility() == View.VISIBLE){
                    radioButtonID = rg20.getCheckedRadioButtonId();
                    if(radioButtonID != -1){
                        radioButton = root.findViewById(radioButtonID);
                        estadoEppList.add(new EstadoEpp(null,textViewList.get(5).getText().toString(),radioButton.getText().toString()));
                    }else {
                        cv20.setBackgroundColor(Color.parseColor("#16D50000"));
                        validado = false;
                    }
                }

                //RADIOGROUP 21
                if(cv21.getVisibility() == View.VISIBLE){
                    radioButtonID = rg21.getCheckedRadioButtonId();
                    if(radioButtonID != -1){
                        radioButton = root.findViewById(radioButtonID);
                        estadoEppList.add(new EstadoEpp(null,textViewList.get(6).getText().toString(),radioButton.getText().toString()));
                    }else {
                        cv21.setBackgroundColor(Color.parseColor("#16D50000"));
                        validado = false;
                    }
                }

                //RADIOGROUP 22
                if(cv22.getVisibility() == View.VISIBLE){
                    radioButtonID = rg22.getCheckedRadioButtonId();
                    if(radioButtonID != -1){
                        radioButton = root.findViewById(radioButtonID);
                        estadoEppList.add(new EstadoEpp(null,textViewList.get(7).getText().toString(),radioButton.getText().toString()));
                    }else {
                        cv22.setBackgroundColor(Color.parseColor("#16D50000"));
                        validado = false;
                    }
                }

                //RADIOGROUP 23
                if(cv23.getVisibility() == View.VISIBLE){
                    radioButtonID = rg23.getCheckedRadioButtonId();
                    if(radioButtonID != -1){
                        radioButton = root.findViewById(radioButtonID);
                        estadoEppList.add(new EstadoEpp(null,textViewList.get(8).getText().toString(),radioButton.getText().toString()));
                    }else {
                        cv23.setBackgroundColor(Color.parseColor("#16D50000"));
                        validado = false;
                    }
                }

                //RADIOGROUP 24
                if(cv24.getVisibility() == View.VISIBLE){
                    radioButtonID = rg24.getCheckedRadioButtonId();
                    if(radioButtonID != -1){
                        radioButton = root.findViewById(radioButtonID);
                        estadoEppList.add(new EstadoEpp(null,textViewList.get(9).getText().toString(),radioButton.getText().toString()));
                    }else {
                        cv24.setBackgroundColor(Color.parseColor("#16D50000"));
                        validado = false;
                    }
                }

                if(validado==true){

                    ((Revision_epp_dt)getActivity()).revisionEppList(estadoEppList);
                    ((Revision_epp_dt)getActivity()).trabajadorChecado(true);
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.ct_revisonEpp, new Estado_epp()).commit();

                }else{
                    Toast.makeText(getContext(),"Debe completar todos los campos",Toast.LENGTH_LONG).show();

                }


                }
        });

        return root;
    }

}
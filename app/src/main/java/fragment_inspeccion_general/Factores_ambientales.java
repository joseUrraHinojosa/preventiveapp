package fragment_inspeccion_general;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.core.content.FileProvider;
import androidx.fragment.app.Fragment;

import android.os.Environment;
import android.provider.MediaStore;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.jose.preventiveapp.Inspeccion_general_dt;
import com.jose.preventiveapp.R;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import dto.FactoresAmbientales;
import dto.Fotografia;
import dto.Observaciones;

import static android.app.Activity.RESULT_OK;


public class Factores_ambientales extends Fragment {

    public Factores_ambientales() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    private FloatingActionButton fBtn_more,fBtn_obs, fBtn_camara;
    private Button btn_sig;
    private CardView cv1,cv2,cv3,cv4,cv5,cv6,cv7,cv8,cv9,cv10;
    private RadioGroup rg1,rg2,rg3,rg4,rg5,rg6,rg7,rg8,rg9,rg10;
    private RadioButton radioButton;
    private String currentPhotoPath="";
    private String nomFoto;
    private List<Fotografia> rutasFotos = new ArrayList<>();
    private List<String> nullList = new ArrayList<>();
    private ImageView imgAtras;

    private Dialog dialog;
    private Button btnCancelar, btnGuardar;
    private EditText edObs;
    private boolean obsOk = false;
    private Observaciones obs = new Observaciones();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_factores_ambientales, container, false);



        imgAtras =(ImageView)root.findViewById(R.id.iv_atras_ce);
        btn_sig = (Button)root.findViewById(R.id.btn_siguiente);
        fBtn_more = (FloatingActionButton) root.findViewById(R.id.fb_more);
        fBtn_obs = (FloatingActionButton) root.findViewById(R.id.fb_observaciones);
        fBtn_camara = (FloatingActionButton) root.findViewById(R.id.fb_camara);


        cv1 =(CardView)root.findViewById(R.id.cv1);
        cv2 =(CardView)root.findViewById(R.id.cv2);
        cv3 =(CardView)root.findViewById(R.id.cv3);
        cv4 =(CardView)root.findViewById(R.id.cv4);
        cv5 =(CardView)root.findViewById(R.id.cv5);
        cv6 =(CardView)root.findViewById(R.id.cv6);
        cv7 =(CardView)root.findViewById(R.id.cv7);
        cv8 =(CardView)root.findViewById(R.id.cv8);
        cv9 =(CardView)root.findViewById(R.id.cv9);
        cv10 =(CardView)root.findViewById(R.id.cv10);

        rg1 =(RadioGroup)root.findViewById(R.id.rg1);
        rg2 =(RadioGroup)root.findViewById(R.id.rg2);
        rg3 =(RadioGroup)root.findViewById(R.id.rg3);
        rg4 =(RadioGroup)root.findViewById(R.id.rg4);
        rg5 =(RadioGroup)root.findViewById(R.id.rg5);
        rg6 =(RadioGroup)root.findViewById(R.id.rg6);
        rg7 =(RadioGroup)root.findViewById(R.id.rg7);
        rg8 =(RadioGroup)root.findViewById(R.id.rg8);
        rg9 =(RadioGroup)root.findViewById(R.id.rg9);
        rg10 =(RadioGroup)root.findViewById(R.id.rg10);


        imgAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eliminarFotosNULL();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.ct_inspeccion_general, new Condiciones_estructurales()).commit();
            }
        });

        fBtn_obs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(obsOk==false){
                    openDialog();
                }else{
                    Toast.makeText(getContext(),"Ya se ingresaron observaciones",Toast.LENGTH_LONG).show();
                }
            }
        });

        //hace visible e invisible boton obsevaciones y camara
        fBtn_more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(fBtn_camara.getVisibility() == View.INVISIBLE){
                    fBtn_obs.setVisibility(View.VISIBLE);
                    fBtn_camara.setVisibility(View.VISIBLE);
                }else{
                    fBtn_obs.setVisibility(View.INVISIBLE);
                    fBtn_camara.setVisibility(View.INVISIBLE);
                }

            }
        });

        fBtn_camara.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dispatchTakePictureIntent();
            }


        });



        btn_sig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //validación y registro
                boolean validado = true;
                int radioButtonID = -1;
                FactoresAmbientales ambientales = new FactoresAmbientales();

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

                radioButtonID = rg1.getCheckedRadioButtonId();
                if(radioButtonID != -1){
                    radioButton = root.findViewById(radioButtonID);
                    ambientales.setCriterio1(radioButton.getText().toString());

                }else {
                    cv1.setBackgroundColor(Color.parseColor("#16D50000"));
                    validado = false;
                }

                radioButtonID = rg2.getCheckedRadioButtonId();
                if(radioButtonID != -1){
                    radioButton = root.findViewById(radioButtonID);
                    ambientales.setCriterio2(radioButton.getText().toString());

                }else {
                    cv2.setBackgroundColor(Color.parseColor("#16D50000"));
                    validado = false;
                }

                radioButtonID = rg3.getCheckedRadioButtonId();
                if(radioButtonID != -1){
                    radioButton = root.findViewById(radioButtonID);
                    ambientales.setCriterio3(radioButton.getText().toString());

                }else {
                    cv3.setBackgroundColor(Color.parseColor("#16D50000"));
                    validado = false;
                }

                radioButtonID = rg4.getCheckedRadioButtonId();
                if(radioButtonID != -1){
                    radioButton = root.findViewById(radioButtonID);
                    ambientales.setCriterio4(radioButton.getText().toString());

                }else {
                    cv4.setBackgroundColor(Color.parseColor("#16D50000"));
                    validado = false;
                }

                radioButtonID = rg5.getCheckedRadioButtonId();
                if(radioButtonID != -1){
                    radioButton = root.findViewById(radioButtonID);
                    ambientales.setCriterio5(radioButton.getText().toString());

                }else {
                    cv5.setBackgroundColor(Color.parseColor("#16D50000"));
                    validado = false;
                }

                radioButtonID = rg6.getCheckedRadioButtonId();
                if(radioButtonID != -1){
                    radioButton = root.findViewById(radioButtonID);
                    ambientales.setCriterio6(radioButton.getText().toString());

                }else {
                    cv6.setBackgroundColor(Color.parseColor("#16D50000"));
                    validado = false;
                }

                radioButtonID = rg7.getCheckedRadioButtonId();
                if(radioButtonID != -1){
                    radioButton = root.findViewById(radioButtonID);
                    ambientales.setCriterio7(radioButton.getText().toString());

                }else {
                    cv7.setBackgroundColor(Color.parseColor("#16D50000"));
                    validado = false;
                }

                radioButtonID = rg8.getCheckedRadioButtonId();
                if(radioButtonID != -1){
                    radioButton = root.findViewById(radioButtonID);
                    ambientales.setCriterio8(radioButton.getText().toString());

                }else {
                    cv8.setBackgroundColor(Color.parseColor("#16D50000"));
                    validado = false;
                }

                radioButtonID = rg9.getCheckedRadioButtonId();
                if(radioButtonID != -1){
                    radioButton = root.findViewById(radioButtonID);
                    ambientales.setCriterio9(radioButton.getText().toString());

                }else {
                    cv9.setBackgroundColor(Color.parseColor("#16D50000"));
                    validado = false;
                }

                radioButtonID = rg10.getCheckedRadioButtonId();
                if(radioButtonID != -1){
                    radioButton = root.findViewById(radioButtonID);
                    ambientales.setCriterio10(radioButton.getText().toString());

                }else {
                    cv10.setBackgroundColor(Color.parseColor("#16D50000"));
                    validado = false;
                }

                ambientales.setFotos(rutasFotos);
                Activity activity =getActivity();

                eliminarFotosNULL();
                if(validado){
                    ((Inspeccion_general_dt)activity).factoresAmbientales(ambientales);
                    ((Inspeccion_general_dt)getContext()).obsAmbientales(obs);
                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.ct_inspeccion_general, new Equipos_herramientas_otros()).commit();

                }else{
                    Toast.makeText(getContext(),"Debe completar todos los campos",Toast.LENGTH_LONG).show();
                }



            }
        });


        return root;
    }


    //crea nombre y archivo para la fotografia
    private File createImageFile() throws IOException {

        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String imageFileName = "JPEG_" + timeStamp + "_";
        File storageDir = getContext().getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        nomFoto= imageFileName;
        File image = File.createTempFile(
                imageFileName,
                ".jpg",
                storageDir
        );

        currentPhotoPath = image.getAbsolutePath();
        nullList.add(currentPhotoPath);

        return image;
    }
    static final int REQUEST_TAKE_PHOTO = 1;

    private void dispatchTakePictureIntent() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);

        if (takePictureIntent.resolveActivity(getContext().getPackageManager()) != null) {

            File photoFile = null;
            try {
                photoFile = createImageFile();
            } catch (IOException ex) {

            }

            if (photoFile != null) {
                Uri photoURI = FileProvider.getUriForFile(getContext(),
                        "com.jose.preventiveapp", photoFile);
                takePictureIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI);
                startActivityForResult(takePictureIntent, REQUEST_TAKE_PHOTO);

            }
        }
    }

    int nFoto=0;
    @Override
    public void onActivityResult(int idPeticion, int resultCode, @Nullable Intent data) {
        super.onActivityResult(idPeticion, resultCode, data);

        if(idPeticion == REQUEST_TAKE_PHOTO && resultCode == RESULT_OK){
            nFoto++;

            rutasFotos.add(new Fotografia(nomFoto,currentPhotoPath));

            Toast toast = Toast.makeText(getContext(), "Captura fotográfica N°"+nFoto, Toast.LENGTH_SHORT);
            toast.setGravity(Gravity.CENTER, 0, 0);
            TextView tv = (TextView) toast.getView().findViewById(android.R.id.message);
            if (null!=tv) {

                tv.setCompoundDrawablesWithIntrinsicBounds(R.drawable.ic_imagen_20, 0, 0, 0);
                tv.setCompoundDrawablePadding(getContext().getResources().getDimensionPixelSize(R.dimen.fab_margin));
            }
            toast.show();
        }
    }


    public  void openDialog(){

        dialog = new Dialog(getContext());
        dialog.setContentView(R.layout.item_observaciones);
        //dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.WHITE));

        edObs = dialog.findViewById(R.id.mtextObs);
        btnGuardar = dialog.findViewById(R.id.btnGuardar);
        btnCancelar = dialog.findViewById(R.id.btnCancelar);

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(edObs.getText().toString().equals("")){
                    Toast.makeText(getActivity(),"EL campo no puede quedar en blanco",Toast.LENGTH_SHORT).show();
                }else{
                    obs.setTitulo("Factores ambientales");
                    obs.setDescripcion(edObs.getText().toString().trim());
                    dialog.dismiss();
                    obsOk=true;

                }

            }
        });

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();

    }

    //Elimina archivo de futo que crea pero se anula al retroceder o al cancelar la captura de fotografía
    public void eliminarFotosNULL(){

        for(String actual: nullList){
            boolean existe =false;

            for(Fotografia obj: rutasFotos){
                if(obj.getRutaFoto().equals(actual)){
                    existe=true;
                }
            }

            if(existe==false){

                File fdelete = new File(actual);
                if (fdelete.exists()) {
                    fdelete.delete();

                }

            }

        }
    }




}
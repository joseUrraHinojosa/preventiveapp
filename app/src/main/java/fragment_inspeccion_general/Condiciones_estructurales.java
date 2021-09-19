package fragment_inspeccion_general;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
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

import dto.CondicionesEstructurales;
import dto.Fotografia;
import dto.Observaciones;

import static android.app.Activity.RESULT_OK;


public class Condiciones_estructurales extends Fragment {

    public Condiciones_estructurales() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private FloatingActionButton fBtn_more,fBtn_obs, fBtn_camara;
    private ImageView img_atras;
    private String currentPhotoPath="";
    private String nomFoto;
    private List<Fotografia>rutasFotos = new ArrayList<>();
    private List<String> nullList = new ArrayList<>();
    private RadioGroup rg1,rg2,rg3,rg4,rg5,rg6;
    private  RadioButton radioButton;
    private Button btn_sig;
    private CardView cv1,cv2,cv3,cv4,cv5,cv6;
    //Dialog
    private Dialog dialog;
    private Button btnCancelar, btnGuardar;
    private EditText edObs;
    private boolean obsOk = false;

    private List<Integer> numeros = new ArrayList<>();
    private Observaciones obs = new Observaciones();



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_condiciones_estructurales, container, false);


        // manda indice para eliminar las observaciones al retroceder de view
        fBtn_more = root.findViewById(R.id.fb_more);
        fBtn_obs = root.findViewById(R.id.fb_observaciones);
        fBtn_camara = root.findViewById(R.id.fb_camara);
        img_atras = root.findViewById(R.id.iv_atras_ce);
        btn_sig = root.findViewById(R.id.btn_siguiente);

        rg1 = root.findViewById(R.id.rg1);
        rg2 = root.findViewById(R.id.rg2);
        rg3 = root.findViewById(R.id.rg3);
        rg4 = root.findViewById(R.id.rg4);
        rg5 = root.findViewById(R.id.rg5);
        rg6 = root.findViewById(R.id.rg6);

        cv1 = root.findViewById(R.id.cv1);
        cv2 = root.findViewById(R.id.cv2);
        cv3 = root.findViewById(R.id.cv3);
        cv4 = root.findViewById(R.id.cv4);
        cv5 = root.findViewById(R.id.cv5);
        cv6 = root.findViewById(R.id.cv6);


/*
        Drawable myFabSrc = getResources().getDrawable(R.drawable.ic_add_50);
        Drawable myFabSrc2 = getResources().getDrawable(R.drawable.ic_camera_24);
        Drawable myFabSrc3 = getResources().getDrawable(R.drawable.ic_observaciones_24);

        Drawable willBeWhite = myFabSrc.getConstantState().newDrawable();
        Drawable willBeWhite2 = myFabSrc2.getConstantState().newDrawable();
        Drawable willBeWhite3 = myFabSrc3.getConstantState().newDrawable();

       //set the color filter, you can use also Mode.SRC_ATOP
        willBeWhite.mutate().setColorFilter(Color.WHITE, PorterDuff.Mode.MULTIPLY);
        willBeWhite2.mutate().setColorFilter(Color.WHITE, PorterDuff.Mode.MULTIPLY);
        willBeWhite3.mutate().setColorFilter(Color.WHITE, PorterDuff.Mode.MULTIPLY);
        //set it to your fab button initialized before
        fBtn_more.setImageDrawable(willBeWhite);
        fBtn_camara.setImageDrawable(willBeWhite2);
        fBtn_obs.setImageDrawable(willBeWhite3);

 */

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


        img_atras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                eliminarFotosNULL();
                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.ct_inspeccion_general, new Datos_generales()).commit();

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

                List<String> condicionesEstructuralesList = new ArrayList<>();
                CondicionesEstructurales estructurales = new CondicionesEstructurales();
                boolean validado = true;
                //validación y registro

                int radioButtonID = -1;

                cv1.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
                cv2.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
                cv3.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
                cv4.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
                cv5.setBackgroundColor(Color.parseColor("#FFFFFFFF"));
                cv6.setBackgroundColor(Color.parseColor("#FFFFFFFF"));


                radioButtonID = rg1.getCheckedRadioButtonId();
                if(radioButtonID != -1){
                    radioButton = root.findViewById(radioButtonID);
                    estructurales.setCriterio1(radioButton.getText().toString());

                }else {
                    cv1.setBackgroundColor(Color.parseColor("#16D50000"));
                    validado = false;
                }


                radioButtonID = rg2.getCheckedRadioButtonId();
                if(radioButtonID != -1){
                    radioButton = root.findViewById(radioButtonID);
                    estructurales.setCriterio2(radioButton.getText().toString());
                }else {
                    cv2.setBackgroundColor(Color.parseColor("#16D50000"));
                    validado = false;
                }

                radioButtonID = rg3.getCheckedRadioButtonId();
                if(radioButtonID != -1){
                    radioButton = root.findViewById(radioButtonID);
                    estructurales.setCriterio3(radioButton.getText().toString());
                }else {
                    cv3.setBackgroundColor(Color.parseColor("#16D50000"));
                    validado = false;
                }

                radioButtonID = rg4.getCheckedRadioButtonId();
                if(radioButtonID != -1){
                    radioButton = root.findViewById(radioButtonID);
                    estructurales.setCriterio4(radioButton.getText().toString());
                }else {
                    cv4.setBackgroundColor(Color.parseColor("#16D50000"));
                    validado = false;
                }

                radioButtonID = rg5.getCheckedRadioButtonId();
                if(radioButtonID != -1){
                    radioButton = root.findViewById(radioButtonID);
                    estructurales.setCriterio5(radioButton.getText().toString());
                }else {
                    cv5.setBackgroundColor(Color.parseColor("#16D50000"));
                    validado = false;
                }

                radioButtonID = rg6.getCheckedRadioButtonId();
                if(radioButtonID != -1){
                    radioButton = root.findViewById(radioButtonID);
                    estructurales.setCriterio6(radioButton.getText().toString());
                }else {
                    cv6.setBackgroundColor(Color.parseColor("#16D50000"));
                    validado = false;
                }
                estructurales.setFotos(rutasFotos);
                eliminarFotosNULL();


                if(validado){


                    Activity activity =getActivity();
                   ((Inspeccion_general_dt)activity).condicionesEstructurales(estructurales);
                   ((Inspeccion_general_dt)getContext()).obsEstructurales(obs);

                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.ct_inspeccion_general, new Factores_ambientales()).commit();
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
            //Se agrega foto a la lista aqui para asegurar que se concreto la captura
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
                    obs.setTitulo("Condiciones estructurales");
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
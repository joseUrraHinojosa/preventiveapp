package fragment_inspeccion_general;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import androidx.annotation.RequiresApi;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageView;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.jose.preventiveapp.Inspeccion_general_dt;
import com.jose.preventiveapp.Menu;
import com.jose.preventiveapp.R;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import dao.UsuarioDAO;
import dto.DatosGenerales;
import dto.Usuario;


public class Datos_generales extends Fragment {

    public Datos_generales() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    private AutoCompleteTextView aut_duaracion_tra, aut_region, aut_ciudad, aut_comuna;
    private TextInputEditText a;
    private TextInputLayout tl_fecha;
    private EditText ed_fecha;
    private Button btn_siguiente;
    private ArrayList<TextInputLayout> list_textInputLayouts = new ArrayList<>();
    private ImageView imgAtras;

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_datos_generales, container, false);


        imgAtras= (ImageView)root.findViewById(R.id.iv_atras_dg) ;
        list_textInputLayouts.add(root.findViewById(R.id.nom_empresa));
        list_textInputLayouts.add(root.findViewById(R.id.cod_sitio));
        list_textInputLayouts.add(root.findViewById(R.id.tipo_tra));
        list_textInputLayouts.add(root.findViewById(R.id.duracion_tra));
        list_textInputLayouts.add(root.findViewById(R.id.direccion));
        list_textInputLayouts.add(root.findViewById(R.id.region));
        list_textInputLayouts.add(root.findViewById(R.id.ciudad));
        list_textInputLayouts.add(root.findViewById(R.id.comuna));
        list_textInputLayouts.add(root.findViewById(R.id.tl_fecha));


        aut_duaracion_tra = root.findViewById(R.id.aut_duracion_tra);
        aut_region = root.findViewById(R.id.aut_region);
        aut_ciudad = root.findViewById(R.id.aut_ciudad);
        aut_comuna = root.findViewById(R.id.aut_comuna);
        ed_fecha = root.findViewById(R.id.ed_fecha);
        tl_fecha = root.findViewById(R.id.tl_fecha);
        btn_siguiente = root.findViewById(R.id.btn_dg_sig);

        rellenaDuracionTrabajo();
        rellenaRegion();

        list_textInputLayouts.get(5).getEditText().setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    list_textInputLayouts.get(6).getEditText().setText("");
                    list_textInputLayouts.get(7).getEditText().setText("");
                }
            }
        });


        list_textInputLayouts.get(6).getEditText().setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    rellenaCiudad();
                    list_textInputLayouts.get(7).getEditText().setText("");
                }
            }
        });

        list_textInputLayouts.get(7).getEditText().setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    rellenaComuna();
                }
            }
        });



        imgAtras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getContext(), Menu.class)
                        .addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP));
                getActivity().finish();

            }
        });


        Activity activity =getActivity();


        btn_siguiente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean validado = true;
                //validaci??n de campos
                for(TextInputLayout actual: list_textInputLayouts){
                    if(actual.getEditText().getText().toString().trim().equals("")){
                        actual.setErrorEnabled(true);
                        actual.setError("Debe completar este campo");
                        validado = false;
                    }else if(actual.getEditText().getText().toString().trim().length()>60){

                        actual.setErrorEnabled(true);
                        actual.setError("No se acepta mas de 60 caracteres");
                        validado = false;
                    }else{
                        actual.setErrorEnabled(false);
                    }
                }

                if(validado){

                    DatosGenerales d = new DatosGenerales();
                    d.setEmpresaMandante(list_textInputLayouts.get(0).getEditText().getText().toString().trim());
                    d.setCodigoSitio(list_textInputLayouts.get(1).getEditText().getText().toString().trim());
                    d.setTipoTrabajo(list_textInputLayouts.get(2).getEditText().getText().toString().trim());
                    d.setDuracionTrabajo(list_textInputLayouts.get(3).getEditText().getText().toString().trim());
                    d.setDireccion(list_textInputLayouts.get(4).getEditText().getText().toString().trim());
                    d.setCiudad(list_textInputLayouts.get(6).getEditText().getText().toString().trim());
                    d.setComuna(list_textInputLayouts.get(7).getEditText().getText().toString().trim());
                    d.setFecha(list_textInputLayouts.get(8).getEditText().getText().toString().trim());
                    Date date = new Date();
                    String formato ="hh:mm:ss";
                    SimpleDateFormat hora = new SimpleDateFormat(formato);
                    d.setHora(hora.format(date));
                    Usuario usu =  new UsuarioDAO().mostrarUsuario(getContext());
                    d.setSupervisor(usu.getNombre()+" "+usu.getPaterno()+" "+usu.getMaterno());
                    d.setRunSupervisor(usu.getRun());


                    //((Inspeccion_general_dt)activity).datosGenerales(datosGeneralesList);
                    ((Inspeccion_general_dt)activity).datosGenerales(d);

                    getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.ct_inspeccion_general, new Condiciones_estructurales()).commit();
                }


            }
        });

         // FALTA CORREGIR****************
        Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR);
        final int month = calendar.get(Calendar.MONTH);
        final int day = calendar.get(Calendar.DAY_OF_MONTH);



        ed_fecha.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                if(hasFocus==true){
                    DatePickerDialog datePickerDialog = new DatePickerDialog(

                            getContext(), new DatePickerDialog.OnDateSetListener() {
                        @Override
                        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                            Calendar cal = Calendar.getInstance();
                            cal.set(year, month, dayOfMonth);
                            Date date = cal.getTime();
                            String fechaFormateada =new SimpleDateFormat("dd/MM/yyyy").format(date);

                            ed_fecha.setText(fechaFormateada);
                        }
                    },year,month,day);

                    datePickerDialog.show();
                }else{

                }




            }
        });


        return  root;
    }

    public  void rellenaDuracionTrabajo(){
        String[] sp = {"1 hora","2 horas","3 horas","4 horas","5 horas","6 horas","7 horas"
                ,"8 horas","9 horas","10 horas","11 horas","12 horas","1 d??a","2 d??as","3 d??as","4 d??as","5 d??as","6 d??as"
                ,"1 semana","2 semanas","3 semanas","1 mes","M??s de un mes"};

        ArrayAdapter arrayAdapter = new ArrayAdapter(getContext(),R.layout.opcion_item, sp);
        aut_duaracion_tra.setAdapter(arrayAdapter);
    }

    public void rellenaRegion(){

        String[] sp ={"I Regi??n de Tarapac??","II Regi??n de Antofagasta","III Regi??n de Atacama",
                "IV Regi??n de Coquimbo","V Regi??n de Valpara??so","VI Regi??n del Libertador General Bernardo O???Higgins","VII Regi??n del Maule",
                "VIII Regi??n del Biob??o","IX Regi??n de La Araucan??a","X Regi??n de Los Lagos","XI Regi??n Ays??n del General Carlos Ib????ez del Campo",
                "XII Regi??n de Magallanes y Ant??rtica Chilena","Regi??n Metropolitana de Santiago","XIV Regi??n de Los R??os","XV Regi??n de Arica y Parinacota",
                "XVI Regi??n de ??uble"};
        ArrayAdapter arrayAdapter = new ArrayAdapter(getContext(),R.layout.opcion_item, sp);
        aut_region.setAdapter(arrayAdapter);

    }

    public  void rellenaCiudad(){

        String[] sp_seleccionado = {""};
        String[] sp_tarapaca = {"Alto Hospicio","Cami??a","Colchane","Huara","Iquique","Pica","Pozo Almonte","Otra"};

        String[] sp_antofagasta = {"Antofagasta","Calama","Mar??a Elena","Mejillones","Ollag??e","San Pedro de Atacama","Sierra Gorda",
                "Taltal","Tocopilla","Otra"};

        String[] sp_atacama = {"Alto del Carmen","Caldera","Cha??aral","Copiap??","Diego de Almagro","Freirina","Huasco",
                "Tierra Amarilla","vallenar"};

        String[] sp_coquimbo = {"Andacollo","Canela","Combarbal??","Coquimbo","Illapel","La Higuera","La Serena",
                "Los Vilos","Monte patria","Ovalle","Paiguano","Punitaqui","R??o Hurtado","Salamanca","Vicu??a","Otra"};

        String[] sp_valparaiso= {"Algarrobo","Cabildo","Calle larga","Cartagena","Casablanca","Catemu","Conc??n",
                "El Quisco","El Tabo","Hijuelas","Isla de pascua","Juan Fern??ndez","La Calera","La Cruz","La Ligua","Limache","Llaillay","Los Andes",
                "Nogales","Olmu??","Panquehue","Papudo","Petorca","Puchuncav??","Putaendo","Quillota","Quilpu??","Quintero","Rinconada","San Antonio","San Esteban",
                "San Felipe","Santa Mar??a","Santo Domingo","Valparaiso","Villa Alemana","Vi??a del Mar","Zapallar","Otra"};

        String[] sp_ohiggins = {"Ch??pica","Chimbarongo","Codegua","Coinco","Coltauco","Do??ihue","Graneros","La Estrella","Las Cabras",
                "Litueche","Lolol","Machal??","Malloa","Marchihue","Mostazal","Nancagua","Navidad","Olivar","Palmillas","Paredones","Peralillo","Peumo","Pichidegua","Pichilemu",
                "Placilla","Pumanque","Quinta de Tilcoco","Rancagua","Rengo","Requinoa","San fernando","Santa Cruz","San Vicente","Otra"};

        String[] sp_maule = {"Cauquenes","Chanco","Colb??n","Constituci??n","Curepto","Curic??","Huala????","Licant??n","Linares","Longav??",
                "Maule","Molina","Parral","Pelarco","Pelluhue","Pencahue","Rauco","Retiro","R??o Claro","Romeral","Sagrada Familia","San Clemente","San Javier","San Rafael","Talca","Te??o",
                "Vichuqu??n","Villa Alegre","Yerbas Buenas","Otra"};

        String[]sp_biobio ={"Alto Biob??o","Antuco","Arauco","Cabrero","Ca??ete","Chiguayante","Concepci??n","Contulmo","Coronel","Curanilahue",
                "Florida","Hualp??n","Hualqui","Laja","Lebu","Los ??lamos","Los Angeles","Lota","Mulch??n","Nacimiento","Negrete","Penco","Quilaco","Quilleco","San Pedro de la Paz",
                "San Rosendo","Santa B??rbara","Santa Juana","Talcahuano","Tir??a","Tom??","Tucapel","Yumbel","Otra"};

        String[]sp_araucania={"Angol","Carahue","Cholchol","Collipulli","Cunco","Curacant??n","Curarrehue","Ercilla","Freire","Galvarino","Gorbea"
                ,"Lautaro","Loncoche","Lonquimay","Los Sauces","Lumaco","Melipeuco","Nueva Imperial","Padre Las Casas","Perquenco","Pitrufqu??n","Puc??n","Pur??n"
                ,"Renaico","Saavedra","Temuco","Teodoro Schmidt","Tolt??n","Traigu??n","Victoria","Vilc??n","Villarrica","Otra"};

        String[]sp_Los_Lagos={"Ancud","Calbuco","Castro","Chait??n","Chonchi","Cocham??","Curaco de V??lez","Dalcahue","Fresia","Frutillar","Futaleuf??",
                "Hualaihu??","Llanquihue","Los Muermos","Maull??n","Osorno","Palena","Puerto Montt","Puerto Octay","Puerto Varas","Puqueld??n","Purranque","Puyehue",
                "Queil??n","Quell??n","Quemchi","Quinchao","R??o Negro","San Juan de la Costa","San Pablo","Otra"};

        String[]sp_Aysen ={"Ais??n","Chile Chico","Cisnes","Cochrane","Coihaique","Guaitecas","Lago Verde","O'Higgins","R??o Iba??ez","Tortel","Otra"};

        String[]sp_magallanes = {"Ant??rtica","Cabo de Hornos","Laguna Blanca","Natales","Porvenir","Primavera","Punta Arenas","R??o Verde","San Gregorio","Timaukel","Torres del Paine","Otra"};

        String[]sp_region_metropolitana = {"Santiago","Colina","Puente Alto","Sant Bernardo","Melipilla","Talagante","otra"};

        String[]sp_los_rios= {"Corral","Futrono","Lago Ranco","Lanco","La Uni??n","Los Lagos","M??fil","Paillaco","Panguipulli","R??o Bueno","San Jos?? de la Mariquina","Valdivia","Otra"};

        String[]sp_arica_y_parinacota = {"Arica","Camarones","General Lagos","Putre","Otra"};

        String[]sp_??uble = {"Bulnes","Chill??n","Chill??n Viejo","Cobquecura","Coelemu","Coihueco","El Carmen","Ninhue","??iqu??n","Pemuco","Pinto","Portezuelo","Quill??n","Quirihue","R??nquil","San Carlos",
                "San Fabi??n","San Ignacio","San Nicol??s","Treguaco","Yungay","Otra"};



        if(!list_textInputLayouts.get(5).getEditText().getText().equals("")){

            String reg_seleccionada = list_textInputLayouts.get(5).getEditText().getText().toString();
            switch (reg_seleccionada){
                case "I Regi??n de Tarapac??":  sp_seleccionado=sp_tarapaca;
                    break;
                case "II Regi??n de Antofagasta": sp_seleccionado=sp_antofagasta;
                    break;
                case "III Regi??n de Atacama": sp_seleccionado=sp_atacama;
                    break;
                case "IV Regi??n de Coquimbo":  sp_seleccionado=sp_coquimbo;
                    break;
                case "V Regi??n de Valpara??so": sp_seleccionado=sp_valparaiso;
                    break;
                case "VI Regi??n del Libertador General Bernardo O???Higgins": sp_seleccionado=sp_ohiggins;
                    break;
                case "VII Regi??n del Maule": sp_seleccionado=sp_maule;
                    break;
                case "VIII Regi??n del Biob??o": sp_seleccionado=sp_biobio;
                    break;
                case "IX Regi??n de La Araucan??a":  sp_seleccionado=sp_araucania;
                    break;
                case "X Regi??n de Los Lagos": sp_seleccionado=sp_Los_Lagos;
                    break;
                case "XI Regi??n Ays??n del General Carlos Ib????ez del Campo": sp_seleccionado=sp_Aysen;
                    break;
                case "XII Regi??n de Magallanes y Ant??rtica Chilena": sp_seleccionado=sp_magallanes;
                    break;
                case "Regi??n Metropolitana de Santiago": sp_seleccionado=sp_region_metropolitana ;
                    break;
                case "XIV Regi??n de Los R??os": sp_seleccionado=sp_los_rios ;
                    break;
                case "XV Regi??n de Arica y Parinacota": sp_seleccionado=sp_arica_y_parinacota;
                    break;
                case "XVI Regi??n de ??uble": sp_seleccionado=sp_??uble;
                    break;

            }

        }

        ArrayAdapter arrayAdapter = new ArrayAdapter(getContext(),R.layout.opcion_item, sp_seleccionado);
        aut_ciudad.setAdapter(arrayAdapter);
    }


    public void rellenaComuna(){
        String[] sp_seleccionado = {""};
        String[]sp_comunas_tarapaca ={"Iquique","Alto Hospicio","Pozo Almonte","Cami??a","Colchane","Comuna de Huara","Pica","Otra"};

        String[]sp_comunas_antofagasta ={"Antofagasta","Mejillones","Sierra Gorda","Taltal","Calama","Ollague","San Pedro de Atacama","Tocopilla","Mar??a Elena","Otra"};

        String[]sp_comunas_atacama ={"Cha??aral","Diego de Almagro","Copiap??","Caldera","Tierra Amarilla","Vallenar","Alto del Carmen","Freirina","Huasco","Otra"};

        String[]sp_comunas_coquimbo ={"Illapel","Canela","Los Vilos","Salamanca","Coquimbo","Andacollo","La Higuera","La Serena","Paihuano","Vicu??a","Ovalle"
                ,"Combarbal??","Monte Patria","Punitaqui","R??o Hurtado","Otra"};

        String[]sp_comunas_valparaiso ={"Isla de Pascua","Los Andes","Calle Larga","Rinconada","San Esteban","Quilpu??","Limache","Olmu??","Villa Alemana","La Ligua"
                ,"Cabildo","Papudo","Petorca","Zapallar","Quillota","Hijuelas","La Calera","La Cruz","Nogales","San Antonio","Algarrobo","Cartagena","El Quisco","El Tabo","Santo Domingo"
                ,"San Felipe","Catemu","Llay Llay","Panquehue","Putaendo","Santa Mar??a","Valpara??so","Casablanca","Conc??n","Juan Fern??ndez","Puchuncav??","Quintero","Vi??a del Mar","Otra"};

        String[]sp_comunas_ogiggins ={"Rancagua","Codegua","Coinco","Coltauco","Do??ihue","Graneros","Las Cabras","Machal??","Malloa","Mostazal","Olivar","Peumo","Pichidegua","Quinta de Tilcoco"
                ,"Rengo","Requ??noa","San Vicente de Tagua Tagua","Pichilemu","La Estrella","Litueche","Marchigue","Navidad","Paredones","Ch??pica","Chimbarongo","Lolol","Nancagua","Palmilla","Peralillo","Placilla"
                ,"Pumanque","Santa Cruz","Otra"};

        String[]sp_comunas_maule ={"Cauquenes","Chanco","Pelluhue","Curic??","Huala????","Licant??n","Molina","Rauco","Romeral","Sangrada Familia","Teno","Vichuqu??n"
                ,"Linares","Colb??n","Longav??","Parral","Retiro","San Javier","Villa Alegre","Yerbas Buenas","Talca","Constituci??n","Curepto","Empedrado","Maule","Pelarco","Pencahue","R??o Claro","San Clemente","San Rafael","Otra"};

        String[]sp_comunas_biobio ={"Lebu","Arauco","Ca??ete","Contulmo","Curanilahue","Los ??lamos","Tir??a","Los ??ngeles","Alto B??o B??o","Antuco","Cabrero","Laja","Mulch??n"
                ,"Nacimiento","Negrete","Quilaco","Quilleco","San Rosendo","Santa B??rbara","Tucapel","Yumbel","Concepci??n","Chiguayante","Coronel","Florida","Hualp??n","Hualqui","Lota","Penco","San Pedro de la Paz"
                ,"Santa Juana","Talcahuano","Tom??","Otra"};

        String[]sp_comunas_araucania ={"Temuco","Carahue","Chol Chol","Cunco","Curarrehue","Freire","Glvarino","Gorbea","Lautaro","Loncoche","Melipeuco","Nueva Imperial","Padre Las Casas"
                ,"Perquenco","Pitrufqu??n","Puc??n","Saavedra","Teodoro Schmidt","Tolt??n","Vilc??n","Villarrica","Angol","Collipulli","Curacaut??n","Ercilla","Lonquimay","Los Sauces","Lumaco","Pur??n","Renaico","Traigu??n","Victoria","Otra"};

        String[]sp_comunas_losLagos ={"Castro","Ancud","Chonchi","Curaco de V??lez","Dalcahue","Puqueld??n","Queil??n","Quell??n","Quemchi","Quinchao","Puerto Montt","Calbuco","Cocham??","Fresio","Frutillar","Los Muermos","Llanquihue"
                ,"Maull??n","Puerto Varas","Osorno","Puerto Octay","Purranque","Puyehue","R??o Negro","San Juan de la Costa","San Pablo","Chait??n","Futaleuf??","Hualaihu??","Palena","Otra"};

        String[]sp_comunas_aysen ={"Ays??n","Cisnes","Guaitecas","Cochrane","O??Higgins","Tortel","Coyhaique","Lago Verde","Chile Chico","R??o Iba??ez","Otra"};

        String[]sp_comunas_magallanes ={"Cabo de Hornos","Ant??rtica","Punta Arenas","Laguna Blanca","R??o verde","San Gregorio","Porvenir","Primavera","Timaukel","Puerto Natales","Torres del Paine","Otra"};

        String[]sp_comunas_santiago ={"Colina","Lampa","Til Til","Puente Alto","Pirque","San Jos?? de Maipo","San Bernardo","Buin","Calera de Tango","Paine","Melipilla","Alhu??","Curacav??","Mar??a Pinto","San Pedro","Santiago","Cerrillos"
                ,"Cerro Navia","Conchal??","El Bosque","Estaci??n Central","Huechuraba","Independencia","La Cisterna","La Florida","La Granja","La Pintana","La Reina","Las Condes","Lo Barnechea","Lo Espejo","Lo Prado","Macul","Maip??","??u??oa","Pedro Aguirre Cerda"
                ,"Pe??alolol??n","Providencia","Pudahuel","Quilicura","Quinta Normal","Recoleta","Renca","San Juaqu??n","San Miguel","San Ram??n","Vitacura","Talagante","El monte","Isla de Miaipo","Padre Hurtado","Pe??aflor","Otra"};

        String[]sp_comunas_losRios ={"La Uni??n","Futrono","Lago Ranco","R??o Bueno","Valdivia","Corral","Lanco","Los Lagos","M??fil","Mariquina","Paillaco","Panguipulli","Otra"};

        String[]sp_comunas_arica ={"Arica","Camarones","Putre","General Lagos","Otra"};
        String[]sp_comunas_??uble ={"Bulnes","Chill??n","Chill??n Viejo","El Carmen","Pemuco","Pinto","Quill??n","San Ignacio","Yungay","Quirihue","Cobquecura","Coelemu","Ninhue","Portezuelo","R??nquil","Trehuaco","San Carlos","Coihueco","??iqu??n","San Fabi??n","San Nicol??s","Otra"};



        if(!list_textInputLayouts.get(5).getEditText().getText().equals("")) {
            String reg_seleccionada = list_textInputLayouts.get(5).getEditText().getText().toString();
            switch (reg_seleccionada) {
                case "I Regi??n de Tarapac??": sp_seleccionado = sp_comunas_tarapaca;
                    break;
                case "II Regi??n de Antofagasta": sp_seleccionado=sp_comunas_antofagasta;
                    break;
                case "III Regi??n de Atacama": sp_seleccionado=sp_comunas_atacama;
                    break;
                case "IV Regi??n de Coquimbo":  sp_seleccionado=sp_comunas_coquimbo;
                    break;
                case "V Regi??n de Valpara??so": sp_seleccionado=sp_comunas_valparaiso;
                    break;
                case "VI Regi??n del Libertador General Bernardo O???Higgins": sp_seleccionado=sp_comunas_ogiggins;
                    break;
                case "VII Regi??n del Maule": sp_seleccionado=sp_comunas_maule;
                    break;
                case "VIII Regi??n del Biob??o": sp_seleccionado=sp_comunas_biobio;
                    break;
                case "IX Regi??n de La Araucan??a":  sp_seleccionado=sp_comunas_araucania;
                    break;
                case "X Regi??n de Los Lagos": sp_seleccionado=sp_comunas_losLagos;
                    break;
                case "XI Regi??n Ays??n del General Carlos Ib????ez del Campo": sp_seleccionado=sp_comunas_aysen;
                    break;
                case "XII Regi??n de Magallanes y Ant??rtica Chilena": sp_seleccionado=sp_comunas_magallanes;
                    break;
                case "Regi??n Metropolitana de Santiago": sp_seleccionado=sp_comunas_santiago ;
                    break;
                case "XIV Regi??n de Los R??os": sp_seleccionado=sp_comunas_losRios ;
                    break;
                case "XV Regi??n de Arica y Parinacota": sp_seleccionado=sp_comunas_arica;
                    break;
                case "XVI Regi??n de ??uble": sp_seleccionado=sp_comunas_??uble;
                    break;

            }

        }
        ArrayAdapter arrayAdapter = new ArrayAdapter(getContext(),R.layout.opcion_item, sp_seleccionado);
        aut_comuna.setAdapter(arrayAdapter);
    }
}
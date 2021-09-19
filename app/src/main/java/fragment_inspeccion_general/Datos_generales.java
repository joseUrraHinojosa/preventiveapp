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
                //validación de campos
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
                ,"8 horas","9 horas","10 horas","11 horas","12 horas","1 día","2 días","3 días","4 días","5 días","6 días"
                ,"1 semana","2 semanas","3 semanas","1 mes","Más de un mes"};

        ArrayAdapter arrayAdapter = new ArrayAdapter(getContext(),R.layout.opcion_item, sp);
        aut_duaracion_tra.setAdapter(arrayAdapter);
    }

    public void rellenaRegion(){

        String[] sp ={"I Región de Tarapacá","II Región de Antofagasta","III Región de Atacama",
                "IV Región de Coquimbo","V Región de Valparaíso","VI Región del Libertador General Bernardo O’Higgins","VII Región del Maule",
                "VIII Región del Biobío","IX Región de La Araucanía","X Región de Los Lagos","XI Región Aysén del General Carlos Ibáñez del Campo",
                "XII Región de Magallanes y Antártica Chilena","Región Metropolitana de Santiago","XIV Región de Los Ríos","XV Región de Arica y Parinacota",
                "XVI Región de Ñuble"};
        ArrayAdapter arrayAdapter = new ArrayAdapter(getContext(),R.layout.opcion_item, sp);
        aut_region.setAdapter(arrayAdapter);

    }

    public  void rellenaCiudad(){

        String[] sp_seleccionado = {""};
        String[] sp_tarapaca = {"Alto Hospicio","Camiña","Colchane","Huara","Iquique","Pica","Pozo Almonte","Otra"};

        String[] sp_antofagasta = {"Antofagasta","Calama","María Elena","Mejillones","Ollagüe","San Pedro de Atacama","Sierra Gorda",
                "Taltal","Tocopilla","Otra"};

        String[] sp_atacama = {"Alto del Carmen","Caldera","Chañaral","Copiapó","Diego de Almagro","Freirina","Huasco",
                "Tierra Amarilla","vallenar"};

        String[] sp_coquimbo = {"Andacollo","Canela","Combarbalá","Coquimbo","Illapel","La Higuera","La Serena",
                "Los Vilos","Monte patria","Ovalle","Paiguano","Punitaqui","Río Hurtado","Salamanca","Vicuña","Otra"};

        String[] sp_valparaiso= {"Algarrobo","Cabildo","Calle larga","Cartagena","Casablanca","Catemu","Concón",
                "El Quisco","El Tabo","Hijuelas","Isla de pascua","Juan Fernández","La Calera","La Cruz","La Ligua","Limache","Llaillay","Los Andes",
                "Nogales","Olmué","Panquehue","Papudo","Petorca","Puchuncaví","Putaendo","Quillota","Quilpué","Quintero","Rinconada","San Antonio","San Esteban",
                "San Felipe","Santa María","Santo Domingo","Valparaiso","Villa Alemana","Viña del Mar","Zapallar","Otra"};

        String[] sp_ohiggins = {"Chépica","Chimbarongo","Codegua","Coinco","Coltauco","Doñihue","Graneros","La Estrella","Las Cabras",
                "Litueche","Lolol","Machalí","Malloa","Marchihue","Mostazal","Nancagua","Navidad","Olivar","Palmillas","Paredones","Peralillo","Peumo","Pichidegua","Pichilemu",
                "Placilla","Pumanque","Quinta de Tilcoco","Rancagua","Rengo","Requinoa","San fernando","Santa Cruz","San Vicente","Otra"};

        String[] sp_maule = {"Cauquenes","Chanco","Colbún","Constitución","Curepto","Curicó","Hualañé","Licantén","Linares","Longaví",
                "Maule","Molina","Parral","Pelarco","Pelluhue","Pencahue","Rauco","Retiro","Río Claro","Romeral","Sagrada Familia","San Clemente","San Javier","San Rafael","Talca","Teño",
                "Vichuquén","Villa Alegre","Yerbas Buenas","Otra"};

        String[]sp_biobio ={"Alto Biobío","Antuco","Arauco","Cabrero","Cañete","Chiguayante","Concepción","Contulmo","Coronel","Curanilahue",
                "Florida","Hualpén","Hualqui","Laja","Lebu","Los Álamos","Los Angeles","Lota","Mulchén","Nacimiento","Negrete","Penco","Quilaco","Quilleco","San Pedro de la Paz",
                "San Rosendo","Santa Bárbara","Santa Juana","Talcahuano","Tirúa","Tomé","Tucapel","Yumbel","Otra"};

        String[]sp_araucania={"Angol","Carahue","Cholchol","Collipulli","Cunco","Curacantín","Curarrehue","Ercilla","Freire","Galvarino","Gorbea"
                ,"Lautaro","Loncoche","Lonquimay","Los Sauces","Lumaco","Melipeuco","Nueva Imperial","Padre Las Casas","Perquenco","Pitrufquén","Pucón","Purén"
                ,"Renaico","Saavedra","Temuco","Teodoro Schmidt","Toltén","Traiguén","Victoria","Vilcún","Villarrica","Otra"};

        String[]sp_Los_Lagos={"Ancud","Calbuco","Castro","Chaitén","Chonchi","Cochamó","Curaco de Vélez","Dalcahue","Fresia","Frutillar","Futaleufú",
                "Hualaihué","Llanquihue","Los Muermos","Maullín","Osorno","Palena","Puerto Montt","Puerto Octay","Puerto Varas","Puqueldón","Purranque","Puyehue",
                "Queilén","Quellón","Quemchi","Quinchao","Río Negro","San Juan de la Costa","San Pablo","Otra"};

        String[]sp_Aysen ={"Aisén","Chile Chico","Cisnes","Cochrane","Coihaique","Guaitecas","Lago Verde","O'Higgins","Río Ibañez","Tortel","Otra"};

        String[]sp_magallanes = {"Antártica","Cabo de Hornos","Laguna Blanca","Natales","Porvenir","Primavera","Punta Arenas","Río Verde","San Gregorio","Timaukel","Torres del Paine","Otra"};

        String[]sp_region_metropolitana = {"Santiago","Colina","Puente Alto","Sant Bernardo","Melipilla","Talagante","otra"};

        String[]sp_los_rios= {"Corral","Futrono","Lago Ranco","Lanco","La Unión","Los Lagos","Máfil","Paillaco","Panguipulli","Río Bueno","San José de la Mariquina","Valdivia","Otra"};

        String[]sp_arica_y_parinacota = {"Arica","Camarones","General Lagos","Putre","Otra"};

        String[]sp_ñuble = {"Bulnes","Chillán","Chillán Viejo","Cobquecura","Coelemu","Coihueco","El Carmen","Ninhue","Ñiquén","Pemuco","Pinto","Portezuelo","Quillón","Quirihue","Ránquil","San Carlos",
                "San Fabián","San Ignacio","San Nicolás","Treguaco","Yungay","Otra"};



        if(!list_textInputLayouts.get(5).getEditText().getText().equals("")){

            String reg_seleccionada = list_textInputLayouts.get(5).getEditText().getText().toString();
            switch (reg_seleccionada){
                case "I Región de Tarapacá":  sp_seleccionado=sp_tarapaca;
                    break;
                case "II Región de Antofagasta": sp_seleccionado=sp_antofagasta;
                    break;
                case "III Región de Atacama": sp_seleccionado=sp_atacama;
                    break;
                case "IV Región de Coquimbo":  sp_seleccionado=sp_coquimbo;
                    break;
                case "V Región de Valparaíso": sp_seleccionado=sp_valparaiso;
                    break;
                case "VI Región del Libertador General Bernardo O’Higgins": sp_seleccionado=sp_ohiggins;
                    break;
                case "VII Región del Maule": sp_seleccionado=sp_maule;
                    break;
                case "VIII Región del Biobío": sp_seleccionado=sp_biobio;
                    break;
                case "IX Región de La Araucanía":  sp_seleccionado=sp_araucania;
                    break;
                case "X Región de Los Lagos": sp_seleccionado=sp_Los_Lagos;
                    break;
                case "XI Región Aysén del General Carlos Ibáñez del Campo": sp_seleccionado=sp_Aysen;
                    break;
                case "XII Región de Magallanes y Antártica Chilena": sp_seleccionado=sp_magallanes;
                    break;
                case "Región Metropolitana de Santiago": sp_seleccionado=sp_region_metropolitana ;
                    break;
                case "XIV Región de Los Ríos": sp_seleccionado=sp_los_rios ;
                    break;
                case "XV Región de Arica y Parinacota": sp_seleccionado=sp_arica_y_parinacota;
                    break;
                case "XVI Región de Ñuble": sp_seleccionado=sp_ñuble;
                    break;

            }

        }

        ArrayAdapter arrayAdapter = new ArrayAdapter(getContext(),R.layout.opcion_item, sp_seleccionado);
        aut_ciudad.setAdapter(arrayAdapter);
    }


    public void rellenaComuna(){
        String[] sp_seleccionado = {""};
        String[]sp_comunas_tarapaca ={"Iquique","Alto Hospicio","Pozo Almonte","Camiña","Colchane","Comuna de Huara","Pica","Otra"};

        String[]sp_comunas_antofagasta ={"Antofagasta","Mejillones","Sierra Gorda","Taltal","Calama","Ollague","San Pedro de Atacama","Tocopilla","María Elena","Otra"};

        String[]sp_comunas_atacama ={"Chañaral","Diego de Almagro","Copiapó","Caldera","Tierra Amarilla","Vallenar","Alto del Carmen","Freirina","Huasco","Otra"};

        String[]sp_comunas_coquimbo ={"Illapel","Canela","Los Vilos","Salamanca","Coquimbo","Andacollo","La Higuera","La Serena","Paihuano","Vicuña","Ovalle"
                ,"Combarbalá","Monte Patria","Punitaqui","Río Hurtado","Otra"};

        String[]sp_comunas_valparaiso ={"Isla de Pascua","Los Andes","Calle Larga","Rinconada","San Esteban","Quilpué","Limache","Olmué","Villa Alemana","La Ligua"
                ,"Cabildo","Papudo","Petorca","Zapallar","Quillota","Hijuelas","La Calera","La Cruz","Nogales","San Antonio","Algarrobo","Cartagena","El Quisco","El Tabo","Santo Domingo"
                ,"San Felipe","Catemu","Llay Llay","Panquehue","Putaendo","Santa María","Valparaíso","Casablanca","Concón","Juan Fernández","Puchuncaví","Quintero","Viña del Mar","Otra"};

        String[]sp_comunas_ogiggins ={"Rancagua","Codegua","Coinco","Coltauco","Doñihue","Graneros","Las Cabras","Machalí","Malloa","Mostazal","Olivar","Peumo","Pichidegua","Quinta de Tilcoco"
                ,"Rengo","Requínoa","San Vicente de Tagua Tagua","Pichilemu","La Estrella","Litueche","Marchigue","Navidad","Paredones","Chépica","Chimbarongo","Lolol","Nancagua","Palmilla","Peralillo","Placilla"
                ,"Pumanque","Santa Cruz","Otra"};

        String[]sp_comunas_maule ={"Cauquenes","Chanco","Pelluhue","Curicó","Hualañé","Licantén","Molina","Rauco","Romeral","Sangrada Familia","Teno","Vichuquén"
                ,"Linares","Colbún","Longaví","Parral","Retiro","San Javier","Villa Alegre","Yerbas Buenas","Talca","Constitución","Curepto","Empedrado","Maule","Pelarco","Pencahue","Río Claro","San Clemente","San Rafael","Otra"};

        String[]sp_comunas_biobio ={"Lebu","Arauco","Cañete","Contulmo","Curanilahue","Los Álamos","Tirúa","Los Ángeles","Alto Bío Bío","Antuco","Cabrero","Laja","Mulchén"
                ,"Nacimiento","Negrete","Quilaco","Quilleco","San Rosendo","Santa Bárbara","Tucapel","Yumbel","Concepción","Chiguayante","Coronel","Florida","Hualpén","Hualqui","Lota","Penco","San Pedro de la Paz"
                ,"Santa Juana","Talcahuano","Tomé","Otra"};

        String[]sp_comunas_araucania ={"Temuco","Carahue","Chol Chol","Cunco","Curarrehue","Freire","Glvarino","Gorbea","Lautaro","Loncoche","Melipeuco","Nueva Imperial","Padre Las Casas"
                ,"Perquenco","Pitrufquén","Pucón","Saavedra","Teodoro Schmidt","Toltén","Vilcún","Villarrica","Angol","Collipulli","Curacautín","Ercilla","Lonquimay","Los Sauces","Lumaco","Purén","Renaico","Traiguén","Victoria","Otra"};

        String[]sp_comunas_losLagos ={"Castro","Ancud","Chonchi","Curaco de Vélez","Dalcahue","Puqueldón","Queilén","Quellón","Quemchi","Quinchao","Puerto Montt","Calbuco","Cochamó","Fresio","Frutillar","Los Muermos","Llanquihue"
                ,"Maullín","Puerto Varas","Osorno","Puerto Octay","Purranque","Puyehue","Río Negro","San Juan de la Costa","San Pablo","Chaitén","Futaleufú","Hualaihué","Palena","Otra"};

        String[]sp_comunas_aysen ={"Aysén","Cisnes","Guaitecas","Cochrane","O´Higgins","Tortel","Coyhaique","Lago Verde","Chile Chico","Río Ibañez","Otra"};

        String[]sp_comunas_magallanes ={"Cabo de Hornos","Antártica","Punta Arenas","Laguna Blanca","Río verde","San Gregorio","Porvenir","Primavera","Timaukel","Puerto Natales","Torres del Paine","Otra"};

        String[]sp_comunas_santiago ={"Colina","Lampa","Til Til","Puente Alto","Pirque","San José de Maipo","San Bernardo","Buin","Calera de Tango","Paine","Melipilla","Alhué","Curacaví","María Pinto","San Pedro","Santiago","Cerrillos"
                ,"Cerro Navia","Conchalí","El Bosque","Estación Central","Huechuraba","Independencia","La Cisterna","La Florida","La Granja","La Pintana","La Reina","Las Condes","Lo Barnechea","Lo Espejo","Lo Prado","Macul","Maipú","Ñuñoa","Pedro Aguirre Cerda"
                ,"Peñalololén","Providencia","Pudahuel","Quilicura","Quinta Normal","Recoleta","Renca","San Juaquín","San Miguel","San Ramón","Vitacura","Talagante","El monte","Isla de Miaipo","Padre Hurtado","Peñaflor","Otra"};

        String[]sp_comunas_losRios ={"La Unión","Futrono","Lago Ranco","Río Bueno","Valdivia","Corral","Lanco","Los Lagos","Máfil","Mariquina","Paillaco","Panguipulli","Otra"};

        String[]sp_comunas_arica ={"Arica","Camarones","Putre","General Lagos","Otra"};
        String[]sp_comunas_ñuble ={"Bulnes","Chillán","Chillán Viejo","El Carmen","Pemuco","Pinto","Quillón","San Ignacio","Yungay","Quirihue","Cobquecura","Coelemu","Ninhue","Portezuelo","Ránquil","Trehuaco","San Carlos","Coihueco","Ñiquén","San Fabián","San Nicolás","Otra"};



        if(!list_textInputLayouts.get(5).getEditText().getText().equals("")) {
            String reg_seleccionada = list_textInputLayouts.get(5).getEditText().getText().toString();
            switch (reg_seleccionada) {
                case "I Región de Tarapacá": sp_seleccionado = sp_comunas_tarapaca;
                    break;
                case "II Región de Antofagasta": sp_seleccionado=sp_comunas_antofagasta;
                    break;
                case "III Región de Atacama": sp_seleccionado=sp_comunas_atacama;
                    break;
                case "IV Región de Coquimbo":  sp_seleccionado=sp_comunas_coquimbo;
                    break;
                case "V Región de Valparaíso": sp_seleccionado=sp_comunas_valparaiso;
                    break;
                case "VI Región del Libertador General Bernardo O’Higgins": sp_seleccionado=sp_comunas_ogiggins;
                    break;
                case "VII Región del Maule": sp_seleccionado=sp_comunas_maule;
                    break;
                case "VIII Región del Biobío": sp_seleccionado=sp_comunas_biobio;
                    break;
                case "IX Región de La Araucanía":  sp_seleccionado=sp_comunas_araucania;
                    break;
                case "X Región de Los Lagos": sp_seleccionado=sp_comunas_losLagos;
                    break;
                case "XI Región Aysén del General Carlos Ibáñez del Campo": sp_seleccionado=sp_comunas_aysen;
                    break;
                case "XII Región de Magallanes y Antártica Chilena": sp_seleccionado=sp_comunas_magallanes;
                    break;
                case "Región Metropolitana de Santiago": sp_seleccionado=sp_comunas_santiago ;
                    break;
                case "XIV Región de Los Ríos": sp_seleccionado=sp_comunas_losRios ;
                    break;
                case "XV Región de Arica y Parinacota": sp_seleccionado=sp_comunas_arica;
                    break;
                case "XVI Región de Ñuble": sp_seleccionado=sp_comunas_ñuble;
                    break;

            }

        }
        ArrayAdapter arrayAdapter = new ArrayAdapter(getContext(),R.layout.opcion_item, sp_seleccionado);
        aut_comuna.setAdapter(arrayAdapter);
    }
}
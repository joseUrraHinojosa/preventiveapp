package dto;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.jose.preventiveapp.Inspeccion_general_dt;
import com.jose.preventiveapp.R;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.crypto.AEADBadTagException;

import fragment_inspeccion_general.Medidas_preventivas;


public class AdapterRiesgos extends RecyclerView.Adapter<AdapterRiesgos.ViewHolderRiesgos> {

    private ArrayList<CheckBox> riesgos_check;
    private Context context;
    private List<String> riesgosList = new ArrayList<>();
    private List<String> listString = new ArrayList<>();
    private List<MedidasPreventivas> mPreventivasList = new ArrayList<>();


    public AdapterRiesgos(ArrayList<CheckBox> riesgos_check, Context context) {
        this.riesgos_check = riesgos_check;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolderRiesgos onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_riesgos,parent,false);
        return new ViewHolderRiesgos(view);
    }

    ArrayList<Boolean> checados = new ArrayList<Boolean>();
    @Override
    public void onBindViewHolder(@NonNull ViewHolderRiesgos holder, int position) {

        CheckBox checkBox = riesgos_check.get(position);

        holder.ch_riesgos.setText(riesgos_check.get(position).getText().toString());
        holder.ch_riesgos.setOnCheckedChangeListener(null);
        holder.ch_riesgos.setChecked(checkBox.isSelected());

        //escucha si se selecciona un checkbox y si se chequea se guarda en lista de risgosList
        holder.ch_riesgos.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                checkBox.setSelected(isChecked);

                if(holder.ch_riesgos.isChecked()== true){

                    riesgosList.add(holder.ch_riesgos.getText().toString());
                    

                }else if (holder.ch_riesgos.isChecked()== false){
                    riesgosList.remove(holder.ch_riesgos.getText().toString());
                }


            }
        });




        //Hace visible el btn al final de la lista de check
        if(position==riesgos_check.size()-1){
            holder.btn_sig.setVisibility(View.VISIBLE);
        }else{
            holder.btn_sig.setVisibility(View.GONE);
        }


        holder.btn_sig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(!riesgosList.isEmpty()){

                    for(String actual: riesgosList){
                        listString = Arrays.asList(context.getResources().getStringArray(R.array.riegos_string));

                        if(actual.equals(listString.get(0))){
                            mPreventivasList.add(new MedidasPreventivas(
                                    actual,"Para las maniobras de izaje el personal en terreno debe tener experiencia y estar calificado, se debe poner mayor ??nfasis en los dispositivos m??viles ( roldanas, etc.)"));
                        }

                        if(actual.equals(listString.get(1))){
                            mPreventivasList.add(new MedidasPreventivas(
                                    actual,"A) Siempre realizar inspecci??n visual a toda la torre, asegur??ndose de la inexistencia  de elementos que disminuyan el agarre durante el ascenso y descenso de los trabajos en torre.\n\n" +
                                    "B) Se proh??be el uso de equipos anti-ca??das en el caso que presenten desperfectos o no se est?? completamente seguro de su estado. En ese caso el EPP deber?? ser inspeccionado por el experto en prevenci??n de riesgos o alg??n otro trabajador competente en el tema.\n\n" +
                                    "C) Se proh??be utilizar objetos en los bolsillos que puedan da??ar o entorpecer la utilizaci??n del arn??s de seguridad.\n\n" +
                                    "D) Es obligaci??n del trabajador mantenerse estribado en todo momento en dos puntos de sujeci??n ya sea ganchos, estrobo de posicionamiento, carro sol, entre otros.\n\n" +
                                    "E) Cuando la estructura no disponga de riel soll o este en mal estado; se reitera la obligaci??n de mantenerse doblemente estrobado.\n\n" +
                                    "F) Se proh??be el desplazamiento con el gancho en la mano, se debe asegurar y luego realizar el desplazamiento. \n\n" +
                                    "G) Se deber?? utilizar para posicionarse en la estructura o para descansar el estrobo de posicionamiento.\n\n" +
                                    "H) El ritmo de ascensos y descenso deber ser evitando fatigarse, de manera que el cansancio no afecte ni la capacidad s??quica ni motora del personal que realice el trabajo.\n\n" +
                                    "I) Es obligaci??n  utilizar ambas manos al subir y bajar por la escalera de la estructura."));
                        }


                        if(actual.equals(listString.get(2))){
                            mPreventivasList.add(new MedidasPreventivas(
                                    actual,"Estrictamente prohibido trasportar herramientas u objetos en las manos, para ello se debe disponer  de bolso porta herramientas."));
                        }

                        if(actual.equals(listString.get(3))){
                            mPreventivasList.add(new MedidasPreventivas(
                                    actual,"Delimitar las zonas de peligro, para lo cual se debe poner mucha atenci??n al trabajo realizado por el t??cnico que se  encuentra posicionado en la torre, a fin de identificar el lugar inmediato y el radio aproximado en caso de ca??da y/o proyecci??n de  materiales."));
                        }

                        if(actual.equals(listString.get(4))){
                            mPreventivasList.add(new MedidasPreventivas(
                                    actual,"Mantener en todo momento las instalaciones limpias, con sus medios de protecci??n y se??alizaci??n de seguridad en caso que aplique."));
                        }

                        if(actual.equals(listString.get(5))){
                            mPreventivasList.add(new MedidasPreventivas(
                                    actual,"Se deben identificar  si los sitios est??n cercanos a l??neas el??ctricas a??reas, para evitar el izaje en esos sectores, en caso que no se pueda, se deber??n tomar las medidas de control necesarias para controlar el riesgo."));
                        }

                        if(actual.equals(listString.get(6))){
                            mPreventivasList.add(new MedidasPreventivas(
                                    actual,"A) Antes de iniciar el trabajo, cumplir con el circulo de seguridad: \n" +
                                    "1. Conocer el trabajo a ejecutar.\n" +
                                    "2. Prepara persona , equipos e herramientas.\n" +
                                    "3. Identificar los distintos puntos de riesgos.\n" +
                                    "4. Controlar los puntos de riesgos directos e indirectos involucrados en el trabajo.\n" +
                                    "5. Asegurar los puntos cr??ticos de riesgos.\n\n" +
                                    "B) Aplicar las 5 reglas de oro:\n" +
                                    "1. Bloquear todas las fuentes en tensi??n.\n" +
                                    "2. Bloquear los aparatos de corte.\n" +
                                    "3. Verificar la ausencia de tensi??n.\n" +
                                    "4. Poner a tierra en cortocircuito todas las posibles fuentes de tensi??n.\n" +
                                    "5. Delimitar y se??alizar la zona de trabajo."));
                        }

                        if(actual.equals(listString.get(7))){
                            mPreventivasList.add(new MedidasPreventivas(
                                    actual,"Evitar el contacto con partes filosas o sobresalientes, ocupar en todo momento los elementos de protecci??n personal."));
                        }

                        if(actual.equals(listString.get(8))){
                            mPreventivasList.add(new MedidasPreventivas(
                                    actual,"En caso de contacto con la piel, quitar la ropa contaminada, lavar el ??rea afectada con abundante agua y jab??n. La ropa contaminada debe ser mojada con" +
                                    "abundante agua antes de ser retirada del lugar, lavar antes de un nuevo uso."));
                        }

                        if(actual.equals(listString.get(9))){
                            mPreventivasList.add(new MedidasPreventivas(
                                    actual,"Los tambores  deben estar certificados  y validaos para el transporte de l??quidos combustibles, adem??s  se deben llenar hasta un 90% de su capacidad nominal y deber??n ir" +
                                    "en posici??n vertical con una tapa adecuadamente herm??tica en su parte superior." ));
                        }

                        if(actual.equals(listString.get(10))){
                            mPreventivasList.add(new MedidasPreventivas(
                                    actual,"Cada trabajador deber?? de disponer para consumo y uso personal agua potable." ));
                        }

                        if(actual.equals(listString.get(11))){
                            mPreventivasList.add(new MedidasPreventivas(
                                    actual,"Los tambores  deben estar certificados  y validaos para el transporte de l??quidos combustibles, adem??s  se deben llenar hasta un 90% de su capacidad nominal y deber??n ir" +
                                    "en posici??n vertical con una tapa adecuadamente herm??tica en su parte superior."));
                        }

                        if(actual.equals(listString.get(12))){
                            mPreventivasList.add(new MedidasPreventivas(
                                    actual,"Utilizaci??n de elementos naturales o artificiales para producir sombra"));
                        }

                        if(actual.equals(listString.get(13))){
                            mPreventivasList.add(new MedidasPreventivas(
                                    actual,"Usar siempre herramientas protegidas y trasportarlas en bolso porta-herramientas."));
                        }

                        if(actual.equals(listString.get(14))){
                            mPreventivasList.add(new MedidasPreventivas(
                                    actual,"Los trabajos que se realice con herramientas corto punzantes deber?? ser realizada solo por personal capacitado. Identificar y se??alizar las partes sobresalientes de equipos o estructuras, para evitar golpes."));
                        }

                        if(actual.equals(listString.get(15))){
                            mPreventivasList.add(new MedidasPreventivas(
                                    actual,"Realizar los trabajos de forma tranquila y segura , evitar movimientos fuertes, asegurarse a las superficies para evitar resbalones y o golpes." +
                                    "Queda estrictamente prohibido ubicarse bajo cargas suspendidas."));
                        }

                        if(actual.equals(listString.get(16))){
                            mPreventivasList.add(new MedidasPreventivas(
                                    actual,"Realizar la tarea de forma pausada, evitar el sobre esfuerzo."));
                        }

                        if(actual.equals(listString.get(17))){
                            mPreventivasList.add(new MedidasPreventivas(
                                    actual,"Mantener la zona de trabajo al menos 1 equipo extintor de PQS. Como m??nimo de 6 Kg.( aumentar la cantidad de extintores dependiendo del trabajo que se realice." +
                                    "Todo el personal deber?? conocer el plan de control de emergencias.)"));
                        }

                        if(actual.equals(listString.get(18))){
                            mPreventivasList.add(new MedidasPreventivas(
                                    actual,"Trasladar a la persona a un ambiente libre de contaminaci??n (aire fresco)"));
                        }

                        if(actual.equals(listString.get(19))){
                            mPreventivasList.add(new MedidasPreventivas(
                                    actual,"n/a"));
                        }

                        if(actual.equals(listString.get(20))){
                            mPreventivasList.add(new MedidasPreventivas(
                                    actual,"En caso de contacto de ??cidos con la piel proceder de inmediato a lavar con abundante agua."));
                        }

                        if(actual.equals(listString.get(21))){
                            mPreventivasList.add(new MedidasPreventivas(
                                    actual,"Se proh??be cargar equipos o materiales al momento de ascender y descender de la torre, utilizar poleas  u otros equipos similares para subir o bajar materiales, herramientas o equipos\n\n" +
                                    "Es obligaci??n de los trabajadores no manipular objetos de mas de 50 Kg, en caso de hombres y 20 Kg en caso de las mujeres.\n\n" +
                                    "Evitar realizar movimientos repetitivos"));
                        }

                        if(actual.equals(listString.get(22))){
                            mPreventivasList.add(new MedidasPreventivas(
                                    actual,"El envi?? desde y hacia el andamio  de herramientas . Equipos u otros  elementos de gran tama??o y peso , se deber??n realizar mediante poleas, tecles u otros equipos de izaje.\n\n" +
                                    "Al realizar actividades de levantamiento de cargas asegurarse de doblar las rodillas para recoger las cargas desde el suelo, evitar giros del tronco con cargas en los brazos y mantener la espalda recta en todo momento.\n\n" +
                                    "No manipular objetos de un peso superior a los 50 Kg para hombres y 20 Kg para mujeres."));
                        }

                        if(actual.equals(listString.get(23))){
                            mPreventivasList.add(new MedidasPreventivas(
                                    actual,"Las maniobras de izaje siempre deben ser realizadas con 2 personas como m??nimo y no exceder el peso  m??ximo de carga humana. Aplicar controles de acuerdo a Gu??a t??cnica (TMERT-EESS) si aplica."));
                        }

                        if(actual.equals(listString.get(24))){
                            mPreventivasList.add(new MedidasPreventivas(
                                    actual,"Realizar el trabajo siempre al alcance del brazo extendido evitar el sobre esfuerzo, realizar pausa en el trabajo e hidratarse si fuese necesario. Aplicar controles de acuerdo a Gu??a t??cnica (TMERT-EESS) si aplica."));
                        }

                         if(actual.equals(listString.get(25))){
                            mPreventivasList.add(new MedidasPreventivas(
                                    actual,"llevar al afectado a un centro asistencial"));
                        }

                    }


                   ((Inspeccion_general_dt)context).riesgosMedidaPrevent(mPreventivasList);
                    AppCompatActivity activity = (AppCompatActivity) v.getContext();

                    Medidas_preventivas md = new Medidas_preventivas();
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("mePreventivas", (Serializable) mPreventivasList);
                    md.setArguments(bundle);
                    activity.getSupportFragmentManager().beginTransaction().replace(R.id.ct_inspeccion_general, md ).addToBackStack(null).commit();

                }else{
                    Toast.makeText(context,"Aun no ha seleccionado ning??n riesgo",Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    @Override
    public int getItemCount() {
        return riesgos_check.size();
    }

    public class ViewHolderRiesgos extends RecyclerView.ViewHolder {

        CheckBox ch_riesgos;
        Button btn_sig;
        public ViewHolderRiesgos(@NonNull View itemView) {
            super(itemView);

            ch_riesgos =(CheckBox) itemView.findViewById(R.id.check_riesgos);
            btn_sig =(Button) itemView.findViewById(R.id.btn_siguiente);

        }
    }


}

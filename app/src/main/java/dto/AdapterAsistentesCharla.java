package dto;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.shape.InterpolateOnScrollPositionChangeHelper;
import com.jose.preventiveapp.Charla_dt;
import com.jose.preventiveapp.R;

import java.util.ArrayList;
import java.util.List;

import fragment_charla.Fin_charla;
import fragment_inspeccion_general.Etapas_tarea;

public class AdapterAsistentesCharla extends RecyclerView.Adapter<AdapterAsistentesCharla.ViewHolderAsistentes> {

    private List<Trabajadores> trabajadoresList = new ArrayList<>();
    private List<Trabajadores> asistentesList = new ArrayList<>();
    private Context context;

    boolean checado =false;
    public AdapterAsistentesCharla(List<Trabajadores> trabajadoresList, Context context) {
        this.trabajadoresList = trabajadoresList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolderAsistentes onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_nomina_asistentes,parent,false);
        return new AdapterAsistentesCharla.ViewHolderAsistentes(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolderAsistentes holder, int position) {


        holder.nombre.setText(trabajadoresList.get(position).getNombre());

        if(trabajadoresList.get(position).getRun()==null){
            holder.tvDocu.setText("Pasaporte");
            holder.documento.setText(trabajadoresList.get(position).getPasaporte());
         }else{
            holder.tvDocu.setText("Run");
            holder.documento.setText(trabajadoresList.get(position).getRun());
        }

        //detecta si se ha checado un asistente para agregarlo para luego registrarlo en una lista sino lo quita de la lista
        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if(isChecked){
                    asistentesList.add(trabajadoresList.get(position));
                }else{
                    asistentesList.remove(trabajadoresList.get(position));
                }
            }
        });


        if(position==trabajadoresList.size()-1){
            holder.btn_sig.setVisibility(View.VISIBLE);
        }else{
            holder.btn_sig.setVisibility(View.GONE);
        }


        holder.btn_sig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(asistentesList.isEmpty()){
                    Toast.makeText(context,"Aun no ha seleccionado ningún asistente",Toast.LENGTH_SHORT).show();
                }else {

                    ((Charla_dt) context).asistentesCharla(asistentesList);
                    ((Charla_dt) context).registroTerminado(true);

                    AppCompatActivity activity = (AppCompatActivity) v.getContext();
                    Fin_charla fin_charla = new Fin_charla();
                    activity.getSupportFragmentManager().beginTransaction().replace(R.id.ct_charla, fin_charla).addToBackStack(null).commit();

                }
            }
        });




    }

    @Override
    public int getItemCount() {
        return trabajadoresList.size();
    }

    public class ViewHolderAsistentes extends RecyclerView.ViewHolder {
        TextView nombre,documento, tvDocu;
        Button btn_sig;
        CardView cvAsis;
        CheckBox checkBox;

        public ViewHolderAsistentes(@NonNull View itemView) {
            super(itemView);

            nombre =(TextView)itemView.findViewById(R.id.tvNombre);
            documento =(TextView)itemView.findViewById(R.id.tvDocumento);
            btn_sig =(Button)itemView.findViewById(R.id.btn_siguiente);
            cvAsis =(CardView)itemView.findViewById(R.id.cvAsistentes);
            checkBox =(CheckBox)itemView.findViewById(R.id.cbxAsistente);
            tvDocu = (TextView)itemView.findViewById(R.id.tvDocu);
        }
    }
}

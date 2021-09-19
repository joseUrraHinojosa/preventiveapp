package dto;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.jose.preventiveapp.Inspeccion_general_dt;
import com.jose.preventiveapp.R;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import fragment_inspeccion_general.Etapas_tarea;
import fragment_inspeccion_general.Fin_inspeccionGeneral;

public class AdapterMedidasPreventivas extends RecyclerView.Adapter<AdapterMedidasPreventivas.ViewHolderMedidas> {

    private List<MedidasPreventivas> mPreventList = new ArrayList<>();
    private List<String> listString = new ArrayList<>();
    private Context context;

    public AdapterMedidasPreventivas(List<MedidasPreventivas> mPreventList, Context context) {
        this.mPreventList = mPreventList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolderMedidas onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_medidas_preventivas,parent,false);
        return new AdapterMedidasPreventivas.ViewHolderMedidas(view);
    }


    @Override
    public void onBindViewHolder(@NonNull ViewHolderMedidas holder, int position) {
        listString = Arrays.asList(context.getResources().getStringArray(R.array.riegos_string));

        holder.tvRiesgo.setText(mPreventList.get(position).getRiesgo());
        holder.tvMedidas.setText(mPreventList.get(position).getMedidaPreventivas());

        if(holder.tvRiesgo.getText().equals(listString.get(0))){
                holder.cvMedidas.setCardBackgroundColor(context.getResources().getColor(R.color.amarillo2));
        }

        if(holder.tvRiesgo.getText().equals(listString.get(1))){
                holder.cvMedidas.setCardBackgroundColor(context.getResources().getColor(R.color.rojo2));
        }

        if(holder.tvRiesgo.getText().equals(listString.get(2))){
            holder.cvMedidas.setCardBackgroundColor(context.getResources().getColor(R.color.rojo2));
        }

        if(holder.tvRiesgo.getText().equals(listString.get(3))){
            holder.cvMedidas.setCardBackgroundColor(context.getResources().getColor(R.color.rojo2));
        }

        if(holder.tvRiesgo.getText().equals(listString.get(4))){
            holder.cvMedidas.setCardBackgroundColor(context.getResources().getColor(R.color.amarillo2));
        }

        if(holder.tvRiesgo.getText().equals(listString.get(5))){
            holder.cvMedidas.setCardBackgroundColor(context.getResources().getColor(R.color.amarillo2));
        }

        if(holder.tvRiesgo.getText().equals(listString.get(6))){
            holder.cvMedidas.setCardBackgroundColor(context.getResources().getColor(R.color.rojo2));
        }

        if(holder.tvRiesgo.getText().equals(listString.get(7))){
            holder.cvMedidas.setCardBackgroundColor(context.getResources().getColor(R.color.rojo2));
        }

        if(holder.tvRiesgo.getText().equals(listString.get(8))){
            holder.cvMedidas.setCardBackgroundColor(context.getResources().getColor(R.color.verde6));
        }

        if(holder.tvRiesgo.getText().equals(listString.get(9))){
            holder.cvMedidas.setCardBackgroundColor(context.getResources().getColor(R.color.amarillo2));
        }

        if(holder.tvRiesgo.getText().equals(listString.get(10))){
            holder.cvMedidas.setCardBackgroundColor(context.getResources().getColor(R.color.amarillo2));
        }

        if(holder.tvRiesgo.getText().equals(listString.get(11))){
            holder.cvMedidas.setCardBackgroundColor(context.getResources().getColor(R.color.amarillo2));
        }

        if(holder.tvRiesgo.getText().equals(listString.get(12))){
            holder.cvMedidas.setCardBackgroundColor(context.getResources().getColor(R.color.amarillo2));
        }

        if(holder.tvRiesgo.getText().equals(listString.get(13))){
            holder.cvMedidas.setCardBackgroundColor(context.getResources().getColor(R.color.rojo2));
        }

        if(holder.tvRiesgo.getText().equals(listString.get(14))){
            holder.cvMedidas.setCardBackgroundColor(context.getResources().getColor(R.color.amarillo2));
        }

        if(holder.tvRiesgo.getText().equals(listString.get(15))){
            holder.cvMedidas.setCardBackgroundColor(context.getResources().getColor(R.color.rojo2));
        }

        if(holder.tvRiesgo.getText().equals(listString.get(16))){
            holder.cvMedidas.setCardBackgroundColor(context.getResources().getColor(R.color.amarillo2));
        }

        if(holder.tvRiesgo.getText().equals(listString.get(17))){
            holder.cvMedidas.setCardBackgroundColor(context.getResources().getColor(R.color.amarillo2));
        }

        if(holder.tvRiesgo.getText().equals(listString.get(18))){
            holder.cvMedidas.setCardBackgroundColor(context.getResources().getColor(R.color.amarillo2));
        }

        if(holder.tvRiesgo.getText().equals(listString.get(19))){
            holder.cvMedidas.setCardBackgroundColor(context.getResources().getColor(R.color.amarillo2));
        }

        if(holder.tvRiesgo.getText().equals(listString.get(10))){
            holder.cvMedidas.setCardBackgroundColor(context.getResources().getColor(R.color.amarillo2));
        }

        if(holder.tvRiesgo.getText().equals(listString.get(21))){
            holder.cvMedidas.setCardBackgroundColor(context.getResources().getColor(R.color.amarillo2));
        }

        if(holder.tvRiesgo.getText().equals(listString.get(22))){
            holder.cvMedidas.setCardBackgroundColor(context.getResources().getColor(R.color.amarillo2));
        }

        if(holder.tvRiesgo.getText().equals(listString.get(23))){
            holder.cvMedidas.setCardBackgroundColor(context.getResources().getColor(R.color.rojo2));
        }

        if(holder.tvRiesgo.getText().equals(listString.get(24))){
            holder.cvMedidas.setCardBackgroundColor(context.getResources().getColor(R.color.rojo2));
        }

         if(holder.tvRiesgo.getText().equals(listString.get(25))){
            holder.cvMedidas.setCardBackgroundColor(context.getResources().getColor(R.color.amarillo2));
        }


        holder.btnSig.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ((Inspeccion_general_dt)context).registroTerminado(true);

                AppCompatActivity activity = (AppCompatActivity) v.getContext();
                Fin_inspeccionGeneral fi = new Fin_inspeccionGeneral();
                activity.getSupportFragmentManager().beginTransaction().replace(R.id.ct_inspeccion_general, fi ).addToBackStack(null).commit();


            }
        });

        //Hace visible el btn al final de la lista de check
        if(position==mPreventList.size()-1){
            holder.btnSig.setVisibility(View.VISIBLE);
        }else{
            holder.btnSig.setVisibility(View.GONE);
        }

        }


    @Override
    public int getItemCount() {
        return mPreventList.size();
    }

    public class ViewHolderMedidas extends RecyclerView.ViewHolder {
        TextView tvRiesgo, tvMedidas;
        CardView cvMedidas;
        Button btnSig;
        public ViewHolderMedidas(@NonNull View itemView) {
            super(itemView);

            tvRiesgo =(TextView)itemView.findViewById(R.id.tvRiesgo);
            tvMedidas =(TextView)itemView.findViewById(R.id.medidaPreventivas);
            cvMedidas =(CardView)itemView.findViewById(R.id.cvMedidasP);
            btnSig =(Button)itemView.findViewById(R.id.btn_siguiente);
        }
    }

}

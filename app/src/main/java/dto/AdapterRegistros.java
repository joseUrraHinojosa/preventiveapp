package dto;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;
import com.jose.preventiveapp.R;
import java.util.ArrayList;
import java.util.List;
import dao.EstadoEppDAO;
import dao.TemasCharlaDAO;


public class AdapterRegistros extends RecyclerView.Adapter<AdapterRegistros.ViewHolderRegistros> {
    private List<DatosGenerales> datosGeneralesList = new ArrayList<>();
    private Context cotext;

    public AdapterRegistros(List<DatosGenerales> datosGeneralesList, Context cotext) {
        this.datosGeneralesList = datosGeneralesList;
        this.cotext = cotext;
    }

    @NonNull
    @Override
    public ViewHolderRegistros onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_registros,parent,false);
        return new AdapterRegistros.ViewHolderRegistros(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderRegistros holder, int position) {

        holder.tvRegistro.setText("Registro N°"+(datosGeneralesList.size()-position));
        holder.tvEmpresa.setText("Empresa mandante: "+datosGeneralesList.get(position).getEmpresaMandante());
        holder.tvSitio.setText("Sitio: "+datosGeneralesList.get(position).getCodigoSitio());
        holder.tvTrabajo.setText("Trabajo: "+datosGeneralesList.get(position).getTipoTrabajo());
        holder.tvFecha.setText("Fecha: "+datosGeneralesList.get(position).getFecha());
        holder.tvComuna.setText("Comuna: "+datosGeneralesList.get(position).getComuna());


        int idG = datosGeneralesList.get(position).getIdDatosGenerales();
        int idT = new TemasCharlaDAO().mostrarTemasCharla(idG,cotext).size();
            if(idT > 0) {

                holder.ivCharla.setImageResource(R.drawable.ic_check_24);
            }else{
                holder.ivCharla.setImageResource(R.drawable.ic_pendiente_24);
            }


            int id = new EstadoEppDAO().mostrarEstadosEpp(idG,cotext).size();
            if(id > 0){
                holder.ivCharla.setImageResource(R.drawable.ic_check_24);
            }else{
                holder.ivRepp.setImageResource(R.drawable.ic_pendiente_24);
            }


        if(position==datosGeneralesList.size()-1){
            margenesCrdView(holder.cardView);
        }else{

        }
    }

    @Override
    public int getItemCount() {
        return datosGeneralesList.size();
    }

    public class ViewHolderRegistros extends RecyclerView.ViewHolder {

        TextView tvEmpresa, tvSitio, tvTrabajo, tvFecha, tvComuna,tvRegistro;
        ImageView  ivCharla, ivRepp;
        MaterialCardView cardView;

        public ViewHolderRegistros(@NonNull View itemView) {
            super(itemView);

            tvEmpresa =(TextView)itemView.findViewById(R.id.tvEmpresaM);
            tvSitio =(TextView)itemView.findViewById(R.id.tvSitio);
            tvTrabajo =(TextView)itemView.findViewById(R.id.tvTrabajo);
            tvFecha =(TextView)itemView.findViewById(R.id.tvFecha);
            tvComuna =(TextView)itemView.findViewById(R.id.tvComuna);
            tvRegistro =(TextView)itemView.findViewById(R.id.tvRegistro);
            ivCharla =(ImageView)itemView.findViewById(R.id.ivCharla);
            ivRepp =(ImageView)itemView.findViewById(R.id.ivRevisionEpp);
            cardView=(MaterialCardView)itemView.findViewById(R.id.cvRegistros);

        }
    }

    public void margenesCrdView(View v){

        ConstraintLayout.LayoutParams newLayoutParams = (ConstraintLayout.LayoutParams) v.getLayoutParams();
        newLayoutParams.bottomMargin =130;
        v.setLayoutParams(newLayoutParams);
    }
}

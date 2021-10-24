package br.com.equipe23.divulgacu.adapter;

import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;
import br.com.equipe23.divulgacu.R;
import br.com.equipe23.divulgacu.config.Mask;
import br.com.equipe23.divulgacu.model.Anuncio;

public class AdapterFeedCityFragment extends RecyclerView.Adapter<AdapterFeedCityFragment.myViewHolder> {

    private List<Anuncio> listaAnuncios;
    public AdapterFeedCityFragment(List<Anuncio> anuncios) {
        this.listaAnuncios = anuncios;
    }

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(
                parent.getContext()
        ).inflate(R.layout.row_rv_feed, parent, false);

        return new myViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {
        Anuncio item = listaAnuncios.get(position);

        holder.nomeEmpresa.setText(item.getNomeEmpresa());
        holder.nomeRua.setText(item.getEndereco().getRua());
        holder.telefone.setText(Mask.maskString(item.getWhatsapp()));

        if (!item.getLogo().equals("")) {
            Uri uri = Uri.parse(item.getLogo());
            Glide.with(holder.itemView.getContext()).load(uri).into(holder.logo);
        }else {
            holder.logo.setImageResource(R.drawable.avatar);
        }
    }

    @Override
    public int getItemCount() {
        return listaAnuncios.size();
    }

    public class myViewHolder extends RecyclerView.ViewHolder{
        public TextView nomeEmpresa, nomeRua, telefone;
        public ImageView logo;


        public myViewHolder(@NonNull View itemView) {
            super(itemView);
            nomeEmpresa = itemView.findViewById(R.id.textViewNomeEmpresaPerfilCardView);
            nomeRua = itemView.findViewById(R.id.textViewRuaPerfilCardView);
            telefone = itemView.findViewById(R.id.textViewTelefonePerfilCardView);
            logo = itemView.findViewById(R.id.imageViewNegocioPerfilCardView);


        }
    }

}

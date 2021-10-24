package br.com.equipe23.divulgacu.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import br.com.equipe23.divulgacu.R;
import br.com.equipe23.divulgacu.model.Anuncio;

public class AdapterPesquisa extends RecyclerView.Adapter<AdapterPesquisa.MyViewHolder> {

    private List<Anuncio> listaAnuncio;
    private Context context;

    public AdapterPesquisa(List<Anuncio> listaAnuncio, Context context) {
        this.listaAnuncio = listaAnuncio;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemLista = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_pesquisa, parent, false);
        return new MyViewHolder(itemLista);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Anuncio anuncio = listaAnuncio.get(position);
        holder.nome.setText(anuncio.getNomeEmpresa());
        holder.foto.setImageResource(R.drawable.avatar);

       /* if (anuncio.getLogo() != "") {
            Uri uri = Uri.parse(anuncio.getId());
            Glide.with(context).load(uri).into(holder.foto);
        }else {
            holder.foto.setImageResource(R.drawable.avatar);
        }*/
    }

    @Override
    public int getItemCount() {
        return listaAnuncio.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView foto;
        TextView nome;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            foto = itemView.findViewById(R.id.imagePesquisa);
            nome = itemView.findViewById(R.id.textPesquisa);
        }
    }
}

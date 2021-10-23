package br.com.equipe23.divugaacu.Adapter;

import android.animation.LayoutTransition;
import android.transition.AutoTransition;
import android.transition.TransitionManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import br.com.equipe23.divugaacu.R;

public class Adapter extends RecyclerView.Adapter<Adapter.myViewHolder> {

    @NonNull
    @Override
    public myViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View perguntaLista = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.adapter_perguntas, parent, false);

        return new myViewHolder(perguntaLista);


    }

    @Override
    public void onBindViewHolder(@NonNull myViewHolder holder, int position) {

        holder.resumo.setText("Resumo Teste");
        holder.pergunta.setText("Titulo Teste");


    }

    @Override
    public int getItemCount() {
        return 5;
    }

    public class myViewHolder extends RecyclerView.ViewHolder{

        private TextView pergunta, resumo;
        private CardView card;
        private LinearLayout layoutQ1;


        public myViewHolder(@NonNull View itemView) {
            super(itemView);

            pergunta = itemView.findViewById(R.id.texteTituloQ1);
            resumo = itemView.findViewById(R.id.textResumoQ1);
            card = itemView.findViewById(R.id.cvq);
            layoutQ1 = itemView.findViewById(R.id.layoutQ1);
            //card.getLayoutTransition().enableTransitionType(LayoutTransition.CHANGING);


            card.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    int v = (resumo.getVisibility() == View.GONE)? View.VISIBLE: View.GONE;

                    TransitionManager.beginDelayedTransition(layoutQ1, new AutoTransition());
                    resumo.setVisibility(v);


                }
            });
        }
    }



}

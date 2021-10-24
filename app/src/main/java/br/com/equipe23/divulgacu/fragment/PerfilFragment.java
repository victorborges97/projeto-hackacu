package br.com.equipe23.divulgacu.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import br.com.equipe23.divulgacu.R;
import br.com.equipe23.divulgacu.config.ConfiguracaoFirebase;
import br.com.equipe23.divulgacu.config.Mask;
import br.com.equipe23.divulgacu.model.Anuncio;
import br.com.equipe23.divulgacu.repository.Firebase;
import br.com.equipe23.divulgacu.view.CadastrarAnuncioActivity;
import br.com.equipe23.divulgacu.view.DetalhesAnuncioActivity;

public class PerfilFragment extends Fragment {

    private CardView cardViewAnuncioUser, cardViewSemAnuncioUser;
    private TextView textViewNomePerfil, textViewTelefonePerfil, textViewCidadePerfil, textViewEnderecoPerfil,
            textViewNomeEmpresaPerfilCardView, textViewRuaPerfilCardView, textViewTelefonePerfilCardView;
    private ImageView imageViewFotoPerfil, imageViewNegocioPerfilCardView;
    private Button buttonSairPerfil;
    private Anuncio meuAnuncio;

    public PerfilFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(
            LayoutInflater inflater,
            ViewGroup container,
            Bundle savedInstanceState
    ) {
        View rootView = inflater.inflate(R.layout.fragment_perfil, container, false);

        iniciarComponentes(rootView);

        buscarMeuAnuncio();

        buttonSairPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ConfiguracaoFirebase.getFirebaseAutenticacao().signOut();
                getFragmentManager().popBackStackImmediate();


            }
        });

        return rootView;
    }

    private void buscarMeuAnuncio() {
        String id = ConfiguracaoFirebase.getIdUsuario();
        if(!id.isEmpty()){
            Firebase.getAnuncioUsuario(id).addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    meuAnuncio = snapshot.getValue(Anuncio.class);
                    preencherAnuncio(meuAnuncio);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }
    }

    private void preencherPerfil(Anuncio anuncio) {
        if(anuncio != null){
            FirebaseUser user = ConfiguracaoFirebase.getUsuario();
            if(user != null){
                textViewNomePerfil.setText(user.getDisplayName());
                if (!user.getPhotoUrl().toString().isEmpty()) {
                    Glide.with(this).load(user.getPhotoUrl()).into(imageViewFotoPerfil);
                }else {
                    imageViewFotoPerfil.setImageResource(R.drawable.avatar);
                }
            }

            textViewTelefonePerfil.setText(anuncio.getWhatsapp());
            textViewCidadePerfil.setText(anuncio.getEndereco().getCidade());
            textViewEnderecoPerfil.setText(anuncio.getEndereco().getRua());

        }
    }

    private void preencherAnuncio(Anuncio anuncio){
        if(anuncio.getNomeEmpresa().isEmpty()){
            cardViewSemAnuncioUser.setVisibility(View.VISIBLE);

            cardViewSemAnuncioUser.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getActivity(), CadastrarAnuncioActivity.class);
                    startActivity(intent);
                }
            });

            cardViewAnuncioUser.setVisibility(View.GONE);
        } else {
            cardViewSemAnuncioUser.setVisibility(View.GONE);
            cardViewAnuncioUser.setVisibility(View.VISIBLE);

            cardViewAnuncioUser.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getActivity(), DetalhesAnuncioActivity.class);
                    intent.putExtra("anuncioSelecionado", anuncio);
                    startActivity(intent);
                }
            });

            if (!anuncio.getLogo().isEmpty()) {
                Uri uri = Uri.parse(anuncio.getLogo());
                Glide.with(this).load(uri).into(imageViewNegocioPerfilCardView);
            }else {
                imageViewNegocioPerfilCardView.setImageResource(R.drawable.img_2);
            }
            textViewNomeEmpresaPerfilCardView.setText(anuncio.getNomeEmpresa());
            textViewRuaPerfilCardView.setText(anuncio.getEndereco().getRua());
            textViewTelefonePerfilCardView.setText(Mask.maskString(anuncio.getWhatsapp()));
            preencherPerfil(anuncio);
        }

    }

    private  void iniciarComponentes(View rootView) {
        textViewNomePerfil = rootView.findViewById(R.id.textViewNomePerfil);
        textViewTelefonePerfil = rootView.findViewById(R.id.textViewTelefonePerfil);
        textViewCidadePerfil = rootView.findViewById(R.id.textViewCidadePerfil);
        textViewEnderecoPerfil = rootView.findViewById(R.id.textViewEnderecoPerfil);

        // CARD ANUNCIO
        cardViewAnuncioUser = rootView.findViewById(R.id.cardViewAnuncioUser);
        cardViewSemAnuncioUser = rootView.findViewById(R.id.cardViewSemAnuncioUser);
        imageViewNegocioPerfilCardView = rootView.findViewById(R.id.imageViewNegocioPerfilCardView);
        textViewNomeEmpresaPerfilCardView = rootView.findViewById(R.id.textViewNomeEmpresaPerfilCardView);
        textViewRuaPerfilCardView = rootView.findViewById(R.id.textViewRuaPerfilCardView);
        textViewTelefonePerfilCardView = rootView.findViewById(R.id.textViewTelefonePerfilCardView);

        imageViewFotoPerfil = rootView.findViewById(R.id.imageViewFotoPerfil);
        buttonSairPerfil = rootView.findViewById(R.id.buttonSairPerfil);
    }
}
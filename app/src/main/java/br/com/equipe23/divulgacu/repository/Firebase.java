package br.com.equipe23.divulgacu.repository;

import android.util.Log;

import com.google.firebase.database.Query;

import java.util.ArrayList;
import java.util.List;

import br.com.equipe23.divulgacu.config.ConfiguracaoFirebase;
import br.com.equipe23.divulgacu.model.Anuncio;

public class Firebase {

    public static Query getAnuncios(String cidade) {
        List<Anuncio> lista = new ArrayList<>();
        try {
            return ConfiguracaoFirebase.getFirebaseDatabase()
                    .child("anuncios")
                    .child(cidade);
        } catch (Exception e){
            Log.d("ERROR", "ERROR AO PEGAR OS ANUNCIOS: "+e.getMessage());
        }
        return null;
    }

    public static Query getAnuncio(String id) {
        try {
            return ConfiguracaoFirebase.getFirebaseDatabase()
                    .child("anuncios")
                    .child(id.toString());

        } catch (Exception e){
            Log.d("ERROR", "ERROR AO PEGAR O ANUNCIO: "+e.getMessage());
        }
        return null;
    }
}

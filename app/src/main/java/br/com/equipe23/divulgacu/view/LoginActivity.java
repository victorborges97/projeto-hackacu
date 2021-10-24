package br.com.equipe23.divulgacu.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import br.com.equipe23.divulgacu.R;
import br.com.equipe23.divulgacu.config.ConfiguracaoFirebase;
import br.com.equipe23.divulgacu.model.Anuncio;
import br.com.equipe23.divulgacu.repository.Firebase;

public class LoginActivity extends AppCompatActivity {

    private SignInButton botaoGoogle;

    private GoogleSignInClient mGoogleSignInClient;
    private final static int RC_SIGN_IN = 123;
    private FirebaseAuth mAuth;
    private FirebaseUser firebaseUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();
        findViewsById();
        mAuth = ConfiguracaoFirebase.getFirebaseAutenticacao();
        firebaseUser = mAuth.getCurrentUser();

        createRequest();

        botaoGoogle.setOnClickListener(view -> {
            signIn();
        });

    }

    private void findViewsById(){
        botaoGoogle = findViewById(R.id.google_button);
    }

    private void createRequest() {
        // Configure Google Sign In
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                Log.i("Resultado", "Google sign in succeeded. Your id is: " + account.getId());
                firebaseAuthWithGoogle(account.getIdToken());
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                Log.i("Resultado", "Google sign in failed", e);
            }
        }
    }

    private void firebaseAuthWithGoogle(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.i("Resultado", "signInWithCredential in Firebase:success");
                            firebaseUser = mAuth.getCurrentUser();
                            updateUI(firebaseUser);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.i("Resultado", "signInWithCredential in Firebase:failure", task.getException());
                            updateUI(null);
                        }
                    }
                });
    }

    private void updateUI(FirebaseUser firebaseUser) {
        if(firebaseUser != null){
            if(verificarEmpresaUsuario(firebaseUser.getUid())){
                startActivity(new Intent(this, MainActivity.class));
                finish();
            } else {
                Toast.makeText(this,"Usuário logado no app com sucesso", Toast.LENGTH_LONG).show();
                startActivity(new Intent(this, CadastrarAnuncioActivity.class));
                finish();
            }
        }
    }

    private Boolean verificarEmpresaUsuario(String id) {
        final Boolean[] resp = new Boolean[1];
        Firebase.getAnuncioUsuario(id)
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        Anuncio anuncio = snapshot.getValue(Anuncio.class);
                        if(anuncio != null){
                            if(!anuncio.getNomeEmpresa().isEmpty()){
                                resp[0] = true;
                            }
                        }
                        resp[0] = false;
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        resp[0] = false;
                    }
                });
        return resp[0];
    }
}
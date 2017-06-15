package com.example.dylan.proyectofinalfixter;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    Button BtnIniciar;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    EditText ETCorreo,ETPassword;
    TextView BtnSingIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Firbase
        mAuth = FirebaseAuth.getInstance();

        ETCorreo=(EditText)findViewById(R.id.EdEmailLog);
        ETPassword=(EditText)findViewById(R.id.EdPassLog);
        BtnIniciar=(Button)findViewById(R.id.BtnIniciar);
        BtnSingIn=(TextView)findViewById(R.id.TxRegistro);


        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d("Bienvenido", "onAuthStateChanged:signed_in:" + user.getUid());
                    Intent intento = new Intent(getApplicationContext(),Perfil.class);
                    startActivity(intento);
                } else {
                    // User is signed out
                    Log.d("Registrate", "onAuthStateChanged:signed_out");
                }
                // ...
            }
        };
        //Registrarse
        BtnSingIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intanito= new Intent(getApplicationContext(),Registro.class);
                startActivity(intanito);
            }
        });
        //IniciarSesion
        BtnIniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String corr=ETCorreo.getText().toString();
                String pass=ETPassword.getText().toString();
                if(corr.length()==0 &&pass.length()==0){
                    Toast.makeText(getApplicationContext(),"Por Favor ingresa tus datos",Toast.LENGTH_SHORT).show();
                }else{

                }
            }
        });

    }

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    public void signIn(String Correo, String Password) {
        mAuth.signInWithEmailAndPassword(Correo, Password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d("asd", "signInWithEmail:onComplete:" + task.isSuccessful());

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Log.w("asdd", "signInWithEmail:failed", task.getException());
                            Toast.makeText(MainActivity.this, "No existes. Registrare por favor", Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });
    }
}

package com.example.dylan.proyectofinalfixter;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Registro extends AppCompatActivity {

    EditText EtCorreo, Etcontra;
    String Scorreo, SContra;
    Button BtnReg;

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registro);

        mAuth = FirebaseAuth.getInstance();

        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d("Holi", "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    // User is signed out
                    Log.d("Adios", "onAuthStateChanged:signed_out");
                }
                // ...
            }
        };

        EtCorreo=(EditText)findViewById(R.id.EdEmailReg);
        Etcontra=(EditText)findViewById(R.id.EdPassReg);
        BtnReg=(Button)findViewById(R.id.BtnReg);

        BtnReg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Scorreo=EtCorreo.getText().toString();
                SContra=Etcontra.getText().toString();
                createAccount(Scorreo,SContra);
                if(SContra.length()==0 && Scorreo.length()==0){
                    Toast.makeText(getApplication(),"Por ingresa tus datos",Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(getApplicationContext(), "Bienvenido a la aplicación", Toast.LENGTH_SHORT).show();
                    Intent uno = new Intent(getApplicationContext(), Perfil.class);
                    startActivity(uno);
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

    public void createAccount(String correo,String contra){
        mAuth.createUserWithEmailAndPassword(correo, contra)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d("Bienvenido", "createUserWithEmail:onComplete:" + task.isSuccessful());

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Log.w("Bienvenido", "signInWithEmail:failed", task.getException());
                            Toast.makeText(getApplicationContext(), "You Shall no pass Prro", Toast.LENGTH_SHORT).show();
                        }

                        // ...
                    }
                });
    }

}

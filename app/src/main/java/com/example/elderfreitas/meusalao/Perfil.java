package com.example.elderfreitas.meusalao;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Perfil extends AppCompatActivity {

    private TextView textEmail, textID;
    private Button btnLogOut, btnAgenda;

    private FirebaseAuth auth;
    private FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);
        inicializaComponentes();
        eventoClicks();
    }

    private void eventoClicks(){
        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               Conexao.logOut();
               finish();
            }
        });

        btnAgenda.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(Perfil.this,Main_Activity.class);
                startActivity(i);
            }
        });


    }


    private void inicializaComponentes(){
        textEmail = (TextView) findViewById(R.id.textPerfilEmail);
        textID =(TextView) findViewById(R.id.textPerfilID);
        btnLogOut =(Button) findViewById(R.id.btnPerfilLogout);
        btnAgenda=(Button) findViewById(R.id.btnAgenda);
    }

    @Override
    protected void onStart() {
        super.onStart();
        auth = Conexao.getFirebaseAuth();
        user = Conexao.getFirebaseUser();
        verificaUser();
    }
    private void verificaUser(){
        if(user == null){
            finish();
        }else{
            textEmail.setText("Email:"+user.getEmail());
            textID.setText("ID:"+user.getUid());
        }
    }
}



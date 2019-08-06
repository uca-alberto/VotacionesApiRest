package com.example.votaciones.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.votaciones.Api.Api;
import com.example.votaciones.Model.UsuariosModel;
import com.example.votaciones.R;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Login extends AppCompatActivity {

    EditText User, Pass;
    Button Sign;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        User = findViewById(R.id.Edt_email_login);
        Pass = findViewById(R.id.Edit_password_login);
        Sign = findViewById(R.id.BtnLogin);

        Sign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SignIn(User.getText().toString(),Pass.getText().toString());
            }
        });
    }

    public void SignIn( String Name,String Carnet){

        UsuariosModel usuariosModel = new UsuariosModel();
        usuariosModel.setUsuario(Name);
        usuariosModel.setClave(Carnet);
        
        Call<UsuariosModel> call = Api.instance().Login(usuariosModel);
        call.enqueue(new Callback<UsuariosModel>() {
            @Override
            public void onResponse(Call<UsuariosModel> call, Response<UsuariosModel> response) {
                if(response.body()!=null){
                    Toast.makeText(Login.this, "Usuario Correcto", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(Login.this,Votaciones.class));
                }else {
                    Toast.makeText(Login.this, "Usuario Incorrecto", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<UsuariosModel> call, Throwable t) {

            }
        });
    }
}

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

import java.util.ArrayList;
import java.util.List;

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
                startActivity(new Intent(Login.this,Votaciones.class));
                //Toast.makeText(Login.this, "Si funciina el boton", Toast.LENGTH_SHORT).show();
            }
        });
    }

    public void SignIn(final String Name, final String Carnet){
        Call<List<UsuariosModel>> call = Api.instance().GetUser();
        call.enqueue(new Callback<List<UsuariosModel>>() {
            @Override
            public void onResponse(Call<List<UsuariosModel>> call, Response<List<UsuariosModel>> response) {
               /* ArrayList<String> myarraylist = new ArrayList<>();
                myarraylist.add(response.body().toString());*/
                List<UsuariosModel> usuariosModel;
                usuariosModel = response.body();



                List<UsuariosModel> user = response.body();
                for (int i = 0; i < usuariosModel.size(); i++)
                {

                    //Toast.makeText(Login.this, "USUARIO:"+usuariosModel.toString(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<UsuariosModel>> call, Throwable t) {

            }
        });

    }
}

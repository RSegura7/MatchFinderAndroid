package pe.edu.utp.matchfinder;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;

import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static android.Manifest.permission.READ_CONTACTS;

/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends AppCompatActivity {

    EditText user, pass;
    TextView registrar, olvidar_pass;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        user = (EditText)findViewById(R.id.user);
        pass = (EditText)findViewById(R.id.password);
        registrar = (TextView) findViewById(R.id.register);
        olvidar_pass = (TextView) findViewById(R.id.olvidar);
        login = (Button)findViewById(R.id.email_sign_in_button);



        registrar.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent move = new Intent(LoginActivity.this,RegisterGamerActivity.class);
                startActivity(move);
            }
        });


        olvidar_pass.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent move = new Intent(LoginActivity.this, ForgotPasswordActivity.class);
                startActivity(move);
            }
        });

        login.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ingresar();
            }
        });

    }

    protected void ingresar(){
        RequestQueue queue = Volley.newRequestQueue(this);

        String url = "http://andre2sm.000webhostapp.com/Users/getLogin.php";
        StringRequest datostring = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                if(response.toString()!=null){
                    Toast.makeText(getApplicationContext(),"INGRESO ACEPTADO",Toast.LENGTH_LONG).show();
                    Intent move = new Intent(LoginActivity.this,MainActivity.class);
                    startActivity(move);
                }else{
                    Toast.makeText(getApplicationContext(),"ERROR",Toast.LENGTH_LONG).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(),"ERROR EN EL RESPONSE",Toast.LENGTH_LONG).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String,String> params = new HashMap<>();
                params.put("username",user.getText().toString());
                params.put("password",pass.getText().toString());
                return super.getParams();
            }
        };
        queue.add(datostring);
    }
}



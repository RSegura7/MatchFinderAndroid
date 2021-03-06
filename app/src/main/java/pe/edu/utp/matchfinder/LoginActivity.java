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
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
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

    EditText et_user, et_pass;
    TextView registrar, olvidar_pass;
    Button login;

    RequestQueue mQueue;

    Boolean loginBool = false;
    ArrayList<String> data = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        et_user = (EditText)findViewById(R.id.user);
        et_pass = (EditText)findViewById(R.id.password);
        registrar = (TextView) findViewById(R.id.register);
        olvidar_pass = (TextView) findViewById(R.id.olvidar);
        login = (Button)findViewById(R.id.email_sign_in_button);

        mQueue = Volley.newRequestQueue(this);

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

                String userText = et_user.getText().toString();
                String passText = et_pass.getText().toString();

                String url = "http://andre2sm.000webhostapp.com/Users/getLogin.php";

                HashMap<String, String> params = new HashMap<String, String>();
                params.put("username", userText);
                params.put("password", passText);

                JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(params), new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            JSONObject user = response.getJSONObject("users");

                            String id = user.getString("id");
                            String rol = user.getString("rol");
                            String username = user.getString("username");
                            String password = user.getString("password");
                            String name = user.getString("name");
                            String lastname = user.getString("lastname");
                            String birthday = user.getString("birthday");
                            String mail = user.getString("mail");
                            String sex = user.getString("sex");

                            if ( userText.equals(username) && passText.equals(password) ){
                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                intent.putExtra("id", id);
                                intent.putExtra("rol", rol);
                                intent.putExtra("username", username);
                                intent.putExtra("name", name);
                                intent.putExtra("lastname", lastname);
                                intent.putExtra("birthday", birthday);
                                intent.putExtra("mail", mail);
                                intent.putExtra("sex", sex);
                                startActivity(intent);
                                finish();
                            }else{
                                Toast.makeText(getApplicationContext(), "Usuario y contraseña no válidos.", Toast.LENGTH_LONG).show();
                            }

                        } catch (JSONException e) {
                            Toast.makeText(getApplicationContext(), "Problemas con el JSON", Toast.LENGTH_LONG).show();
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(getApplicationContext(), "Problemas con Voley, no devuelve response", Toast.LENGTH_LONG).show();
                    }
                });

                mQueue.add(request);

            }
        });
    }
}
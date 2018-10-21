package pe.edu.utp.matchfinder;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class ForgotPasswordActivity extends AppCompatActivity {

    private Button submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);
        ActionBar actionBar = getSupportActionBar();

        if(actionBar != null){
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle("Regresar");
        }

        submit = (Button) findViewById(R.id.button_recover);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(ForgotPasswordActivity.this,"Un mensaje ha sido enviado", Toast.LENGTH_LONG).show();


            }
        });
    }
}

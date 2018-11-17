package pe.edu.utp.matchfinder;

import android.app.DatePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;

public class ReserveActivity extends AppCompatActivity {
    Button bfecha, bhora;
    EditText efecha, ehora;
    int dia,mes,ano,hora,minutos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserve);

        bfecha = (Button)findViewById(R.id.btn_fecha);
        efecha = (EditText)findViewById(R.id.label_fecha);

        bhora = (Button)findViewById(R.id.btn_hora);
        ehora = (EditText)findViewById(R.id.label_hora);

        bfecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    final Calendar calendar = Calendar.getInstance();
                    dia = calendar.get(Calendar.DAY_OF_MONTH);
                    mes = calendar.get(Calendar.MONTH);
                    ano = calendar.get(Calendar.YEAR);

                    mes = mes + 1;

                    efecha.setText(dia + "/" + mes + "/" + ano);


            }
        });
        bhora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(v==bhora){

                }
            }
        });
    }
}

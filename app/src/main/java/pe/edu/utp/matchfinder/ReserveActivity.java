package pe.edu.utp.matchfinder;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Calendar;
import java.util.HashMap;

public class ReserveActivity extends AppCompatActivity {
    Button bfecha, bhora,reserva;
    TextView efecha, ehora;
    int dia,mes,ano,hora,minutos;
    String format;

    String dayText;
    String hourText;

    RequestQueue mQueue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserve);

        bfecha = (Button)findViewById(R.id.btn_fecha);
        efecha = (TextView)findViewById(R.id.label_fecha);

        mQueue = Volley.newRequestQueue(this);

        bhora = (Button)findViewById(R.id.btn_hora);
        ehora = (TextView) findViewById(R.id.label_hora);
        efecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar calendar = Calendar.getInstance();
                dia = calendar.get(Calendar.DAY_OF_MONTH);
                mes = calendar.get(Calendar.MONTH);
                ano = calendar.get(Calendar.YEAR);

                mes = mes + 1;

                DatePickerDialog date = new DatePickerDialog(ReserveActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        month = month+1;
                        efecha.setText(dayOfMonth + "/" + month + "/" + year);
                    }
                },ano,mes,dia);
                date.show();
            }
        });


        //ehora.setText(hora + ":" + minutos + " " +format);

        ehora.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar horas = Calendar.getInstance();
                hora = horas.get(Calendar.HOUR_OF_DAY);
                minutos = horas.get(Calendar.MINUTE);

                selectedTimeFormat(hora);
                TimePickerDialog time = new TimePickerDialog(ReserveActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        selectedTimeFormat(hourOfDay);
                        ehora.setText(hourOfDay + ":" + minute + " " +format );
                    }
                }, hora , minutos,true);
                time.show();
            }
            private void selectedTimeFormat(int hour){
                if(hour==0){
                    hour += 12;
                    format =  "AM";
                }else if(hour == 12){
                    format = "PM";
                }else if (hour > 12){
                    hour -= 12;
                    format = "PM";
                }else{
                    format = "AM";
                }
            }
        });


        reserva = (Button)findViewById(R.id.button_reservar);
        reserva.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dayText = efecha.toString();
                hourText = ehora.toString();

                HashMap<String, String> params = new HashMap<String, String>();
                params.put("business_id", "1");
                params.put("user_id", "1");
                params.put("date_time", dayText);
                params.put("init_hour", hourText);

                String url = "http://andre2sm.000webhostapp.com/Reservations/createReservation.php";

                JsonObjectRequest request = new JsonObjectRequest(Request.Method.POST, url, new JSONObject(params), new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        if (response.equals("Data was added correctly to the database")){
                               Toast.makeText(getApplicationContext(), "Reserva realizada con éxito", Toast.LENGTH_LONG).show();
                        }else {
                               Toast.makeText(getApplicationContext(), "Hubo un problema para realizar tu reserva", Toast.LENGTH_LONG).show();
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

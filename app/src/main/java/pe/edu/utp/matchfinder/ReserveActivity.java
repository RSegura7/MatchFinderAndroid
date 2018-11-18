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
import android.widget.TimePicker;

import java.util.Calendar;

public class ReserveActivity extends AppCompatActivity {
    Button bfecha, bhora,reserva;
    EditText efecha, ehora;
    int dia,mes,ano,hora,minutos;
    String format;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserve);

        bfecha = (Button)findViewById(R.id.btn_fecha);
        efecha = (EditText)findViewById(R.id.label_fecha);

        bhora = (Button)findViewById(R.id.btn_hora);
        ehora = (EditText)findViewById(R.id.label_hora);


                    //efecha.setText(dia + "/" + mes + "/" + ano);

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


            reserva = (Button)findViewById(R.id.reservar);
            reserva.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    //Intent gracias = new Intent(ReserveActivity.class,)
                }
            });

    }
}

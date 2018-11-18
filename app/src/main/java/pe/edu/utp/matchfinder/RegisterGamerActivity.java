package pe.edu.utp.matchfinder;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Calendar;

public class RegisterGamerActivity extends AppCompatActivity {


    TextView efecha;
    private int nYearIn1, nMonthIn1, nDayIn1, sYearIn1, sMonthIn1, sDayIn1;
    static final int DATE_ID = 0;
    Calendar C = Calendar.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_gamer);

        sMonthIn1 = C.get(Calendar.MONTH);
        sDayIn1 = C.get(Calendar.DAY_OF_MONTH);
        sYearIn1 = C.get(Calendar.YEAR);

        efecha = (TextView) findViewById(R.id.efecha);

        efecha.setOnClickListener((view) -> {

            showDialog(DATE_ID);
        });
       }

       private void colocar_fecha(){
        efecha.setText(nDayIn1  +  "-" + (nMonthIn1 +1) + "-" + nYearIn1+"");
       }

       private DatePickerDialog.OnDateSetListener nDateSetListener =
               new DatePickerDialog.OnDateSetListener() {
                   @Override
                   public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                       nYearIn1 = year;
                       nMonthIn1 = month;
                       nDayIn1 = dayOfMonth;
                       colocar_fecha();

                   }
               };

        protected Dialog onCreateDialog(int id){
            switch (id){
            case DATE_ID:
                return new DatePickerDialog(this,nDateSetListener, sYearIn1,sMonthIn1,sDayIn1);
            }
            return null;
        }
        }

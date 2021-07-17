package com.assignment.ceylonlinuxassignment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;

import com.assignment.ceylonlinuxassignment.Adapter.ExpensesTypeListAdapter;
import com.assignment.ceylonlinuxassignment.Database.DBUtils;
import com.assignment.ceylonlinuxassignment.Object.Expenses;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class AddExpensesActivity extends AppCompatActivity {
    ImageView view ;
    public  static  Uri selectedImage;
    public  static String CurrentDate;
    public  static String CurrentTime;
    public  static String date;

    TextView tv_date;
    ImageView date_image;

    final Calendar myCalendar = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expenses);

        tv_date = findViewById(R.id.date);
        date_image = findViewById(R.id.date_image);

        TextView current_date = findViewById(R.id.current_date);
        TextView current_time = findViewById(R.id.current_time);

        SimpleDateFormat sdf = new SimpleDateFormat("EEE, d MMM, ''yyyy");
        SimpleDateFormat sdfT = new SimpleDateFormat("HH:mm:ss z");
        String currentDate = sdf.format(new Date());
        String currentTime = sdfT.format(new Date());
        current_date.setText(currentDate);
        current_time.setText(currentTime);
        CurrentDate = currentDate.toString();
        CurrentTime = currentTime.toString();

        ArrayList<Expenses> expensesArrayList = new ArrayList<>();
        Expenses expenses = new Expenses();
        expenses.setExpenses_type("TRAVEL");
        expenses.setExpenses_type("FUEL");
        expenses.setExpenses_type("HIGHWAY CHARGE");
        expenses.setExpenses_type("PARKING");
        expenses.setExpenses_type("VEHICLE SERVICE");
        expenses.setExpenses_type("NIGHT OUT");
        expenses.setExpenses_type("LUNCH");

        expensesArrayList.add(expenses);

        RecyclerView expenses_type_list = findViewById(R.id.expense_type_list);
        LinearLayoutManager layoutManagerExpenses_type_list = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        expenses_type_list.setLayoutManager(layoutManagerExpenses_type_list);
        ExpensesTypeListAdapter expensesTypeListAdapter = new ExpensesTypeListAdapter(expensesArrayList, this);
        expenses_type_list.setHasFixedSize(true);
        expenses_type_list.setAdapter(expensesTypeListAdapter);
        expensesTypeListAdapter.setOnItemClick(new ExpensesTypeListAdapter.OnItemClick() {
            @Override
            public void getPosition(ImageView data) {
                view = data;
                Intent pickPhoto = new Intent(Intent.ACTION_PICK,
                        android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(pickPhoto , 1);
            }
        });

        DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                // TODO Auto-generated method stub
                myCalendar.set(Calendar.YEAR, year);
                myCalendar.set(Calendar.MONTH, monthOfYear);
                myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                updateLabel();
            }

        };

        date_image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DatePickerDialog(AddExpensesActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

        Button save = findViewById(R.id.save);
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             ArrayList<Expenses>addList =   ExpensesTypeListAdapter.getExpensesArrayList ;

             for(int i = 0 ; i < addList.size(); i++){
                 DBUtils.insert_expenses_to_expense_table(addList.get(i),AddExpensesActivity.this);

                 if(i == addList.size()){
                     startActivity(new Intent(AddExpensesActivity.this,MainActivity.class));
                 }
             }
            }
        });

    }

    protected void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {
        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);
        switch(requestCode) {
            case 0:
                if(resultCode == RESULT_OK){
                    selectedImage = imageReturnedIntent.getData();
                    view.setImageURI(selectedImage);
                }

                break;
            case 1:
                if(resultCode == RESULT_OK){
                     selectedImage = imageReturnedIntent.getData();
                    view.setImageURI(selectedImage);
                }
                break;
        }
    }

    private void updateLabel() {
        String myFormat = "EEE, d MMM, ''yyyy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

        tv_date.setVisibility(View.VISIBLE);
        date_image.setVisibility(View.GONE);

        tv_date.setText(sdf.format(myCalendar.getTime()));
        date = myCalendar.getTime().toString();
    }
}
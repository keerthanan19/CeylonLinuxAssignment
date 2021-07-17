package com.assignment.ceylonlinuxassignment;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

import com.assignment.ceylonlinuxassignment.Adapter.ExpensesListAdapter;
import com.assignment.ceylonlinuxassignment.Adapter.ExpensesTypeListAdapter;
import com.assignment.ceylonlinuxassignment.Database.DBUtils;
import com.assignment.ceylonlinuxassignment.Object.Expenses;
import com.assignment.ceylonlinuxassignment.Utils.Utility;
import com.assignment.ceylonlinuxassignment.async_task.getAllData;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton add_expenses = findViewById(R.id.add_expenses);
        add_expenses.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this,AddExpensesActivity.class));
            }
        });

        ArrayList<Expenses> expensesArrayList = DBUtils.getAllExpensesData(MainActivity.this);

        RecyclerView expense_list = findViewById(R.id.expense_list);
        LinearLayoutManager layoutManagerExpenses_list = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        expense_list.setLayoutManager(layoutManagerExpenses_list);
        ExpensesListAdapter expensesTypeListAdapter = new ExpensesListAdapter(expensesArrayList, this);
        expense_list.setHasFixedSize(true);
        expense_list.setAdapter(expensesTypeListAdapter);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.dash_bord_menu, menu);

        return true;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == R.id.action_syncOrders){
            sync();
        }

        return super.onOptionsItemSelected(item);
    }

    private void sync() {
        if (Utility.isNetworkNotAvailable(this)) {
            try {

                getAllData getAllData = new getAllData(this);
                getAllData.execute();

            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {

        }
    }
}
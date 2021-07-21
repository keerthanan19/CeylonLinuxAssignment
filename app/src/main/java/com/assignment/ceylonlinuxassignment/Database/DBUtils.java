package com.assignment.ceylonlinuxassignment.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.assignment.ceylonlinuxassignment.Object.Expenses;

import java.util.ArrayList;

public class DBUtils {
    private static db_manager db_manager;
    private static final String TAG = "DBUtils";

    public static boolean insert_expenses_to_expense_table(Expenses data, Context mContext){
        boolean isSuccess = false;
        db_manager = new db_manager(mContext);
        try (SQLiteDatabase db = db_manager.getWritableDatabase()) {

            ContentValues cv = new ContentValues();
            cv.put(db_tables.EXPENSES_TYPE, data.getExpenses_type());
            cv.put(db_tables.EXPENSES_AMOUNT, data.getExpenses_amount());
            cv.put(db_tables.EXPENSES_IMAGE, data.getExpenses_image());
            cv.put(db_tables.EXPENSES_DATE,data.getExpenses_date());
            cv.put(db_tables.EXPENSES_ADD_DATE, data.getExpenses_add_date());
            cv.put(db_tables.EXPENSES_ADD_TIME, data.getExpenses_add_time());

            try{
                long rowCount = db.insertOrThrow(db_tables.TABLE_NAME_EXPENSES, null, cv);
                if (rowCount != -1){
                    Log.d(TAG, "Expense table " + "expense inserted");
                    isSuccess = true;
                }else{
                    Log.e(TAG, "Expense table " + "expense insert failed");
                    isSuccess = false;
                }
            }catch (Exception e){
                try{
                    long rowCount = db.replaceOrThrow(db_tables.TABLE_NAME_EXPENSES, null, cv);
                    if (rowCount != -1){
                        Log.d(TAG, "Expense table " + "expense replaced");
                        isSuccess = true;
                    }else{
                        Log.e(TAG, "Expense table " + "expense replaced failed");
                        isSuccess = false;
                    }
                }catch (Exception ie){
                    Log.e(TAG, "Expense table " + "expense inserted or replaced failed  :"+ ie.getMessage());
                    isSuccess = false;
                }
                isSuccess = false;
            }
        }
        return isSuccess;
    }

    public static ArrayList<Expenses> getAllExpensesData(Context mContext) {
        db_manager = new db_manager(mContext);
        Cursor c;
        ArrayList<Expenses> dateCollection = new ArrayList<>();

        StringBuilder query = new StringBuilder("SELECT * FROM " + db_tables.TABLE_NAME_EXPENSES
                + " ORDER BY " + db_tables.EXPENSES_DATE + " ASC"
        );

        try (SQLiteDatabase db = db_manager.getWritableDatabase()) {
            c = db.rawQuery(query.toString(), null);
            if (c.moveToFirst()) {
                do {
                    Expenses expenses = new Expenses();

                    expenses.setExpenses_type(c.getString(c.getColumnIndex(db_tables.EXPENSES_TYPE)));
                    expenses.setExpenses_amount(c.getString(c.getColumnIndex(db_tables.EXPENSES_AMOUNT)));
                    expenses.setExpenses_image(c.getString(c.getColumnIndex(db_tables.EXPENSES_IMAGE)));
                    expenses.setExpenses_date(c.getString(c.getColumnIndex(db_tables.EXPENSES_DATE)));

                    dateCollection.add(expenses);
                } while (c.moveToNext());
                Log.e(TAG, "getAllExpensesData " + dateCollection.size());
            }
            return dateCollection;
        } catch (Exception e) {
            Log.e(TAG, "getAllExpensesData " + e.toString());
        }
        return dateCollection;
    }


}

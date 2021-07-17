package com.assignment.ceylonlinuxassignment.Adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.assignment.ceylonlinuxassignment.AddExpensesActivity;
import com.assignment.ceylonlinuxassignment.Object.Expenses;
import com.assignment.ceylonlinuxassignment.R;

import java.util.ArrayList;

public class ExpensesTypeListAdapter extends RecyclerView.Adapter<ExpensesTypeListAdapter.ViewHolder>{
    private ArrayList<Expenses> expensesArrayList = new ArrayList<>();
   public static ArrayList<Expenses> getExpensesArrayList = new ArrayList<>();
   Expenses expense = new Expenses();
    private final Activity mContext;

    OnItemClick onItemClick;

    public void setOnItemClick(OnItemClick onItemClick) {
        this.onItemClick = onItemClick;
    }

    public interface OnItemClick {
        void getPosition(ImageView data); //pass any things
    }

    public ExpensesTypeListAdapter(ArrayList<Expenses>  objects, Activity context) {
        this.mContext = context;
        this.expensesArrayList = objects;
        double total = 0;

    }

    @Override
    public ExpensesTypeListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.expense_type_list, parent, false);
        return new ExpensesTypeListAdapter.ViewHolder(listItem);
    }

    @SuppressLint("CheckResult")
    @Override
    public void onBindViewHolder(ExpensesTypeListAdapter.ViewHolder holder, int position) {
        holder.type.setText(expensesArrayList.get(position).getExpenses_type());
        expense.setExpenses_type(expensesArrayList.get(position).getExpenses_type());

        holder.amount.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_NEXT || actionId == EditorInfo.IME_ACTION_DONE) {
                    expense.setExpenses_amount(holder.amount.getText().toString());
                    return true;
                }

                return false;
            }
        });

        holder.btnImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClick.getPosition(holder.image);
            }
        });

        expense.setExpenses_image(AddExpensesActivity.selectedImage.toString());
        expense.setExpenses_date(AddExpensesActivity.date);
        expense.setExpenses_add_date(AddExpensesActivity.CurrentDate);
        expense.setExpenses_add_time(AddExpensesActivity.CurrentTime);

        if(!holder.amount.getText().toString().isEmpty() || !holder.amount.getText().toString().equalsIgnoreCase("00.00")) {
            getExpensesArrayList.add(expense);
        }

    }

    @Override
    public int getItemCount() {
        return expensesArrayList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        final ImageView image;
        final TextView type;
        final EditText amount;
        final ImageButton btnImage;


        ViewHolder(View itemView) {
            super(itemView);
            this.image = itemView.findViewById(R.id.image);
            this.type = itemView.findViewById(R.id.type);
            this.amount = itemView.findViewById(R.id.amount);
            this.btnImage = itemView.findViewById(R.id.btnImage);
        }
    }

}

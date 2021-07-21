package com.assignment.ceylonlinuxassignment.Adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
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
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;

public class ExpensesListAdapter extends RecyclerView.Adapter<ExpensesListAdapter.ViewHolder>{
    private ArrayList<Expenses> expensesArrayList = new ArrayList<>();
    private final Activity mContext;

    public ExpensesListAdapter(ArrayList<Expenses>  objects, Activity context) {
        this.mContext = context;
        this.expensesArrayList = objects;


    }
    @Override
    public ExpensesListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.expense_list, parent, false);
        return new ExpensesListAdapter.ViewHolder(listItem);
    }

    @SuppressLint("CheckResult")
    @Override
    public void onBindViewHolder(ExpensesListAdapter.ViewHolder holder, int position) {
        if(expensesArrayList.size() != 0) {
          /*  holder.amount.setText(expensesArrayList.get(position).getExpenses_amount());
            holder.type.setText(expensesArrayList.get(position).getExpenses_date());
            holder.expense_type.setText(expensesArrayList.get(position).getExpenses_type());
*/
       /* Glide.with(mContext)
                .load(expensesArrayList.get(position).getExpenses_image())
                .into(holder.image);*/
        }
    }

    @Override
    public int getItemCount() {
        return expensesArrayList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        final TextView type;
        final TextView expense_type;
        final TextView amount;
        final ImageView image;

        ViewHolder(View itemView) {
            super(itemView);
            this.type = itemView.findViewById(R.id.type);
            this.expense_type = itemView.findViewById(R.id.expense_type);
            this.amount = itemView.findViewById(R.id.amount);
            this.image = itemView.findViewById(R.id.image);

        }
    }

}

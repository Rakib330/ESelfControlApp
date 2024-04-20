package com.example.eselfcontrol.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.eselfcontrol.R;
import com.example.eselfcontrol.databinding.RowAccountBinding;
import com.example.eselfcontrol.databinding.SampleCategoryItemBinding;
import com.example.eselfcontrol.models.Account;
import com.example.eselfcontrol.models.Category;

import java.util.ArrayList;

public class AccountsAdapter extends RecyclerView.Adapter<AccountsAdapter.AccountsViewHolder> {
    Context context;
    ArrayList<Account> accountArrayList;



    public interface AccountsClickListener{
        void onAccountClicked(Account account);
    }

    AccountsAdapter.AccountsClickListener accountsClickListener;

    public AccountsAdapter(Context context, ArrayList<Account> accountArrayList,AccountsAdapter.AccountsClickListener accountsClickListener) {
        this.accountArrayList = accountArrayList;
        this.context = context;
        this.accountsClickListener = accountsClickListener;
    }

    @NonNull
    @Override
    public AccountsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new AccountsAdapter.AccountsViewHolder(LayoutInflater.from(context).inflate(R.layout.row_account,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull AccountsViewHolder holder, int position) {
        Account account = accountArrayList.get(position);
        holder.binding.accountName.setText(account.getAccountName());

        holder.itemView.setOnClickListener(c->{
            accountsClickListener.onAccountClicked(account);
        });
    }

    @Override
    public int getItemCount() {
        return accountArrayList.size();
    }

    public class AccountsViewHolder extends RecyclerView.ViewHolder{


        RowAccountBinding binding;
        public AccountsViewHolder(@NonNull View itemView){
            super(itemView);
            binding = RowAccountBinding.bind(itemView);
        }
    }
}

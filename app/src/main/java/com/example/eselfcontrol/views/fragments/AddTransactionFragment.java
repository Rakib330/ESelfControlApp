package com.example.eselfcontrol.views.fragments;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.eselfcontrol.R;
import com.example.eselfcontrol.adapters.AccountsAdapter;
import com.example.eselfcontrol.adapters.CategoryAdapter;
import com.example.eselfcontrol.databinding.FragmentAddTransactionBinding;
import com.example.eselfcontrol.databinding.ListDialogBinding;
import com.example.eselfcontrol.models.Account;
import com.example.eselfcontrol.models.Category;
import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class AddTransactionFragment extends BottomSheetDialogFragment {

    public AddTransactionFragment () {

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    FragmentAddTransactionBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAddTransactionBinding.inflate(inflater);

        binding.incomeBtn.setOnClickListener(view ->{
            binding.incomeBtn.setBackground(getContext().getDrawable(R.drawable.income_selector));
            binding.expenceBtn.setBackground(getContext().getDrawable(R.drawable.default_selector));
            binding.expenceBtn.setTextColor(getContext().getColor(R.color.textColor));
            binding.incomeBtn.setTextColor(getContext().getColor(R.color.greenColor));
        });

        binding.expenceBtn.setOnClickListener(view ->{
            binding.incomeBtn.setBackground(getContext().getDrawable(R.drawable.default_selector));
            binding.expenceBtn.setBackground(getContext().getDrawable(R.drawable.expense_selector));
            binding.incomeBtn.setTextColor(getContext().getColor(R.color.textColor));
            binding.expenceBtn.setTextColor(getContext().getColor(R.color.redColor));
        });

        binding.date.setOnClickListener(view->{
            DatePickerDialog datePickerDialog = new DatePickerDialog(getContext());
            datePickerDialog.setOnDateSetListener((datepicker,i,i1,i2)->{
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.DAY_OF_MONTH,datepicker.getDayOfMonth());
                calendar.set(Calendar.MONTH,datepicker.getMonth());
                calendar.set(Calendar.YEAR,datepicker.getYear());

                SimpleDateFormat dateFormat = new SimpleDateFormat("dd MMMM, yyyy");
                String dateToShow = dateFormat.format(calendar.getTime());

                binding.date.setText(dateToShow);
            });
            datePickerDialog.show();
        });


        binding.category.setOnClickListener(c->{
            ListDialogBinding dialogBinding = ListDialogBinding.inflate(inflater);
            AlertDialog categoryDialog = new AlertDialog.Builder(getContext()).create();
            categoryDialog.setView(dialogBinding.getRoot());

            ArrayList<Category> categories = new ArrayList<>();

            categories.add(new Category("Salary",R.drawable.ic_salary, R.color.category1));
            categories.add(new Category("Business",R.drawable.ic_business,  R.color.category2));
            categories.add(new Category("Investment",R.drawable.ic_investment,  R.color.category3));
            categories.add(new Category("Loan",R.drawable.ic_loan,  R.color.category4));
            categories.add(new Category("Rent",R.drawable.ic_rent,  R.color.category5));
            categories.add(new Category("Other",R.drawable.ic_other,  R.color.category6));

            CategoryAdapter categoryAdapter = new CategoryAdapter(getContext(), categories, new CategoryAdapter.CategoryClickListener() {
                @Override
                public void onCategoryClicked(Category category) {
                    binding.category.setText(category.getCategoryName());
                    categoryDialog.dismiss();
                }
            });
            dialogBinding.recyclerView.setLayoutManager(new GridLayoutManager(getContext(),3));
            dialogBinding.recyclerView.setAdapter(categoryAdapter);


            categoryDialog.show();
        });





        binding.account.setOnClickListener(c->{
            ListDialogBinding dialogBinding = ListDialogBinding.inflate(inflater);
            AlertDialog accountDialog = new AlertDialog.Builder(getContext()).create();
            accountDialog.setView(dialogBinding.getRoot());

            ArrayList<Account> accounts = new ArrayList<>();

            accounts.add(new Account(0,"Cash"));
            accounts.add(new Account(0,"Bank"));
            accounts.add(new Account(0,"Bkash"));
            accounts.add(new Account(0,"Nagad"));
            accounts.add(new Account(0,"Other"));

            AccountsAdapter accountsAdapter = new AccountsAdapter(getContext(), accounts, new AccountsAdapter.AccountsClickListener() {
                @Override
                public void onAccountClicked(Account account) {
                    binding.account.setText(account.getAccountName());
                    accountDialog.dismiss();
                }
            });
            dialogBinding.recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            dialogBinding.recyclerView.setAdapter(accountsAdapter);


            accountDialog.show();
        });





        return binding.getRoot();
    }
}
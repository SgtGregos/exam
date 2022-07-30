package com.example.exam;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.example.exam.databinding.FragmentReceiptBinding;


public class ReceiptFragment extends Fragment {

    private FragmentReceiptBinding binding;
    MainActivityViewModel mainActivityViewModel;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState


    ) {

        binding = FragmentReceiptBinding.inflate(inflater, container, false);
        return binding.getRoot();

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        String[] arraySpinner = new String[]{
                "1" , "2" , "3" , "4" , "5" , "6" , "7" , "8" , "9" , "10" , "11" , "12"
        };

        mainActivityViewModel = new ViewModelProvider(requireActivity()).get(MainActivityViewModel.class);

        int amount = mainActivityViewModel.getInput();
        binding.textReceiptAmount.setText("Amount: " + amount);
        binding.textReceiptPayments.setText("Payments: " + arraySpinner[mainActivityViewModel.getSpinnerIndex()]);
        String currentCurrency = "no Currecy selected";
        if (mainActivityViewModel.isILS()){
            currentCurrency = "ILS";
        }else if (mainActivityViewModel.isUSD()){
            currentCurrency = "USD";
        }
        binding.textReceiptCurrency.setText("Currency: " + currentCurrency);
        if (mainActivityViewModel.isSignature()){
            currentCurrency = "ILS";
        }else {
            binding.textReceiptSignature.setVisibility(View.INVISIBLE);
        }






        binding.buttonReceiptFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resetValues();

                NavHostFragment.findNavController(ReceiptFragment.this)
                        .navigate(R.id.action_SecondFragment_to_FirstFragment);
            }
        });

        binding.buttonReceiptCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(ReceiptFragment.this)
                        .navigate(R.id.action_SecondFragment_to_FirstFragment);
            }
        });
    }

    private void resetValues() {
        mainActivityViewModel.setInput(0);
        mainActivityViewModel.setPayments(true);
        mainActivityViewModel.setSpinnerIndex(0);
        mainActivityViewModel.setILS(true);
        mainActivityViewModel.setSignature(false);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}
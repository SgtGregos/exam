package com.example.exam;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;
import androidx.preference.PreferenceFragmentCompat;

import com.example.exam.databinding.FragmentSettingsBinding;


public class SettingsFragment extends Fragment {

    private FragmentSettingsBinding binding;
    MainActivityViewModel mainActivityViewModel;


    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        binding = FragmentSettingsBinding.inflate(inflater, container, false);
        mainActivityViewModel = new ViewModelProvider(requireActivity()).get(MainActivityViewModel.class);
        checkState();

        return binding.getRoot();


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        binding.buttonBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                NavHostFragment.findNavController(SettingsFragment.this)
                        .navigate(R.id.action_settingsFragment_to_FirstFragment);
            }
        });

        binding.switchAllowPayments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkPayment();
            }
        });

        binding.switchAllowCurrency.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                checkCurrency();
            }
        });

        binding.switchAllowSignature.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                checkSignature();
            }
        });
    }

    public void checkPayment(){
        if (binding.switchAllowPayments.isChecked()){
            mainActivityViewModel.setAllowPayments(true);
        }else {
            mainActivityViewModel.setAllowPayments(false);
        }
    }

    public void checkCurrency(){
        if (binding.switchAllowCurrency.isChecked()){
            mainActivityViewModel.setAllowCurrency(true);
        }else {
            mainActivityViewModel.setAllowCurrency(false);
        }
    }
    public void checkSignature(){
        if (binding.switchAllowSignature.isChecked()){
            mainActivityViewModel.setAllowSignature(true);
        }else {
            mainActivityViewModel.setAllowSignature(false);
        }
    }

    public void checkState(){
        if (mainActivityViewModel.isAllowPayments()) {
            binding.switchAllowPayments.setChecked(true);
        }else{
            binding.switchAllowPayments.setChecked(false);
        }
        if (mainActivityViewModel.isAllowCurrency()){
            binding.switchAllowCurrency.setChecked(true);
        }else{
            binding.switchAllowCurrency.setChecked(false);
        }
        if (mainActivityViewModel.isAllowSignature()){
            binding.switchAllowSignature.setChecked(true);
        }else{
            binding.switchAllowSignature.setChecked(false);
        }
    }
}
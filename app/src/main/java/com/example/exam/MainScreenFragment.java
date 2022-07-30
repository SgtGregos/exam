package com.example.exam;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Switch;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.fragment.NavHostFragment;

import com.example.exam.databinding.FragmentMainScreenBinding;

public class MainScreenFragment extends Fragment {

    private FragmentMainScreenBinding binding;
    private SettingsFragment mainActivity = new SettingsFragment();



    MainActivityViewModel mainActivityViewModel;


    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        binding = FragmentMainScreenBinding.inflate(inflater, container, false);
        mainActivityViewModel = new ViewModelProvider(requireActivity()).get(MainActivityViewModel.class);




        return binding.getRoot();


    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        String[] arraySpinner = new String[]{
                "1" , "2" , "3" , "4" , "5" , "6" , "7" , "8" , "9" , "10" , "11" , "12"
        };

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity(), android.R.layout.simple_spinner_item, arraySpinner);
        adapter.setDropDownViewResource(androidx.appcompat.R.layout.support_simple_spinner_dropdown_item);
        binding.spinnerPayments.setAdapter(adapter);

        if (!mainActivityViewModel.isAllowPayments()){
            binding.spinnerPayments.setVisibility(View.INVISIBLE);
            mainActivityViewModel.setSpinnerIndex(0);
            binding.switchPayments.setVisibility(View.INVISIBLE);
        }else{
            binding.spinnerPayments.setVisibility(View.VISIBLE);
            binding.switchPayments.setVisibility(View.VISIBLE);
        }

        if (!mainActivityViewModel.isAllowCurrency()){
            binding.textCurrency.setVisibility(View.INVISIBLE);
            mainActivityViewModel.setILS(true);
            mainActivityViewModel.setILS(false);
            binding.radioGroupCurrency.setVisibility(View.INVISIBLE);
        }else{
            binding.textCurrency.setVisibility(View.VISIBLE);
            binding.radioGroupCurrency.setVisibility(View.VISIBLE);
        }

        if (!mainActivityViewModel.isAllowSignature()){
            binding.switchSignature.setVisibility(View.INVISIBLE);
            mainActivityViewModel.setSignature(false);
        }else{
            binding.switchSignature.setVisibility(View.VISIBLE);
        }


        binding.switchPayments.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //sets the current spinner state
                spinnerState();
            }
        });



        //displays current settings to the user
        getSettings();
        //inits buttons logic
        initButtons();
    }

    private void getSettings() {



        binding.editAmount.setText(String.valueOf(mainActivityViewModel.getInput()));
        binding.switchPayments.setChecked(mainActivityViewModel.isPayments());
        binding.spinnerPayments.setSelection(mainActivityViewModel.getSpinnerIndex());
        spinnerState();
        binding.radioCurrencyIls.setChecked(mainActivityViewModel.isILS());
        binding.radioCurrencyUsd.setChecked(mainActivityViewModel.isUSD());
        binding.switchPayments.setChecked(mainActivityViewModel.isPayments());
        binding.switchSignature.setChecked(mainActivityViewModel.isSignature());
    }


    private void initButtons() {

        binding.buttonSettings.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                NavHostFragment.findNavController(MainScreenFragment.this)
                        .navigate(R.id.action_FirstFragment_to_settingsFragment);

            }
        });

        binding.buttonExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                System.exit(0);
            }
        });

        binding.buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                //sets new logic to attributes by users choice
                transfereData();



                if (mainActivityViewModel.isSignature()){
                    NavHostFragment.findNavController(MainScreenFragment.this)
                            .navigate(R.id.action_FirstFragment_to_signatureScreenFragment);
                }else{
                NavHostFragment.findNavController(MainScreenFragment.this)
                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
                }

            }


        });
    }



    private void transfereData() {


        String input = binding.editAmount.getText().toString();
        int inputAmount = Integer.parseInt(input);
        mainActivityViewModel.setInput(inputAmount);

        mainActivityViewModel.setPayments(binding.switchPayments.isChecked());

        String arrayIndex = binding.spinnerPayments.getSelectedItem().toString();
        int intArrayIndex = Integer.parseInt(arrayIndex);
        mainActivityViewModel.setSpinnerIndex(binding.spinnerPayments.getSelectedItemPosition());
        mainActivityViewModel.setILS(binding.radioCurrencyIls.isChecked());
        mainActivityViewModel.setUSD(binding.radioCurrencyUsd.isChecked());
        mainActivityViewModel.setSignature(binding.switchSignature.isChecked());

    }

    private void spinnerState(){
        if (binding.switchPayments.isChecked()){
            binding.spinnerPayments.setEnabled(true);
        }else {
            mainActivityViewModel.setSpinnerIndex(0);
            binding.spinnerPayments.setSelection(0);
            binding.spinnerPayments.setEnabled(false);
        }

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}
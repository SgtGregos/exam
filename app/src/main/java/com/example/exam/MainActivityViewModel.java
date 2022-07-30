package com.example.exam;

import android.util.Log;
import android.view.View;

import androidx.lifecycle.ViewModel;

public class MainActivityViewModel extends ViewModel {

    private int input = 0;
    private boolean payments = true;
    private int spinnerIndex = 0;
    private boolean ILS = true;
    private boolean USD = false;
    private boolean signature = false;

    private boolean allowPayments = true;
    private boolean allowCurrency = true;
    private boolean allowSignature = true;


    public MainActivityViewModel() {
        Log.d("new instance", "MainActivityViewModel: 23333333333");
    }

    public void setInput(int input) {
        this.input = input;
    }

    public int getInput() {
        return input;
    }

    public boolean isPayments() {
        return payments;
    }

    public void setPayments(boolean payments) {
        this.payments = payments;
    }

    public int getSpinnerIndex() {
        return spinnerIndex;
    }

    public void setSpinnerIndex(int spinnerIndex) {
        this.spinnerIndex = spinnerIndex;
    }

    public boolean isILS() {
        return ILS;
    }

    public void setILS(boolean ILS) {
        this.ILS = ILS;
    }

    public boolean isUSD() {
        return USD;
    }

    public void setUSD(boolean USD) {
        this.USD = USD;
    }

    public boolean isSignature() {
        return signature;
    }

    public void setSignature(boolean signature) {
        this.signature = signature;
    }

    public boolean isAllowPayments() {
        return allowPayments;
    }

    public void setAllowPayments(boolean allowPayments) {
        this.allowPayments = allowPayments;
    }

    public boolean isAllowCurrency() {
        return allowCurrency;
    }

    public void setAllowCurrency(boolean allowCurrency) {
        this.allowCurrency = allowCurrency;
    }

    public boolean isAllowSignature() {
        return allowSignature;
    }

    public void setAllowSignature(boolean allowSignature) {
        this.allowSignature = allowSignature;
    }
}

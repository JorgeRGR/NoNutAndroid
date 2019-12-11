package com.oniriccreations.nonutapp.ui.calendario;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class CalendarioViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public CalendarioViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Fragmento Calendario");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
package com.oniriccreations.nonutapp.ui.actividades;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ActividadesViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ActividadesViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Fragmento Actividades");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
package com.oniriccreations.nonutapp.ui.comunidad;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ComunidadViewModel extends ViewModel {

    private MutableLiveData<String> mText;

    public ComunidadViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Próximamente");
    }

    public LiveData<String> getText() {
        return mText;
    }
}
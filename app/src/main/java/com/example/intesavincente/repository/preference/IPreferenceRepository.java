package com.example.intesavincente.repository.preference;

import androidx.lifecycle.MutableLiveData;

import com.example.intesavincente.MODEL.Utente;
import com.firebase.ui.auth.data.model.User;

public interface IPreferenceRepository {
    MutableLiveData<Boolean> saveUserPreferences(Utente utente);
    MutableLiveData<User> readUserInfo(String uId);
}

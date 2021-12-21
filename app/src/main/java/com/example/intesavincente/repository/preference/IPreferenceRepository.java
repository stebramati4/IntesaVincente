package com.example.intesavincente.repository.preference;

import androidx.lifecycle.MutableLiveData;

import com.firebase.ui.auth.data.model.User;

public interface IPreferenceRepository {
    MutableLiveData<Boolean> saveUserPreferences(User user);
    MutableLiveData<User> readUserInfo(String uId);
}

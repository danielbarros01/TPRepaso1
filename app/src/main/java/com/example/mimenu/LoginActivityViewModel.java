package com.example.mimenu;

import android.view.View;

import androidx.lifecycle.ViewModel;

import com.example.mimenu.modelo.User;

public class LoginActivityViewModel extends ViewModel {
    private User user = new User("danibelgranocab@hotmail.com", "123456");

    public boolean validarDatos(String email, String password) {
        return user != null && user.getEmail().equals(email) && user.getPassword().equals(password);
    }

}

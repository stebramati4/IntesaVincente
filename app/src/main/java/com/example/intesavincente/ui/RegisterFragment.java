package com.example.intesavincente.ui;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.intesavincente.R;
import com.example.intesavincente.UserViewModel;
import com.google.android.material.snackbar.Snackbar;


public class RegisterFragment extends Fragment {

    private static final String TAG = "RegisterFragment";

    private UserViewModel mUserViewModel;

    public RegisterFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mUserViewModel = new ViewModelProvider(requireActivity()).get(UserViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_register, container, false);

        final EditText editTextEmail = view.findViewById(R.id.email);
        final EditText editTextPassword = view.findViewById(R.id.password);

        final Button buttonRegister = view.findViewById(R.id.register_button);
        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = editTextEmail.getText().toString();
                String password = editTextPassword.getText().toString();
                mUserViewModel.signUpWithEmail(email, password).observe(getViewLifecycleOwner(), authenticationResponse -> {
                    if (authenticationResponse != null) {
                        if (authenticationResponse.isSuccess()) {
                            Navigation.findNavController(v).navigate(R.id.mainActivity);
                        } else {
                            updateUIForFailure(authenticationResponse.getMessage());
                        }
                    }
                });
            }
        });

        return view;
    }

    /**
     * It shows a warning message to the user with a Snackbar.
     * @param message The warning message to be shown in the Snackbar.
     */
    private void updateUIForFailure(String message) {
        Snackbar.make(requireActivity().findViewById(android.R.id.content),
                message, Snackbar.LENGTH_SHORT).show();
        mUserViewModel.clear();
    }
}
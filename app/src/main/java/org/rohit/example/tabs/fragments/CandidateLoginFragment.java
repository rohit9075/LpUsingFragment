package org.rohit.example.tabs.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.rohit.example.R;
import org.rohit.example.tabs.Utils.InputValidation;
import org.rohit.example.tabs.activities.CandidateProfileActivity;
import org.rohit.example.tabs.database.DatabaseHelper;

/**
 * Fragment will be used as first page in the activity
 */
public class CandidateLoginFragment extends Fragment implements View.OnClickListener {

    EditText mEtEmail , mEtPassword;

    Button mButtonLogin;

    private InputValidation mInputValidation;

    DatabaseHelper mDatabaseHelper;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_candidate_login, container, false);

        mEtEmail = rootView.findViewById(R.id.editText_candidate_login);
        mEtPassword = rootView.findViewById(R.id.editText_caldidate_login_password);

        mButtonLogin = rootView.findViewById(R.id.button_login);

        mButtonLogin.setOnClickListener(this);

        mInputValidation = new InputValidation(getContext());

        mDatabaseHelper = new DatabaseHelper(getContext());


        return rootView;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.button_login:
                inputFieldValidation();
                break;
        }

    }


    public void inputFieldValidation() {

        // Checking the First Name Field
        if (mInputValidation.isInputEditTextFilled(mEtEmail, "Email Required")) {
            return;
        }
        // Checking the Last Name Field
        if (mInputValidation.isInputEditTextFilled(mEtPassword, "Required")) {
            return;
        }


        candidateLogin();


    }


    private void candidateLogin() {
        boolean login = mDatabaseHelper.checkCandidate(mEtEmail.getText().toString().trim(),mEtPassword.getText().toString().trim());

        if (login){

            Intent mIntentLogin = new Intent(getContext(), CandidateProfileActivity.class);
            mIntentLogin.putExtra("email", mEtEmail.getText().toString().trim());
            startActivity(mIntentLogin);


        }else {
            Toast.makeText(getContext(), "Invalid id and password", Toast.LENGTH_SHORT).show();
        }
    }


}

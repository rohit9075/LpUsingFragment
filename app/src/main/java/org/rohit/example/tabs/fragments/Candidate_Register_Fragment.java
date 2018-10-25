package org.rohit.example.tabs.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.rohit.example.R;
import org.rohit.example.tabs.Utils.InputValidation;
import org.rohit.example.tabs.database.DatabaseHelper;
import org.rohit.example.tabs.model.Candidate;

import java.util.Objects;

/* Fragment used as page 2 */
public class Candidate_Register_Fragment extends Fragment implements View.OnClickListener {

    EditText mEtFname, mEtLname, mEtEmail, mEtPhone, mEtPassword, mEdtCnfPassword;
    Button mBRegister;

    DatabaseHelper mDataBaseHelper;

    Candidate  mCandidate;

    private InputValidation mInputValidation;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_register, container, false);

        initView(rootView);
        
        return rootView;
    }



    private void initView(View rootView) {

        mEtFname = rootView.findViewById(R.id.editText_first_name);
        mEtLname = rootView.findViewById(R.id.editText_last_name);
        mEtEmail = rootView.findViewById(R.id.editText_email);
        mEtPhone = rootView.findViewById(R.id.editText_mobile_number);
        mEtPassword = rootView.findViewById(R.id.editText_password);
        mEdtCnfPassword = rootView.findViewById(R.id.editText_confirm_password);
        mBRegister = rootView.findViewById(R.id.button_register);

        mBRegister.setOnClickListener(this);


        mInputValidation = new InputValidation(getContext());

        mDataBaseHelper = new DatabaseHelper(getContext());
    }
    private void getData(){



        // candidate object instantiation

        mCandidate = new Candidate();

        // storing candidate data into candidate object

        mCandidate.setFirstName(mEtFname.getText().toString().trim());
        mCandidate.setLastName(mEtLname.getText().toString().trim());
        mCandidate.setEmailId(mEtEmail.getText().toString().trim());
        mCandidate.setMobileNumber(mEtPhone.getText().toString().trim());
        mCandidate.setDateOfBirth("06/04/1890");
        mCandidate.setPassword(Objects.requireNonNull(mEtPassword.getText()).toString().trim());
        mCandidate.setGender("Male");

    }


    @Override
    public void onClick(View v) {

        inputFieldValidation();

//        Toast.makeText(getContext(), "Button Clicked", Toast.LENGTH_SHORT).show();

    }



    public void inputFieldValidation() {

        // Checking the First Name Field
        if (mInputValidation.isInputEditTextFilled(mEtFname, "First Name Required")) {
            return;
        }
        // Checking the Last Name Field
        if (mInputValidation.isInputEditTextFilled(mEtLname, "Last Name Required")) {
            return;
        }

//        // Checking the Email Empty Field
//        if (mInputValidation.isInputEditTextFilled(mEditTextEmail, "Email Required")) {
//            return;
//        }
//
//        // Checking the valid email or not Field
//        if (mInputValidation.isInputEditTextEmail(mEditTextEmail)) {
//            return;
//        }
//
//        // Checking the Mobile Number Field
//        if (mInputValidation.isInputEditTextFilled(mEditTextMobile, "Mobile Number Required")) {
//            return;
//        }
//
//        // Checking the DOB Field
//        if (mInputValidation.isInputEditTextFilled(mEditTextDob, "DOB Required")) {
//            return;
//        }
//
//        // Checking the RadioButton
//        if (mInputValidation.isRadioButtonSelected(mRadioGroupGender, mRelativeLayout)) {
//            return;
//        }
//
//
//        // Checking the Password Field
//        if (mInputValidation.isInputTextInputEditTextFilled(mTextInputEditTextPassword, "Password Required")) {
//            return;
//        }
//
//        // Checking the Confirm Password field Field
//        if (mInputValidation.isInputTextInputEditTextFilled(mTextInputEditTextConfirmPassword, "Confirm Password  Required")) {
//            return;
//        }
//
//
//        // Checking the matching both password Field
//        if (mInputValidation. isTextInputEditTextPasswordMatches(mTextInputEditTextPassword, mTextInputEditTextConfirmPassword)) {
//            return;
//        }


        getData();   //getData() method call

        postDataToDatabase();



    }


    private void postDataToDatabase() {


            mDataBaseHelper.addCandidate(mCandidate);

    }


}

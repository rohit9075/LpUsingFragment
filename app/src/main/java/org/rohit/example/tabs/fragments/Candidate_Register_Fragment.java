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

/* Fragment used as page 2 */
public class Candidate_Register_Fragment extends Fragment implements View.OnClickListener {

    EditText mEtFname, mEtLname, mEtEmail, mEtPhone, mEtPassword, mEdtCnfPassword;
    Button mBRegister;



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
    }
    private void getData(){

        StringBuilder builder  = new StringBuilder();

        String name ;

        name = mEtFname.getText().toString();

        builder.append("Fisrt Name " +  mEtFname.getText().toString() + "  Last Name " + mEtLname.getText().toString() +
                " Email  " + mEtEmail.getText().toString());

        Toast.makeText(getContext(), builder, Toast.LENGTH_SHORT).show();

    }


    @Override
    public void onClick(View v) {

        getData();

//        Toast.makeText(getContext(), "Button Clicked", Toast.LENGTH_SHORT).show();

    }


}

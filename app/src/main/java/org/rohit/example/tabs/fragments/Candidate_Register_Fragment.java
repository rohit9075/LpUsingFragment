package org.rohit.example.tabs.fragments;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Shader;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.theartofdev.edmodo.cropper.CropImage;
import com.theartofdev.edmodo.cropper.CropImageView;

import org.rohit.example.R;

import org.rohit.example.tabs.Utils.InputValidation;
import org.rohit.example.tabs.database.DatabaseHelper;
import org.rohit.example.tabs.model.Candidate;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Objects;

import static android.app.Activity.RESULT_OK;


/* Fragment used as page 2 */
public class Candidate_Register_Fragment extends Fragment implements View.OnClickListener {

    EditText mEtFname, mEtLname, mEtEmail, mEtPhone, mEtPassword, mEdtCnfPassword;
    Button mBRegister;

    ImageView mImageViewProfile;

    DatabaseHelper mDataBaseHelper;

    private static final int MY_PERMISSIONS_REQUEST_READ_CONTACTS = 1001;
    byte [] mCandidateImageBytes;

    Candidate  mCandidate;

    private InputValidation mInputValidation;

    private static final String TAG = "RegisterActivity";



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

        mImageViewProfile = rootView.findViewById(R.id.imageView_candidate_profile);

        mImageViewProfile.setOnClickListener(this);

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

        switch (v.getId()){
            case R.id.button_register:
                inputFieldValidation();
                break;

            case R.id.imageView_candidate_profile:
//                checkPermissions();

                CropImage.activity()
                        .start(getContext(), this);
                break;
        }



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


    /**
     * method to handle image result
     * @param requestCode image request code
     * @param resultCode result status
     * @param data intent data
     */

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == CropImage.CROP_IMAGE_ACTIVITY_REQUEST_CODE) {
            CropImage.ActivityResult result = CropImage.getActivityResult(data);
            if (resultCode == RESULT_OK) {

                Uri resultUri = result.getUri();

                BitmapFactory.Options options = new BitmapFactory.Options();

                options.inSampleSize = 4;

                try {
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContext().getContentResolver(), resultUri);
                    Bitmap circleBitmap = Bitmap.createBitmap(bitmap.getWidth(), bitmap.getHeight(), Bitmap.Config.ARGB_8888);
                    BitmapShader shader = new BitmapShader (bitmap,  Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
                    Paint paint = new Paint();
                    paint.setShader(shader);
                    paint.setAntiAlias(true);
                    Canvas c = new Canvas(circleBitmap);
                    c.drawCircle(bitmap.getWidth()/2, bitmap.getHeight()/2, bitmap.getWidth()/2, paint);

                    mImageViewProfile.setImageBitmap(circleBitmap);

                } catch (IOException e) {
                    e.printStackTrace();
                }

                // converting the image to bytes Arrays

                mImageViewProfile.setDrawingCacheEnabled(true);
                mImageViewProfile.buildDrawingCache();
                Bitmap bitmap=mImageViewProfile.getDrawingCache();
                ByteArrayOutputStream outputStream=new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.PNG,100,outputStream);

                mCandidateImageBytes = outputStream.toByteArray();

            } else if (resultCode == CropImage.CROP_IMAGE_ACTIVITY_RESULT_ERROR_CODE) {
                Exception error = result.getError();
                Log.d(TAG, "onActivityResult: " + error);
            }
        }
    }





}

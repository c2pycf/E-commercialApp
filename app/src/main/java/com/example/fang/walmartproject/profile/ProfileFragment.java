package com.example.fang.walmartproject.profile;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.fang.walmartproject.AppController;
import com.example.fang.walmartproject.R;
import com.example.fang.walmartproject.login.LoginActivity;


public class ProfileFragment extends Fragment implements ProfileContract.ProfileView {
    AppController volley;
    EditText fnameEditText,lnameEditText,addressEditText,emailEditText,phoneEditText;
    LinearLayout profileContent;
    Button signInButton;
    ProfilePresenter mProfilePresenter;
    static String TAG = ProfileFragment.class.getSimpleName();

    public ProfileFragment() {
        mProfilePresenter = new ProfilePresenter(this);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profie,container,false);
        volley = AppController.getInstance();
        Log.d(TAG,"Fragment on creat");
        fnameEditText = view.findViewById(R.id.et_fname_pro);
        lnameEditText = view.findViewById(R.id.et_lname_pro);
        addressEditText = view.findViewById(R.id.et_address_pro);
        emailEditText = view.findViewById(R.id.et_emil_pro);
        phoneEditText = view.findViewById(R.id.et_mobile_pro);
        profileContent = view.findViewById(R.id.profile_content);
        signInButton = view.findViewById(R.id.bt_login_pro);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Profile");
        Log.d(TAG,"resume "+volley.getSignFlag());
        if(volley.getSignFlag()==1){
            //getdata
            Log.d(TAG,"showing");
            profileContent.setVisibility(View.VISIBLE);
            signInButton.setVisibility(View.GONE);
            mProfilePresenter.setText();
        }
        else {
            profileContent.setVisibility(View.GONE);
            signInButton.setVisibility(View.VISIBLE);
            signInButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mProfilePresenter.onSignInHandled();
                }
            });

        }

    }

    @Override
    public void onResume() {
        super.onResume();

    }

    @Override
    public void showSignInPage() {
        Intent intent = new Intent(getActivity(),LoginActivity.class);
        getActivity().startActivity(intent);

    }

}

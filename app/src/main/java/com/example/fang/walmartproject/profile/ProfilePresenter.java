package com.example.fang.walmartproject.profile;

public class ProfilePresenter implements ProfileContract.ProfilePresenter {
    ProfileContract.ProfileView mView;

    public ProfilePresenter(ProfileFragment context) {
        this.mView = context;
    }

    @Override
    public void onSignInHandled() {
        mView.showSignInPage();
    }

    @Override
    public void setText() {

    }
}

package di2k.csi.loginpage.main;

import di2k.csi.loginpage.R;
import di2k.csi.loginpage.auth.LoginActivity;
import di2k.csi.loginpage.network.Utils;
//Created by 디딬 Didik M. Hadiningrat on 14 July 2019
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class MainActivity extends AppCompatActivity {
    private static FragmentManager fragmentManager;
    public Object replaceLoginFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentManager = getSupportFragmentManager();

        // If savedinstnacestate is null then replace login fragment
        if (savedInstanceState == null) {
            fragmentManager
                    .beginTransaction()
                    .replace(R.id.frameContainer, new LoginActivity(),
                            Utils.Login_Fragment).commit();
        }

        // On close icon click finish activity
        findViewById(R.id.close_activity).setOnClickListener(
                new OnClickListener() {

                    @Override
                    public void onClick(View arg0) {
                        finish();

                    }
                });

    }

    // Replace Login Fragment with animation
    public void replaceLoginFragment() {
        fragmentManager
                .beginTransaction()
                .setCustomAnimations(R.anim.left_enter, R.anim.right_out)
                .replace(R.id.frameContainer, new LoginActivity(),
                        Utils.Login_Fragment).commit();
    }

    @Override
    public void onBackPressed() {

        // Find the tag of signup and forgot password fragment
        Fragment RegisterActivity = fragmentManager
                .findFragmentByTag(Utils.SignUp_Fragment);
        Fragment ForgotPasswordActivity = fragmentManager
                .findFragmentByTag(Utils.ForgotPassword_Fragment);

        // Check if both are null or not
        // If both are not null then replace login fragment else do backpressed
        // task

        if (RegisterActivity != null)
            replaceLoginFragment();
        else if (ForgotPasswordActivity != null)
            replaceLoginFragment();
        else
            super.onBackPressed();
    }
}
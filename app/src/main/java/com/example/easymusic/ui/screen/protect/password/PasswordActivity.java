package com.example.easymusic.ui.screen.protect.password;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.example.easymusic.R;
import com.example.easymusic.databinding.ActivityPasswordBinding;
import com.example.easymusic.ui.base.BaseActivity;
import com.example.easymusic.ui.screen.protect.hidden.HiddenActivity;
import com.hanks.passcodeview.PasscodeView;

public class PasswordActivity extends BaseActivity<ActivityPasswordBinding, PasswordViewModel> implements PasswordListener {

    SharedPreferences sharedPreferences;

    @Override
    protected Class<PasswordViewModel> getViewModeClass() {
        return PasswordViewModel.class;
    }

    @Override
    protected void init() {
        sharedPreferences = getSharedPreferences("password", MODE_PRIVATE);
        checkPasswordScreen();
        binding.setListener(this);

        binding.passcodeView.setListener(new PasscodeView.PasscodeViewListener() {
            @Override
            public void onFail() {
                Toast.makeText(PasswordActivity.this, "Sai mật khẩu", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onSuccess(String number) {
                setPassword(number);
                String password = sharedPreferences.getString("password", "");
                Log.e("onSuccess: ", password);
                Log.e("onSuccess: ", binding.passcodeView.getLocalPasscode());
                Intent intent = new Intent(PasswordActivity.this, HiddenActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_password;
    }

    private void setPassword(String number){
        String password = number;
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("password", password);
        editor.commit();
    }

    @SuppressLint("WrongConstant")
    private void checkPasswordScreen(){
        String password = sharedPreferences.getString("password", null);
        if (password == null){
            binding.passcodeView.setPasscodeType(0);
        }else {
            binding.passcodeView.setLocalPasscode(password);
            binding.passcodeView.setPasscodeType(1);

        }
    }


    @Override
    public void creteMenu(View view) {
        PopupMenu popupMenu = new PopupMenu(view.getContext(), view);
        popupMenu.getMenuInflater().inflate(R.menu.password_popup_menu, popupMenu.getMenu());
        popupMenu.show();

        popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {
                switch (menuItem.getItemId()){
                    case R.id.login:


                        return true;
                    case R.id.reset_password:


                        return true;

                    default:
                        return false;
                }
            }
        });
    }


}
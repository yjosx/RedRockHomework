package pow.jie.music;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import org.litepal.LitePal;

import java.util.List;

public class RegisterActivity extends AppCompatActivity {
    private EditText accountEdit;
    private EditText passwordEdit;

    public static void startRegisterActivity(Context context) {
        Intent toRegister = new Intent(context, RegisterActivity.class);
        context.startActivity(toRegister);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Toolbar toolbar = findViewById(R.id.toolbar);
        //添加返回键
        toolbar.setNavigationIcon(R.drawable.ic_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        //定义两个textview、按键
        Button buttonRegister = findViewById(R.id.register);
        accountEdit = findViewById(R.id.text_register_account);
        passwordEdit = findViewById(R.id.text_register_passwd);

        buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputAccount = accountEdit.getText().toString();
                String inputPassword = passwordEdit.getText().toString();
                if (register(inputAccount, inputPassword)) {
                    finish();
                } else {
                    accountEdit.setText("");
                    passwordEdit.setText("");
                }
            }
        });
    }

    public boolean register(String account, String password) {
        if (account.length() > 11) {
            Toast.makeText(RegisterActivity.this,
                    "手机号有误", Toast.LENGTH_SHORT).show();
            return false;
        }
        List<UserInfo> userInfos = LitePal.findAll(UserInfo.class);
        for (UserInfo user : userInfos) {
            if (user.getAccount().equals(account)) {
                Toast.makeText(RegisterActivity.this,
                        "手机号已注册", Toast.LENGTH_SHORT).show();
                return false;
            }
        }
        UserInfo userInfo = new UserInfo();
        userInfo.setAccount(account);
        userInfo.setPassword(password);
        userInfo.save();
        Toast.makeText(RegisterActivity.this,
                "注册成功，快登录吧", Toast.LENGTH_SHORT).show();
        return true;
    }
}

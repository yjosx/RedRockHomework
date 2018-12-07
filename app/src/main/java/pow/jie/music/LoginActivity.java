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

public class LoginActivity extends AppCompatActivity {
    private EditText accountEdit;
    private EditText passwordEdit;

    public static void startLoginActivity(Context context) {
        Intent toLogin = new Intent(context, LoginActivity.class);
        context.startActivity(toLogin);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
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
        accountEdit = findViewById(R.id.text_login_account);
        passwordEdit = findViewById(R.id.text_login_password);
        Button buttonLogin = findViewById(R.id.login);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String inputAccount = accountEdit.getText().toString();
                String inputPassword = passwordEdit.getText().toString();
                login(inputAccount, inputPassword);
            }
        });
    }

    public boolean login(String account, String password) {
        if (account.length() > 11) {
            Toast.makeText(LoginActivity.this,
                    "手机号有误", Toast.LENGTH_SHORT).show();
            return false;
        }
        List<UserInfo> userInfos = LitePal.findAll(UserInfo.class);
        for (UserInfo user : userInfos) {
            if (user.getAccount().equals(account)) {
                if (user.getPassword().equals(password)) {
                    Toast.makeText(LoginActivity.this,
                            "登录成功", Toast.LENGTH_SHORT).show();
                    return true;
                }
            }
        }
        Toast.makeText(LoginActivity.this,
                "账号或密码错误", Toast.LENGTH_SHORT).show();
        accountEdit.setText("");
        passwordEdit.setText("");
        return false;
    }
}

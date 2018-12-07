package pow.jie.music;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import org.litepal.LitePal;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //创建数据库
        LitePal.getDatabase();
        UserInfo userInfo = new UserInfo();
        userInfo.setAccount("123456");
        userInfo.setPassword("admin");
        userInfo.save();
        //登录按键相关逻辑
        Button toLogin = findViewById(R.id.to_login);
        toLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LoginActivity.startLoginActivity(MainActivity.this);
            }
        });
        //注册按键相关逻辑
        Button toRegister = findViewById(R.id.to_register);
        toRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegisterActivity.startRegisterActivity(MainActivity.this);
            }
        });
    }

}

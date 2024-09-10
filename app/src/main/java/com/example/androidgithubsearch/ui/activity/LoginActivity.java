

package com.example.androidgithubsearch.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.androidgithubsearch.R;

import java.util.UUID;


/**
 * Created on 2017/7/12.
 *
 * @author ThirtyDegreesRay
 */

public class LoginActivity extends AppCompatActivity {

    public String clientId = "Ov23liKVbkq5XZdnexNX";
    public String clientSecret = "a0662553ede60d2bf5e1d963755a125380c44ddd";

    public static String openhub_client_id = "8f7213694e115df205fb";
    public static String  openhub_client_secret = "82c57672382db5c7b528d79e283c398ad02e3c3f";
    private final String TAG = LoginActivity.class.getSimpleName();
    private Button loginBn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); // 调用父类的onCreate方法

        // 设置当前Activity的布局
        setContentView(R.layout.activity_login_new);


        loginBn = findViewById(R.id.login_bn);

        loginBn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppOpener.openInCustomTabsOrBrowser(LoginActivity.this,
                        getOAuth2Url());
            }
        });

    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
//        mPresenter.handleOauth(intent);
        Log.d(TAG, intent.toString());

    }

    public static String getOAuth2Url() {
        String randomState = UUID.randomUUID().toString();
        return AppConfig.OAUTH2_URL +
                "?client_id=" + openhub_client_id +
                "&scope=" + AppConfig.OAUTH2_SCOPE +
                "&state=" + randomState;
    }


}

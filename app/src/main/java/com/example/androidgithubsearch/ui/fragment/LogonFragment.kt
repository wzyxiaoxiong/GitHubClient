package com.example.androidgithubsearch.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.example.androidgithubsearch.R
import com.example.androidgithubsearch.data.api.GitHubApiService
import com.example.androidgithubsearch.ui.activity.AppConfig
import com.example.androidgithubsearch.ui.activity.AppOpener
import com.example.androidgithubsearch.ui.activity.LoginActivity
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.hilt.android.AndroidEntryPoint
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.UUID


@AndroidEntryPoint
class LogonFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_login, container, false)

        val usernameEditText = view.findViewById<EditText>(R.id.username)
        val passwordEditText = view.findViewById<EditText>(R.id.password)
        val loginButton = view.findViewById<View>(R.id.login_button)

        loginButton.setOnClickListener(View.OnClickListener {
            val username: String = usernameEditText.getText().toString()
            val password: String = passwordEditText.getText().toString()
            performLogin(username, password)
        })

        val loginButton2 = view.findViewById<View>(R.id.login_button2)

        loginButton2.setOnClickListener(View.OnClickListener {
            AppOpener.openInCustomTabsOrBrowser(
                context,
                LoginActivity.getOAuth2Url()
            )
        })
        return view
    }

    fun provideGitHubApiService(): GitHubApiService {
        val moshi = Moshi.Builder()
            .add(KotlinJsonAdapterFactory())
            .build()

        val logging = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        val client = OkHttpClient.Builder().addInterceptor(logging).build()

        val retrofit = Retrofit.Builder()
            .baseUrl("https://github.com/")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .client(client)
            .build()

        return retrofit.create(GitHubApiService::class.java)
    }

    private fun performLogin(username: String, password: String) {
         val gitHubService = provideGitHubApiService()
        val randomState = UUID.randomUUID().toString()
        gitHubService.getAccessToken(LoginActivity.openhub_client_id, AppConfig.OAUTH2_SCOPE, randomState)?.enqueue(object :
            Callback<ResponseBody?> {
            override fun onResponse(call: Call<ResponseBody?>, response: Response<ResponseBody?>) {
                if (response.isSuccessful) {
                    val token = response.body()?.string()
                    Log.d("LogonFragment", "token=$token")
                } else {
                    Log.d("LogonFragment", "response=$response")
                }
            }

            override fun onFailure(call: Call<ResponseBody?>, t: Throwable) {
                Log.d("LogonFragment", "t=$t")
            }
        })
    }


}
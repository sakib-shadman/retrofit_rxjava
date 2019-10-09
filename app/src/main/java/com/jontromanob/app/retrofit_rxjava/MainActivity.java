package com.jontromanob.app.retrofit_rxjava;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.jontromanob.app.retrofit_rxjava.retrofit.ApiClient;
import com.jontromanob.app.retrofit_rxjava.retrofit.login.LoginApiInterface;
import com.jontromanob.app.retrofit_rxjava.retrofit.login.model.LogInResponse;

import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class MainActivity extends AppCompatActivity {


    CompositeDisposable compositeDisposable;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        compositeDisposable = new CompositeDisposable();
        Button loginButton = (Button) findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                getLoginData();
            }
        });
    }

    private void getLoginData(){

        LoginApiInterface loginApiInterface = ApiClient.getClient().create(LoginApiInterface.class);

        loginApiInterface.postLoginInfo("shadman","123456","password",2,"","","")
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
        .subscribe(new SingleObserver<LogInResponse>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess(LogInResponse logInResponse) {

                Toast.makeText(MainActivity.this,"Login Success",Toast.LENGTH_SHORT).show();

            }

            @Override
            public void onError(Throwable e) {

                Toast.makeText(MainActivity.this,"Login Failure",Toast.LENGTH_SHORT).show();
            }
        });


    }

}

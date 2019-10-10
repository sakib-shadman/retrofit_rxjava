package com.jontromanob.app.retrofit_rxjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.jontromanob.app.retrofit_rxjava.retrofit.ApiClient;
import com.jontromanob.app.retrofit_rxjava.retrofit.ServiceRepository;
import com.jontromanob.app.retrofit_rxjava.retrofit.login.model.LogInResponse;
import com.jontromanob.app.retrofit_rxjava.retrofit.visitapplication.model.VisitApplicationDetails;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {


    String auth = "";
    CompositeDisposable compositeDisposable;
    RepoRepository repoRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_main);
        compositeDisposable = new CompositeDisposable ();
        repoRepository = new RepoRepository (ApiClient.getClient ().create (ServiceRepository.class));
        Button loginButton = (Button) findViewById (R.id.loginButton);
        loginButton.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {

                getLoginData ();
            }
        });
    }

    private void getLoginData() {

        compositeDisposable.add (repoRepository.getLoginInfo ("shadman", "12345", "password", 2)
                .subscribeOn (Schedulers.io ()).observeOn (AndroidSchedulers.mainThread ())
                .subscribeWith (new DisposableSingleObserver<LogInResponse> () {


                                    @Override
                                    public void onSuccess(LogInResponse logInResponse) {
                                        auth = logInResponse.getTokenType () + " " + logInResponse.getAccessToken ();
                                        getVisitData ();
                                        Toast.makeText (MainActivity.this, "Login Success", Toast.LENGTH_SHORT).show ();
                                    }

                                    @Override
                                    public void onError(Throwable e) {

                                    }
                                }

                ));


       /* final LoginApiInterface loginApiInterface = ApiClient.getClient ().create (LoginApiInterface.class);

        loginApiInterface.postLoginInfo ("shadman", "12345", "password", 2, null, null, null)
                .subscribeOn (Schedulers.io ()).observeOn (AndroidSchedulers.mainThread ())
                .subscribe (new SingleObserver<LogInResponse> () {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(LogInResponse logInResponse) {

                        auth = logInResponse.getTokenType () + " " + logInResponse.getAccessToken ();
                        getVisitData ();
                        Toast.makeText (MainActivity.this, "Login Success", Toast.LENGTH_SHORT).show ();

                    }

                    @Override
                    public void onError(Throwable e) {

                        Toast.makeText (MainActivity.this, "Login Failure", Toast.LENGTH_SHORT).show ();
                    }
                });*/


    }


    private void getVisitData() {

        compositeDisposable.add (repoRepository.getVisitApplications (auth).subscribeOn (Schedulers.io ()).observeOn (AndroidSchedulers.mainThread ())
                .subscribeWith (new DisposableSingleObserver<Response<List<VisitApplicationDetails>>> () {


                    @Override
                    public void onSuccess(Response<List<VisitApplicationDetails>> listResponse) {

                        Toast.makeText (MainActivity.this, " " + listResponse.body ().size (), Toast.LENGTH_SHORT).show ();
                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                }));

       /* Single<Response<List<VisitApplicationDetails>>> testObservable = ApiClient.getClient ().create (VisitApplicationInterface.class).getAllVisitApplicationList (auth);

        testObservable.subscribeOn (Schedulers.io ()).observeOn (AndroidSchedulers.mainThread ()).subscribe (new SingleObserver<Response<List<VisitApplicationDetails>>> () {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess(Response<List<VisitApplicationDetails>> listResponse) {

                Toast.makeText (MainActivity.this, " " + listResponse.body ().size (), Toast.LENGTH_SHORT).show ();
                listResponse.body ().size ();
            }

            @Override
            public void onError(Throwable e) {

            }
        });*/


       /* VisitApplicationInterface visitApplicationInterface = ApiClient.getClient ().create (VisitApplicationInterface.class);

        visitApplicationInterface.getAllVisitApplicationList (auth).subscribeOn (Schedulers.io ()).observeOn (AndroidSchedulers.mainThread ())
                .subscribe (new SingleObserver<List<VisitApplicationDetails>> () {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(List<VisitApplicationDetails> visitApplicationDetails) {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }
                });*/
    }

    @Override
    protected void onDestroy() {
        super.onDestroy ();
        if(compositeDisposable != null){
            compositeDisposable.clear ();
        }
    }
}

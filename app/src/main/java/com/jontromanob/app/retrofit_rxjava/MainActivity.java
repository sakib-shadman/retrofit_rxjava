package com.jontromanob.app.retrofit_rxjava;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.jontromanob.app.retrofit_rxjava.retrofit.ApiClient;
import com.jontromanob.app.retrofit_rxjava.retrofit.ServiceRepository;
import com.jontromanob.app.retrofit_rxjava.retrofit.leaveapplication.model.CombinedApplications;
import com.jontromanob.app.retrofit_rxjava.retrofit.leaveapplication.model.LeaveApplicationDetails;
import com.jontromanob.app.retrofit_rxjava.retrofit.login.model.LogInResponse;
import com.jontromanob.app.retrofit_rxjava.retrofit.visitapplication.VisitApplicationInterface;
import com.jontromanob.app.retrofit_rxjava.retrofit.visitapplication.model.VisitApplicationDetails;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.BiFunction;
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

        /////******* Log in api call is implemented using composite disposable ******/////////
        compositeDisposable.add (repoRepository.getLoginInfo ("shadman", "12345", "password", 2)
                .subscribeOn (Schedulers.io ()).observeOn (AndroidSchedulers.mainThread ())
                .subscribeWith (new DisposableSingleObserver<LogInResponse> () {


                                    @Override
                                    public void onSuccess(LogInResponse logInResponse) {
                                        auth = logInResponse.getTokenType () + " " + logInResponse.getAccessToken ();

                                        setCombinedObservable ();
                                        //getVisitDataUsingCompositeDisposable ();
                                        Toast.makeText (MainActivity.this, "Login Success", Toast.LENGTH_SHORT).show ();
                                    }

                                    @Override
                                    public void onError(Throwable e) {

                                    }
                                }

                ));

        /////******* Log in api call is implemented using composite disposable ******/////////


        ////****** Log in api call is implemented using single  observer ********//////////
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

        ////****** Log in api call is implemented using single  observer ********//////////


    }


    /////******* Visit api call is implemented using single response observer ******/////////

    private void getVisitDataUsingSingleResponseObserver() {


        Single<Response<List<VisitApplicationDetails>>> testObservable = ApiClient.getClient ().create (VisitApplicationInterface.class).getAllVisitApplicationList (auth);

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
        });


    }
    /////******* Visit api call is implemented using single response observer ******/////////


    /////******* Visit api call is implemented using single observer ******/////////

    private void getVisitDataUsingSingleObserver() {


        VisitApplicationInterface visitApplicationInterface = ApiClient.getClient ().create (VisitApplicationInterface.class);

        visitApplicationInterface.getAllVisitApplicationListSingle (auth).subscribeOn (Schedulers.io ()).observeOn (AndroidSchedulers.mainThread ())
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
                });

    }

    /////******* Visit api call is implemented using single observer ******/////////


    /////******* Visit api call is implemented using composite disposable ******/////////

    private void getVisitDataUsingCompositeDisposable() {


        compositeDisposable.add (repoRepository.getVisitApplications (auth).subscribeOn (Schedulers.io ()).observeOn (AndroidSchedulers.mainThread ())
                .subscribeWith (new DisposableSingleObserver<Response<List<VisitApplicationDetails>>> () {


                    @Override
                    public void onSuccess(Response<List<VisitApplicationDetails>> listResponse) {

                        Toast.makeText (MainActivity.this, " " + listResponse.body ().size (), Toast.LENGTH_SHORT).show ();
                    }

                    @Override
                    public void onError(Throwable e) {

                        Toast.makeText (MainActivity.this, " " + e, Toast.LENGTH_SHORT).show ();

                    }
                }));


    }

    /////******* Visit api call is implemented using composite disposable ******/////////



    ////****** Combine multiple request using zip ********//////////
    private Observable<List<LeaveApplicationDetails>> getLeaveAppObservable(String auth) {

        return repoRepository.getLeaveApplication (auth).subscribeOn (Schedulers.io ()).observeOn (AndroidSchedulers.mainThread ());

    }

    private Observable<List<VisitApplicationDetails>> getVisitAppObservable(String auth) {

        return repoRepository.getVisitApplicationObservable (auth).subscribeOn (Schedulers.io ()).observeOn (AndroidSchedulers.mainThread ());

    }

    private void setCombinedObservable() {

        Observable<CombinedApplications> combined = Observable.zip (getLeaveAppObservable (auth), getVisitAppObservable (auth),
                new BiFunction<List<LeaveApplicationDetails>, List<VisitApplicationDetails>, CombinedApplications> () {
                    @Override
                    public CombinedApplications apply(List<LeaveApplicationDetails> leaveApplicationDetails, List<VisitApplicationDetails> visitApplicationDetails) throws Exception {
                        return new CombinedApplications (leaveApplicationDetails, visitApplicationDetails);
                    }
                });


        combined.observeOn (AndroidSchedulers.mainThread()).subscribe (new Observer<CombinedApplications> () {

            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(CombinedApplications combinedApplications) {

                combinedApplications.getLeaveApplicationDetails ().size ();
                combinedApplications.getVisitApplicationDetails ().size ();

            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onComplete() {

            }
        });

    }

    ////****** Combine multiple request using zip ********//////////

    @Override
    protected void onDestroy() {
        super.onDestroy ();
        if (compositeDisposable != null) {
            compositeDisposable.clear ();
        }
    }
}

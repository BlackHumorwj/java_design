package com.example.sf_demo.frame.rxjava;


import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class RxUtil {



    public void doRxJava(){

        Observable<String> observable =  Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> emitter) throws Exception {
                emitter.onNext("1");
                emitter.onNext("2");
                emitter.onComplete();

            }
        });

        //ObservableCreate#subscribe()
        observable.subscribe(new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                //1
            }

            @Override
            public void onNext(String s) {
                //2
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {
                //3
            }
        });


    }

}

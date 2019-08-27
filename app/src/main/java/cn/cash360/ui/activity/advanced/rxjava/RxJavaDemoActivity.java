package cn.cash360.ui.activity.advanced.rxjava;

import cn.cash360.ui.activity.base.BaseActivity;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * @time 2019/8/16 11:24
 * @desc
 */
public class RxJavaDemoActivity extends BaseActivity {

    @Override
    protected int getLayoutResID() {
        return super.getLayoutResID();
    }


    Observer<String> myObserver;

    @Override
    protected void init() {
        super.init();
        rumenji();
        zhongji();
        xianChengQiehuan();
    }

    /**
     * 线程切换
     */
    private void xianChengQiehuan() {
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {

            }
        })
                .observeOn(Schedulers.io())

                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(String s) {

                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });


    }

    /**
     * 订阅流程梳理
     */
    private void zhongji() {

        ObservableOnSubscribe<String> source1;

        Function<String, ObservableSource<String>> mapper;

        Observer<String> myObserver;

        Observable<String> observable1 = Observable.create(source1 = new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                e.onNext("hhh");


            }
        });


        Observable<String> observable2 = observable1.flatMap(mapper = new Function<String, ObservableSource<String>>() {
            @Override
            public ObservableSource<String> apply(String s) throws Exception {
                return Observable.just(s);
            }
        });

        observable2.subscribe(myObserver = new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String s) {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });

        //订阅流程 向上调用依次订阅 ，发射流程从上往下依次调用 Observer.onSubscribe(Disposable s)  Observer.onNext(T t)

        //observable2.subscribe(myObserver)

        // -->  ObservableFlatMap.subscribeActual(myObserver)

        // --> source.subscribe(mergeObserver) 包装一下observer

        // -->  observable1.subscribe(mergeObserver)

        //-->observable1.subscribeActual(mergeObserver) -- > myObserver.onSubscribe(@NonNull Disposable d)

        // --> ObservableOnSubscribe.subscribe(ObservableEmitter<String> e)


        //第一个Observable调用,返回observable2
        //
        //
        // ObservableFlatMap  observable2
        // ObservableSource  source  是  observable1
        //
        // public final <R> Observable<R> flatMap(Function<? super T, ? extends ObservableSource<? extends R>> mapper,
        //                  boolean delayErrors, int maxConcurrency, int bufferSize) {
        //        ObjectHelper.requireNonNull(mapper, "mapper is null");
        //        ObjectHelper.verifyPositive(maxConcurrency, "maxConcurrency");
        //        ObjectHelper.verifyPositive(bufferSize, "bufferSize");
        //        if (this instanceof ScalarCallable) {
        //            @SuppressWarnings("unchecked")
        //            T v = ((ScalarCallable<T>)this).call();
        //            if (v == null) {
        //                return empty();
        //            }
        //            return ObservableScalarXMap.scalarXMap(v, mapper);
        //        }

        // this 将当前的 调用对象的 传给 ObservableFlatMap中并赋值给 ObservableSource<T> source
        //        return RxJavaPlugins.onAssembly(new ObservableFlatMap<T, R>(this, mapper, delayErrors, maxConcurrency, bufferSize));
        //    }

        // /

    }


    /**
     * 简单订阅
     */
    private void rumenji() {
        //Observable#create(ObservableOnSubscribe<T> source) 返回一个 ObservableCreate 对象，ObservableCreate是
        //Observable 的子类
        final Observable<String> observableCreate = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
                e.onNext("123");
                e.onNext("234");
                e.onComplete();
            }
        });


        //        @Override
        //        protected void subscribeActual(Observer<? super T> observer) {
        //            CreateEmitter<T> parent = new CreateEmitter<T>(observer);
        //            observer.onSubscribe(parent);
        //
        //            try {
        //                source.subscribe(parent);
        //            } catch (Throwable ex) {
        //                Exceptions.throwIfFatal(ex);
        //                parent.onError(ex);
        //            }
        //        }


        //=> observableCreate*subscribe(Observer<? super T> observer);
        //=> ObservableCreate*subscribeActual(observer);
        //=> MyObserver*onSubscribe(@NonNull Disposable d);
        //=> observableCreate*subscribe(@NonNull ObservableEmitter<T> e);

        observableCreate.subscribe(myObserver = new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onNext(String s) {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onComplete() {

            }
        });
    }
}

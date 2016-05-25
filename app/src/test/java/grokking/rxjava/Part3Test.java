package grokking.rxjava;

import org.junit.Test;

import rx.Observable;
import rx.Subscriber;

public class Part3Test {
    @Test
    public void error_handling() throws Exception {
        Observable.just("Hello World")
                .map(s -> potentialException(s))
                .map(s -> anotherPotentialException(s))
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {
                        System.out.println("Completed !!");
                    }

                    @Override
                    public void onError(Throwable e) {
                        System.out.println("Ouch !!");
                    }

                    @Override
                    public void onNext(String s) {
                        System.out.println(s);
                    }
                });
        // Every Observable ends with either a single call to onCompleted() or onError().
        // onError() is called if an Exception is thrown at any time. makes error handling much simpler.
        // operators don't have to handle the Exception.
        // this pattern a lot easier than traditional error handling.
        // With callbacks you have to handle errors in each callback.
        //      Not only does that lead to repetitious code,
        //      but it also means that each callback must know how to handle errors,
        //      meaning your callback code is tightly coupled to the caller.
    }

    @Test
    public void schedulers() throws Exception {
        // subscribeOn() : observer code가 실행되는 쓰레드 명시
        // observeOn(): subscriber가 동작하는 쓰레드 명시
        // observer <> subscriber

//        myObservableServices.retrieveImage(url)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(bitmap -> myImageView.setImageBitmap(bitmap));
    }

    private String anotherPotentialException(String s) {
        return s;
    }

    private String potentialException(String s) {
        return s;
    }
}

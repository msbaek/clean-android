package grokking.rxjava;

import org.junit.Test;

import rx.Observable;
import rx.Subscriber;
import rx.functions.Action1;
import rx.functions.Func1;

public class Part1Test {
    @Test
    public void create_observable_with_create() throws Exception {
        // this observable emits "Hello, world !!" then completes
        Observable<String> observable = Observable.create(new Observable.OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("Hello, world !!");
                subscriber.onCompleted();
            }
        });
        System.out.println("1. observable created");

        Subscriber<String> subscriber = new Subscriber<String>() {
            @Override
            public void onCompleted() {
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onNext(String s) {
                System.out.println(s);
            }
        };
        System.out.println("2. subscriber created");

        System.out.println("3. subscribe will be started");
        observable.subscribe(subscriber);
    }

    @Test
    public void just_emits_a_single_item_then_completes() throws Exception {
        Observable<String> observable = Observable.just("Hello, world !!");
        System.out.println("1. observable created");

//        Action1<? super String> onNextAction = new Action1<String>() {
//            @Override
//            public void call(String s) {
//                System.out.println(s);
//            }
//        };

//        Action1<? super String> onNextAction = (Action1<String>) s -> System.out.println(s); // replace with lambda

        Action1<? super String> onNextAction = (Action1<String>) System.out::println; // replace with method reference

        observable.subscribe(onNextAction);

        System.out.println("2. subscribe done");
    }

    @Test
    public void use_just_and_Action1_with_method_chaining() throws Exception {
//        Observable.just("Hello, world !!")
//                .subscribe(new Action1<String>() {
//                    @Override
//                    public void call(String s) {
//                        System.out.println(s);
//                    }
//                });

//        Observable.just("Hello, world !!")
//                .subscribe(s -> {
//                    System.out.println(s);
//                }); // replace with lambda

        Observable.just("Hello, world !!") //
                .subscribe(System.out::println); // replace with method reference
    }

    @Test
    public void transformation___use_observable_in_multiple_places_but_only_sometimes_want_to_add_the_signature() throws Exception {
        Observable.just("Hello, World !!") //
                .map(s -> s + " -Dan") //
                .subscribe(s -> System.out.println(s));
    }
}

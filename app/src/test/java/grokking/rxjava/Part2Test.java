package grokking.rxjava;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import rx.Observable;
import rx.functions.Func1;

public class Part2Test {
    // Returns a List of website URLs based on a text search
    Observable<List<String>> query(String text) {
        return Observable.just(Arrays.asList("url1", "url2", "url3"));
    }


    // Returns the title of a website, or null if 404
    Observable<String> getTitle(String URL) {
        return Observable.just("title for " + URL);
    }

    private void saveTitle(String title) {
        System.out.printf("save title [%s]\n", title);
    }

    @Test
    public void name() throws Exception {
        query("Hello, There !!")
                .subscribe(urls -> {
                    for (String url : urls) {
                        System.out.println(url);
                    }
                }); // using for loop

        query("Hello, There !!")
                .subscribe(urls -> {
                    Observable.from(urls) // from: takes a collection of items and emits each them one at a time
                            .subscribe(url -> System.out.println(url));
                }); // using Observable.from

        // flatMap takes the emissions of one Observable
        // and returns the emissions of another Observable to take its place.
        query("Hello, There !!")
                .flatMap(new Func1<List<String>, Observable<String>>() { // map과 달리 Observable을 반환
                    @Override
                    public Observable<String> call(List<String> strings) {
                        return Observable.from(strings);
                    }
                })
//                .flatMap(new Func1<String, Observable<String >>() {
//                    @Override
//                    public Observable<String> call(String s) {
//                        return Observable.just(s);
//                    }
//                })
//                .map(new Func1<String, String>() {
//                    @Override
//                    public String call(String s) {
//                        return s;
//                    }
//                }) // this map works sames as above flatMap
                .subscribe(url -> System.out.println(url));
//        query("Hello, There !!")
//                .flatMap(urls -> Observable.from(urls))
//                .subscribe(url -> System.out.println(url));
        query("Hello, There !!")
                .flatMap(Observable::from)
                .subscribe(System.out::println);

//        query("Hello, There !!")
//                .flatMap(Observable::from)
//                .flatMap(new Func1<String, Observable<String>>() {
//                    @Override
//                    public Observable<String> call(String url) {
//                        return getTitle(url); // getTitle returns Observable<String> not String
//                    }
//                })
//                .subscribe(System.out::println);
        query("Hello, There !!")
                .flatMap(Observable::from)
                .flatMap(this::getTitle)
                .subscribe(System.out::println);

        query("Hello, There !!")
                .flatMap(Observable::from)
                .flatMap(this::getTitle)
                .filter(title -> title != null)
                .take(2)
                .doOnNext(title -> saveTitle(title)) // add extra behavior each time an item is emitted
                .subscribe(System.out::println);
    }
}

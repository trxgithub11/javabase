package com.trx.streamdemo;

import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class OptionalDemo {

    public static void main(String[] args) {

//        Author author = getAuthor();
//        Optional<Author> authorOptional = Optional.ofNullable(author);
//        authorOptional.ifPresent(author1 -> System.out.println(author1.getName()));

        Optional<Author> authorOptional = getAuthorOptional();
        authorOptional.ifPresent(author -> System.out.println(author.getName()));
        //安全获取get值
//        Author author = authorOptional.orElseGet(() -> new Author());

        //没有值就抛异常
//        try {
//            Author author = authorOptional.orElseThrow(() -> new RuntimeException("数据为null"));
//        } catch (Throwable throwable) {
//            throwable.printStackTrace();
//        }

        //过滤数据

//        testFilter();
        //数据转换
        testMap();

    }

    private static void testMap() {
        Optional<Author> authorOptional = getAuthorOptional();
        authorOptional.map(author -> author.getBooks())
                .ifPresent(books -> System.out.println(books));

    }

    private static void testFilter() {

        Optional<Author> authorOptional = getAuthorOptional();
        authorOptional.filter(author1 -> author1.getAge() > 18)
                .ifPresent(author -> System.out.println(author.getName()));
    }

    public static Optional<Author> getAuthorOptional(){
        Author author = new Author(1L,"测试",33,"fdd",null);
        return Optional.ofNullable(author);
    }

    public static Author getAuthor(){

        Author author = new Author(1L,"测试",33,"fdd",null);
        return author;
    }
}

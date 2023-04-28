package com.trx.streamdemo;

import java.util.Optional;

public class OptionalDemo {

    public static void main(String[] args) {

        Author author = getAuthor();
        Optional<Author> authorOptional = Optional.ofNullable(author);
        authorOptional.ifPresent(author1 -> System.out.println(author1.getName()));
    }

    public static Author getAuthor(){

        Author author = new Author(1L,"测试",33,"fdd",null);
        return author;
    }
}

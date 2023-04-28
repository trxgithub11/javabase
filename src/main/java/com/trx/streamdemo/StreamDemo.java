package com.trx.streamdemo;

import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamDemo {

    public static void main(String[] args) {

        List<Author> authors = getAuthors();
//        test01(authors);
//          testMap();

//        testArr();
//        testMapStream(authors);

//        testSort(authors);
//        testLimit( authors);
//        testSkip( authors);
//        testFlatMap( authors);
//        testFlatMap2( authors);
//        testMaxAndMin( authors);
//        testCollectList( authors);
//        testCollectSet( authors);
//        testCollectMap( authors);
//        testCollectAnyMatch( authors);
//        testCollectAllMatch( authors);
//        testReduce(authors);
//        testReduce2(authors);
//        testReduce3(authors);
//        testReduce4(authors);
//        testAnd(authors);
//        test11(authors);
        test12(authors);

    }

    private static void test12(List<Author> authors) {

        //并行流
//        authors.parallelStream();
        Stream<Integer> stream = Stream.of(1,2,3,4,5,6,7,8,9,10);
        Integer sum = stream.parallel()
                .peek(integer -> System.out.println(integer+Thread.currentThread().getName()))
                .filter(result -> result > 5)
        .reduce((result, integer2) -> result + integer2).get();
        System.out.println(sum);
    }

    private static void test11(List<Author> authors) {

        authors.stream()
                .map(author -> author.getAge())
                .map(age -> age+10)
                .filter(integer -> integer>18)
                .map(age -> age+2)
                .forEach(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer x) {
                        System.out.println(x);
                    }
                });
        authors.stream()
                .mapToInt(value -> value.getAge())
                .map(operand -> operand+10)
                .filter(value -> value>18)
                .map(operand -> operand + 2)
                .forEach(System.out::println);
    }


    private static void testAnd(List<Author> authors) {

        authors.stream()
                .filter(((Predicate<Author>) author -> author.getAge() > 17)
                        .and(author -> author.getName().length()>1))
                .forEach(author -> System.out.println(author.getName()+"||"+author
                .getAge()));
    }

    private static void testReduce4(List<Author> authors) {

        Optional<Integer> minOptional = authors.stream()
                .map(author -> author.getAge())
                .reduce((result, element) -> result > element ? element : result);
        minOptional.ifPresent(age-> System.out.println(age));
    }

    private static void testReduce3(List<Author> authors) {

        Integer min = authors.stream()
                .map(author -> author.getAge())
                .reduce(Integer.MAX_VALUE, (result, element) -> result > element ? element : result);
        System.out.println(min);
    }

    private static void testReduce2(List<Author> authors) {

        Integer max = authors.stream()
                .map(author -> author.getAge())
                .reduce(Integer.MIN_VALUE, (result, element) -> result < element ? element : result);
        System.out.println(max);
    }

    private static void testReduce(List<Author> authors) {
        Integer sum = authors.stream()
                .distinct()
                .map(author -> author.getAge())
                .reduce(0, (integer, integer2) -> integer + integer2);
        System.out.println(sum);
    }

    private static void testCollectAllMatch(List<Author> authors) {
        boolean b = authors.stream()
                .allMatch(author -> author.getAge() > 18);
        System.out.println(b);

    }

    private static void testCollectAnyMatch(List<Author> authors) {
        boolean b = authors.stream()
                .anyMatch(author -> author.getAge() > 100);
        System.out.println(b);
    }

    private static void testCollectMap(List<Author> authors) {

        Map<String, List<Book>> maps = authors.stream()
                .distinct()
                .collect(Collectors.toMap(author -> author.getName(), author -> author.getBooks()));
        System.out.println(maps);

    }

    private static void testCollectSet(List<Author> authors) {
        Set<Book> books = authors.stream()
                .flatMap(author -> author.getBooks().stream()).collect(Collectors.toSet());
        System.out.println(books);
    }

    private static void testCollectList(List<Author> authors) {
        List<String> list = authors.stream()
                .map(author -> author.getName())
                .collect(Collectors.toList());
        System.out.println(list);
    }

    private static void testMaxAndMin(List<Author> authors) {

        Optional<Integer> max = authors.stream()
                .flatMap(author -> author.getBooks().stream())
                .distinct()
                .map(book -> book.getScore())
                .max((o1, o2) -> o1 - o2);

        Optional<Integer> min = authors.stream()
                .flatMap(author -> author.getBooks().stream())
                .distinct()
                .map(book -> book.getScore())
                .min((o1, o2) -> o1 - o2);
        System.out.println(min.get());
        System.out.println(max.get());

    }

    public static void  testFlatMap2(List<Author> authors){
        authors.stream()
                .flatMap( author -> author.getBooks().stream())
                .distinct()
                .flatMap(book -> Arrays.stream(book.getCategory().split(",")))
                .distinct()
                .forEach(s -> System.out.println(s));
    }

    public static void  testFlatMap(List<Author> authors){
        authors.stream()
                .flatMap(author -> author.getBooks().stream())
                .distinct()
                .forEach(book -> System.out.println(book.getName()));

    }
    public static void  testSkip(List<Author> authors){

        authors.stream()
                .distinct()
                .sorted()
                .skip(1)
                .forEach(author -> System.out.println(author.getName()));

    }


    public static void  testLimit(List<Author> authors){

        authors.stream()
                .distinct()
                .sorted()
                .limit(2)
                .forEach(author -> System.out.println(author.getName()));

    }

    public static void testSort(List<Author> authors){

        authors.stream()
                .distinct()
                .sorted((o1, o2) -> o1.getAge()-o2.getAge())
                .forEach(author -> System.out.println(author.getAge()));
    }

    public static void testMapStream(List<Author> authors){

//        authors.stream()
//                .map(author -> author.getName()).distinct()
//                .forEach(s -> System.out.println(s));

        authors.stream()
                .map(author -> author.getAge())
                .map(integer -> integer+10)
                .forEach(integer -> System.out.println(integer));
    }

    public static void  testArr(){
        Integer arr[] = {1,2,3,4,5,6,7,8};
        Stream<Integer> stream = Arrays.stream(arr);
//        Stream<int[]> arr1 = Stream.of(arr);
        stream.forEach(integer -> System.out.println(integer));

    }

    public static void testMap(){

        Map<String,String> map = new HashMap<String,String>();
        map.put("test","12");
        map.put("test2","13");
        map.put("test3","14");

//        Set<String> strings = map.keySet();

//        for (String string : strings) {
//            System.out.println(string);
//            System.out.println(map.get(string));
//        }

//        Iterator<String> iterator = strings.iterator();
//        while(iterator.hasNext()){
//            String next = iterator.next();
//            System.out.println(next);
//            System.out.println(map.get(next));
//
//        }


        Set<Map.Entry<String, String>> entries = map.entrySet();
        entries.stream().filter(stringStringEntry -> stringStringEntry.getKey().equals("test3"))
                .forEach(stringStringEntry -> System.out.println(stringStringEntry.getValue()));


    }
    private static void test01(List<Author> authors) {
        authors.stream()
                .distinct()
                .filter(author -> author.getAge()<18)
                .forEach(author -> System.out.println(author.getName()));
    }

    private static List<Author> getAuthors() {
        //数据初始化
        Author author = new Author(1L,"蒙多",33,"一个从菜刀中明悟哲理的祖安人",null);
        Author author2 = new Author(2L,"亚拉索",15,"狂风也追逐不上他的思考速度",null);
        Author author3 = new Author(3L,"易",14,"是这个世界在限制他的思维",null);
        Author author4 = new Author(3L,"易",14,"是这个世界在限制他的思维",null);

        //书籍列表
        List<Book> books1 = new ArrayList<>();
        List<Book> books2 = new ArrayList<>();
        List<Book> books3 = new ArrayList<>();

        books1.add(new Book(1L,"刀的两侧是光明与黑暗","哲学,爱情",88,"用一把刀划分了爱恨"));
        books1.add(new Book(2L,"一个人不能死在同一把刀下","个人成长,爱情",99,"讲述如何从失败中明悟真理"));

        books2.add(new Book(3L,"那风吹不到的地方","哲学",85,"带你用思维去领略世界的尽头"));
        books2.add(new Book(3L,"那风吹不到的地方","哲学",85,"带你用思维去领略世界的尽头"));
        books2.add(new Book(4L,"吹或不吹","爱情,个人传记",56,"一个哲学家的恋爱观注定很难把他所在的时代理解"));

        books3.add(new Book(5L,"你的剑就是我的剑","爱情",56,"无法想象一个武者能对他的伴侣这么的宽容"));
        books3.add(new Book(6L,"风与剑","个人传记",100,"两个哲学家灵魂和肉体的碰撞会激起怎么样的火花呢？"));
        books3.add(new Book(6L,"风与剑","个人传记",100,"两个哲学家灵魂和肉体的碰撞会激起怎么样的火花呢？"));

        author.setBooks(books1);
        author2.setBooks(books2);
        author3.setBooks(books3);
        author4.setBooks(books3);

        List<Author> authorList = new ArrayList<>(Arrays.asList(author,author2,author3,author4));
        return authorList;
    }
}

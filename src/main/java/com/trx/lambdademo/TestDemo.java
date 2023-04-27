package com.trx.lambdademo;

public class TestDemo {

    public static void main(String[] args) {


    }




    @FunctionalInterface
    public interface test {
        public void ttt();

        public static void ttt1(){

        }

    }


    //正确展示
    @FunctionalInterface
    public interface Interface {
        public void ttt();

        // java.lang.Object中的方法不是抽象方法
        public boolean equals(Object var);

        // default不是抽象方法
        public default void defaultlove(){

        }

        // static不是抽象方法
        public static void love(){
        }



    }




}

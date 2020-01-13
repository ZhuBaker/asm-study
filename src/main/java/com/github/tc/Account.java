package com.github.tc;

/**
 * @author bozhu
 * @description
 * @data 2020/1/11
 */
public class Account {
    public void operation() {
        System.out.println("operation ...");
    }

    public static void main(String[] args) {
        System.out.println(Account.class.getName());
    }
}

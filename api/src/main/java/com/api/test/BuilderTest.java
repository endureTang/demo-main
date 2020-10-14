package com.api.test;

import java.util.Scanner;

public class BuilderTest {
    public static void main(String[] args) {
        RobustPerson jack = new RobustPerson.Builder(1, "Jack")
                .age(18).gender("male").height(1.70).weight(65).build();
        System.out.println(jack);

        System.out.println("Jack keeps eating too much...");
        System.out.println(jack.toBuilder().weight(80).build());

        String regex = "1[38]?\\d{9}";
        Scanner sc = new Scanner(System.in);
        System.out.println("请输入手机号");
        String s = sc.nextLine();
        boolean flag = s.matches(regex);
        System.out.println(flag);
    }
}
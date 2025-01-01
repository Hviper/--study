package com.example.redisstarter.config;

class Person {
    public Person() {
        System.out.println("父类初始化");
    }

    public void func() {
        System.out.println("父类函数");
    }
}

class Student extends Person {
    public Student() {
        System.out.println("子类初始化");
    }

    @Override
    public void func() {
        System.out.println("子类");
        super.func();
        System.out.println("子类");
    }
}

public class MainStream {
    public static void main(String[] args) {
        Student student = new Student();
        student.func();
    }
}

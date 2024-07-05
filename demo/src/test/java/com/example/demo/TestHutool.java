package com.example.demo;

import org.junit.jupiter.api.Test;

public class TestHutool {
    @Test
    public void test(){
        ChildClient childClient = new ChildClient();
        childClient.getThis().getThis().getThis().getThis();
    }
}
class BaseClient<T>{
    public T getThis(){
        System.out.println("设置一些东西额");
        return (T) this;
    }

    public T getThis(Object o){
        System.out.println("设置");
        return (T) this;
    }

}
class ChildClient extends BaseClient<ChildClient>{

}
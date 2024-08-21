package com.example.demo.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

import java.util.Iterator;
@Component
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {
//        Iterator<String> beanNamesIterator =
//                configurableListableBeanFactory.getBeanNamesIterator();
//        beanNamesIterator.forEachRemaining(System.out::println);
//        System.out.println(configurableListableBeanFactory);
    }
}

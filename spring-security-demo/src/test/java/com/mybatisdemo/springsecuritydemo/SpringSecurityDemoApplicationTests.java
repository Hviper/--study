package com.mybatisdemo.springsecuritydemo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;

@SpringBootTest
class SpringSecurityDemoApplicationTests {


    @Test
    void contextLoads() {
        ExpressionParser parser = new SpelExpressionParser();
        Expression exp = parser.parseExpression("@ss.change('hello')");
        String message = exp.getValue(String.class);
        System.out.println(message);
    }

}

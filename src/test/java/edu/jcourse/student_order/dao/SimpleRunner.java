package edu.jcourse.student_order.dao;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class SimpleRunner {

    public static void main(String[] args) {
        SimpleRunner runner = new SimpleRunner();
        runner.runTests();
    }

    private void runTests() {
        try {
            Class<?> clazz = Class.forName("edu.jcourse.student_order.dao.DictionaryDAOImplTest");

            Constructor<?> constructor = clazz.getDeclaredConstructor();
            Object entity = constructor.newInstance();

            Method[] methods = clazz.getDeclaredMethods();
            for (Method method : methods) {
                if (method.isAnnotationPresent(Test.class)) {
                    method.invoke(entity);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

package com.example.stack.service;

import com.esotericsoftware.reflectasm.ConstructorAccess;
import com.esotericsoftware.reflectasm.FieldAccess;
import com.esotericsoftware.reflectasm.MethodAccess;
import com.example.stack.model.User;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

@Service
public class ReflectAsmService {

    public  void testMothod() throws Exception {
        // java reflection
        Class<User> clazz = User.class;
        Field field = clazz.getField("name");
        field.setAccessible(true);
        Method method = clazz.getMethod("setName", String.class);
        method.setAccessible(true);

        // reflection asm
        ConstructorAccess <User> ca = ConstructorAccess.get(clazz);
        FieldAccess fa = FieldAccess.get(User.class);
        MethodAccess ma = MethodAccess.get(User.class);

        // use for test
        User user = new User();
        int times = 100000000;

        // case0: common
        long startTime0 = System.currentTimeMillis();
        for (int i = 0; i < times; i++) {
            user = new User();
            user.name = "LXQ";
            user.setName("liuxianqiang");
        }
        System.out.println("common : " + (System.currentTimeMillis() - startTime0));

        // case1: java reflect
        long startTime1 = System.currentTimeMillis();
        for (int i = 0; i < times; i++) {
            user = clazz.newInstance();
            field.set(user, "LXQ");
            method.invoke(user, "liuxianqiang");
        }
        System.out.println("java reflect : " + (System.currentTimeMillis() - startTime1));

        // case2: reflectasm use name
        long startTime2 = System.currentTimeMillis();
        for (int i = 0; i < times; i++) {
            user = ca.newInstance();
            fa.set(user, "name", "LXQ");
            ma.invoke(user, "setName", "liuxianqiang");
        }
        System.out.println("reflectasm use name : " + (System.currentTimeMillis() - startTime2));

        // case3: reflectasm use index
        int index1 = fa.getIndex("name");
        int index2 = ma.getIndex("setName");
        long startTime3 = System.currentTimeMillis();
        for (int i = 0; i < times; i++) {
            user = ca.newInstance();
            fa.set(user, index1, "LXQ");
            ma.invoke(user, index2, "liuxianqiang");
        }
        System.out.println("reflectasm set use index : " + (System.currentTimeMillis() - startTime3));


        int index3 = fa.getIndex("name");
        int index4 = ma.getIndex("getName");
        long startTime4 = System.currentTimeMillis();
        for (int i = 0; i < times; i++) {
            user = ca.newInstance();
            fa.set(user, index3,"LXQ");
            Object invoke = ma.invoke(user, index4);
            //System.out.println(invoke);
        }
        System.out.println("reflectasm get use index : " + (System.currentTimeMillis() - startTime4));
    }

    public static void main(String[] args) {
        List <String> list = new ArrayList <>();
        list.add("1");
        list.add("2");
        list.stream().forEach(item ->{
            System.out.println(item);
        });
        for (String s : list) {
            System.out.println(s);
        }
    }
}

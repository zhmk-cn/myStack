package com.example.stack.test;

import java.io.IOException;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class TestService {

    public static void main(String[] args) {
        Object obj = null;
        List <String> objects = new ArrayList <>();
        objects.add("1");
        obj = objects;

        ArrayList<?> list;

        if (obj instanceof ArrayList<?>) {
            System.out.println(getActualType(obj,0));
            System.out.println(obj.getClass().getTypeName());
            System.out.println();
        } else {
            //return null;
        }

        //return null;
    }


    public static String getActualType(Object o,int index) {
        Type clazz = o.getClass().getGenericSuperclass();
        ParameterizedType pt = (ParameterizedType)clazz;
        return pt.getActualTypeArguments()[index].toString();
    }
}

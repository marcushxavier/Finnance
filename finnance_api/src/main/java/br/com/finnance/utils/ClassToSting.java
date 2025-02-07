package br.com.finnance.utils;

import java.lang.reflect.Field;

public class ClassToSting {

    public static String turnToString(Object obj) {
        String[] varNames = new GetObjectFieldsName().getNames(obj);
        String result = "";

        for (String name : varNames) {
            try {
                Field fieldToAccess = obj.getClass().getDeclaredField(name);
                fieldToAccess.setAccessible(true);
                result = result.concat(name + ": " + fieldToAccess.get(obj) + "\n");
            } catch (NoSuchFieldException | IllegalAccessException e) {
                throw new RuntimeException(e.getMessage());
            }
        }
        return result;
    }
}

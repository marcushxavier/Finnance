package br.com.finnance.utils;

public class ClassToSting {

    public static String turnToString(Object obj) {
        String[] varNames = new GetObjectFieldsName().getNames(obj);
        String result = "";

        for (String name : varNames) {
            try {
                result = result.concat(name + ": " + obj.getClass().getDeclaredField(name) + "\n");
            } catch (NoSuchFieldException e) {
                throw new RuntimeException(e.getMessage());
            }
        }
        return result;
    }
}

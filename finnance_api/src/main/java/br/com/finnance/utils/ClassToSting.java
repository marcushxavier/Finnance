package br.com.finnance.utils;

import java.lang.reflect.Field;

public class ClassToSting {

    public static String turnToString(Object obj) {

        // coleta os nomes das variaveis de um obj em forma de array
        String[] varNames = new GetObjectFieldsName().getNames(obj);
        String result = "";

        for (String name : varNames) {
            try {
                // pega o *field* da variavel com o nome atual no loop
                Field fieldToAccess = obj.getClass().getDeclaredField(name);
                fieldToAccess.setAccessible(true);
                /*
                // pega *a variavel* com o nome atual no loop
                Object currentVar = fieldToAccess.get(obj);

                // tratamento de datas
                if (currentVar.getClass().getTypeName().equals("Date")) {
                    // faz casting da string pra data
                    Date noteDate = (Date) fieldToAccess.get(obj);

                    // passa pra o fuso de Brasilia
                    Instant rightTime = noteDate.toInstant().atOffset(ZoneOffset.of("+03:00")).toInstant();

                    // adiciona os dados ao resultado
                    result = result.concat(name + ": " + rightTime + "\n");

                } else {
                    result = result.concat(name + ": " + fieldToAccess.get(obj) + "\n");
                }*/
                result = result.concat(name + ": " + fieldToAccess.get(obj) + "\n");
            } catch (NoSuchFieldException | IllegalAccessException e) {
                throw new RuntimeException(e.getMessage());
            }
        }
        return result;
    }
}

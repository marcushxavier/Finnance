package br.com.finnance.utils;

import java.lang.reflect.Field;
import java.util.Arrays;

public class UpdateClass<T> {
    public T update(T objToUpdate, T newObjData, String[] blackList) {
        var fieldsNames = new GetObjectFieldsName().getNames(objToUpdate); // nomes das variáveis do obj

        for (String fieldName : fieldsNames) {
            if (blackList != null && Arrays.stream(blackList).toList().contains(fieldName) ) { // verifica se há algo na lista, se houver, não altera as variáveis da lista
                continue;
            }
            try {
                Field fieldToUpdate = objToUpdate.getClass().getDeclaredField(fieldName);  // coleta uma variável do obj antigo
                fieldToUpdate.setAccessible(true); // permite o acesso

                Field newField = newObjData.getClass().getDeclaredField(fieldName); // coleta una variável do novo obj
                newField.setAccessible(true); // permite o acesso

                fieldToUpdate.set(objToUpdate, newField.get(newObjData)); //altera os dados da antiga variável com dados da nova, coletando dados de variáveis equivalentes

            } catch (NoSuchFieldException | IllegalAccessException e) {
                throw new RuntimeException(e.getMessage());
            }
        }

        return objToUpdate;
    }
}

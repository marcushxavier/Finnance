package br.com.finnance.utils;

import java.lang.reflect.Field;

public class UpdateClass <T>{
    public T update(T objToUpdate, T newObjData) {
        var fieldsNames = new GetObjectFieldsName().getNames(objToUpdate); // nomes das variáveis do obj

        for (String fieldName : fieldsNames){
            try {
                Field fieldToUpdate = objToUpdate.getClass().getDeclaredField(fieldName);  // coleta uma variável do obj antigo
                fieldToUpdate.setAccessible(true); // permite o acesso

                Field newField = newObjData.getClass().getDeclaredField(fieldName); // coleta una variável do novo obj
                newField.setAccessible(true); // permite o acesso

                fieldToUpdate.set(objToUpdate,newField.get(newObjData)); //altera os dados da antiga variável com dados da nova, coletando dados de variáveis equivalentes

            } catch (NoSuchFieldException | IllegalAccessException e) {
                throw new RuntimeException(e.getMessage());
            }
        }

        return objToUpdate;
    }
}

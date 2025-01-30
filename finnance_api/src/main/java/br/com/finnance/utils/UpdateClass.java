package br.com.finnance.utils;

import java.lang.reflect.Field;

public class UpdateClass <T>{
    public String[] getFieldsNames(T objToExtract){
        Field[] fields = objToExtract.getClass().getDeclaredFields();
        String[] variablesName = new String[fields.length -1];
        for(int i = 0; i < fields.length -1; i++){
            variablesName[i] = fields[i].getName();
        }
        return variablesName;
    }

    public T update(T objToUpdate, T newObjData) {
        var fieldsNames = getFieldsNames(objToUpdate);

        for (String fieldName : fieldsNames){
            try {
                Field fieldToUpdate = objToUpdate.getClass().getDeclaredField(fieldName);
                fieldToUpdate.setAccessible(true);

                Field newField = newObjData.getClass().getDeclaredField(fieldName);
                newField.setAccessible(true);

                fieldToUpdate.set(objToUpdate,newField.get(newObjData));

            } catch (NoSuchFieldException | IllegalAccessException e) {
                throw new RuntimeException(e.getMessage());
            }
        }

        return objToUpdate;
    }
}

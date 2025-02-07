package br.com.finnance.utils;

import java.lang.reflect.Field;

public class GetObjectFieldsName {
    public String[] getNames(Object objToExtract){
        Field[] fields = objToExtract.getClass().getDeclaredFields(); // coleta as referências das variáveis do objeto
        String[] variablesName = new String[fields.length]; // array para armazenar nos nomes
        for(int i = 0; i < fields.length; i++){
            variablesName[i] = fields[i].getName(); // armazena os nomes coletados
        }
        return variablesName;
    }

}
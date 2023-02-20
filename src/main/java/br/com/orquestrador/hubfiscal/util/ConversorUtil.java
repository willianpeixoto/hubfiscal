package br.com.orquestrador.hubfiscal.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

@Slf4j
public class ConversorUtil {
    public static String toJson(Object obj) {
        if(obj == null) {
            return StringUtils.EMPTY;
        }
        ObjectMapper mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return StringUtils.EMPTY;
    }

    public static Object toObject(String json, Class classeRetorno) {
        ObjectMapper mapper = new ObjectMapper();
        Object retorno = null;
        try {
            retorno = mapper.readValue(json, classeRetorno);
        } catch (Exception e) {
            log.error("Falha ao tentar converter JSON: {} para Objeto: {} \n{}", json, classeRetorno, e);
        }
        return retorno;
    }
}

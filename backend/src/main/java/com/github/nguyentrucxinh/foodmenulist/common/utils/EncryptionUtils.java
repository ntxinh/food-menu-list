package com.github.nguyentrucxinh.foodmenulist.common.utils;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EncryptionUtils {

    private static final Logger LOGGER = Logger.getLogger(EncryptionUtils.class.getName());

    private EncryptionUtils() {
    }

    public static String mapToJsonString(Map<String, String> map) {
        try {

            return new ObjectMapper().writeValueAsString(map);

        } catch (JsonGenerationException e) {
            LOGGER.info("Throw JsonGenerationException: ");
            LOGGER.log(Level.SEVERE, e.toString(), e);
        } catch (JsonMappingException e) {
            LOGGER.info("Throw JsonMappingException: ");
            LOGGER.log(Level.SEVERE, e.toString(), e);
        } catch (IOException e) {
            LOGGER.info("Throw IOException: ");
            LOGGER.log(Level.SEVERE, e.toString(), e);
        }

        return null;
    }

    public static Map<String, String> jsonStringtoMap(String jsonString) {
        try {

            return new ObjectMapper().readValue(jsonString, new TypeReference<Map<String, String>>() {
            });

        } catch (JsonGenerationException e) {
            LOGGER.info("Throw JsonGenerationException: ");
            LOGGER.log(Level.SEVERE, e.toString(), e);
        } catch (JsonMappingException e) {
            LOGGER.info("Throw JsonMappingException: ");
            LOGGER.log(Level.SEVERE, e.toString(), e);
        } catch (IOException e) {
            LOGGER.info("Throw IOException: ");
            LOGGER.log(Level.SEVERE, e.toString(), e);
        }

        return null;
    }

    public static String encrypt() {
        return null;
    }

    public static String decrypt() {
        return null;
    }
}

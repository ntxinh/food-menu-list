package com.github.nguyentrucxinh.foodmenulist.common.utils;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.util.Base64Utils;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.IOException;
import java.security.Key;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EncryptionUtils {

    private static final Logger LOGGER = Logger.getLogger(EncryptionUtils.class.getName());
    private static final String BLOWFISH = "Blowfish";
    private static final String SECRET_KEY = "xm8EV6Hy5RMFK4EEACIDAwQus";

    private EncryptionUtils() {
    }

    public static String encrypt(Map<String, Object> map) {
        if (map != null && !map.isEmpty()) {
            return encrypt(mapToJsonString(map));
        } else {
            return "";
        }
    }

    public static String encrypt(String strClearText) {
        String strData = "";

        try {
            Key key = new SecretKeySpec(SECRET_KEY.getBytes(), BLOWFISH);
            Cipher cipher = Cipher.getInstance(BLOWFISH);
            cipher.init(Cipher.ENCRYPT_MODE, key);
            byte[] encrypted = cipher.doFinal(strClearText.getBytes());
            strData = Base64Utils.encodeToUrlSafeString(encrypted);

        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.toString(), e);
        }
        return strData;
    }

    public static String decrypt(String strEncrypted) {
        String strData = "";

        try {
            byte[] strEncryptedDecoded = Base64Utils.decodeFromUrlSafeString(strEncrypted);
            Key key = new SecretKeySpec(SECRET_KEY.getBytes(), BLOWFISH);
            Cipher cipher = Cipher.getInstance(BLOWFISH);
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte[] decrypted = cipher.doFinal(strEncryptedDecoded);
            strData = new String(decrypted);

        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.toString(), e);
        }
        return strData;
    }

    public static String mapToJsonString(Map<String, Object> map) {
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
}

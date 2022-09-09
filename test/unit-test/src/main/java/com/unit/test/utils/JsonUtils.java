package com.unit.test.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;

@Slf4j
public class JsonUtils {
    private static final ObjectMapper OBJECT_MAPPER;

    public JsonUtils() {
    }

    static {
        OBJECT_MAPPER = new ObjectMapper();
        OBJECT_MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    /**
     * 将对象转为byte数组
     *
     * @param obj 要转换的对象
     * @return byte数组
     */
    public static byte[] toBytes(Object obj) {
        try {
            return OBJECT_MAPPER.writeValueAsBytes(obj);
        } catch (JsonProcessingException e) {
            log.error(e.toString());
        }
        return null;
    }

    /**
     * 将对象转为字符串
     *
     * @param obj 要转换的对象
     * @return byte数组
     */
    public static String toStringOrEmpty(Object obj) {
        try {
            return OBJECT_MAPPER.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            log.error(e.toString());
        }
        return "";
    }

    /**
     * JSON字符串转对象
     *
     * @param clazz   要转换的类型
     * @param jsonStr 要转换的字符串
     * @param <T>     转换的类型
     * @return 转换的类型
     */
    public static <T> T str2obj(Class<T> clazz, String jsonStr) {
        try {
            return OBJECT_MAPPER.readValue(jsonStr, clazz);
        } catch (IOException e) {
            log.error(e.toString());
        }
        return null;
    }

    /**
     * JSON字符串转对象
     *
     * @param clazz   要转换的类型
     * @param url 待转换的资源
     * @param <T>     转换的类型
     * @return 转换的类型
     */
    public static <T> T url2obj(Class<T> clazz, URL url) {
        try {
            return OBJECT_MAPPER.readValue(url, clazz);
        } catch (IOException e) {
            log.error(e.toString());
        }
        return null;
    }

    /**
     * 将JSON字符串转换为List对象集合
     *
     * @param clazz   要转换的类型
     * @param jsonStr 要转换的JSONArray
     * @param <T>     转换的类型
     * @return List数组
     */
    public static <T> List<T> toList(Class<T> clazz, String jsonStr) {
        ObjectMapper mapper = new ObjectMapper();
        CollectionType type = mapper.getTypeFactory().constructCollectionType(ArrayList.class, clazz);
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try {
            return mapper.readValue(jsonStr, type);
        } catch (IOException e) {
            log.error(e.toString());
        }
        return new ArrayList<>(0);
    }

    /**
     * 将URL的json资源转化生成JSON对象集合
     *
     * @param clazz 类型
     * @param url url
     * @param <T> 类型
     * @return 转行的集合
     */
    public static <T> List<T> toList(Class<T> clazz, URL url) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            CollectionType type = mapper.getTypeFactory().constructCollectionType(ArrayList.class, clazz);
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            return mapper.readValue(url, type);
        } catch (IOException e) {
            log.error(e.toString());
        }
        return new ArrayList<>(0);
    }

    /**
     * JSON字符串转对象
     *
     * @param clazz   要转换的类型
     * @param bytes   要转换的byte数组
     * @param <T>     转换的类型
     * @return 转换的类型
     */
    public static <T> T byteArray2Object(Class<T> clazz, byte[] bytes) {
        try {
            return OBJECT_MAPPER.readValue(bytes, clazz);
        } catch (IOException e) {
            log.error(e.toString());
        }
        return null;
    }

    /**
     * 将字符串转换为List对象集合
     *
     * @param listClass List集合类型，例如List.class
     * @param objClass 对象类型
     * @param dbData 待装的字符串
     * @param <T> 类型
     * @return List集合
     */
    public static <T> List<T> parseList(Class<?> listClass, Class<T> objClass, String dbData) {
        if (dbData == null) {
            return new ArrayList<>(0);
        }
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try {
            JavaType javaType = objectMapper.getTypeFactory().constructParametricType(listClass, objClass);
            return objectMapper.readValue(dbData, javaType);
        } catch (IOException e) {
            log.error("parse string to object list, ", e);
        }
        return new ArrayList<>(0);
    }

    /**
     * 将字符串转换为Map
     *
     * @param dbData 待转换的字符串
     * @param typeReference Map类型
     * @param <K> Map的KEY类型
     * @param <V> Map的Value的类型
     * @return 转换的Map集合
     */
    public static <K, V> Map<K, V> parseMap(String dbData, TypeReference<? extends Map<K, V>> typeReference) {
        if (dbData == null) {
            return new HashMap<>(0);
        }
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        try {
            return objectMapper.readValue(dbData, typeReference);
        } catch (IOException e) {
            log.error("parse string to object list, ", e);
        }
        return new HashMap<>(0);
    }

    /**
     * JSON字符串转对象
     *
     * @param clazz 要转换的类型
     * @param file  json文件
     * @param <T>   转换的类型
     * @return 转换的类型
     */
    public static <T> T file2obj(Class<T> clazz, File file) {
        InputStream inputStream = null;
        try {
            inputStream = FileUtils.openInputStream(file);
            return OBJECT_MAPPER.readValue(inputStream, clazz);
        } catch (IOException e) {
            log.error("file2obj fail", e);
        } finally {
            IoUtils.close(inputStream);
        }
        return null;
    }

    /**
     * JSON字符串转对象
     *
     * @param file  json文件
     * @param value 写入的文件
     * @return 转换的类型
     */
    public static void obj2file(File file, Object value) {
        OutputStream outputStream = null;
        try {
            outputStream = FileUtils.openOutputStream(file);
            OBJECT_MAPPER.writeValue(outputStream, value);
        } catch (IOException e) {
            log.error("obj2file fail,", e);
        } finally {
            IoUtils.close(outputStream);
        }
    }
}


package com.github.wugenshui.baseutil.baseutil.util;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.stream.Stream;

/**
 * map工具类
 *
 * @author : chenbo
 * @date : 2019-12-07
 */
public class MapUtil {

    /**
     * 定义Map，利用该对象链式构造map
     */
    private Map map;

    private MapUtil() {

    }

    /**
     * 初始化一个hashMap
     * @return
     */
    public static Map init(){
        return new HashMap(16);
    }

    /**
     * 初始化LinkedHashMap
     * @return
     */
    public static Map initLinked(){
        return new LinkedHashMap(16);
    }

    /**
     * 将map的key和value互相转换
     * @param map
     * @return
     */
    public static Map<String, String> mapInverse(Map<String, String> map) {
        Map<String, String> result = new LinkedHashMap<>();
        for(Map.Entry<String, String> enter : map.entrySet()) {
            result.put(enter.getValue(), enter.getKey());
        }
        return result;
    }

    /**
     * 对map的key进行排序
     * @param map
     * @param asc
     * @param <K>
     * @param <V>
     * @return
     */
    public static <K extends Comparable<? super K>, V > Map<K, V> mapSortByKey(Map<K, V> map, boolean asc){
        Map<K, V> result = new LinkedHashMap<>();
        Stream<Map.Entry<K, V>> stream = map.entrySet().stream();
        if (asc){
            stream.sorted(Map.Entry.<K, V>comparingByKey())
                    .forEachOrdered(e -> result.put(e.getKey(), e.getValue()));
        } else {
            stream.sorted(Map.Entry.<K, V>comparingByKey().reversed())
                    .forEachOrdered(e -> result.put(e.getKey(), e.getValue()));
        }
        return result;
    }

    /**
     * 初始化map
     * @return
     */
    public static MapUtil build() {
        MapUtil instance = new MapUtil();
        instance.map = new HashMap<>(16);
        return instance;
    }

    /**
     * 初始化用户传入Map
     * @param map
     * @return
     */
    public static MapUtil build(Map map) {
        MapUtil instance = new MapUtil();
        instance.map = map;
        return instance;
    }

    /**
     * 初始化LinkedHashMap有序map
     * @return
     */
    public static MapUtil buildLink() {
        MapUtil instance = new MapUtil();
        instance.map = new LinkedHashMap<>();
        return instance;
    }

    /**
     * 链式添加数据
     * @param key
     * @param value
     * @return
     */
    public MapUtil put(Object key, Object value) {
        this.map.put(key, value);
        return this;
    }

    /**
     * 链式添加map对象数据
     * @param map
     * @return
     */
    public MapUtil putAll(Map map) {
        this.map.putAll(map);
        return this;
    }

    /**
     * 删除当前map数据
     * @param key
     * @return
     */
    public MapUtil remove(Object key) {
        this.map.remove(key);
        return this;
    }

    /**
     * 结束链式返回当前map
     * @return
     */
    public Map over() {
        return this.map;
    }

}
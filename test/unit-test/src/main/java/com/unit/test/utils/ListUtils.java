package com.unit.test.utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import org.apache.commons.collections4.CollectionUtils;

public class ListUtils {
    private ListUtils() {
        super();
    }

    /**
     * 将给定的数据对象构建一个列表类型对象
     *
     * @param params 字符串数组
     * @param <S>    任意类型
     * @return List 结果列表
     */
    public static <S> List<S> createList(S... params) {
        if (params == null) {
            return null;
        }
        return Arrays.asList(params);
    }

    /**
     * 合并给定两个列表数据到一个新列表中，源列表始终会排在前面,不会影响源和目标数据列表
     *
     * @param sources 源列表
     * @param targets 目标列表
     * @param <T>     任意类型
     * @return List 结果列表
     */
    public static <T> List<T> mergerList(List<T> sources, List<T> targets) {
        List<T> results = new ArrayList<>();
        if (sources != null && !sources.isEmpty()) {
            results.addAll(sources);
        }
        if (targets != null && !targets.isEmpty()) {
            results.addAll(targets);
        }
        return results;
    }

    /**
     * 将给定的列表合并成一个列表
     *
     * @param sources 源列表
     * @param <T>     任意类型
     * @return List 结果列表
     */
    public static <T> List<T> mergerList(List<List<T>> sources) {
        List<T> results = new ArrayList<>();
        if (sources == null || sources.isEmpty()) {
            return results;
        }
        for (List<T> source : sources) {
            if (source == null || source.isEmpty()) {
                continue;
            }
            results.addAll(source);
        }
        return results;
    }

    /**
     * 计算指定列表的长度
     *
     * @param lists 字符串集合
     * @return 大小
     */
    public static int size(Collection<?> lists) {
        return lists == null ? 0 : lists.size();
    }

    /**
     * 根据指定的大小拆分列表
     *
     * @param list 需要拆分的列表
     * @param size 每个子列表最大元素个数
     * @param <T>  任意类型数据
     * @return 拆分之后的列表
     */
    public static <T> List<List<T>> splitList(List<T> list, int size) {
        List<List<T>> splitList = new LinkedList<>();
        if (CollectionUtils.isEmpty(list)) {
            return splitList;
        }
        int listSize = list.size();
        for (int i = 0; i < Math.ceil(listSize / (double) size); i++) {
            int end = Math.min((i + 1) * size, listSize);
            List<T> subList = list.subList(i * size, end);
            splitList.add(subList);
        }
        return splitList;
    }
}

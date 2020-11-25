package com.study.hua.homework.demo;

import java.util.*;
import java.util.stream.Collectors;

public class StreamDemo {
    public static void main(String[] args) {
        Integer[] arr = {1, 3, 2, 9, 7, 4, 2, 5, 6, 3, 7, 4, 5, 8};
        List<Integer> list = Arrays.asList(arr);
        List<Integer> data;

        data = list.stream().skip(5).collect(Collectors.toList());
        System.out.println("跳过五个元素后的集合：" + data);

        data = list.parallelStream().sorted(Integer::compareTo).collect(Collectors.toList());
        System.out.println("从小到大排序后的集合：" + data);

        data = list.parallelStream().sorted(Comparator.comparing(Integer::intValue).reversed()).collect(Collectors.toList());
        System.out.println("从大到小排序后的集合：" + data);

        data = list.parallelStream().filter(i -> i > 6).collect(Collectors.toList());
        System.out.println("过滤出大于6的集合：" + data);

        Optional<Integer> first = list.parallelStream().filter(i -> i > 6).findFirst();
        first.ifPresent(i -> System.out.println("找出大于6的第一个元素：" + i));

        Optional<Integer> any = list.parallelStream().filter(i -> i > 6).findAny();
        any.ifPresent(i -> System.out.println("找出大于6的任意一个元素：" + i));

        data = list.parallelStream().map(i -> i * 5).collect(Collectors.toList());
        System.out.println("所有元素乘以5之后的集合：" + data);

        data = list.parallelStream().distinct().collect(Collectors.toList());
        System.out.println("去重之后的集合：" + data);

        Map<Integer, List<Integer>> map = list.parallelStream().collect(Collectors.groupingBy(Integer::intValue));
        System.out.println("分组之后的数据：" + map);

        Optional<Integer> max = list.parallelStream().max(Integer::compare);
        max.ifPresent(i -> System.out.println("集合中的最大值是： " + i));

        Optional<Integer> min = list.parallelStream().min(Integer::compare);
        min.ifPresent(i -> System.out.println("集合中的最小值是： " + i));
    }
}
package com.biabuluo.utils;

import com.biabuluo.domain.entity.Article;
import com.biabuluo.domain.vo.HotArticleVo;
import org.springframework.beans.BeanUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author 小宇
 * @date 2023-08-26:14:17
 * @preference: 类：大驼峰 方法：蛇形 变量：全小写
 * @description: Bean拷贝工具类
 */
public class BeanCopyUtil {
    private BeanCopyUtil(){}

    //实现list对象拷贝
    public static <V, E> List<V> copyBeanList(List<E> list, Class<V> clazz){
        //使用流
        return list.stream()
                .map(o->copyBean(o, clazz))
                .collect(Collectors.toList());
    }


    //实现单个对象拷贝
    public static<V> V copyBean(Object source, Class<V> clazz){
        V result = null;
        //创建目标对象
        try {
            result = clazz.getDeclaredConstructor().newInstance();
            //实现copy
            BeanUtils.copyProperties(source, result);

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return result;
    }
}

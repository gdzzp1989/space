package com.zcloud.space.common.core.util;

import ma.glasnost.orika.MapperFacade;
import ma.glasnost.orika.MapperFactory;
import ma.glasnost.orika.impl.DefaultMapperFactory;
import ma.glasnost.orika.metadata.ClassMapBuilder;

import java.util.*;

/**
 * @ClassName MapperUtils
 * @Description 实体映射工具类
 * @Author Y
 * @Date 2019-12-26 20:12
 */
public class MapperUtils {

    private MapperUtils(){}

    public static final MapperUtils INSTANCE = new MapperUtils();
    /**
     * 获取默认字段工厂
     */
    private static MapperFactory MAPPER_FACTORY = new DefaultMapperFactory.Builder().build();
    /**
     * 默认字段实例
     */
    private static final MapperFacade MAPPER_FACADE = MAPPER_FACTORY.getMapperFacade();

    private static Map<String, MapperFacade> CACHE_MAPPER_FACADE_MAP = new HashMap<>();

    /**
     * 映射实体（默认字段）
     * 这种映射就是dto字段名称和实体对象DO之间字段名称一致
     * @param toClass 映射类对象 dto对象
     * @param data   数据（对象）do对象
     * @return 映射类对象
     */
    public <E, T> E map(Class<E> toClass, T data) {
        return MAPPER_FACADE.map(data, toClass);
    }

    /**
     * 映射实体（自定义配置）
     * @param toClass   映射类对象 dto对象
     * @param data     数据（对象）do对象
     * @param configMap 自定义配置,dto与do对象字段名称不一致时配置的映射 {dataAttr, toClassAttr}
     * @return 映射类对象
     */
    public <E, T> E map(Class<E> toClass, T data, Map<String, String> configMap) {
        MapperFacade mapperFacade = this.getMapperFacade(toClass, data.getClass(), configMap);
        return mapperFacade.map(data, toClass);
    }

    /**
     * 映射集合（默认字段）
     * 映射为集合的形式
     * @param toClass 映射类对象 dto对象
     * @param data    数据（集合）do对象
     * @return 映射类对象
     */
    public <E, T> List<E> mapAsList(Class<E> toClass, Collection<T> data) {
        return MAPPER_FACADE.mapAsList(data, toClass);
    }

    /**
     * 获取自定义映射
     *
     * @param toClass   映射类
     * @param dataClass 数据映射类
     * @param configMap 自定义配置
     * @return 映射类对象
     */
    private <E, T> MapperFacade getMapperFacade(Class<E> toClass, Class<T> dataClass, Map<String, String> configMap) {
        String mapKey = dataClass.getCanonicalName() + "_" + toClass.getCanonicalName();
        MapperFacade mapperFacade = CACHE_MAPPER_FACADE_MAP.get(mapKey);
        if (Objects.isNull(mapperFacade)) {
            MapperFactory factory = new DefaultMapperFactory.Builder().build();
            ClassMapBuilder classMapBuilder = factory.classMap(dataClass, toClass);
            configMap.forEach(classMapBuilder::field);
            classMapBuilder.byDefault().register();
            mapperFacade = factory.getMapperFacade();
            CACHE_MAPPER_FACADE_MAP.put(mapKey, mapperFacade);
        }
        return mapperFacade;
    }
}

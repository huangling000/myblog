package com.zero.service;

import com.zero.po.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface TypeService {
    Type saveType(Type type);   //保存一个分类

    Type getType(Long id);    //根据一个id获得type

    Page<Type> listType(Pageable pageable);    //分页查询

    Type getTypeByName(String name);

    Type updateType(Long id, Type type);

    void deletedType(Long id);
}

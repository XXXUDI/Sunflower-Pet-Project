package com.soCompany.sunflower.mapper;

public interface Mapper<F, T>{

    T map(F from);

    default T map(F object, T toObject) {
        throw new UnsupportedOperationException("Not implemented");
    }

}

package com.soCompany.sunflower.mapper;

public interface Mapper<F, T>{

    /*Convert F (From Obj) -> T (ToObject)
    Needed for service and repository classes
     */
    T map(F from);

    //This method is used to update existing users
    default T map(F object, T toObject) {
        throw new UnsupportedOperationException("Not implemented");
    }

}

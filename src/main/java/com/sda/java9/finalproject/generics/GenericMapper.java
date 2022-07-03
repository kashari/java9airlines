package com.sda.java9.finalproject.generics;

public interface GenericMapper <T,E>{
    T mapToEntity(E e);
    E mapToDto(T t);
}

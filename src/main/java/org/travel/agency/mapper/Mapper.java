package org.travel.agency.mapper;

public interface Mapper<T, U> {
    T from(U u);
    U to(T t);
}

package by.dulik.eureka.users.converter;

public interface Converter<R, T> {

    R convert(T object);
}

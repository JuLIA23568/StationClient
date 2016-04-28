package by.bsuir.station.validator;

public interface Validator<T> {
    boolean validate(T object);
}

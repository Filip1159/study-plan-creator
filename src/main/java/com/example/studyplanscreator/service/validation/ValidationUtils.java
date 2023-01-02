package com.example.studyplanscreator.service.validation;

import java.util.Collection;

public class ValidationUtils {
    public static <T> boolean deepEqual(Collection<T> firstCollection, Collection<T> secondCollection) {
        return firstCollection.containsAll(secondCollection) && secondCollection.containsAll(firstCollection);
    }
}

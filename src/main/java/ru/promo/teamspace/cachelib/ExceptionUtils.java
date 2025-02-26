package ru.promo.teamspace.cachelib;

import jakarta.el.PropertyNotFoundException;

public class ExceptionUtils {

    public static PropertyNotFoundException cachePropertiesNotFound(String cacheName) {
        return new PropertyNotFoundException("Not specified cache properties for cache: " + cacheName);
    }
}

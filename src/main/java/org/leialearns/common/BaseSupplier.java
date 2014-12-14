package org.leialearns.common;

import java.util.function.Supplier;

/**
 * Adds a convenient {@link Object#toString()} method to a {@link Supplier}. Note that each call to toString also calls
 * the get method.
 *
 * It would have been nice to make this into an interface with a default method, but a defaulr method is not allowed to
 * override an existing method. Too bad.
 *
 * @param <T> The return type of the supplier
 */
public abstract class BaseSupplier<T> implements Supplier<T> {

    @Override
    public String toString() {
        return String.valueOf(get());
    }
}

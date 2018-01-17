package com.rootsoft.taters.repositories;

import java.util.List;

/**
 * Implementation of the repository pattern to support other storage options later on.
 */
public interface Repository<T> {

    /**
     * Adds a new item to the repository.
     * @param item the item to be added.
     */
    boolean add(T item);

    /**
     * Get a list of all the items.
     */
    List<T> getItems();

    /**
     * Gets am item at a specific position in the repository.
     */
    T getItem(int position);

    /**
     * Gets the length of the items.
     * @return the length of the items
     */
    int getSize();

}

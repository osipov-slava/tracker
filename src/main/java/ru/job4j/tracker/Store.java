package ru.job4j.tracker;

import java.util.List;
import java.util.Optional;

public interface Store {
    Item add(Item item);

    boolean replace(Integer id, Item item);

    boolean delete(Integer id);

    List<Item> findAll();

    List<Item> findByName(String key);

    Optional<Item> findById(Integer id);
}
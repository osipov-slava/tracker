package ru.job4j.tracker;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class TrackerHbmTest {

    @AfterEach
    public void clearItems() {
        try (var tracker = new HbmTracker()) {
            var items = tracker.findAll();
            for (var item : items) {
                tracker.delete(item.getId());
            }
        }
    }

    @Test
    public void whenAddNewItemThenTrackerHasSameItem() {
        try (var tracker = new HbmTracker()) {
            Item item = new Item();
            item.setName("test1");
            tracker.add(item);

            Item result = tracker.findById(item.getId()).get();
            assertThat(result.getName()).isEqualTo(item.getName());
        }
    }

    @Test
    public void whenUpdateItemThenReturnTrue() {
        try (var tracker = new HbmTracker()) {
            Item item = new Item();
            item.setName("test1");
            tracker.add(item);
            item.setName("test2");

            boolean result = tracker.replace(item.getId(), item);
            assertThat(result).isTrue();

            Item updated = tracker.findById(item.getId()).get();
            assertThat(updated.getName()).isEqualTo(item.getName());
        }
    }

    @Test
    public void whenUpdateUnknownItemThenReturnFalse() {
        try (var tracker = new HbmTracker()) {
            Item item = new Item();
            item.setName("test1");

            boolean result = tracker.replace(100, item);
            assertThat(result).isFalse();
        }
    }

    @Test
    public void whenDeleteItemThenReturnTrue() {
        try (var tracker = new HbmTracker()) {
            Item item = new Item();
            item.setName("test1");
            tracker.add(item);

            boolean result = tracker.delete(item.getId());
            assertThat(result).isTrue();

            var optional = tracker.findById(item.getId());
            assertThat(optional.isEmpty()).isTrue();
        }
    }

    @Test
    public void whenDeleteUnknownItemThenReturnFalse() {
        try (var tracker = new HbmTracker()) {
            boolean result = tracker.delete(1);
            assertThat(result).isFalse();
        }
    }

    @Test
    public void whenAddItemsThenGetAllItems() {
        try (var tracker = new HbmTracker()) {
            Item item = new Item();
            item.setName("test1");
            tracker.add(item);

            Item item2 = new Item();
            item2.setName("test2");
            tracker.add(item2);
            var expected = Arrays.asList(item, item2);

            List<Item> actual = tracker.findAll();
            assertThat(actual).usingRecursiveAssertion().isEqualTo(expected);
        }
    }

    @Test
    public void whenAddItemsThenFindItemByName() {
        try (var tracker = new HbmTracker()) {
            Item item = new Item();
            item.setName("test1");
            tracker.add(item);

            Item item2 = new Item();
            item2.setName("test2");
            tracker.add(item2);

            var actual = tracker.findByName("test2");
            var expected = List.of(item2);
            assertThat(actual).isEqualTo(expected);
        }
    }
}
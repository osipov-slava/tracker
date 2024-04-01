package ru.job4j.tracker;

import java.util.List;

public class HbmUsage {

    public static void main(String[] args) {
        try (HbmTracker hbmTracker = new HbmTracker()) {
            List<Item> itemList = hbmTracker.findAll();
            for (Item item : itemList) {
                if (hbmTracker.delete(item.getId())) {
                    System.out.println(item + " was deleted");
                }
            }

            Item item1 = new Item();
            item1.setName("item1");
            Item item2 = new Item();
            item2.setName("item2");
            item1 = hbmTracker.add(item1);
            item2 = hbmTracker.add(item2);
            System.out.println(item1);
            System.out.println(item2);

            hbmTracker.replace(item1.getId(), item2);
            hbmTracker.findByName("item2")
                    .forEach(System.out::println);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

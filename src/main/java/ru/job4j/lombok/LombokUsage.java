package ru.job4j.lombok;

public class LombokUsage {
    public static void main(String[] args) {
        var bird = new BirdData();
        bird.setAge(1);
        System.out.println(bird);

        var permission = Permission.of()
                .id(1)
                .name("name")
                .rules("first")
                .rules("second")
                .build();
        System.out.println(permission);
    }
}
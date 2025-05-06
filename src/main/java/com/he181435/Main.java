package com.he181435;

import com.he181435.controller.MainController;
import com.he181435.entity.Person;

import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Person> personList = new ArrayList<>(List.of(new Person("Ann", 10, "Male"),
                new Person("Bin", 20, "Female"),
                new Person("Cin", 18, "Male"),
                new Person("Din", 60, "Female"),
                new Person("Enn", 70, "Male"),
                new Person("Fin", 77, "Female"),
                new Person("Gin", 34, "Other"),
                new Person("Hin", 12, "Male"),
                new Person("Inn", 65, "Other"),
                new Person("Kin", 40, "Female")));

        MainController mainController = new MainController();
        mainController.classify(personList);
        mainController.average(personList);
        mainController.findAge(personList);
        mainController.countNumberPersonByGender(personList);
        mainController.sortByAge(personList);

        Scanner sc = new Scanner(System.in);
        System.out.println("Nhập tên:");
        String name = sc.nextLine();
        mainController.findByName(personList, name);
        mainController.browseList(personList);
        mainController.insertPerson(personList);
    }
}
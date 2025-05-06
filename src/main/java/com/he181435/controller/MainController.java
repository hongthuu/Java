package com.he181435.controller;

import com.he181435.entity.Person;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class MainController {
    public void classify(List<Person> personList) {
        personList.forEach(p -> {
                    int check = 0;
                    if (p.getAge() < 18) {
                        check = 1;
                    } else if (p.getAge() >= 18 && p.getAge() < 60) {
                        check = 2;
                    }
                    String group = switch (check) {
                        case 1 -> "Trẻ em";
                        case 2 -> "Người lớn";
                        default -> "Người cao tuổi";
                    };
                    System.out.println(p.getName() + ": " + group);
                }
        );
    }

    public  void average(List<Person> personList) {
        personList.stream().mapToInt(Person::getAge).average().ifPresent(System.out::println);
    }

    public  void findAge(List<Person> personList) {
        personList.stream().max(Comparator.comparingInt(Person::getAge)).ifPresent(p -> System.out.println("Age max:" + p.getName()));
        personList.stream().min(Comparator.comparingInt(Person::getAge)).ifPresent(p -> System.out.println("Age min:" + p.getName()));
    }

    public  void countNumberPersonByGender(List<Person> personList) {
        personList.stream().collect(Collectors.groupingBy(Person::getGender, Collectors.counting())).forEach((gender, count) -> {
            System.out.println(gender + ": " + count);
        });
    }

    public  void insertPerson(List<Person> personList) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhập tên:");
        String name = sc.nextLine();
        System.out.println("Nhập tuổi:");
        int age = Integer.parseInt(sc.nextLine());
        if (age > 0 && age < 120) {
            System.out.println("Nhập giới tính:");
            String gender = sc.nextLine();
            Person p = new Person(name, age, gender);
            personList.add(p);
            System.out.println(p.getName() + " " + p.getAge() + " " + p.getGender());
            System.out.println("Đã thêm thành công!");
        } else {
            System.out.println("Tuổi không hợp lệ!");
        }
    }

    public  void sortByAge(List<Person> personList) {
        personList.stream().sorted(Comparator.comparingInt(Person::getAge)).forEach(p -> System.out.println(p.toString()));
    }

    public  void findByName(List<Person> personList, String name) {
        personList.forEach(p -> {
            if (p.getName().equals(name)) {
                System.out.println(p.toString());
            }
        });
    }

    public  void browseList(List<Person> personList) {
        personList.forEach(p -> {
                    String browse = switch (p.getGender()) {
                        case "Male" -> "Hey boy!";
                        case  "Female" -> "Hey girl!";
                        default -> "Hey mn!";
                    };
                    System.out.println(p.getName() + ": " + browse);
                }
        );
    }


}

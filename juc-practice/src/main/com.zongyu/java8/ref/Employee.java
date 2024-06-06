package com.zongyu.java8.ref;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Employee {
    private int id;
    private String name;
    private int age;
    private double salary;

    public Employee() {
        new Employee(1, "Tom", 5, 500);
    }

    public Employee(int id) {
        this.id = id;
    }

    public Employee(int id, String name) {
        this.id = id;
        this.name = name;
    }
}

package com.zongyu.java8.api;

import com.zongyu.java8.ref.Employee;

import java.util.ArrayList;
import java.util.List;

public class EmployeeData {
    public static List<Employee> getEmployees() {
        List<Employee> list = new ArrayList<>();

        list.add(new Employee(1001, "刘松", 34, 26000));
        list.add(new Employee(1001, "马云", 12, 6000));
        list.add(new Employee(1002, "刘强东", 40, 216000));
        list.add(new Employee(1003, "马化腾", 45, 26000));
        list.add(new Employee(1004, "扎克伯格", 35, 16000));

        return list;
    }
}

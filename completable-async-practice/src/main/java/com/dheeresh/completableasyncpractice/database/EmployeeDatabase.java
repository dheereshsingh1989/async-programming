package com.dheeresh.completableasyncpractice.database;

import com.dheeresh.completableasyncpractice.entity.Employee;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class EmployeeDatabase {

    public static List<Employee> fetchEmployees() throws IOException {

        return new ObjectMapper()
                .readValue(new File("employees.json"), new TypeReference<List<Employee>>() {});
    }
}

package com.codegym.dto.employee;

import javax.persistence.*;
import java.util.List;

public class PositionDto {

   private int id;

    private String name;

    private List<EmployeeDto> employees;

    public PositionDto() {
    }

    public PositionDto(int id, String name, List<EmployeeDto> employees) {
        this.id = id;
        this.name = name;
        this.employees = employees;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<EmployeeDto> getEmployees() {
        return employees;
    }

    public void setEmployees(List<EmployeeDto> employees) {
        this.employees = employees;
    }
}

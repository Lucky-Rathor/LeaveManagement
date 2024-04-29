package com.leave.entity;


import lombok.Data;
import org.springframework.context.annotation.Bean;


import javax.persistence.Entity;
import javax.persistence.Id;



@Data
@Entity
public class Employee {

    @Id
    private Integer employeeId;

    private String firstName;

    private String lastName;

    private String email;

    private String department;

    private String position;

    private Double salary;
}

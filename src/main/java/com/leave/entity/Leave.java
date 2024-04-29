package com.leave.entity;




import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Entity
@Data
@Table(name = "employee_leave")
public class Leave {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "leave_id")
    private Integer leaveId;

    private Date applicationDate;

    @Column(name = "start_date")
    @NotNull(message = "start date is mandatory")
    private Date startDate;

    @Column(name = "end_date")
    @NotNull(message = "end date is mandatory")
    private Date endDate;

    @Column(name = "leave_type")
    @NotNull(message = "leave type is mandatory")
    private String type;

    @Column(name = "leave_reason")
    @NotNull(message = "reason for leave is mandatory")
    private String reason;


    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @NotNull(message = "employee id is not null")
    private Integer employeeId;

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @OneToMany(cascade = CascadeType.REFRESH)
    private List<Employee> employee;
}

package com.leave.controller;


import com.leave.entity.Leave;
import com.leave.excpetion.LeaveException;
import com.leave.service.LeaveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class LeaveController {

    @Autowired
    private LeaveService leaveService;

    @PostMapping(value = "/v1/employee/leave")
    public ResponseEntity<Leave> addLeave(@Valid @RequestBody Leave leave) throws LeaveException {
        return new ResponseEntity<>(leaveService.addLeave(leave), HttpStatus.CREATED);
    }

    @GetMapping(value = "/v1/employees/leave/{employeeId}")
    public ResponseEntity<Leave> getEmployeeDetailsByEmpId(@PathVariable("employeeId") Integer employeeId) throws LeaveException {
        return new ResponseEntity<>(leaveService.getEmployeeDetailsByEmpId(employeeId),HttpStatus.OK);
    }

    @GetMapping(value = "/v1/leave/employee/{leaveId}")
    public ResponseEntity<Leave> getDetailsByLeaveId(@PathVariable("leaveId") Integer leaveId) throws LeaveException {
        return new ResponseEntity<>(leaveService.getDetailsByLeaveId(leaveId),HttpStatus.OK);
    }
}

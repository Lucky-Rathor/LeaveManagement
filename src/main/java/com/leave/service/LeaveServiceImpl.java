package com.leave.service;

import com.leave.constants.EmployeeConstant;
import com.leave.entity.Employee;
import com.leave.entity.Leave;
import com.leave.excpetion.LeaveException;
import com.leave.repository.LeaveRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class LeaveServiceImpl implements LeaveService {



    @Autowired
    private LeaveRepository leaveRepository;

    @Autowired
    private RestTemplate restTemplate;

    @Override
    public Leave addLeave(Leave leave) throws LeaveException {
         if (ObjectUtils.isEmpty(leave)) {
             throw new LeaveException("please provide all required details to create a leave");
         }
        return leaveRepository.save(leave);
    }

    @Override
    public Leave getEmployeeDetailsByEmpId(Integer employeeId) throws LeaveException {
        Employee employee = new Employee();
        try {
             employee = restTemplate.getForObject(EmployeeConstant.EMPLOYEE_URI + employeeId, Employee.class);
            log.info("getting employee object" + employee);
        } catch (Exception e) {
            log.error("Eror while callig employee service" + e.getMessage());
        }
        Optional<Leave> leave =  leaveRepository.getLeaveByEmployeeId(employeeId);
        if (leave.isEmpty()) {
            throw new LeaveException("No record found for leave for this employee id:"+ employeeId);
        }
        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(employee);
        leave.get().setEmployee(employeeList);

        return leave.get();
    }

    @Override
    public Leave getDetailsByLeaveId(Integer leaveId) throws LeaveException {
       Optional<Leave> leaveOptional = leaveRepository.findById(leaveId);
       if (leaveOptional.isEmpty()) {
           throw new LeaveException("No record found for leave for this employee id:"+ leaveId);
       }
       Leave leave = leaveOptional.get();
       Employee employee = restTemplate.getForObject(EmployeeConstant.EMPLOYEE_URI+leave.getEmployeeId(),Employee.class);
        List<Employee> employeeList = new ArrayList<>();
        employeeList.add(employee);
        leave.setEmployee(employeeList);
        return leave;
    }


  

}

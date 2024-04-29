package com.leave.repository;


import com.leave.entity.Employee;
import com.leave.entity.Leave;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LeaveRepository extends JpaRepository<Leave,Integer> {

        Optional<Employee> findByEmployeeId(Integer employeeId);

        Optional<Leave> getLeaveByEmployeeId(Integer employeeId);
}

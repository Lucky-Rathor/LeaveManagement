package com.leave.service;

import com.leave.entity.Leave;
import com.leave.excpetion.LeaveException;

public interface LeaveService {

    Leave addLeave(Leave leave) throws LeaveException;

    Leave getEmployeeDetailsByEmpId(Integer employeeId) throws LeaveException;

    Leave getDetailsByLeaveId(Integer leaveId) throws LeaveException;
}

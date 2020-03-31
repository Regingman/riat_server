package com.riatServer.service;

import com.riatServer.domain.ListOfEmployees;

public interface ListOfEmployeeService {
    ListOfEmployees taskInfo(Long userId, boolean active, Long TaskId);
}

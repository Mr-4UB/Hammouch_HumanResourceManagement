package com.test.backend.repositories.employee;

import com.test.backend.entities.Employee;
import com.test.backend.repositories.common.CommonJPARepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepo extends CommonJPARepository<Employee, String> {

    @Query(value = "From Employee")
    List<Employee> findAll();

    @Query(value = "From Employee Where employeeId =?1")
    Employee findById(Integer employeeId);

    @Query(value = "delete From Employee Where employeeId =?1")
    void deleteById(Integer employeeId);

}

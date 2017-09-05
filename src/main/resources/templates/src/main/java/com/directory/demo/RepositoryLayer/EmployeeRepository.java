package com.directory.demo.RepositoryLayer;

import com.directory.demo.ModelLayer.Employee;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface EmployeeRepository extends CrudRepository<Employee, Long> {

    Set<Employee> findByDepartment_id(long id);
}

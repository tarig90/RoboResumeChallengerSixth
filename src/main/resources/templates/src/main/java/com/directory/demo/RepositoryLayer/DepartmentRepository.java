package com.directory.demo.RepositoryLayer;

import com.directory.demo.ModelLayer.Department;
import org.springframework.data.repository.CrudRepository;

public interface DepartmentRepository extends CrudRepository<Department,Long> {
}

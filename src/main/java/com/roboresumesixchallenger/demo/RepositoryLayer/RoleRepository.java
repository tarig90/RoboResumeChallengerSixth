package com.roboresumesixchallenger.demo.RepositoryLayer;

import com.roboresumesixchallenger.demo.ModelLayer.Role;
import org.springframework.data.repository.CrudRepository;

public interface RoleRepository extends CrudRepository<Role, Long> {
    Role findByRole(String role);
}

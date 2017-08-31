package com.roboresumesixchallenger.demo.RepositoryLayer;

import com.roboresumesixchallenger.demo.ModelLayer.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
}

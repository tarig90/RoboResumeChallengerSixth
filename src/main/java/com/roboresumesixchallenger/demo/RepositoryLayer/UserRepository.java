package com.roboresumesixchallenger.demo.RepositoryLayer;

import com.roboresumesixchallenger.demo.ModelLayer.RoboUser;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<RoboUser, Long> {
}

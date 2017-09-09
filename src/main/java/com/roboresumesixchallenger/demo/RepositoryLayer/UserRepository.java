package com.roboresumesixchallenger.demo.RepositoryLayer;

import com.roboresumesixchallenger.demo.ModelLayer.RoboUser;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<RoboUser, Long> {

   RoboUser findByUsername(String username);
   Iterable<RoboUser> findAllByUsername(String username);
   RoboUser findByEmailAddress(String email);
   Long countByEmailAddress(String email);
   Long countByUsername(String username);
}

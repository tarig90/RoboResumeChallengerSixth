package com.directory.demo.RepositoryLayer;

import com.directory.demo.ModelLayer.Teams;
import org.springframework.data.repository.CrudRepository;

public interface TeamRepository extends CrudRepository<Teams, Long> {
}

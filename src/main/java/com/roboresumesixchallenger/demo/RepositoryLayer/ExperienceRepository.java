package com.roboresumesixchallenger.demo.RepositoryLayer;

import com.roboresumesixchallenger.demo.ModelLayer.Experience;
import org.springframework.data.repository.CrudRepository;

public interface ExperienceRepository extends CrudRepository<Experience, Long> {
}

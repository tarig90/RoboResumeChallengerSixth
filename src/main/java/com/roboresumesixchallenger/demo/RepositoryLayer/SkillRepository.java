package com.roboresumesixchallenger.demo.RepositoryLayer;

import com.roboresumesixchallenger.demo.ModelLayer.SkillsClass;
import org.springframework.data.repository.CrudRepository;

public interface SkillRepository extends CrudRepository<SkillsClass, Long>{
}

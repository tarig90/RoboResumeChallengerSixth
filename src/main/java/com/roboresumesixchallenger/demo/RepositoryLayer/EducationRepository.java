package com.roboresumesixchallenger.demo.RepositoryLayer;

import com.roboresumesixchallenger.demo.ModelLayer.EducationClass;
import org.springframework.data.repository.CrudRepository;

public interface EducationRepository extends CrudRepository<EducationClass, Long>
{



}

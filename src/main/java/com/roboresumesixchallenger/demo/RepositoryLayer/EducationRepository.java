package com.roboresumesixchallenger.demo.RepositoryLayer;

import com.roboresumesixchallenger.demo.ModelLayer.EducationClass;
import com.roboresumesixchallenger.demo.ModelLayer.JobDetils;
import org.springframework.data.repository.CrudRepository;

public interface EducationRepository extends CrudRepository<EducationClass, Long>
{
    //EducationClass findBySchoolName(String schoolName);

    Iterable<EducationClass> findAllBySchoolName(String schoolName);


}

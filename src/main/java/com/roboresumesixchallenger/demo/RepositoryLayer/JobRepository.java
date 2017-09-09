package com.roboresumesixchallenger.demo.RepositoryLayer;

import com.roboresumesixchallenger.demo.ModelLayer.JobDetils;
import org.springframework.data.repository.CrudRepository;

public interface JobRepository extends CrudRepository<JobDetils, Long> {

    Iterable<JobDetils> findByTitle(String title);
    Iterable<JobDetils> findByEmployerName(String employerName);

}

package com.example.repository;

import com.example.model.Path;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by mmonti on 11/7/16.
 */
@Repository
public interface PathRepository extends CrudRepository<Path, String> {
}

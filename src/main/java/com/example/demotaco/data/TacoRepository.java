package com.example.demotaco.data;

import com.example.demotaco.domain.Taco;
import org.springframework.data.repository.CrudRepository;

import javax.sql.rowset.CachedRowSet;

public interface TacoRepository extends CrudRepository<Taco, Long> {
}

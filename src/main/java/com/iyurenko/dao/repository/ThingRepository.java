package com.iyurenko.dao.repository;

import com.iyurenko.dao.domain.Thing;
import org.apache.camel.Header;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by iyurenko on 22.11.16.
 */
public interface ThingRepository extends CrudRepository<Thing, Long> {

    List<Thing> findByName(String name);
}

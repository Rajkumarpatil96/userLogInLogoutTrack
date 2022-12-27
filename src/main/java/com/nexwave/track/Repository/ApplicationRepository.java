package com.nexwave.track.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.nexwave.track.entity.Application;


@Repository
public interface ApplicationRepository extends MongoRepository<Application, Long> {
//	@Query("{'name' : ?0}")
//	Application findByapplication(String name);
}

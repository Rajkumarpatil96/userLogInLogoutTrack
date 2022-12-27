package com.nexwave.track.Repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.nexwave.track.entity.UserLogin;


@Repository
public interface UserLoginRepository extends  MongoRepository<UserLogin, Long>{
   

}

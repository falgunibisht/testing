package com.training.task3.dao;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.training.task3.entity.User;

public interface Dao extends MongoRepository<User, Integer>{

}

package com.training.task3.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.training.task3.dao.Dao;
import com.training.task3.entity.User;

@RestController
public class Controller {

	@Autowired
	public Dao dao;
	
	@Autowired
	KafkaTemplate<String, Object> kafkaTemplate;
	
	private final String topic="Kafka_Project";
	
	@PostMapping("/addUser")
	public String addUser(@RequestBody User u)
	{
		dao.save(u);
		kafkaTemplate.send(topic, u);
		return "User Saved";
	}
	
	@GetMapping("getUser/{id}")
	public Optional<User> getUser(@PathVariable int id)
	{
		return dao.findById(id);
	}
	
	@PutMapping("/updateUser")
	public String updateUser(@PathVariable User u)
	{
		dao.save(u);
		kafkaTemplate.send(topic, u);
		return "Updated Successfully";
	}

}

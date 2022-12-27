package com.nexwave.track.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nexwave.track.entity.Application;
import com.nexwave.track.entity.UserLogin;
import com.nexwave.track.service.UserLoginServices;

@RestController
@RequestMapping("/main")
public class UserLoginController {

	@Autowired
	UserLoginServices service;

	@PostMapping("/login")
	public UserLogin login(@RequestBody UserLogin log) {

		return service.add(log);
	}

	@GetMapping("/get/{id}")
	public UserLogin getByid(@PathVariable("id") Long id) {
		return service.getById(id);
	}

	@GetMapping("/find")
	public List<UserLogin> find() {
		return service.getAll();
	}

	@PutMapping("/update/{id}")
	public UserLogin upd(@RequestBody UserLogin log, @PathVariable("id") Long id) {

		return service.update(log, id);
	}

	@DeleteMapping("/delete/{id}")
	public String delete(@PathVariable("id") Long id) {
		service.delete(id);
		return " User Deleted SuccessFully";
	}
}

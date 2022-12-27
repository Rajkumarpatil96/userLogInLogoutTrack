package com.nexwave.track.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nexwave.track.Repository.ApplicationRepository;
import com.nexwave.track.Repository.UserLoginRepository;
import com.nexwave.track.entity.Application;
import com.nexwave.track.entity.UserLogin;
import com.nexwave.track.service.UserLoginServices;

@RestController
public class AppController {

	Application appl = new Application();

	@Autowired
	UserLoginServices service;
	@Autowired
	UserLoginRepository loginRepository;
	@Autowired
	ApplicationRepository apprepo;

	@PostMapping("/appl/{id}")
	public void application(@RequestBody Application app, @PathVariable("id") Long id) {

		appl.setStartTime(new Date());
		service.loginApp(app, id);

	}

	@PutMapping("/appupdate/{id}")
	public void updateApp(@PathVariable long id, @RequestBody Application aps) {

		appl.setId(aps.getId());
		appl.setApplication(aps.getApplication());
		appl.setEndTime(new Date());

		service.appUpdate(appl, id);

	}

}

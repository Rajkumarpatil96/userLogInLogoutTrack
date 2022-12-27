package com.nexwave.track.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nexwave.track.Repository.ApplicationRepository;
import com.nexwave.track.Repository.UserLoginRepository;
import com.nexwave.track.entity.Application;
import com.nexwave.track.entity.UserLogin;

@Service
public class UserLoginServices {

	@Autowired
	UserLoginRepository repo;

	@Autowired
	ApplicationRepository apprepo;
	Application appls = new Application();
	List<Application> appList = new ArrayList<>();

	public UserLogin add(UserLogin login) {
		login.setStartTime(new Date());
		return repo.save(login);
	}

	public void logout(UserLogin login) {
		repo.save(login);
	}

	public List<UserLogin> getAll() {
		return repo.findAll();
	}

	public UserLogin update(UserLogin log, long id) {
		UserLogin existuserlogin = repo.findById(id).get();

		log.setStartTime(existuserlogin.getStartTime());
		log.set_id(existuserlogin.get_id());
		log.setUsername(existuserlogin.getUsername());
		log.setApplications(existuserlogin.getApplications());
		log.setEndTime(new Date());

		existuserlogin.setEndTime(new Date());
		int startHours = existuserlogin.getStartTime().getHours();
		int startMinutes = existuserlogin.getStartTime().getMinutes();
		int startSeconds = existuserlogin.getStartTime().getSeconds();
		long totalStartTime = startSeconds + (startMinutes * 60) + (startHours * 60 * 60);

		int endHours = existuserlogin.getEndTime().getHours();
		int endMinutes = existuserlogin.getEndTime().getMinutes();
		int endSeconds = existuserlogin.getEndTime().getSeconds();
		long totalEndTime = endSeconds + (endMinutes * 60) + (endHours * 60 * 60);

		long totalSpendSystem = totalEndTime - totalStartTime;
		long totalHours = totalSpendSystem / 3600;
		long totalMins = (totalSpendSystem % 3600) / 60;
		long totalSecs = (totalSpendSystem % 60);
		String duration = String.format("%02d:%02d:%02d", totalHours, totalMins, totalSecs);
		existuserlogin.setDuration(duration);
		log.setDuration(existuserlogin.getDuration());

		return repo.save(log);
	}

	public void appUpdate(Application app, Long id) {

		UserLogin loggedInUser = repo.findById(id).get();

		List<Application> allApps = loggedInUser.getApplications();

		appList.add(appls);

		Application currentApp = null;

		for (Application eachapp : allApps) {
			currentApp = eachapp;

		}

		if (currentApp != null) {
			allApps.get(allApps.indexOf(currentApp)).setEndTime(new Date());

			int startHours = currentApp.getStartTime().getHours();
			int startMins = currentApp.getStartTime().getMinutes();
			int startSecs = currentApp.getStartTime().getSeconds();
			long totalStartTime = startSecs + (startMins * 60) + (startHours * 60 * 60);

			int endHours = currentApp.getEndTime().getHours();
			int endMins = currentApp.getEndTime().getMinutes();
			int endSecs = currentApp.getEndTime().getSeconds();
			long totalEndTime = endSecs + (endMins * 60) + (endHours * 60 * 60);

			long totalSpendSystem = totalEndTime - totalStartTime;
			long totalHours = totalSpendSystem / 3600;
			long totalMins = (totalSpendSystem % 3600) / 60;
			long totalSecs = (totalSpendSystem % 60);
			String duration = String.format("%02d:%02d:%02d", totalHours, totalMins, totalSecs);
			allApps.get(allApps.indexOf(currentApp)).setDuration(duration);
			loggedInUser.setApplications(allApps);
			repo.save(loggedInUser);
			return;
		}
	}

	public void loginApp(Application app, Long id) {
		List<UserLogin> logins = repo.findAll();

		UserLogin currentLoginUser = null;

		for (UserLogin eachLogin : logins) {
			if (eachLogin.get_id() == id) {
				currentLoginUser = eachLogin;

			}
		}

		List<Application> apps = null;

		if (currentLoginUser != null) {
			apps = logins.get(logins.indexOf(currentLoginUser)).getApplications();
		}

		if (apps != null) {
			app.setStartTime(new Date());
			apps.add(app);
		} else {
			apps = new ArrayList<>();
			app.setStartTime(new Date());
			apps.add(app);
			currentLoginUser.setApplications(apps);
		}

		repo.save(currentLoginUser);
	}

	public UserLogin getById(Long id) {
		return repo.findById(id).get();

	}

	public void delete(Long id) {
		repo.deleteById(id);
	}

}

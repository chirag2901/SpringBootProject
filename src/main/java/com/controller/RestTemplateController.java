package com.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.bean.ResUser;
import com.bean.detail;

@RestController
public class RestTemplateController {
	@Autowired
	RestTemplate restTemplate;
	
	@GetMapping(value = "/getdata")
	public void getData() {
		final String url = "https://reqres.in/api/users?page=2";
		String res = restTemplate.getForObject(url,String.class);
		System.out.println(res);
		detail detail = restTemplate.getForObject(url,detail.class );
		System.out.println(detail.getPage());
		System.out.println(detail.getData()[2].getFirst_name());
		System.out.println(detail.getData()[3].getLast_name());
	}
	
	@RequestMapping("/adddata")
	public void addData() {
		final String url = "https://reqres.in/api/users";
		ResUser resUser = new ResUser();
		resUser.setName("Chirag");
		resUser.setJob("Manager");
		ResUser user = restTemplate.postForObject(url, resUser, ResUser.class);
		System.out.println(user.getName());
		System.out.println(user.getJob());
		System.out.println(user.getId());
		System.out.println(user.getCreatedAt());
	}
	
	@GetMapping(value = "/coronadata")
	public void coronaData() {
		
	}
}

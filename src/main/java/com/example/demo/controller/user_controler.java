package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.exceptionhandling.ApiResponse;
import com.example.demo.models.users;
import com.example.demo.payload.user_payload;
import com.example.demo.services.users_services;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1")
public class user_controler {
	@Autowired
	users_services service;
	
@PostMapping("/insert")
	public ResponseEntity<user_payload> adduser(@Valid @RequestBody user_payload user){
		
		user_payload users = this.service.addusers(user);
		
		return new ResponseEntity<>(users,HttpStatus.CREATED);
	
}
	@PutMapping("/update/{id}")
	public ResponseEntity<user_payload>updateusers(@Valid @PathVariable int id,@RequestBody user_payload user){
	user_payload users=this.service.updateusers(user, id);
			return ResponseEntity.ok(users);
}
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<ApiResponse>delete(@Valid @PathVariable int id){
	this.service.deleteusers(id);
	return new ResponseEntity<ApiResponse>(new ApiResponse("id delete successfully",true),HttpStatus.OK);
}
	
	@GetMapping("/getall")
	public ResponseEntity<List<user_payload>>getall(){
		return ResponseEntity.ok(this.service.getallusers());
	}
	
	
	
	@GetMapping("/getbyid/{id}")
	public ResponseEntity<user_payload>getbyid(@Valid @PathVariable int id){
		return ResponseEntity.ok(this.service.getUsersbyid(id));
	}
}

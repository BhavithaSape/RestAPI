package com.example.demo.services.imp;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.exceptionhandling.ResourceNotFoundException;
import com.example.demo.models.users;
import com.example.demo.payload.user_payload;
import com.example.demo.repository.user_repo;
import com.example.demo.services.users_services;

@Service
public class impli implements users_services {
	

	


	@Autowired
	user_repo repo;
	@Autowired
	ModelMapper modelmapper;
	@Override
	public user_payload addusers(user_payload adu) {
		users u=this.dto_users(adu);
		users savesusers = this.repo.save(u);
		return this.users_dto(savesusers);
		
	
	}

	@Override
	public user_payload updateusers(user_payload upu, int id) {
		// TODO Auto-generated method stub
		 users u =this.repo.findById(id).orElseThrow(()->new ResourceNotFoundException("users","id",id));
		 u.setName(upu.getName());
		 u.setName(upu.getEmail());
		 u.setPassword(upu.getPassword());
		 users u1=this.repo.save(u);
		 user_payload us=this.users_dto(u1);
		 return us;
	}

	@Override
	public void deleteusers(int id) {
		users u=this.repo.findById(id).orElseThrow(()->new ResourceNotFoundException("users","id",id));
				this.repo.delete(u);

	}

	@Override
	public List<user_payload> getallusers() {
		List<users> u=(List<users>)this.repo.findAll();
		List<user_payload> up =u.stream().map(user->this.users_dto(user)).collect(Collectors.toList());
		return up;
		
	}

	@Override
	public user_payload getUsersbyid(int id) {
		users u=this.repo.findById(id).orElseThrow(()->new ResourceNotFoundException("users","id",id));
		return users_dto(u);
	}

	
	public users dto_users(user_payload up) {
		users u=this.modelmapper.map(up,users.class);
		return u;
	}
	
	public user_payload users_dto(users usp) {
		user_payload usersdto=this.modelmapper.map(usp,user_payload.class);
		return usersdto;
	}
	
//	public users dto_users(user_payload userp ) {
//		users u = this.modelmapper.map(userp, users.class);
//			return u;
//		}
//		
//		
//		public users dto_users(users  user ) {
//			user_payload us = this.modelmapper.map(user, user_payload.class);
//				return us;
//			}
//		
//	}

}

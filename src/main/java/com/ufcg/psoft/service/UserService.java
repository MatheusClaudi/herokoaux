package com.ufcg.psoft.service;

import java.util.List;

import com.ufcg.psoft.model.User;
import com.ufcg.psoft.model.DTO.UserDTO;

public interface UserService {
	
	List<User> findAllUsers();

	User findUserById(long id) throws Exception;
	
	User findUserByEmail(String email) throws Exception;

	void updateUser(User user) throws Exception;

	void deleteUserById(long id) throws Exception;
	
	User createAdmin(UserDTO userDTO);

	User createCliente(UserDTO userDTO);
}

package com.ufcg.psoft.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ufcg.psoft.model.Admin;
import com.ufcg.psoft.model.CargoSistema;
import com.ufcg.psoft.model.Lote;
import com.ufcg.psoft.model.Produto;
import com.ufcg.psoft.model.User;
import com.ufcg.psoft.model.DTO.UserDTO;
import com.ufcg.psoft.service.UserService;
import com.ufcg.psoft.service.UserServiceImpl;
import com.ufcg.psoft.util.CustomErrorType;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;



@RestController
@RequestMapping("/api")
@Api("API REST Users")
@CrossOrigin()
public class UserController {
	
	@Autowired
	UserService userService = new UserServiceImpl();
	
	
	@RequestMapping(value = "/public/user/admin", method = RequestMethod.POST)
	@ApiOperation(value="Cria usuario admin, como não foi especificado como se daria a criação de admins o metodo de criação está público.")
	public ResponseEntity<?> criarUserAdmin(@RequestBody UserDTO userDTO) {
		
		try {			
			User userCreated = this.userService.createAdmin(userDTO);
			UserDTO resposta = UserDTO.fromCompleteUser(userCreated);

			return new ResponseEntity<>(resposta, HttpStatus.CREATED);
		}catch (Exception e) {
			CustomErrorType error = new CustomErrorType(e.getMessage());
			return new ResponseEntity<>(error , HttpStatus.BAD_REQUEST);
		}
	}
	
	@RequestMapping(value = "/admin/user/cliente", method = RequestMethod.POST)
	@ApiOperation(value="Cria usuario admin, foi adotado que apenas admins  podem cadastrar.")
	public ResponseEntity<?> criarUserCliente(@RequestBody UserDTO userDTO) {
		
		try {
			User userCreated = this.userService.createCliente(userDTO);
			UserDTO resposta = UserDTO.fromCompleteUser(userCreated);
			
			return new ResponseEntity<>(resposta, HttpStatus.CREATED);	
		}catch (Exception e) {
			CustomErrorType error = new CustomErrorType(e.getMessage());
			return new ResponseEntity<>(error , HttpStatus.BAD_REQUEST);
		}
	}

}

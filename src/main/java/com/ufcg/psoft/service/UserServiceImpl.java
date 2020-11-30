package com.ufcg.psoft.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ufcg.psoft.model.Admin;
import com.ufcg.psoft.model.CargoSistema;
import com.ufcg.psoft.model.Cliente;
import com.ufcg.psoft.model.User;
import com.ufcg.psoft.model.DTO.UserDTO;
import com.ufcg.psoft.model.repositories.UserDAO;

@Service("userService")
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDAO userRepository;
	
	@Autowired
    private PasswordEncoder encoder;
	
	@Override
	public List<User> findAllUsers() {
		return this.userRepository.findAll();
	}

	@Override
	public User findUserById(long id) throws Exception {
		try {
			return this.userRepository.findById(id).get();
		}
		catch(Exception e) {
			throw new Exception("Usuário de id: " + id + " não encontrado.");
		}
	}

	@Override
	public void updateUser(User user) throws Exception {
		
		long id = user.getId();
		
		if (this.userRepository.existsById(id)) {
			this.userRepository.save(user);
		}
		else {
			throw new Exception("Usuário com id: " + id +", não está contido no banco de dados");
		}
		
	}

	@Override
	public void deleteUserById(long id) throws Exception {
		
		
		if (this.userRepository.existsById(id)) {
			this.userRepository.deleteById(id);
		}
		else {
			throw new Exception("Usuário com id: " + id +", não está contido no banco de dados");
		}
	}

	@Override
	public User findUserByEmail(String email) throws Exception {
		try {
			return this.userRepository.findByEmail(email).get();
		}
		catch(Exception e) {
			throw new Exception("Usuário de email: " + email + " não encontrado.");
		}
	}

	@Override
	public User createAdmin(UserDTO userDTO) {
		
		String nome = userDTO.getNome();
		String sobrenome = userDTO.getSobrenome();
		String senha = userDTO.getSenha();
		String email = userDTO.getEmail();
		
		User user = new User(nome, sobrenome, senha, email);
		
		CargoSistema cargo = new Admin();
		user.setCargoSistema(cargo);
		cargo.setUser(user);
		
		user.setSenha(encoder.encode(user.getSenha()));
		return this.userRepository.save(user);
	}

	@Override
	public User createCliente(UserDTO userDTO) {
		
		String nome = userDTO.getNome();
		String sobrenome = userDTO.getSobrenome();
		String senha = userDTO.getSenha();
		String email = userDTO.getEmail();
		
		User user = new User(nome, sobrenome, senha, email);
		
		CargoSistema cargo = new Cliente();
		user.setCargoSistema(cargo);
		cargo.setUser(user);
		
		user.setSenha(encoder.encode(user.getSenha()));
		return this.userRepository.save(user);
	}
}

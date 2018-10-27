package com.vvtalico.workshopmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vvtalico.workshopmongo.domain.User;
import com.vvtalico.workshopmongo.dto.UserDTO;
import com.vvtalico.workshopmongo.repository.UserRepository;
import com.vvtalico.workshopmongo.services.exception.ObjectNotFoundException;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repo;

	public List<User> findAll(){
		
		return repo.findAll();
		
	}
	
	public User findById(String id) {
		Optional<User> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}
	
	public User insert (User obj) {
		return repo.insert(obj);
	}
	
	public void delete(String id) {
		
		findById(id);
		repo.deleteById(id);
	}
	
	public User update(User newObj) {
		
		User originalObj = findById(newObj.getId());
		updateData(originalObj, newObj);		
		return repo.save(originalObj);		
	}
	
	private void updateData(User originalObj, User newObj) {
		originalObj.setEmail(newObj.getEmail());
		originalObj.setName(newObj.getName());		
	}
	
	public User fromDTO(UserDTO objDTO) {
		return new User(objDTO.getId(), objDTO.getName(), objDTO.getEmail());		
	}
	
}

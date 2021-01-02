package com.poc.purchaseOrder1.servicesImpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poc.purchaseOrder1.entities.PurchaseEntity;
import com.poc.purchaseOrder1.entities.UserEntity;
import com.poc.purchaseOrder1.models.User;
import com.poc.purchaseOrder1.repository.UserRepository;
import com.poc.purchaseOrder1.services.IUserService;

@Service
public class UserService implements IUserService {

	
	@Autowired
	UserRepository repo;
	
	public UserService() {
		// TODO Auto-generated constructor stub
	}

	public User getUserById(Integer id) {
		
		UserEntity userEntity =  repo.findById(id).orElse(null);
		return entityToModelMapping(userEntity);
	}
	
	public List<User> getAllUsers() {
		List<User> list =  repo.findAll().stream().map(ue -> entityToModelMapping(ue)).collect(Collectors.toList());
		System.out.println("................................................");
		System.out.println(list + " " + repo.findAll());
		System.out.println("................................................");

		return list;
	}
	
	public User saveUser(User u) {
		
		UserEntity ue = repo.save(modelToEntityMapping(u));
		if(ue != null)
			return u;
		else return null;
	}
	
	public int  deleteUser(User u) {
		
		UserEntity ue = repo.findById(u.getUserid()).orElse(null);
		if(ue == null) return 0;
		repo.delete(ue);
		return 1;
		
	}
	
	
	private User entityToModelMapping(UserEntity entity) {
		User user = new User();
		user.setUserid(entity.getUserid());
		user.setContactno(entity.getContactno());
		user.setName(entity.getName());
		user.setPurchaseid(entity.getPurchase().stream().map(x -> x.getPurchaseid()).collect(Collectors.toList()));
		return user;
	}
	
	@SuppressWarnings("unchecked")
	private UserEntity modelToEntityMapping(User user) {
		UserEntity entity = new UserEntity();
		entity.setUserid(user.getUserid());
		entity.setContactno(user.getContactno());
		entity.setName(user.getName());
		Optional<UserEntity> oList = repo.findById(user.getUserid());
		if(oList.isPresent()) entity.setPurchase((List<PurchaseEntity>)oList.get());
		else entity.setPurchase(null);
		return entity;
	}


}

package com.cg.bookstore.serviceImp;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.bookstore.entities.UserClass;
import com.cg.bookstore.exception.UserNotFoundException;
import com.cg.bookstore.repository.ILoginRepository;
import com.cg.bookstore.service.ILoginService;

@Service
public class UserServiceImplementation implements ILoginService{

	@Autowired 
	ILoginRepository iLoginRepository;


		@Override
		public Optional<UserClass> addUser(UserClass user) throws UserNotFoundException {
			iLoginRepository.save(user);
			return Optional.of(user);
		}
		
	    @Override
		public List<UserClass> removeUser(UserClass user) throws UserNotFoundException {
	    	
	    	Optional<UserClass> optional = iLoginRepository.findById(user.getUserId());
	    	if(optional.isPresent()) {
	    		
	    		iLoginRepository.delete(user);
				return iLoginRepository.findAll();
	    	}
	    	else {
				throw new UserNotFoundException("User not found...!");
			}
	    	
		}
	    

		@Override
		public UserClass validateUser(Integer userId, String password) throws UserNotFoundException
		{
			
			Optional<UserClass> ol = iLoginRepository.findById(userId);
			if(ol.isEmpty())
				throw new UserNotFoundException("please enter valid userId");
			else if(ol.get().getUserId()==userId && ol.get().getPassword().equals(password))
				return ol.get();
			else
				throw new UserNotFoundException("userID and password does not match");
		}
}

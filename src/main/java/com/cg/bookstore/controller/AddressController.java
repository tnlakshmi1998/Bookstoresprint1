package com.cg.bookstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.bookstore.entities.Address;
import com.cg.bookstore.repository.AddressRepository;

@RestController
@RequestMapping("/api/v1/address")
public class AddressController {

	@Autowired
	AddressRepository addressRepository;
	
	@GetMapping("/getall")
	List<Address> getAddress() {
		return addressRepository.findAll();
	}
	
	// To create/INSERT new Student into database
	@PostMapping("/insertnew")
	Address insertAddress(@RequestBody Address address) {
		return addressRepository.save(address);
	}
	
	// To search Student by Id
	@GetMapping("/{id}")
	public Address getAddressById(@PathVariable Integer id) {
		return addressRepository.findById(id).get();
		
	}
	
	// To Update student existing record
	//@PutMapping("/{id}")
	//public Student updateStudent(@PathVariable Long id, @RequestBody Student student) {
		//Student existingStudent = studentReprosotry.findById(id).get();
		//BeanUtils.copyProperties(student, existingStudent, "id");
		//return studentReprosotry.save(existingStudent);
	//}
	
	//To Delete Student 
	@DeleteMapping("/{id}")
	public void deleteAddress(@PathVariable Integer id) {
		addressRepository.deleteById(id);
	}
}

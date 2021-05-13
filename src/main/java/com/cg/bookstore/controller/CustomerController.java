package com.cg.bookstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.bookstore.entities.Customer;
import com.cg.bookstore.service.ICustomerService;

@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {

	@Autowired
	ICustomerService iCustomerService;

	// create
	@PostMapping(path = "/createCustomer")
	public ResponseEntity<Customer> addCustomer(@RequestBody Customer customer) {
		
		Customer o = iCustomerService.createCustomer(customer);
		return new ResponseEntity<Customer>(o, HttpStatus.OK);
	}

	
	// list
	@GetMapping(path = "/getAllCustomers")
	public ResponseEntity<List<Customer>> listCustomer() {
		
		List<Customer> list = iCustomerService.listCustomers();
		return new ResponseEntity<List<Customer>>(list, HttpStatus.OK);
	}

	// delete
	@DeleteMapping(path = "/deleteCustomer")
	public ResponseEntity<Customer> deleteCustomer(@RequestBody Customer customer) {
		Customer o = iCustomerService.deleteCustomer(customer);
		return new ResponseEntity<Customer>(o, HttpStatus.OK);
	}

	
	// update
	@PutMapping(path = "/Update/{id}")
	public ResponseEntity<Customer> updateCustomer(@RequestBody Customer customer) {
		Customer o = iCustomerService.updateCustomer(customer);
		return new ResponseEntity<Customer>(o, HttpStatus.OK);
	}

	// view
	@PostMapping(path = "/viewCustomers")
	public ResponseEntity<Customer> viewCustomer(@RequestBody Customer customer) {
		Customer c = iCustomerService.viewCustomer(customer);
		return new ResponseEntity<Customer>(c, HttpStatus.OK);
	}

	/*
	 * @GetMapping("/getall") List<Customer> getCustomer() { return
	 * iCustomerRepository.findAll(); }
	 * 
	 * // To create/INSERT new Student into database
	 * 
	 * @PostMapping("/insertnew") Customer insertCustomer(@RequestBody Customer
	 * customer) { return iCustomerRepository.save(customer); }
	 * 
	 * // To search Student by Id
	 * 
	 * @GetMapping("/{id}") public Customer getCustomerById(@PathVariable Integer
	 * id) { return iCustomerRepository.findById(id).get();
	 * 
	 * }
	 * 
	 * // To Update student existing record //@PutMapping("/{id}") //public Student
	 * updateStudent(@PathVariable Long id, @RequestBody Student student) {
	 * //Student existingStudent = studentReprosotry.findById(id).get();
	 * //BeanUtils.copyProperties(student, existingStudent, "id"); //return
	 * studentReprosotry.save(existingStudent); //}
	 * 
	 * //To Delete Student
	 * 
	 * @DeleteMapping("/{id}") public void deleteCustomer(@PathVariable Integer id)
	 * { iCustomerRepository.deleteById(id); }
	 */
}

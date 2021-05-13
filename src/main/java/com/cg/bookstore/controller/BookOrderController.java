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

import com.cg.bookstore.entities.BookOrder;
import com.cg.bookstore.repository.BookOrderRepository;

@RestController
@RequestMapping("/api/v1/bookorder")
public class BookOrderController {

	@Autowired
	BookOrderRepository bookOrderRepository;
	
	@GetMapping("/getall")
	List<BookOrder> getBookOrders() {
		return bookOrderRepository.findAll();
	}
	
	// To create/INSERT new Student into database
	@PostMapping("/insertnew")
	BookOrder insertBookOrder(@RequestBody BookOrder bookOrder) {
		return bookOrderRepository.save(bookOrder);
	}
	
	// To search Student by Id
	@GetMapping("/{id}")
	public BookOrder getBookOrderById(@PathVariable Integer id) {
		return bookOrderRepository.findById(id).get();
		
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
	public void deleteBookOrder(@PathVariable Integer id) {
		bookOrderRepository.deleteById(id);
	}
}

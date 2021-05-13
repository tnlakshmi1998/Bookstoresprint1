package com.cg.bookstore.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.bookstore.entities.Book;
import com.cg.bookstore.entities.Customer;
import com.cg.bookstore.entities.OrderDetails;
import com.cg.bookstore.service.IOrderService;

@RestController
@RequestMapping("/api/v1/orderdetails")
public class OrderDetailsController {

	@Autowired
	private IOrderService iOrderService;
	
	
	@GetMapping("/getAllOrders")
	public ResponseEntity<List<OrderDetails>>  listAllOrders() {
		List<OrderDetails> orderDetails = iOrderService.listAllOrders();

		if(orderDetails.isEmpty()) {
			return new ResponseEntity("Order not available", HttpStatus.NOT_FOUND);
		}
		else {
			return new ResponseEntity<List<OrderDetails>>(orderDetails, HttpStatus.OK);
		}
	}

	
	@GetMapping("/viewOrderForAdmin")
	public List<OrderDetails> viewOrderForAdmin() {
		return iOrderService.viewOrderForAdmin();
	}
	
	
	
	@GetMapping("/viewOrderForCustomer")
	public List<OrderDetails> viewOrderForCustomer() {
		return iOrderService.viewOrderForCustomer();
	}
	
	
	
	@PostMapping("/addOrder")
	public OrderDetails addOrder(@RequestBody OrderDetails od) {
		return iOrderService.addOrder(od);
	}
	
	@PutMapping("/updateOrder")
	public OrderDetails updateOrder(@RequestBody OrderDetails od) {
		return iOrderService.updateOrder(od);
	}
	
	@GetMapping("/orderByBook")
	public ResponseEntity<List<OrderDetails>> viewOrderByBook() {
		List<OrderDetails> orderDetails = iOrderService.viewOrderByBook();
		if(orderDetails.isEmpty()) {
			return new ResponseEntity("Book is Not ordered till now", HttpStatus.NOT_FOUND);
		}
		else {
			return new ResponseEntity<List<OrderDetails>>(orderDetails, HttpStatus.OK);
		}
	}
	
	
	@PutMapping("/cancelOrder")
	public OrderDetails cancelOrder(@RequestBody OrderDetails od) {
		return iOrderService.cancelOrder(od);
	}
	
	
	
	@PostMapping("/listOrderByCustomer")
	public List<OrderDetails> listOrderByCustomer(@RequestBody Customer cs) {
		return iOrderService.listOrderByCustomer(cs);
	}
	
	
	@GetMapping("/listBestSellingBook")
	public Book listBestSellingBook() {
		return iOrderService.listBestSellingBook();
	}
	
	
	
	
	
	
	
	
	/*@GetMapping("/listBestSellingBook")
	public Integer listBestSellingBook() {
		return iOrderService.listBestSellingBook();
	}*/
	
	
	
	/*@Autowired
	IOrderRepository iOrderRepository;
	
	@GetMapping("/getall")
	List<OrderDetails> getOrderDetails() {
		return iOrderRepository.findAll();
	}
	
	// To create/INSERT new Student into database
	@PostMapping("/insertnew")
	OrderDetails insertOrderDetails(@RequestBody OrderDetails orderDetails) {
		return iOrderRepository.save(orderDetails);
	}
	
	// To search Student by Id
	@GetMapping("/{id}")
	public OrderDetails getOrderDetailsById(@PathVariable Integer id) {
		return iOrderRepository.findById(id).get();
		
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
	public void deleteOrderDetails(@PathVariable Integer id) {
		iOrderRepository.deleteById(id);
	}*/
	public IOrderService getiOrderService() {
		return iOrderService;
	}

	public void setiOrderService(IOrderService iOrderService) {
		this.iOrderService = iOrderService;
	}
	
}

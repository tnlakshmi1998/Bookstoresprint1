package com.cg.bookstore.controller;

import java.util.List;
import java.util.Optional;

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

import com.cg.bookstore.entities.Book;
import com.cg.bookstore.entities.Customer;
import com.cg.bookstore.entities.Review;
import com.cg.bookstore.service.IReviewService;

@RestController
@RequestMapping("/api/v1/review")
public class ReviewController {

	@Autowired
	IReviewService iReviewService;

	@PostMapping(path = "/addReview")
	public ResponseEntity<Review> addReview(@RequestBody Review rev) {
		Optional<Review> optional = iReviewService.addReview(rev);
		return new ResponseEntity<Review>(optional.get(), HttpStatus.OK);
	}

	@DeleteMapping(path = "/deleteReview")
	public ResponseEntity<List<Review>> deleteReview(@RequestBody Review rev) {
		List<Review> list = iReviewService.deleteReview(rev);
		return new ResponseEntity<List<Review>>(list, HttpStatus.OK);
	}

	@GetMapping(path = "/listAllReviews")
	public ResponseEntity<List<Review>> listAllReviews() {
		List<Review> list = iReviewService.listAllReviews();
		if (list.isEmpty()) {
			return new ResponseEntity("Sorry! Review not available!", HttpStatus.NOT_FOUND);
		} else {
			return new ResponseEntity<List<Review>>(list, HttpStatus.OK);
		}
	}

	@PostMapping(path = "/viewReview")
	public ResponseEntity<Review> viewReview(@RequestBody Review rev) {
		Review list = iReviewService.viewReview(rev);
		return new ResponseEntity<Review>(list, HttpStatus.OK);
	}

	@PutMapping(path = "/updateReview")
	public ResponseEntity<Review> updateReview(@RequestBody Review rev) {
		Review optional = iReviewService.updateReview(rev);
		return new ResponseEntity<Review>(optional, HttpStatus.OK);
	}
	
	
	@PutMapping(path = "/listAllReviewsByBook")
	public List<Review> listAllReviewsByBook(@RequestBody Book book) {
		return iReviewService.listAllReviewsByBook(book);
	}
	
	
	@PutMapping(path = "/listAllReviewsByCustomer")
	public List<Review> listAllReviewsByCustomer(@RequestBody Customer c) {
		
		return iReviewService.listAllReviewsByCustomer(c);
	}
	
	
	@GetMapping(path = "/listMostFavoredBooks")
	public Book listMostFavoredBooks() {
		
		return iReviewService.listMostFavoredBooks();
	}

	/*
	 * @Autowired IReviewRepository iReviewRepository;
	 * 
	 * @GetMapping("/getall") List<Review> getReview() { return
	 * iReviewRepository.findAll(); }
	 * 
	 * // To create/INSERT new Student into database
	 * 
	 * @PostMapping("/insertnew") Review insertReview(@RequestBody Review review) {
	 * return iReviewRepository.save(review); }
	 * 
	 * // To search Student by Id
	 * 
	 * @GetMapping("/{id}") public Review getReviewById(@PathVariable Integer id) {
	 * return iReviewRepository.findById(id).get();
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
	 * @DeleteMapping("/{id}") public void deleteReview(@PathVariable Integer id) {
	 * iReviewRepository.deleteById(id); }
	 */
}

package com.cg.bookstore.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cg.bookstore.entities.Book;
import com.cg.bookstore.exception.NoBookException;
import com.cg.bookstore.repository.IBookRepository;
import com.cg.bookstore.service.IBookService;

@RestController
@RequestMapping("/api/v1/book")
public class BookController {

	@Autowired
	  IBookService iBookService;
	  
	  @Autowired
	  IBookRepository iBookRepository;
	  
	  
	  @GetMapping(path = "/getall")
	  public ResponseEntity<List<Book>> getAllBooks() throws NoBookException {
	  
		  List<Book> list=iBookService.listAllBooks();
		  if(list.size()==0)
			  throw new NoBookException("Sorry, No Books are found");
		  else
		  return new ResponseEntity<List<Book>>(list, HttpStatus.OK);
	  }
	  
	  
	  @PostMapping(path="/createBook")
	  public ResponseEntity<Book> createBook(@RequestBody Book book) {
	  
		  Optional<Book> opi=iBookService.createBook(book);
		  return new ResponseEntity<Book>(opi.get(), HttpStatus.OK); 
	  }
	  
	  
	  @DeleteMapping(path="/deleteBook/{id}")
	  public ResponseEntity<List<Book>> deleteBook(@PathVariable Integer id) {
	  
		  List<Book> bookObj= iBookService.deleteBook(id);
		  return new ResponseEntity<List<Book>>(bookObj,HttpStatus.OK);
	  }
	  
	  
	  @GetMapping(path="/getBooksByCategory/{categoryId}")
	  public ResponseEntity<List<Book>> getBooksByCategory(@PathVariable Integer categoryId) throws NoBookException {
	  
		  List<Book> list=iBookRepository.findByCategory(categoryId);
		  if(list.size()==0)
			  throw new NoBookException("Sorry, No books found on this category");
		  else
		  return new ResponseEntity<List<Book>>(list, HttpStatus.OK);
	  }
	  
	  
	  @PutMapping(path="/editBook/{id}")
	  public ResponseEntity<Book> editBooks(@RequestBody Book book,@PathVariable Integer id) {
	  
		  Book bookObj=iBookService.editBook(book,id);
		  if(bookObj==null)
			  throw new NoBookException("Sorry, No books found to edit");
		  else
		  return new ResponseEntity<Book>(bookObj, HttpStatus.OK);
	  }
	  
	  
	  @GetMapping(path="/searchBooksByKeyWord/{keyword}")
	  public ResponseEntity<List<Book>> getBooksByKeyword(@PathVariable String keyword) throws NoBookException {
	   
	     List<Book> list=iBookRepository.search(keyword);
		  if(list.size()==0)
			  throw new NoBookException("Sorry, No books found on this Keyword");
		  else
		  return new ResponseEntity<List<Book>>(list, HttpStatus.OK);
	  } 
	  
	  
	  @GetMapping(path="/getBooksByTitle/{title}") 
	  public ResponseEntity<List<Book>> getBooksByTitle(@PathVariable String title) throws NoBookException {
	  
		  List<Book> list=iBookService.findByTitle(title);
		  if(list.size()==0)
			  throw new NoBookException("Sorry, No books found on this title");
		  else
		  return new ResponseEntity<List<Book>>(list, HttpStatus.OK);
	  }
	  
	  
	  @GetMapping(path="/getRecentlyAddedBooks") 
	  public ResponseEntity<List<Book>> getBooksByTitle() throws NoBookException {
	  
		  List<Book> list=iBookRepository.listRecentlyAdded();
		  if(list.size()==0)
			  throw new NoBookException("Sorry, No books found on this title");
		  else
		  return new ResponseEntity<List<Book>>(list, HttpStatus.OK);
	  }
	
	
	
/*	
	
	@Autowired
	IBookRepository iBookRepository;
	
	@GetMapping("/getall")
	List<Book> getBooks() {
		return iBookRepository.findAll();
	}
	
	// To create/INSERT new Student into database
	@PostMapping("/insertnew")
	Book insertBook(@RequestBody Book book) {
		return iBookRepository.save(book);
	}
	
	// To search Student by Id
	@GetMapping("/{id}")
	public Book getStudentById(@PathVariable Integer id) {
		return iBookRepository.findById(id).get();
		
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
	public void deleteBook(@PathVariable Integer id) {
		iBookRepository.deleteById(id);
	}
	
	*/
}

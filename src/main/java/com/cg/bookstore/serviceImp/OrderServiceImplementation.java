package com.cg.bookstore.serviceImp;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.TreeMap;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.bookstore.entities.Book;
import com.cg.bookstore.entities.Customer;
import com.cg.bookstore.entities.OrderDetails;
import com.cg.bookstore.exception.OrderDetailsEmptyException;
import com.cg.bookstore.exception.OrderDetailsNotFoundException;
import com.cg.bookstore.exception.OrderNotFoundForAdminException;
import com.cg.bookstore.exception.OrderNotFoundForCustomerException;
import com.cg.bookstore.repository.IBookRepository;
import com.cg.bookstore.repository.IOrderRepository;
import com.cg.bookstore.service.IOrderService;

@Service
public class OrderServiceImplementation implements IOrderService{

	@Autowired
	private IOrderRepository iOrderRepository;
	
	@Autowired
	private IBookRepository iBookRepository;
	
	
	@Override
	@Transactional
	public List<OrderDetails> listAllOrders() throws OrderDetailsEmptyException {
		
		List<OrderDetails> orderDetails = iOrderRepository.findAll();
		
		if(orderDetails.isEmpty()) {
			throw new OrderDetailsEmptyException("Sorry, Order Details Is Empty...!");
		}
		else {
			return orderDetails;
		}
	}

	
	
	public List<OrderDetails> listOrderByCustomer(Customer cs) {
		List<OrderDetails> temp = iOrderRepository.findAll();
		List<OrderDetails> orderDetails = new ArrayList<>();
		for(OrderDetails tp:temp) {
			if(tp.getBookOrder().getCustomer().getCustomerId()==cs.getCustomerId()) {
				orderDetails.add(tp);
			}
		}
		return orderDetails;
	}
	
	
	
	public List<OrderDetails> viewOrderForCustomer() throws OrderNotFoundForCustomerException {
		List<OrderDetails> temporary = iOrderRepository.findAll();
		List<OrderDetails> orderDetails = new ArrayList<>();
		String string="customer";
		
		for(OrderDetails temp:temporary) {
			if(temp.getBookOrder().getCustomer().getUserClass().getRole().toLowerCase().equals(string)) {
				 orderDetails.add(temp);
			}
		}
		if(orderDetails.isEmpty()) {
			throw new OrderNotFoundForCustomerException("Soory, Order Details Not Found For Customer...!");
		}
		else {
			return orderDetails;
		}
	}
	
	
	
	
	public List<OrderDetails> viewOrderForAdmin() throws OrderNotFoundForAdminException {
		
		List<OrderDetails> temporary = iOrderRepository.findAll();
		List<OrderDetails> orderDetails = new ArrayList<>();
		String string="admin";
		
		for(OrderDetails temp:temporary) {
			if(temp.getBookOrder().getCustomer().getUserClass().getRole().toLowerCase().equals(string)) {
				 orderDetails.add(temp);
			}
		}
		if(orderDetails.isEmpty()) {
			throw new OrderNotFoundForAdminException("Soory, Order Details Not Found For Admin...!");
		}
		else {
			return orderDetails;
		}
	}
	
	
	
	/*
	public List<OrderDetails> listOrderByCustomer(Customer cs) {
		
	}
	public OrderDetails viewOrderForCustomer(OrderDetails od) {
		
	}
	public OrderDetails viewOrderForAdmin(OrderDetails od) {
		
	}*/
	
	
	public OrderDetails cancelOrder(OrderDetails od) throws OrderDetailsNotFoundException {
		OrderDetails orderDetails = iOrderRepository.findById(od.getOrderDetailsId()).orElse(null);
		if(orderDetails==null) {
			throw new OrderDetailsNotFoundException("Sorry, You have entered wrong order details to cancel the order...!");
		}
		else {
			orderDetails.getBookOrder().setStatus("Canceled");
			orderDetails.getBookOrder().setOrderDate(null);
			orderDetails.getBookOrder().setOrderTotal(0);
			orderDetails.getBookOrder().setPaymentMethod(null);
			orderDetails.getBookOrder().setRecipientName(null);
			orderDetails.getBookOrder().setRecipientPhone(null);
			orderDetails.getBookOrder().setCustomer(null);
			orderDetails.getBookOrder().setShippingAddress(null);
			orderDetails.setBook(null);
			orderDetails.setQuantity(0);
			orderDetails.setSubtotal(0);
			iOrderRepository.save(orderDetails);
			
			return iOrderRepository.findById(od.getOrderDetailsId()).orElse(null);
		}
	} 
	
	 
	
	@Override
	@Transactional
	public OrderDetails addOrder(OrderDetails od) {
		return iOrderRepository.save(od);
	}
	
	
	
	public OrderDetails updateOrder(OrderDetails od) throws OrderDetailsNotFoundException {
		Optional<OrderDetails> orderdDetails = iOrderRepository.findById(od.getOrderDetailsId());
		if(orderdDetails.isPresent())
			return iOrderRepository.save(od);
		else
			throw new OrderDetailsNotFoundException("Sorry, Order Not Found...!");
	}
	
	
	
	
	public List<OrderDetails> viewOrderByBook() throws OrderDetailsEmptyException {
		
		List<OrderDetails> orderDetails = iOrderRepository.findByOrderByBook();

		if(orderDetails.isEmpty()) {
			throw new OrderDetailsEmptyException("Sorry, Order Details Is Empty...!");
		}
		else {
			return orderDetails;
		}
	}
	
	
	
	
	
/*	public List<Book> listBestSellingBook() throws OrderDetailsEmptyException {
		List<OrderDetails> orderDetails = iOrderRepository.findAll();
		List<Book> books = new ArrayList<>();
		List<Book> maxToMinBooks = new ArrayList<>();
		
		for(OrderDetails od: orderDetails) {
			books.add(od.getBook());
		}
		List<Integer> bookIds = books.stream().map(n -> n.getBookId()).collect(Collectors.toList());
		List<Integer> distinctBookIds = bookIds.stream().distinct().collect(Collectors.toList());
		Map<Book, Long> counts = new LinkedHashMap<>();
		Map<Long, Book> sorted = new TreeMap<>(Collections.reverseOrder());
		
		for(Integer dbi:distinctBookIds) {
			Long count = bookIds.stream().map(n -> n==dbi).count(); 
			Book book = books.stream().filter(n -> n.getBookId()==dbi).collect(Collectors.toList()).get(0);
			counts.put(book, count);
		}
		for(Entry<Book, Long> bookMap:counts.entrySet()) {
			sorted.put(bookMap.getValue(), bookMap.getKey());
		}
		Collection<Book> reverseBooks = sorted.values();
		books.clear();
		books.addAll(reverseBooks);
		
		if(books.isEmpty()) {
			throw new OrderDetailsEmptyException("Sorry, Till now book has been not sold...!");
		}
		else {
			return books;
		}
	}
				*/
	
	public Book listBestSellingBook() throws OrderDetailsEmptyException {
		
		Book book = iBookRepository.findById(iOrderRepository.listBestSellingBook()).get();
		return book;
	}
	
	
	
	
	public IOrderRepository getiOrderRepository() {
		return iOrderRepository;
	}

	public void setiOrderRepository(IOrderRepository iOrderRepository) {
		this.iOrderRepository = iOrderRepository;
	}
	
	
}

package com.cg.bookstore.service;

import java.util.List;

import com.cg.bookstore.entities.Book;
import com.cg.bookstore.entities.Customer;
import com.cg.bookstore.entities.OrderDetails;

public interface IOrderService {

	public List<OrderDetails> listAllOrders();
	
	
	public List<OrderDetails> listOrderByCustomer(Customer cs);
	
	
	public List<OrderDetails> viewOrderForCustomer();
	
	
	public List<OrderDetails> viewOrderForAdmin();
	
	
	
	/*public List<OrderDetails> listOrderByCustomer(Customer cs);
	public OrderDetails viewOrderForCustomer(OrderDetails od);
	public OrderDetails viewOrderForAdmin(OrderDetails od);    */
	
	public OrderDetails cancelOrder(OrderDetails od);  
	
	
	
	public OrderDetails addOrder(OrderDetails od);
	public OrderDetails updateOrder(OrderDetails od);
	public List<OrderDetails> viewOrderByBook();
	public Book listBestSellingBook(); 

}

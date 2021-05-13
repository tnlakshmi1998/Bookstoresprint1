package com.cg.bookstore.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cg.bookstore.entities.OrderDetails;

@Repository
public interface IOrderRepository extends JpaRepository<OrderDetails, Integer>{

	@Query("SELECT od FROM  OrderDetails od")
	public List<OrderDetails> listAllOrders();
	
	public List<OrderDetails> findByOrderByBook();
	
	@Query(value = "SELECT book_id FROM review GROUP BY book_id ORDER BY count(*) desc LIMIT 1", nativeQuery = true)
	public int listBestSellingBook();
	
//	public List<OrderDetails> findByOrderByCustomer();
	
	//@Query(value = "SELECT book_id FROM review WHERE rating IN (SELECT max(rating) FROM review)", nativeQuery = true)
	//public List<Book> listBestSellingBook();
	
	
	
	
	
	
	
	
	/*public List<Book> findBestSellingBook(Integer book_id);
	
	public List<OrderDetails> listOrderByCustomer(Customer cs);
	public OrderDetails viewOrderForCustomer(OrderDetails od);
	public OrderDetails viewOrderForAdmin(OrderDetails od);
	public OrderDetails cancelOrder(OrderDetails od);  */
	/*@Query("INSERT INTO OrderDetails ")
	public OrderDetails addOrder(OrderDetails od);
	public OrderDetails addOrder(OrderDetails od);
	/*public OrderDetails updateOrder(OrderDetails od);
	public List<OrderDetails> viewOrderByBook(Book book);
	public List<Book> listBestSellingBook();*/

	
	
	
	
}

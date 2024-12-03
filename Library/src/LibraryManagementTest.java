import static org.junit.Assert.*;
import org.junit.Test;
import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;

public class LibraryManagementTest {

	@Test
	// Test method to test the Book class works as expected.
	public void testBookId() 
	{
		try
		{
			// Validate the ID of boundary case 100
			Book book1 = new Book (100, "Book 1");
			assertEquals("Book 1 should have a ID of 100 but isn't.", 100, book1.getId());
			assertTrue("The ID of Book 1 should be valid but isn't.", book1.isValidId(book1.getId()));
			
			// Validate the ID of boundary case 999
			Book book2 = new Book (999, "Book 2");
			assertEquals( "Book 2 should have a ID of 999 but isn't.", 999, book2.getId());
			assertTrue("The ID of Book 2 should be valid but isn't.", book2.isValidId(book2.getId()));
			
			// Validate the ID of boundary case 1000
			Book book3 = new Book (1000, "Book 3");
			fail("Software is accepting not valid ID.");
			
			// Make sure the program does not accept an ID lower than 100
			Book book4 = new Book(25, "Book 4");
			fail("Software is accepting ID less than 100.");
			
			// Make sure the program does not accept an ID higher than 999
			Book book5 = new Book(7500, "Book 5");
			fail("Software is accepting ID greater than 999.");
		}catch (Exception e)
		{
			// Ensure that the correct exception message is thrown for invalid IDs.
			assertEquals("ID is not valid.", e.getMessage());
		}
		
	}
	
	@Test
	// Test method to validate that book borrowing and returning works as expected
	public void testBorrowReturn() throws Exception
	{
		// Instantiate a Book object and Member object.
		Book book = new Book(100, "CS");
		Member member = new Member(1111, "John");
		
		// Make sure that the book is available
		assertTrue("Book is not available.", book.isAvailable());
		
		// Ensure that borrowing is successful when the book is available
		Transaction transaction = Transaction.getTransaction();
		boolean successfulBorrow = transaction.borrowBook(book, member);
		assertTrue("Borrowing was not successful", successfulBorrow);
		assertFalse("The book is still available", book.isAvailable());
			
		// Ensure that borrowing is not successful when the book is not available
		boolean successfulBorrow2 = transaction.borrowBook(book, member);
		assertFalse("The book was able to be borrowed twice.", successfulBorrow2);
			
		// Ensure that returning is successful when the book is borrowed
		boolean successfulReturn = transaction.returnBook(book, member);
		assertTrue("The book was not returned successfully.", successfulReturn);
		assertTrue("The book is not avaiable.", book.isAvailable());
			
		// Ensure that returning is not successful when the book is available.
		boolean successfulReturn2 = transaction.returnBook(book, member);
		assertFalse("The book was returned twice.", successfulReturn2);
	}
	
	@Test
	// Test method to validate that the Transaction class enforces Singleton behavior
	public void testSingletonTransaction() throws Exception
	{
		// Return the constructor of the Transaction class
		Constructor<Transaction> constructor = Transaction.class.getDeclaredConstructor();
		
		// Get the constructor's modifier
		int i = constructor.getModifiers();
		
		// Ensure that constructor's modifier is equal to Modifier.PRIVATE
		assertEquals("The Transaction class does not implement Singleton behavior.", i, Modifier.PRIVATE);
	}
}

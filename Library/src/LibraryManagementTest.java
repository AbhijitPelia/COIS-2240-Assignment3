import static org.junit.Assert.*;

import org.junit.Test;

public class LibraryManagementTest {

	@Test
	// Test method to test the Book class for various cases of valid and non-valid IDs.
	public void testBookId() 
	{
		try
		{
			// Validate the ID of boundary case 100
			Book book1 = new Book (100, "Book 1");
			assertEquals("Book 1 should have a ID of 100.", 100, book1.getId());
			assertTrue("The ID of Book 1 should be valid.", book1.isValidId(book1.getId()));
			
			// Validate the ID of boundary case 999
			Book book2 = new Book (999, "Book 2");
			assertEquals( "Book 2 should have a ID of 999.", 999, book2.getId());
			assertTrue("The ID of Book 2 should be valid.", book2.isValidId(book2.getId()));
			
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

}

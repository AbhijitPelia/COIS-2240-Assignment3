import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;

public class Transaction {

	// Private static singleton instance
	private static Transaction instance;
	
	// Private constructor
	private Transaction() {};
	
	// Method to retrieve instance 
	public static Transaction getTransaction() 
	{
		if (instance == null)
		{
			instance = new Transaction();
		}
		return instance;
	}
	
	// Perform the borrowing of a book
	public boolean borrowBook(Book book, Member member) {
		if (book.isAvailable()) {
			book.borrowBook();
			member.borrowBook(book);
			String transactionDetails = getCurrentDateTime() + " - Borrowing: " + member.getName() + " borrowed "
					+ book.getTitle();
			System.out.println(transactionDetails);
			saveTransaction(transactionDetails);
			return true;
		} else {
			System.out.println("The book is not available.");
			return false;
		}
	}

	// Perform the returning of a book
	public boolean returnBook(Book book, Member member) {
		if (member.getBorrowedBooks().contains(book)) {
			member.returnBook(book);
			book.returnBook();
			String transactionDetails = getCurrentDateTime() + " - Returning: " + member.getName() + " returned "
					+ book.getTitle();
			System.out.println(transactionDetails);
			saveTransaction(transactionDetails);
			return true;
		} else {
			System.out.println("This book was not borrowed by the member.");
			return false;
		}
	}

	// Get the current date and time in a readable format
	private String getCurrentDateTime() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return sdf.format(new Date());
	}
	
	// Save the transactions into a text file
	private void saveTransaction(String transactionDetails) 
	{
		try (BufferedWriter writer = new BufferedWriter(new FileWriter("transactions.txt", true)))
			{
				// Write the transaction details into the text file
				writer.write(transactionDetails); 
				writer.newLine();
			} catch (IOException e)
			{
				System.out.println("Failed to save transaction");
			}
	}
	
	// Display the transaction history
	public void displayTransactionHistory()
	{
		
		try 
		{
			File file = new File ("transactions.txt");
			Scanner scanner = new Scanner(file);
			
			System.out.println("");
			
			// Use the scanner tool to read each line of the file and display transactions
			while (scanner.hasNextLine())
			{
				String transaction = scanner.nextLine();
				System.out.println(transaction);
			}
			
			scanner.close();
			
		} catch (FileNotFoundException e) // Catch the exception if the file does not exist
		{
			System.out.println("Sorry, no transaction history to display.");
		}
	}
}
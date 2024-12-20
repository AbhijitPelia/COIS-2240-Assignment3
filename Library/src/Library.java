import java.util.ArrayList;
import java.util.List;

public class Library {
	private List<Member> members = new ArrayList<Member>();
	private List<Book> books = new ArrayList<Book>();

	// Add a new member to the library
	public boolean addMember(Member member) {
		// Check if the member already exists 
		if (findMemberById(member.getId()) == null)
		{
			// Add member and return true if the member does not exist
			members.add(member);
			return true;
		}
		else
		{
			return false;
		}
	}

	// Add a new book to the library
	public boolean addBook(Book book) {
		// Check if the book already exists
		if (findBookById(book.getId()) == null)
		{
			// Add book and return true if the book does not exist
			books.add(book);
			return true; 
		}
		else
		{
			return false;
		}
	}

	// Find a member by ID
	public Member findMemberById(int id) {
		for (Member member : members) {
			if (member.getId() == id) {
				return member;
			}
		}
		return null;
	}

	// Find a book by ID
	public Book findBookById(int id) {
		for (Book book : books) {
			if (book.getId() == id) {
				return book;
			}
		}
		return null;
	}

	// Get the list of members
	public List<Member> getMembers() {
		return members;
	}

	// Get the list of books
	public List<Book> getBooks() {
		return books;
	}
}

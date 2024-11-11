package JunitMockito.testing;

public class LibraryService {
	private final BookRepository bookRepository;

	public LibraryService(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}

	public String borrowBook(Long bookId) {

		Book book = bookRepository.findById(bookId).orElseThrow(() -> new RuntimeException("Book not found"));

		if ("Borrowed".equals(book.getStatus())) {
			return "Book is already borrowed.";
		}

		book.setStatus("Borrowed");
		bookRepository.save(book);
		return "Book borrowed successfully.";
	}

	public String returnBook(Long bookId) {

		Book book = bookRepository.findById(bookId).orElseThrow(() -> new RuntimeException("Book not found"));

		if ("Available".equals(book.getStatus())) {
			return "Book was not borrowed.";
		}

		book.setStatus("Available");
		bookRepository.save(book);
		return "Book returned successfully.";
	}
}

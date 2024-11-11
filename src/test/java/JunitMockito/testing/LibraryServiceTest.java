package JunitMockito.testing;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

public class LibraryServiceTest {

	@Mock
	private BookRepository bookRepository;

	@InjectMocks
	private LibraryService libraryService;

	private Book book;

	@Before
	public void setup() {
		MockitoAnnotations.openMocks(this);
		book = new Book(1L, "The Great Gatsby", "Available");
	}

	@Test
	public void testBorrowBook_AvailableBook() {

		when(bookRepository.findById(1L)).thenReturn(Optional.of(book));

		String result = libraryService.borrowBook(1L);

		assertEquals("Book borrowed successfully.", result);
		assertEquals("Borrowed", book.getStatus());

		verify(bookRepository, times(1)).save(book);
	}

	@Test
	public void testBorrowBook_AlreadyBorrowed() {

		book.setStatus("Borrowed");
		when(bookRepository.findById(1L)).thenReturn(Optional.of(book));

		String result = libraryService.borrowBook(1L);

		assertEquals("Book is already borrowed.", result);

		verify(bookRepository, never()).save(book);
	}

	@Test
	public void testReturnBook_BorrowedBook() {

		book.setStatus("Borrowed");
		when(bookRepository.findById(1L)).thenReturn(Optional.of(book));

		String result = libraryService.returnBook(1L);

		assertEquals("Book returned successfully.", result);
		assertEquals("Available", book.getStatus());

		verify(bookRepository, times(1)).save(book);
	}

	@Test
	public void testReturnBook_NotBorrowed() {

		book.setStatus("Available");
		when(bookRepository.findById(1L)).thenReturn(Optional.of(book));

		String result = libraryService.returnBook(1L);

		assertEquals("Book was not borrowed.", result);

		verify(bookRepository, never()).save(book);
	}

	@Test
	public void testBorrowBook_BookNotFound() {

		when(bookRepository.findById(1L)).thenReturn(Optional.empty());

		try {
			libraryService.borrowBook(1L);
			fail("Expected RuntimeException was not thrown.");
		} catch (RuntimeException e) {
			assertEquals("Book not found", e.getMessage());
		}

		verify(bookRepository, never()).save(any(Book.class));
	}

	@Test
	public void testReturnBook_BookNotFound() {

		when(bookRepository.findById(1L)).thenReturn(Optional.empty());

		try {
			libraryService.returnBook(1L);
			fail("Expected RuntimeException was not thrown.");
		} catch (RuntimeException e) {
			assertEquals("Book not found", e.getMessage());
		}

		verify(bookRepository, never()).save(any(Book.class));
	}
}

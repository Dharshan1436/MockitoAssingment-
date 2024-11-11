package JunitMockito.testing;

import java.util.Optional;

public interface BookRepository {
    Optional<Book> findById(Long bookId);
    Book save(Book book);
}

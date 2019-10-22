package managesys.common;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import managesys.model.Book;
import managesys.model.Category;
import managesys.model.Format;
import managesys.repository.BookRepository;

@Component
@ConditionalOnProperty(prefix = "extension.test.generator", name = "enabled", matchIfMissing = false)
public class TestDataGenerator {

    @Autowired
    private BookRepository bookRepository;

    private static final int SIZE = 7;

    @PostConstruct
    @Transactional
    public void initialize() {
        for (int i = 0; i < SIZE; i++) {
            Category c = Category.findById(bookRepository, (i + 1) % 3 + 1);
            Format f = Format.findById(bookRepository, (i + 1) % 2 + 1);
            Book b = new Book("Test_" + i, "123-234-567-" + i, c, f);
            b.save(bookRepository);
        }
    }
}

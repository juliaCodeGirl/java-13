package ru.netology.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Book;
import ru.netology.domain.Product;
import ru.netology.domain.Smartphone;
import ru.netology.exception.NotFoundException;
import ru.netology.repository.ProductRepository;

import static org.junit.jupiter.api.Assertions.*;

class ProductManagerTest {
    private ProductRepository repository = new ProductRepository();
    private ProductManager manager = new ProductManager(repository);

    private Book first = new Book(1, "Война и Мир", 1000, "Толстой");
    private Smartphone second = new Smartphone(2, "Redmi", 8000, "Xiaomi");
    private Book third = new Book(3, "Galaxy", 1200, "Коршунов");
    private Smartphone fourth = new Smartphone(4, "Phone", 9000, "Nokia");

    @BeforeEach
    void setUp() {
        manager = new ProductManager(repository);
        manager.add(first);
        manager.add(second);
        manager.add(third);
        manager.add(fourth);
    }

    @Test
    void shouldFind() {
        manager.findById(3);
        Product expected = third;
        Product actual = manager.findById(3);
        assertEquals(expected, actual);
    }

    @Test
    void shouldRemove() {
        manager.removeById(3);
        Product[] actual = manager.findAll();
        Product[] expected = new Product[]{first,second,fourth};
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldReturnNullIfNotExisting() {
        repository.findById(8);
        Product actual = manager.findById(8);
        assertNull(actual);
    }

    @Test
    void shouldNotRemoveIfNotExisting() {
        assertThrows(NotFoundException.class, () -> manager.removeById(5));
    }
}
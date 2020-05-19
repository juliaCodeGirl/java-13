package ru.netology.repository;

import org.junit.jupiter.api.Test;
import ru.netology.domain.Book;
import ru.netology.domain.Product;
import ru.netology.domain.Smartphone;
import ru.netology.exception.NotFoundException;

import static org.junit.jupiter.api.Assertions.*;

class ProductRepositoryTest {
    private ProductRepository repository = new ProductRepository();

    private Book first = new Book(1, "Война и Мир", 1000, "Толстой");
    private Smartphone second = new Smartphone(2, "Redmi", 8000, "Xiaomi");
    private Book third = new Book(3, "Galaxy", 1200, "Коршунов");
    private Smartphone fourth = new Smartphone(4, "Phone", 9000, "Nokia");

    @Test
    void shouldSave() {
        repository.save(first);
        Product[] expected = new Product[]{first};
        Product[] actual = repository.findAll();
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldFind() {
        repository.save(first);
        repository.save(second);
        repository.save(third);
        repository.save(fourth);
        repository.findById(3);
        Product expected = third;
        Product actual = repository.findById(3);
        assertEquals(expected, actual);
    }

    @Test
    void shouldRemove() {
        repository.save(first);
        repository.save(second);
        repository.save(third);
        repository.save(fourth);
        repository.removeById(2);
        Product[] actual = repository.findAll();
        Product[] expected = new Product[]{first, third, fourth};
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldReturn() {
        repository.save(first);
        repository.save(fourth);
        Product[] expected = new Product[]{first, fourth};
        Product[] actual = repository.findAll();
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldReturnNull() {
        repository.save(first);
        repository.save(second);
        repository.save(third);
        repository.save(fourth);
        repository.findById(8);
        Product actual = repository.findById(8);
        assertNull(actual);
    }

    @Test
    void shouldReturnEmpty() {
        Product[] expected = new Product[]{};
        Product[] actual = repository.findAll();
        assertArrayEquals(expected, actual);
    }

    @Test
    void shouldNotRemoveIfNotExisting() {
        repository.save(first);
        repository.save(second);
        repository.save(third);
        repository.save(fourth);
        assertThrows(NotFoundException.class, () -> repository.removeById(5));
    }
}

package ru.netology.manager;

import ru.netology.domain.Book;
import ru.netology.domain.Product;
import ru.netology.domain.Smartphone;
import ru.netology.repository.ProductRepository;

public class ProductManager {
    private ProductRepository repository;

    public ProductManager(ProductRepository repository) {
        this.repository = repository;
    }

    public Product[] findAll() {
        return repository.findAll();
    }

    public void add(Product item) {
        repository.save(item);
    }

    public Product findById(int id) {
        return repository.findById(id);
    }

    public void removeById(int id) {
        repository.removeById(id);
    }
}

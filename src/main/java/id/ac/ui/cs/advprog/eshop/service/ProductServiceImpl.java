package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.exceptions.NegativeProductQuantityException;
import id.ac.ui.cs.advprog.eshop.exceptions.NullProductIdException;
import id.ac.ui.cs.advprog.eshop.exceptions.NullProductNameException;
import id.ac.ui.cs.advprog.eshop.exceptions.ProductNotFoundException;
import id.ac.ui.cs.advprog.eshop.exceptions.ZeroLengthProductIdException;
import id.ac.ui.cs.advprog.eshop.exceptions.ZeroLengthProductNameException;
import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class ProductServiceImpl implements ProductService {
    private static AtomicLong idCounter = new AtomicLong(1);

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product create(Product product) {
        if (product == null)
            throw new IllegalArgumentException("product is null");

        String productName = product.getProductName();
        int productQuantity = product.getProductQuantity();

        if (productName == null)
            throw new NullProductNameException();
        if (productName.length() == 0)
            throw new ZeroLengthProductNameException();
        if (productQuantity < 0)
            throw new NegativeProductQuantityException();

        String productId = Long.toString(idCounter.getAndIncrement());
        product.setProductId(productId);

        productRepository.create(product);

        return product;
    }

    @Override
    public List<Product> findAll() {
        Iterator<Product> productIterator = productRepository.findAll();
        List<Product> allProduct = new ArrayList<>();
        productIterator.forEachRemaining(allProduct::add);
        return allProduct;
    }

    @Override
    public Product findOne(String productId) {
        if (productId == null)
            throw new IllegalArgumentException("productId is null");

        Product product;
        try {
            product = productRepository.findOne(productId);
        } catch (ProductNotFoundException exception) {
            throw new ProductNotFoundException(exception);
        }

        return product;
    }

    @Override
    public Product delete(Product product) {
        if (product == null)
            throw new IllegalArgumentException("product is null");

        String productId = product.getProductId();

        if (productId == null)
            throw new NullProductIdException();
        if (productId.length() == 0)
            throw new ZeroLengthProductIdException();

        Product productFromRepo;
        try {
            productFromRepo = productRepository.delete(product);
        } catch (ProductNotFoundException exception) {
            throw new ProductNotFoundException(exception);
        }

        return productFromRepo;
    }

    @Override
    public Product edit(Product product) {
        if (product == null)
            throw new IllegalArgumentException("product is null");

        String productId = product.getProductId();
        String productName = product.getProductName();
        int productQuantity = product.getProductQuantity();

        if (productId == null)
            throw new NullProductIdException();
        if (productName == null)
            throw new NullProductNameException();
        if (productId.length() == 0)
            throw new ZeroLengthProductIdException();
        if (productName.length() == 0)
            throw new ZeroLengthProductNameException();
        if (productQuantity < 0)
            throw new NegativeProductQuantityException();

        Product productFromRepo;
        try {
            productFromRepo = productRepository.edit(product);
        } catch (ProductNotFoundException exception) {
            throw new ProductNotFoundException(exception);
        }

        return productFromRepo;
    }
}

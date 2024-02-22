package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.exceptions.*;
import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService {
    private static final String NULL_PRODUCT_EXCEPTION_MSG = "product is null";

    private ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product create(Product product) {
        if (product == null)
            throw new IllegalArgumentException(NULL_PRODUCT_EXCEPTION_MSG);

        String productId = product.getProductId();
        String productName = product.getProductName();
        int productQuantity = product.getProductQuantity();

        if (productName == null)
            throw new NullItemNameException();
        if (productName.length() == 0)
            throw new ZeroLengthItemNameException();
        if (productQuantity < 0)
            throw new NegativeItemQuantityException();

        if (productId == null) {
            UUID uuid = UUID.randomUUID();
            product.setProductId(uuid.toString());
        }

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
    public Product findById(String productId) {
        if (productId == null)
            throw new IllegalArgumentException("productId is null");

        Product product;
        try {
            product = productRepository.findById(productId);
        } catch (ItemNotFoundException exception) {
            throw new ItemNotFoundException(exception);
        }

        return product;
    }

    @Override
    public Product deleteById(String productId) {
        if (productId == null)
            throw new IllegalArgumentException(NULL_PRODUCT_EXCEPTION_MSG);

        if (productId.length() == 0)
            throw new ZeroLengthItemIdException();

        Product productFromRepo;
        try {
            productFromRepo = productRepository.deleteById(productId);
        } catch (ItemNotFoundException exception) {
            throw new ItemNotFoundException(exception);
        }

        return productFromRepo;
    }

    @Override
    public Product update(Product updatedProduct) {
        if (updatedProduct == null)
            throw new IllegalArgumentException(NULL_PRODUCT_EXCEPTION_MSG);

        String updatedProductId = updatedProduct.getProductId();
        String updatedProductName = updatedProduct.getProductName();
        int updatedProductQuantity = updatedProduct.getProductQuantity();

        if (updatedProductId == null)
            throw new NullItemIdException();
        if (updatedProductName == null)
            throw new NullItemNameException();
        if (updatedProductId.length() == 0)
            throw new ZeroLengthItemIdException();
        if (updatedProductName.length() == 0)
            throw new ZeroLengthItemNameException();
        if (updatedProductQuantity < 0)
            throw new NegativeItemQuantityException();

        Product product;
        try {
            product = productRepository.update(updatedProduct);
        } catch (ItemNotFoundException exception) {
            throw new ItemNotFoundException(exception);
        }

        return product;
    }
}

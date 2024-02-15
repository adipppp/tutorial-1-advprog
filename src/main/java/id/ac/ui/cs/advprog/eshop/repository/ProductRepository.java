package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.exceptions.ProductNotFoundException;
import id.ac.ui.cs.advprog.eshop.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Repository
public class ProductRepository {
    private List<Product> productData;

    public ProductRepository() {
        this.productData = new ArrayList<>();
    }

    public Product create(Product product) {
        productData.add(product);
        return product;
    }

    public Iterator<Product> findAll() {
        return productData.iterator();
    }
    
    public Product findOne(String productId) {
        boolean productIsFound = false;

        Iterator<Product> productIterator = findAll();
        Product product = null;
        while (productIterator.hasNext()) {
            product = productIterator.next();
            if (productId.equals(product.getProductId())) {
                productIsFound = true;
                break;
            }
        }

        if (!productIsFound)
            throw new ProductNotFoundException();

        return product;
    }

    public Product delete(Product product) {
        boolean productIsFound = false;

        Iterator<Product> productIterator = findAll();
        Product productFromRepo = null;
        while (productIterator.hasNext()) {
            productFromRepo = productIterator.next();
            String productId1 = product.getProductId();
            String productId2 = productFromRepo.getProductId();
            if (productId1.equals(productId2)) {
                productIterator.remove();
                productIsFound = true;
                break;
            }
        }

        if (!productIsFound)
            throw new ProductNotFoundException();

        return productFromRepo;
    }

    public Product edit(Product product) {
        Product productFromRepo;
        try {
            productFromRepo = findOne(product.getProductId());
        } catch (ProductNotFoundException exception) {
            throw new ProductNotFoundException(exception);
        }

        productFromRepo.setProductName(product.getProductName());
        productFromRepo.setProductQuantity(product.getProductQuantity());

        return productFromRepo;
    }
}

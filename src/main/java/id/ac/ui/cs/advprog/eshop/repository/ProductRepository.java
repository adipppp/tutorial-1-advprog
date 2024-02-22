package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.exceptions.ItemNotFoundException;
import id.ac.ui.cs.advprog.eshop.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Repository
public class ProductRepository implements ItemRepository<Product> {
    private List<Product> productData;

    public ProductRepository() {
        productData = new ArrayList<>();
    }

    @Override
    public Product create(Product product) {
        productData.add(product);
        return product;
    }

    @Override
    public Iterator<Product> findAll() {
        return productData.iterator();
    }
    
    @Override
    public Product findById(String productId) {
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
            throw new ItemNotFoundException();

        return product;
    }

    @Override
    public Product deleteById(String productId) {
        boolean productIsFound = false;

        Iterator<Product> productIterator = findAll();
        Product productFromRepo = null;
        while (productIterator.hasNext()) {
            productFromRepo = productIterator.next();
            String productId1 = productId;
            String productId2 = productFromRepo.getProductId();
            if (productId1.equals(productId2)) {
                productIterator.remove();
                productIsFound = true;
                break;
            }
        }

        if (!productIsFound)
            throw new ItemNotFoundException();

        return productFromRepo;
    }

    @Override
    public Product update(Product updatedProduct) {
        Product productFromRepo;
        try {
            productFromRepo = findById(updatedProduct.getProductId());
        } catch (ItemNotFoundException exception) {
            throw new ItemNotFoundException(exception);
        }

        productFromRepo.setProductName(updatedProduct.getProductName());
        productFromRepo.setProductQuantity(updatedProduct.getProductQuantity());

        return productFromRepo;
    }
}

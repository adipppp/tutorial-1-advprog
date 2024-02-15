package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.exceptions.*;
import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.ProductRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProductServiceImplTest {

    private AutoCloseable closeable;

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImpl productService;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
    }

    @AfterEach
    void cleanUp() {
        try {
            closeable.close();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    @Test
    void testCreate() {
        Product product = new Product();
        product.setProductId("46e4ce01-d7f8-4c50-811f-871ab409a05a");
        product.setProductName("Sendal Mas Faiz");
        product.setProductQuantity(2);
        
        Product returnedProduct = productService.create(product);

        assertEquals(product, returnedProduct);
    }

    @Test
    void testCreateIfArgumentIsNull() {
        assertThrows(IllegalArgumentException.class, () -> productService.create(null));
    }

    @Test
    void testCreateIfProductNameIsNull() {
        Product product = new Product();
        product.setProductQuantity(2);

        assertThrows(NullProductNameException.class, () -> productService.create(product));
    }

    @Test
    void testCreateIfProductNameHasZeroLength() {
        Product product = new Product();
        product.setProductName("");
        product.setProductQuantity(2);

        assertThrows(ZeroLengthProductNameException.class, () -> productService.create(product));
    }

    @Test
    void testCreateIfProductQuantityIsNegative() {
        Product product = new Product();
        product.setProductName("Sendal Mas Faiz");
        product.setProductQuantity(-1);

        assertThrows(NegativeProductQuantityException.class, () -> productService.create(product));
    }

    @Test
    void testFindAll() {
        Iterator<Product> productIteratorMock = new ArrayList<Product>().iterator();
        Mockito.when(productRepository.findAll()).thenReturn(productIteratorMock);

        List<Product> productList = productService.findAll();

        assertNotNull(productList);
    }

    @Test
    void testFindOne() {
        Product product1 = new Product();
        product1.setProductId("46e4ce01-d7f8-4c50-811f-871ab409a05a");
        product1.setProductName("Sendal Mas Faiz");
        product1.setProductQuantity(2);

        Mockito.when(productRepository.findOne(Mockito.any())).thenReturn(product1);

        Product product2 = new Product();
        product2.setProductId("46e4ce01-d7f8-4c50-811f-871ab409a05a");
        product2.setProductName("Sendal Mas Faiz");
        product2.setProductQuantity(2);

        Product returnedValue = null;
        try {
            returnedValue = productService.findOne(product2.getProductId());
        } catch (RuntimeException exception) {}

        assertNotNull(returnedValue);
        assertEquals(product1.getProductId(), returnedValue.getProductId());
        assertEquals(product1.getProductName(), returnedValue.getProductName());
        assertEquals(product1.getProductQuantity(), returnedValue.getProductQuantity());
    }

    @Test
    void testFindOneIfArgumentIsNull() {
        assertThrows(IllegalArgumentException.class, () -> productService.findOne(null));
    }

    @Test
    void testFindOneIfNoSuchProduct() {
        String expectedMessage = "No such product in repository";

        Mockito.when(productRepository.findOne(Mockito.any()))
            .thenThrow(new ProductNotFoundException(expectedMessage));

        Product product = new Product();
        product.setProductId("46e4ce01-d7f8-4c50-811f-871ab409a05a");
        product.setProductName("Sendal Mas Faiz");
        product.setProductQuantity(2);

        assertThrows(ProductNotFoundException.class, () ->
            productService.findOne(product.getProductId()));
    }

    @Test
    void testDelete() {
        Product product1 = new Product();
        product1.setProductId("46e4ce01-d7f8-4c50-811f-871ab409a05a");
        product1.setProductName("Sendal Mas Faiz");
        product1.setProductQuantity(2);

        Mockito.when(productRepository.delete(Mockito.any())).thenReturn(product1);

        Product product2 = new Product();
        product2.setProductId("46e4ce01-d7f8-4c50-811f-871ab409a05a");
        product2.setProductName("Sendal Mas Faiz");
        product2.setProductQuantity(2);

        Product returnedValue = null;
        try {
            returnedValue = productService.delete(product2);
        } catch (RuntimeException exception) {}

        assertNotNull(returnedValue);
        assertEquals(product1.getProductId(), returnedValue.getProductId());
        assertEquals(product1.getProductName(), returnedValue.getProductName());
        assertEquals(product1.getProductQuantity(), returnedValue.getProductQuantity());
    }

    @Test
    void testDeleteIfArgumentIsNull() {
        assertThrows(IllegalArgumentException.class, () -> productService.delete(null));
    }

    @Test
    void testDeleteIfProductIdIsNull() {
        Product product = new Product();
        product.setProductName("Sendal Mas Faiz");
        product.setProductQuantity(2);

        assertThrows(NullProductIdException.class, () -> productService.delete(product));
    }

    @Test
    void testDeleteIfProductIdHasZeroLength() {
        Product product = new Product();
        product.setProductId("");
        product.setProductName("Sendal Mas Faiz");
        product.setProductQuantity(2);

        assertThrows(ZeroLengthProductIdException.class, () -> productService.delete(product));
    }

    @Test
    void testDeleteIfNoSuchProduct() {
        String expectedMessage = "No such product in repository";

        Mockito.when(productRepository.delete(Mockito.any()))
            .thenThrow(new ProductNotFoundException(expectedMessage));

        Product product = new Product();
        product.setProductId("46e4ce01-d7f8-4c50-811f-871ab409a05a");
        product.setProductName("Sendal Mas Faiz");
        product.setProductQuantity(2);

        assertThrows(ProductNotFoundException.class, () -> productService.delete(product));
    }

    @Test
    void testEdit() {
        Product product1 = new Product();
        product1.setProductId("46e4ce01-d7f8-4c50-811f-871ab409a05a");
        product1.setProductName("Sendal Mas Faiz");
        product1.setProductQuantity(2);

        Mockito.when(productRepository.edit(Mockito.any())).thenReturn(product1);

        Product product2 = new Product();
        product2.setProductId("46e4ce01-d7f8-4c50-811f-871ab409a05a");
        product2.setProductName("Sendal Mas Faiz");
        product2.setProductQuantity(2);

        Product returnedValue = null;
        try {
            returnedValue = productService.edit(product2);
        } catch (RuntimeException exception) {}

        assertNotNull(returnedValue);
        assertEquals(product1.getProductId(), returnedValue.getProductId());
        assertEquals(product1.getProductName(), returnedValue.getProductName());
        assertEquals(product1.getProductQuantity(), returnedValue.getProductQuantity());
    }

    @Test
    void testEditIfArgumentIsNull() {
        assertThrows(IllegalArgumentException.class, () -> productService.edit(null));
    }

    @Test
    void testEditIfProductIdIsNull() {
        Product product = new Product();
        product.setProductName("Sendal Mas Faiz");
        product.setProductQuantity(2);

        assertThrows(NullProductIdException.class, () -> productService.edit(product));
    }

    @Test
    void testEditIfProductIdHasZeroLength() {
        Product product = new Product();
        product.setProductId("");
        product.setProductName("Sendal Mas Faiz");
        product.setProductQuantity(2);

        assertThrows(ZeroLengthProductIdException.class, () -> productService.edit(product));
    }

    @Test
    void testEditIfProductNameIsNull() {
        Product product = new Product();
        product.setProductId("46e4ce01-d7f8-4c50-811f-871ab409a05a");
        product.setProductQuantity(2);

        assertThrows(NullProductNameException.class, () -> productService.edit(product));
    }

    @Test
    void testEditIfProductNameHasZeroLength() {
        Product product = new Product();
        product.setProductId("46e4ce01-d7f8-4c50-811f-871ab409a05a");
        product.setProductName("");
        product.setProductQuantity(2);

        assertThrows(ZeroLengthProductNameException.class, () -> productService.edit(product));
    }

    @Test
    void testEditIfProductQuantityIsNegative() {
        Product product = new Product();
        product.setProductId("46e4ce01-d7f8-4c50-811f-871ab409a05a");
        product.setProductName("Sendal Mas Faiz");
        product.setProductQuantity(-1);

        assertThrows(NegativeProductQuantityException.class, () -> productService.edit(product));
    }

    @Test
    void testEditIfNoSuchProduct() {
        String expectedMessage = "An error occured in ProductRepository";

        Mockito.when(productRepository.edit(Mockito.any()))
            .thenThrow(new ProductNotFoundException(expectedMessage));

        Product product = new Product();
        product.setProductId("46e4ce01-d7f8-4c50-811f-871ab409a05a");
        product.setProductName("Sendal Mas Faiz");
        product.setProductQuantity(2);

        assertThrows(ProductNotFoundException.class, () -> productService.edit(product));
    }
}

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
    void cleanUp() throws Exception {
        closeable.close();
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

        assertThrows(NullItemNameException.class, () -> productService.create(product));
    }

    @Test
    void testCreateIfProductNameHasZeroLength() {
        Product product = new Product();
        product.setProductName("");
        product.setProductQuantity(2);

        assertThrows(ZeroLengthItemNameException.class, () -> productService.create(product));
    }

    @Test
    void testCreateIfProductQuantityIsNegative() {
        Product product = new Product();
        product.setProductName("Sendal Mas Faiz");
        product.setProductQuantity(-1);

        assertThrows(NegativeItemQuantityException.class, () -> productService.create(product));
    }

    @Test
    void testCreateIfProductIdIsNull() {
        Product product = new Product();
        product.setProductId(null);
        product.setProductName("Sendal Mas Faiz");
        product.setProductQuantity(2);

        assertDoesNotThrow(() -> productService.create(product));
    }

    @Test
    void testCreateIfProductIdHasZeroLength() {
        Product product = new Product();
        product.setProductId("");
        product.setProductName("Sendal Mas Faiz");
        product.setProductQuantity(2);

        assertThrows(ZeroLengthItemIdException.class, () -> productService.create(product));
    }

    @Test
    void testFindAll() {
        Iterator<Product> productIteratorMock = new ArrayList<Product>().iterator();
        Mockito.when(productRepository.findAll()).thenReturn(productIteratorMock);

        List<Product> productList = productService.findAll();

        assertNotNull(productList);
    }

    @Test
    void testFindById() {
        Product product1 = new Product();
        product1.setProductId("46e4ce01-d7f8-4c50-811f-871ab409a05a");
        product1.setProductName("Sendal Mas Faiz");
        product1.setProductQuantity(2);

        Mockito.when(productRepository.findById(Mockito.any())).thenReturn(product1);

        Product product2 = new Product();
        product2.setProductId("46e4ce01-d7f8-4c50-811f-871ab409a05a");
        product2.setProductName("Sendal Mas Faiz");
        product2.setProductQuantity(2);

        Product returnedValue = null;
        try {
            returnedValue = productService.findById(product2.getProductId());
        } catch (RuntimeException exception) {}

        assertNotNull(returnedValue);
        assertEquals(product1.getProductId(), returnedValue.getProductId());
        assertEquals(product1.getProductName(), returnedValue.getProductName());
        assertEquals(product1.getProductQuantity(), returnedValue.getProductQuantity());
    }

    @Test
    void testFindByIdIfArgumentIsNull() {
        assertThrows(IllegalArgumentException.class, () -> productService.findById(null));
    }

    @Test
    void testFindByIdIfNoSuchProduct() {
        String expectedMessage = "No such product in repository";

        Mockito.when(productRepository.findById(Mockito.any()))
            .thenThrow(new ItemNotFoundException(expectedMessage));

        Product product = new Product();
        product.setProductId("46e4ce01-d7f8-4c50-811f-871ab409a05a");
        product.setProductName("Sendal Mas Faiz");
        product.setProductQuantity(2);

        String productId = product.getProductId();
        assertNotNull(productId);

        assertThrows(ItemNotFoundException.class, () ->
            productService.findById(productId));
    }

    @Test
    void testDelete() {
        Product product1 = new Product();
        product1.setProductId("46e4ce01-d7f8-4c50-811f-871ab409a05a");
        product1.setProductName("Sendal Mas Faiz");
        product1.setProductQuantity(2);

        Mockito.when(productRepository.deleteById(Mockito.any())).thenReturn(product1);

        Product product2 = new Product();
        product2.setProductId("46e4ce01-d7f8-4c50-811f-871ab409a05a");
        product2.setProductName("Sendal Mas Faiz");
        product2.setProductQuantity(2);

        Product returnedValue = null;
        try {
            returnedValue = productService.deleteById(product2.getProductId());
        } catch (RuntimeException exception) {}

        assertNotNull(returnedValue);
        assertEquals(product1.getProductId(), returnedValue.getProductId());
        assertEquals(product1.getProductName(), returnedValue.getProductName());
        assertEquals(product1.getProductQuantity(), returnedValue.getProductQuantity());
    }

    @Test
    void testDeleteByIdIfArgumentIsNull() {
        assertThrows(IllegalArgumentException.class, () -> productService.deleteById(null));
    }

    @Test
    void testDeleteByIdIfNoSuchProduct() {
        String expectedMessage = "No such product in repository";

        Mockito.when(productRepository.deleteById(Mockito.any()))
            .thenThrow(new ItemNotFoundException(expectedMessage));

        Product product = new Product();
        product.setProductId("46e4ce01-d7f8-4c50-811f-871ab409a05a");
        product.setProductName("Sendal Mas Faiz");
        product.setProductQuantity(2);

        assertThrows(ItemNotFoundException.class, () ->
            productService.deleteById(product.getProductId()));
    }

    @Test
    void testEdit() {
        Product product1 = new Product();
        product1.setProductId("46e4ce01-d7f8-4c50-811f-871ab409a05a");
        product1.setProductName("Sendal Mas Faiz");
        product1.setProductQuantity(2);

        Mockito.when(productRepository.update(Mockito.any())).thenReturn(product1);

        Product product2 = new Product();
        product2.setProductId("46e4ce01-d7f8-4c50-811f-871ab409a05a");
        product2.setProductName("Sendal Mas Faiz");
        product2.setProductQuantity(2);

        Product returnedValue = null;
        try {
            returnedValue = productService.update(product2);
        } catch (RuntimeException exception) {}

        assertNotNull(returnedValue);
        assertEquals(product1.getProductId(), returnedValue.getProductId());
        assertEquals(product1.getProductName(), returnedValue.getProductName());
        assertEquals(product1.getProductQuantity(), returnedValue.getProductQuantity());
    }

    @Test
    void testEditIfArgumentIsNull() {
        assertThrows(IllegalArgumentException.class, () -> productService.update(null));
    }

    @Test
    void testEditIfProductIdIsNull() {
        Product product = new Product();
        product.setProductName("Sendal Mas Faiz");
        product.setProductQuantity(2);

        assertThrows(NullItemIdException.class, () -> productService.update(product));
    }

    @Test
    void testEditIfProductIdHasZeroLength() {
        Product product = new Product();
        product.setProductId("");
        product.setProductName("Sendal Mas Faiz");
        product.setProductQuantity(2);

        assertThrows(ZeroLengthItemIdException.class, () -> productService.update(product));
    }

    @Test
    void testEditIfProductNameIsNull() {
        Product product = new Product();
        product.setProductId("46e4ce01-d7f8-4c50-811f-871ab409a05a");
        product.setProductQuantity(2);

        assertThrows(NullItemNameException.class, () -> productService.update(product));
    }

    @Test
    void testEditIfProductNameHasZeroLength() {
        Product product = new Product();
        product.setProductId("46e4ce01-d7f8-4c50-811f-871ab409a05a");
        product.setProductName("");
        product.setProductQuantity(2);

        assertThrows(ZeroLengthItemNameException.class, () -> productService.update(product));
    }

    @Test
    void testEditIfProductQuantityIsNegative() {
        Product product = new Product();
        product.setProductId("46e4ce01-d7f8-4c50-811f-871ab409a05a");
        product.setProductName("Sendal Mas Faiz");
        product.setProductQuantity(-1);

        assertThrows(NegativeItemQuantityException.class, () -> productService.update(product));
    }

    @Test
    void testEditIfNoSuchProduct() {
        String expectedMessage = "An error occured in ProductRepository";

        Mockito.when(productRepository.update(Mockito.any()))
            .thenThrow(new ItemNotFoundException(expectedMessage));

        Product product = new Product();
        product.setProductId("46e4ce01-d7f8-4c50-811f-871ab409a05a");
        product.setProductName("Sendal Mas Faiz");
        product.setProductQuantity(2);

        assertThrows(ItemNotFoundException.class, () -> productService.update(product));
    }
}

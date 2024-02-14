package id.ac.ui.cs.advprog.eshop.service;

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
        Exception exception = assertThrows(RuntimeException.class, () ->
            productService.create(null));

        String expectedMessage = "Product is null";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void testCreateIfProductNameIsNull() {
        Product product = new Product();
        product.setProductQuantity(2);

        Exception exception = assertThrows(RuntimeException.class, () ->
            productService.create(product));

        String expectedMessage = "Field Product.productName is null";
        String actualMessage = exception.getMessage();
            
        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void testCreateIfProductNameHasZeroLength() {
        Product product = new Product();
        product.setProductName("");
        product.setProductQuantity(2);

        Exception exception = assertThrows(RuntimeException.class, () ->
            productService.create(product));

        String expectedMessage = "Field Product.productName has 0 length";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void testCreateIfProductQuantityIsNegative() {
        Product product = new Product();
        product.setProductName("Sendal Mas Faiz");
        product.setProductQuantity(-1);

        Exception exception = assertThrows(RuntimeException.class, () ->
            productService.create(product));

        String expectedMessage = "Field Product.productQuantity is less than 0";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
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
        Exception exception = assertThrows(RuntimeException.class, () ->
            productService.findOne(null));

        String expectedMessage = "productId is null";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void testFindOneIfNoSuchProduct() {
        String expectedMessage = "No such product in repository";

        Mockito.when(productRepository.findOne(Mockito.any()))
            .thenThrow(new RuntimeException(expectedMessage));

        Product product = new Product();
        product.setProductId("46e4ce01-d7f8-4c50-811f-871ab409a05a");
        product.setProductName("Sendal Mas Faiz");
        product.setProductQuantity(2);

        Exception exception = assertThrows(RuntimeException.class, () ->
            productService.findOne(product.getProductId()));

        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
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
        Exception exception = assertThrows(RuntimeException.class, () ->
            productService.delete(null));

        String expectedMessage = "Product is null";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void testDeleteIfProductIdIsNull() {
        Product product = new Product();
        product.setProductName("Sendal Mas Faiz");
        product.setProductQuantity(2);

        Exception exception = assertThrows(RuntimeException.class, () ->
            productService.delete(product));

        String expectedMessage = "Field Product.productId is null";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void testDeleteIfProductIdHasZeroLength() {
        Product product = new Product();
        product.setProductId("");
        product.setProductName("Sendal Mas Faiz");
        product.setProductQuantity(2);

        Exception exception = assertThrows(RuntimeException.class, () ->
            productService.delete(product));

        String expectedMessage = "Field Product.productId has 0 length";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void testDeleteIfNoSuchProduct() {
        String expectedMessage = "No such product in repository";

        Mockito.when(productRepository.delete(Mockito.any()))
            .thenThrow(new RuntimeException(expectedMessage));

        Product product = new Product();
        product.setProductId("46e4ce01-d7f8-4c50-811f-871ab409a05a");
        product.setProductName("Sendal Mas Faiz");
        product.setProductQuantity(2);

        Exception exception = assertThrows(RuntimeException.class, () ->
            productService.delete(product));

        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
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
        Exception exception = assertThrows(RuntimeException.class, () ->
            productService.edit(null));

        String expectedMessage = "Product is null";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void testEditIfProductIdIsNull() {
        Product product = new Product();
        product.setProductName("Sendal Mas Faiz");
        product.setProductQuantity(2);

        Exception exception = assertThrows(RuntimeException.class, () ->
            productService.edit(product));

        String expectedMessage = "Field Product.productId is null";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void testEditIfProductIdHasZeroLength() {
        Product product = new Product();
        product.setProductId("");
        product.setProductName("Sendal Mas Faiz");
        product.setProductQuantity(2);

        Exception exception = assertThrows(RuntimeException.class, () ->
            productService.edit(product));

        String expectedMessage = "Field Product.productId has 0 length";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void testEditIfProductNameIsNull() {
        Product product = new Product();
        product.setProductId("46e4ce01-d7f8-4c50-811f-871ab409a05a");
        product.setProductQuantity(2);

        Exception exception = assertThrows(RuntimeException.class, () ->
            productService.edit(product));

        String expectedMessage = "Field Product.productName is null";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void testEditIfProductNameHasZeroLength() {
        Product product = new Product();
        product.setProductId("46e4ce01-d7f8-4c50-811f-871ab409a05a");
        product.setProductName("");
        product.setProductQuantity(2);

        Exception exception = assertThrows(RuntimeException.class, () ->
            productService.edit(product));

        String expectedMessage = "Field Product.productName has 0 length";
        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void testEditIfProductQuantityIsNegative() {
        Product product = new Product();
        product.setProductId("46e4ce01-d7f8-4c50-811f-871ab409a05a");
        product.setProductName("Sendal Mas Faiz");
        product.setProductQuantity(-1);

        Exception exception = assertThrows(RuntimeException.class, () ->
            productService.edit(product));

        assertEquals("Field Product.productQuantity is less than 0", exception.getMessage());
    }

    @Test
    void testEditIfNoSuchProduct() {
        String expectedMessage = "An error occured in ProductRepository";

        Mockito.when(productRepository.edit(Mockito.any()))
            .thenThrow(new RuntimeException(expectedMessage));

        Product product = new Product();
        product.setProductId("46e4ce01-d7f8-4c50-811f-871ab409a05a");
        product.setProductName("Sendal Mas Faiz");
        product.setProductQuantity(2);

        Exception exception = assertThrows(RuntimeException.class, () ->
            productService.edit(product));

        String actualMessage = exception.getMessage();

        assertEquals(expectedMessage, actualMessage);
    }
}

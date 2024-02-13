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
    void testServiceIfArgsAreNull() {
        Exception exception = assertThrows(RuntimeException.class,
            () -> {
                productService.create(null);
                productService.edit(null);
                productService.delete(null);
            });

        assertEquals("Product is null", exception.getMessage());

        exception = assertThrows(RuntimeException.class,
            () -> productService.findOne(null));

        assertEquals("productId is null", exception.getMessage());
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
    void testCreateIfProductNameIsInvalid() {
        Exception exception = assertThrows(RuntimeException.class,
            () -> {
                Product product = new Product();
                productService.create(product);
            });
            
        assertEquals("Field Product.productName is null", exception.getMessage());

        exception = assertThrows(RuntimeException.class,
            () -> {
                Product product = new Product();
                product.setProductName("");
                productService.create(product);
            });

        assertEquals("Field Product.productName has 0 length", exception.getMessage());
    }

    @Test
    void testCreateIfProductQuantityIsInvalid() {
        Exception exception = assertThrows(RuntimeException.class,
            () -> {
                Product product = new Product();
                product.setProductName("Sendal Mas Faiz");
                product.setProductQuantity(-1);
                productService.create(product);
            });

        assertEquals("Field Product.productQuantity is less than 0", exception.getMessage());
    }

    @Test
    void testFindAll() {
        Iterator<Product> productIteratorMock = new ArrayList<Product>().iterator();
        Mockito.when(productRepository.findAll()).thenReturn(productIteratorMock);

        List<Product> productList = productService.findAll();
        assertNotNull(productList);
    }
}

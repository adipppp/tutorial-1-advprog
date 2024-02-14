package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.ProductService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ProductControllerTest {

    private AutoCloseable closeable;

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

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
    void testCreateProductPage() {
        Model modelMock = Mockito.mock(Model.class);

        String expectedViewName = "createProduct";
        String actualViewName = productController.createProductPage(modelMock);

        assertEquals(expectedViewName, actualViewName);
    }

    @Test
    void testCreateProductPost() {
        Model modelMock = Mockito.mock(Model.class);
        Product productMock = Mockito.mock(Product.class);
        BindingResult resultMock = Mockito.mock(BindingResult.class);

        String expectedViewName = "redirect:/product/list";
        String actualViewName = productController.createProductPost(modelMock, productMock, resultMock);

        assertEquals(expectedViewName, actualViewName);
    }

    @Test
    void testCreateProductPostIfResultHasErrors() {
        Model modelMock = Mockito.mock(Model.class);
        Product productMock = Mockito.mock(Product.class);
        BindingResult resultMock = Mockito.mock(BindingResult.class);

        Mockito.when(resultMock.hasErrors()).thenReturn(true);

        String expectedViewName = "createProduct";
        String actualViewName = productController.createProductPost(modelMock, productMock, resultMock);
        assertEquals(expectedViewName, actualViewName);
    }

    @Test
    void testCreateProductPostIfCreateHasErrors() {
        Model modelMock = Mockito.mock(Model.class);
        Product productMock = Mockito.mock(Product.class);
        BindingResult resultMock = Mockito.mock(BindingResult.class);

        Mockito.when(productService.create(productMock))
            .thenThrow(new RuntimeException("An error has occured"));

        String expectedViewName = "createProduct";
        String actualViewName = productController.createProductPost(modelMock, productMock, resultMock);
        assertEquals(expectedViewName, actualViewName);
    }

    @Test
    void testProductListPage() {
        Model modelMock = Mockito.mock(Model.class);

        String expectedViewName = "productList";
        String actualViewName = productController.productListPage(modelMock);

        assertEquals(expectedViewName, actualViewName);
    }

    @Test
    void testEditProductPage() {
        Model modelMock = Mockito.mock(Model.class);
        String productId = "46e4ce01-d7f8-4c50-811f-871ab409a05a";

        String expectedViewName = "editProduct";
        String actualViewName = productController.editProductPage(modelMock, productId);
        
        assertEquals(expectedViewName, actualViewName);
    }

    @Test
    void testEditProductPageIfFindOneHasErrors() {
        Model modelMock = Mockito.mock(Model.class);
        String productId = "46e4ce01-d7f8-4c50-811f-871ab409a05a";

        Mockito.when(productService.findOne(productId))
            .thenThrow(new RuntimeException("An error has occured"));

        String expectedViewName = "redirect:/product/list";
        String actualViewName = productController.editProductPage(modelMock, productId);

        assertEquals(expectedViewName, actualViewName);
    }

    @Test
    void testEditProductPost() {
        Model modelMock = Mockito.mock(Model.class);
        String productId = "46e4ce01-d7f8-4c50-811f-871ab409a05a";
        Product productMock = Mockito.mock(Product.class);
        BindingResult resultMock = Mockito.mock(BindingResult.class);

        String expectedViewName = "redirect:/product/list";
        String actualViewName = productController.editProductPost(
            modelMock, productId, productMock, resultMock);

        assertEquals(expectedViewName, actualViewName);
    }

    @Test
    void testEditProductPostIfResultHasErrors() {
        Model modelMock = Mockito.mock(Model.class);
        String productId = "46e4ce01-d7f8-4c50-811f-871ab409a05a";
        Product productMock = Mockito.mock(Product.class);
        BindingResult resultMock = Mockito.mock(BindingResult.class);

        Mockito.when(resultMock.hasErrors()).thenReturn(true);

        String expectedViewName = "editProduct";
        String actualViewName = productController.editProductPost(
            modelMock, productId, productMock, resultMock);

        assertEquals(expectedViewName, actualViewName);
    }

    @Test
    void testEditProductPostIfEditHasErrors() {
        Model modelMock = Mockito.mock(Model.class);
        String productId = "46e4ce01-d7f8-4c50-811f-871ab409a05a";
        Product productMock = Mockito.mock(Product.class);
        BindingResult resultMock = Mockito.mock(BindingResult.class);

        Mockito.when(productService.edit(productMock))
            .thenThrow(new RuntimeException("An error has occured"));

        String expectedViewName = "editProduct";
        String actualViewName = productController.editProductPost(
            modelMock, productId, productMock, resultMock);

        assertEquals(expectedViewName, actualViewName);
    }

    @Test
    void testDeleteProductPage() {
        Model modelMock = Mockito.mock(Model.class);
        String productId = "46e4ce01-d7f8-4c50-811f-871ab409a05a";

        String expectedViewName = "deleteProduct";
        String actualViewName = productController.deleteProductPage(modelMock, productId);
        
        assertEquals(expectedViewName, actualViewName);
    }

    @Test
    void testDeleteProductPageIfFindOneHasErrors() {
        Model modelMock = Mockito.mock(Model.class);
        String productId = "46e4ce01-d7f8-4c50-811f-871ab409a05a";

        Mockito.when(productService.findOne(productId))
            .thenThrow(new RuntimeException("An error has occured"));

        String expectedViewName = "redirect:/product/list";
        String actualViewName = productController.deleteProductPage(modelMock, productId);
        
        assertEquals(expectedViewName, actualViewName);
    }

    @Test
    void testDeleteProductPost() {
        Model modelMock = Mockito.mock(Model.class);
        String productId = "46e4ce01-d7f8-4c50-811f-871ab409a05a";
        Product productMock = Mockito.mock(Product.class);
        BindingResult resultMock = Mockito.mock(BindingResult.class);

        String expectedViewName = "redirect:/product/list";
        String actualViewName = productController.deleteProductPost(
            modelMock, productId, productMock, resultMock);

        assertEquals(expectedViewName, actualViewName);
    }
}

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
    private static final String CREATE_PRODUCT = "CreateProduct";
    private static final String PRODUCT_LIST = "ProductList";
    private static final String REDIRECT_PRODUCT_LIST = "redirect:/product/list";
    private static final String EDIT_PRODUCT = "EditProduct";
    private static final String DELETE_PRODUCT = "DeleteProduct";

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
    void cleanUp() throws Exception {
        closeable.close();
    }

    @Test
    void testCreateProductPage() {
        Model modelMock = Mockito.mock(Model.class);

        String expectedViewName = CREATE_PRODUCT;
        String actualViewName = productController.createProductPage(modelMock);

        assertEquals(expectedViewName, actualViewName);
    }

    @Test
    void testCreateProductPost() {
        Model modelMock = Mockito.mock(Model.class);
        Product productMock = Mockito.mock(Product.class);
        BindingResult resultMock = Mockito.mock(BindingResult.class);

        String expectedViewName = REDIRECT_PRODUCT_LIST;
        String actualViewName = productController.createProductPost(modelMock, productMock, resultMock);

        assertEquals(expectedViewName, actualViewName);
    }

    @Test
    void testCreateProductPostIfResultHasErrors() {
        Model modelMock = Mockito.mock(Model.class);
        Product productMock = Mockito.mock(Product.class);
        BindingResult resultMock = Mockito.mock(BindingResult.class);

        Mockito.when(resultMock.hasErrors()).thenReturn(true);

        String expectedViewName = CREATE_PRODUCT;
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

        String expectedViewName = CREATE_PRODUCT;
        String actualViewName = productController.createProductPost(modelMock, productMock, resultMock);
        assertEquals(expectedViewName, actualViewName);
    }

    @Test
    void testProductListPage() {
        Model modelMock = Mockito.mock(Model.class);

        String expectedViewName = PRODUCT_LIST;
        String actualViewName = productController.productListPage(modelMock);

        assertEquals(expectedViewName, actualViewName);
    }

    @Test
    void testEditProductPage() {
        Model modelMock = Mockito.mock(Model.class);
        String productId = "46e4ce01-d7f8-4c50-811f-871ab409a05a";

        String expectedViewName = EDIT_PRODUCT;
        String actualViewName = productController.editProductPage(modelMock, productId);
        
        assertEquals(expectedViewName, actualViewName);
    }

    @Test
    void testEditProductPageIfFindOneHasErrors() {
        Model modelMock = Mockito.mock(Model.class);
        String productId = "46e4ce01-d7f8-4c50-811f-871ab409a05a";

        Mockito.when(productService.findOne(productId))
            .thenThrow(new RuntimeException("An error has occured"));

        String expectedViewName = REDIRECT_PRODUCT_LIST;
        String actualViewName = productController.editProductPage(modelMock, productId);

        assertEquals(expectedViewName, actualViewName);
    }

    @Test
    void testEditProductPost() {
        Model modelMock = Mockito.mock(Model.class);
        String productId = "46e4ce01-d7f8-4c50-811f-871ab409a05a";
        Product productMock = Mockito.mock(Product.class);
        BindingResult resultMock = Mockito.mock(BindingResult.class);

        String expectedViewName = REDIRECT_PRODUCT_LIST;
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

        String expectedViewName = EDIT_PRODUCT;
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

        String expectedViewName = EDIT_PRODUCT;
        String actualViewName = productController.editProductPost(
            modelMock, productId, productMock, resultMock);

        assertEquals(expectedViewName, actualViewName);
    }

    @Test
    void testDeleteProductPage() {
        Model modelMock = Mockito.mock(Model.class);
        String productId = "46e4ce01-d7f8-4c50-811f-871ab409a05a";

        String expectedViewName = DELETE_PRODUCT;
        String actualViewName = productController.deleteProductPage(modelMock, productId);
        
        assertEquals(expectedViewName, actualViewName);
    }

    @Test
    void testDeleteProductPageIfFindOneHasErrors() {
        Model modelMock = Mockito.mock(Model.class);
        String productId = "46e4ce01-d7f8-4c50-811f-871ab409a05a";

        Mockito.when(productService.findOne(productId))
            .thenThrow(new RuntimeException("An error has occured"));

        String expectedViewName = REDIRECT_PRODUCT_LIST;
        String actualViewName = productController.deleteProductPage(modelMock, productId);
        
        assertEquals(expectedViewName, actualViewName);
    }

    @Test
    void testDeleteProductPost() {
        Model modelMock = Mockito.mock(Model.class);
        String productId = "46e4ce01-d7f8-4c50-811f-871ab409a05a";
        Product productMock = Mockito.mock(Product.class);
        BindingResult resultMock = Mockito.mock(BindingResult.class);

        String expectedViewName = REDIRECT_PRODUCT_LIST;
        String actualViewName = productController.deleteProductPost(
            modelMock, productId, productMock, resultMock);

        assertEquals(expectedViewName, actualViewName);
    }

    @Test
    void testDeleteProductPostIfDeleteHasErrors() {
        Model modelMock = Mockito.mock(Model.class);
        String productId = "46e4ce01-d7f8-4c50-811f-871ab409a05a";
        Product productMock = Mockito.mock(Product.class);
        BindingResult resultMock = Mockito.mock(BindingResult.class);

        Mockito.when(productService.delete(productMock))
            .thenThrow(new RuntimeException("An error has occured"));

        String expectedViewName = REDIRECT_PRODUCT_LIST;
        String actualViewName = productController.deleteProductPost(
            modelMock, productId, productMock, resultMock);

        assertEquals(expectedViewName, actualViewName);
    }
}

package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.exceptions.*;
import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.validation.BindingResult;

import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {
    private static final String PRODUCT_ATTR_NAME = "product";
    private static final String ERR_ATTR_NAME = "error";
    private static final String CREATE_PRODUCT = "createProduct";
    private static final String PRODUCT_LIST = "productList";
    private static final String REDIRECT_PRODUCT_LIST = "redirect:/product/list";
    private static final String EDIT_PRODUCT = "editProduct";
    private static final String DELETE_PRODUCT = "deleteProduct";
    private static final String NEGATIVE_QUANTITY_EXCEPTION_MSG = "Product quantity is not an integer";

    private ProductService service;

    @Autowired
    public ProductController(ProductService service) {
        this.service = service;
    }

    @GetMapping({"/create", "/create/"})
    public String createProductPage(Model model) {
        Product product = new Product();
        model.addAttribute(PRODUCT_ATTR_NAME, product);
        return CREATE_PRODUCT;
    }

    @PostMapping({"/create", "/create/"})
    public String createProductPost(Model model, @ModelAttribute Product product, BindingResult result) {
        try {
            if (result.hasErrors())
                throw new IllegalProductQuantityException(NEGATIVE_QUANTITY_EXCEPTION_MSG);

            service.create(product);

        } catch (IllegalProductQuantityException exception) {

            model.addAttribute(ERR_ATTR_NAME, NEGATIVE_QUANTITY_EXCEPTION_MSG);
            return CREATE_PRODUCT;

        } catch (NegativeProductQuantityException exception) {

            model.addAttribute(ERR_ATTR_NAME, "Product quantity cannot be negative");
            return CREATE_PRODUCT;

        } catch (ZeroLengthProductNameException exception) {

            model.addAttribute(ERR_ATTR_NAME, "Product name should not be left empty");
            return CREATE_PRODUCT;

        } catch (NullProductNameException exception) {

            model.addAttribute(ERR_ATTR_NAME, "Request body is invalid");
            return CREATE_PRODUCT;

        } catch (RuntimeException exception) {

            model.addAttribute(ERR_ATTR_NAME, "An unknown exception has occured");
            return CREATE_PRODUCT;

        }

        return REDIRECT_PRODUCT_LIST;
    }

    @GetMapping({"/list", "/list/"})
    public String productListPage(Model model) {
        List<Product> allProducts = service.findAll();
        model.addAttribute("products", allProducts);
        return PRODUCT_LIST;
    }

    @GetMapping({"/edit", "/edit/", "/edit/{productId}", "/edit/{productId}/"})
    public String editProductPage(Model model, @PathVariable(required=false) String productId) {
        Product product;
        try {
            product = service.findOne(productId);
        } catch (RuntimeException exception) {
            return REDIRECT_PRODUCT_LIST;
        }

        model.addAttribute(PRODUCT_ATTR_NAME, product);
        return EDIT_PRODUCT;
    }

    @PostMapping({"/edit", "/edit/", "/edit/{productId}", "/edit/{productId}/"})
    public String editProductPost(Model model, @PathVariable(required=false) String productId, @ModelAttribute Product product, BindingResult result) {

        product.setProductId(productId);

        try {
            if (result.hasErrors())
                throw new IllegalProductQuantityException(NEGATIVE_QUANTITY_EXCEPTION_MSG);

            service.edit(product);

        } catch (IllegalProductQuantityException | ZeroLengthProductIdException exception) {

            model.addAttribute(ERR_ATTR_NAME, NEGATIVE_QUANTITY_EXCEPTION_MSG);
            return EDIT_PRODUCT;

        } catch (ProductNotFoundException exception) {

            model.addAttribute(ERR_ATTR_NAME, "Invalid product ID");
            return EDIT_PRODUCT;

        } catch (NegativeProductQuantityException exception) {

            model.addAttribute(ERR_ATTR_NAME, "Product quantity cannot be negative");
            return EDIT_PRODUCT;

        } catch (ZeroLengthProductNameException exception) {

            model.addAttribute(ERR_ATTR_NAME, "Product name should not be left empty");
            return EDIT_PRODUCT;

        } catch (NullProductNameException exception) {

            model.addAttribute(ERR_ATTR_NAME, "Request body is invalid");
            return EDIT_PRODUCT;

        } catch (NullProductIdException exception) {

            return REDIRECT_PRODUCT_LIST;

        } catch (RuntimeException exception) {

            model.addAttribute(ERR_ATTR_NAME, "An unknown exception has occured");
            return EDIT_PRODUCT;

        }

        return REDIRECT_PRODUCT_LIST;
    }

    @GetMapping({"/delete", "/delete/", "/delete/{productId}", "/delete/{productId}"})
    public String deleteProductPage(Model model, @PathVariable(required=false) String productId) {
        Product product;
        try {
            product = service.findOne(productId);
        } catch (RuntimeException exception) {
            return REDIRECT_PRODUCT_LIST;
        }

        model.addAttribute(PRODUCT_ATTR_NAME, product);
        return DELETE_PRODUCT;
    }

    @PostMapping({"/delete", "/delete/", "/delete/{productId}", "/delete/{productId}"})
    public String deleteProductPost(Model model, @PathVariable(required=false) String productId, @ModelAttribute Product product, BindingResult result) {

        product.setProductId(productId);

        try {
            service.delete(product);
        } catch (RuntimeException exception) {}

        return REDIRECT_PRODUCT_LIST;
    }
}

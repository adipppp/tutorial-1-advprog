package id.ac.ui.cs.advprog.eshop.controller;

import id.ac.ui.cs.advprog.eshop.exceptions.*;
import id.ac.ui.cs.advprog.eshop.model.Car;
import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.service.CarServiceImpl;
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

    private static final String CREATE_PRODUCT = "CreateProduct";
    private static final String PRODUCT_LIST = "ProductList";
    private static final String REDIRECT_PRODUCT_LIST = "redirect:/product/list";
    private static final String EDIT_PRODUCT = "EditProduct";
    private static final String DELETE_PRODUCT = "DeleteProduct";

    private static final String ILLEGAL_QUANTITY_EXCEPTION_MSG = "Product quantity is not an integer";
    private static final String NEGATIVE_QUANTITY_EXCEPTION_MSG = "Product quantity cannot be negative";
    private static final String ZERO_LENGTH_NAME_EXCEPTION_MSG = "Product name should not be left empty";
    private static final String NULL_NAME_EXCEPTION_MSG = "Request body is invalid";
    private static final String INVALID_ID_MSG = "Invalid product ID";
    private static final String RUNTIME_EXCEPTION_MSG = "An unknown exception has occured";

    @Autowired
    private ProductService service;

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
                throw new IllegalProductQuantityException(ILLEGAL_QUANTITY_EXCEPTION_MSG);

            service.create(product);

        } catch (IllegalProductQuantityException exception) {

            model.addAttribute(ERR_ATTR_NAME, ILLEGAL_QUANTITY_EXCEPTION_MSG);
            return CREATE_PRODUCT;

        } catch (NegativeProductQuantityException exception) {

            model.addAttribute(ERR_ATTR_NAME, NEGATIVE_QUANTITY_EXCEPTION_MSG);
            return CREATE_PRODUCT;

        } catch (ZeroLengthProductNameException exception) {

            model.addAttribute(ERR_ATTR_NAME, ZERO_LENGTH_NAME_EXCEPTION_MSG);
            return CREATE_PRODUCT;

        } catch (NullProductNameException exception) {

            model.addAttribute(ERR_ATTR_NAME, NULL_NAME_EXCEPTION_MSG);
            return CREATE_PRODUCT;

        } catch (RuntimeException exception) {

            model.addAttribute(ERR_ATTR_NAME, RUNTIME_EXCEPTION_MSG);
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
                throw new IllegalProductQuantityException(ILLEGAL_QUANTITY_EXCEPTION_MSG);

            service.edit(product);

        } catch (IllegalProductQuantityException exception) {

            model.addAttribute(ERR_ATTR_NAME, ILLEGAL_QUANTITY_EXCEPTION_MSG);
            return EDIT_PRODUCT;

        } catch (ProductNotFoundException | ZeroLengthProductIdException exception) {

            model.addAttribute(ERR_ATTR_NAME, INVALID_ID_MSG);
            return EDIT_PRODUCT;

        } catch (NegativeProductQuantityException exception) {

            model.addAttribute(ERR_ATTR_NAME, NEGATIVE_QUANTITY_EXCEPTION_MSG);
            return EDIT_PRODUCT;

        } catch (ZeroLengthProductNameException exception) {

            model.addAttribute(ERR_ATTR_NAME, ZERO_LENGTH_NAME_EXCEPTION_MSG);
            return EDIT_PRODUCT;

        } catch (NullProductNameException exception) {

            model.addAttribute(ERR_ATTR_NAME, NULL_NAME_EXCEPTION_MSG);
            return EDIT_PRODUCT;

        } catch (NullProductIdException exception) {

            return REDIRECT_PRODUCT_LIST;

        } catch (RuntimeException exception) {

            model.addAttribute(ERR_ATTR_NAME, RUNTIME_EXCEPTION_MSG);
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
        } catch (RuntimeException exception) {
            return REDIRECT_PRODUCT_LIST;
        }

        return REDIRECT_PRODUCT_LIST;
    }
}

@Controller
@RequestMapping("/car")
class CarController extends ProductController {
    @Autowired
    private CarServiceImpl carService;

    @GetMapping("/createCar")
    public String createCarPage(Model model) {
        Car car = new Car();
        model.addAttribute("car", car);
        return "createCar";
    }

    @PostMapping("/createCar")
    public String createCarPost(@ModelAttribute Car car, Model model) {
        carService.create(car);
        return "redirect:listCar";
    }

    @GetMapping("/listCar")
    public String carListPage(Model model) {
        List<Car> allCars = carService.findAll();
        model.addAttribute("cars", allCars);
        return "carList";
    }

    @GetMapping("/editCar/{carId}")
    public String editCarPage(@PathVariable String carId, Model model) {
        Car car = carService.findById(carId);
        model.addAttribute("car", car);
        return "editCar";
    }

    @PostMapping("/editCar")
    public String editCarPost(@ModelAttribute Car car, Model model) {
        System.out.println(car.getCarId());
        carService.update(car.getCarId(), car);
        return "redirect:listCar";
    }

    @PostMapping("deleteCar")
    public String deleteCar(@RequestParam("carId") String carId) {
        carService.deleteCarById(carId);
        return "redirect:listCar";
    }
}

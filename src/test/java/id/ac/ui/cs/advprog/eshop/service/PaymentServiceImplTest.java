package id.ac.ui.cs.advprog.eshop.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import id.ac.ui.cs.advprog.eshop.enums.OrderStatus;
import id.ac.ui.cs.advprog.eshop.enums.PaymentMethod;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.OrderRepository;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;

class PaymentServiceImplTest {

    private AutoCloseable closeable;
    private List<Product> products;
    
    @Mock
    PaymentRepository paymentRepository;

    @Mock
    OrderRepository orderRepository;

    @InjectMocks
    PaymentServiceImpl paymentService;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
        
        products = new ArrayList<>();
        Product product = new Product();
        product.setProductId("eb558e9f-1c39-460e-8860-71af6af63bd6");
        product.setProductName("Sampo Cap Bambang");
        product.setProductQuantity(2);
        products.add(product);
    }

    @AfterEach
    void cleanUp() throws Exception {
        closeable.close();
    }

    @Test
    void testAddPaymentByVoucherCodeSuccessStatus() {
        Order order = new Order("ac1c32b3-d0f8-40ba-97d9-2f77ef19507d",
            products, 1708560000L, "Safira Sudrajat");

        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP_12345678_A");

        Payment returnedPayment = paymentService.addPayment(order,
            PaymentMethod.VOUCHER_CODE.getValue(), paymentData);

        assertEquals(PaymentMethod.VOUCHER_CODE.getValue(), returnedPayment.getMethod());
        assertEquals(PaymentStatus.SUCCESS.getValue(), returnedPayment.getStatus());
        assertEquals(paymentData, returnedPayment.getPaymentData());
    }

    @Test
    void testAddPaymentByVoucherCodeRejectedStatus() {
        Order order = new Order("ac1c32b3-d0f8-40ba-97d9-2f77ef19507d",
            products, 1708560000L, "Safira Sudrajat");

        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "1234567890");

        Payment returnedPayment = paymentService.addPayment(order,
            PaymentMethod.VOUCHER_CODE.getValue(), paymentData);

        assertEquals(PaymentMethod.VOUCHER_CODE.getValue(), returnedPayment.getMethod());
        assertEquals(PaymentStatus.REJECTED.getValue(), returnedPayment.getStatus());
        assertEquals(paymentData, returnedPayment.getPaymentData());
    }

    @Test
    void testAddPaymentByBankTransferSuccessStatus() {
        Order order = new Order("ac1c32b3-d0f8-40ba-97d9-2f77ef19507d",
            products, 1708560000L, "Safira Sudrajat");

        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("bankName", "Bank Udah Bank");
        paymentData.put("deliveryFee", "24434");

        Payment returnedPayment = paymentService.addPayment(order,
            PaymentMethod.BANK_TRANSFER.getValue(), paymentData);

        assertEquals(PaymentMethod.BANK_TRANSFER.getValue(), returnedPayment.getMethod());
        assertEquals(PaymentStatus.SUCCESS.getValue(), returnedPayment.getStatus());
        assertEquals(paymentData, returnedPayment.getPaymentData());
    }
    
    @Test
    void testAddPaymentByBankTransferRejectedStatus() {
        Order order = new Order("ac1c32b3-d0f8-40ba-97d9-2f77ef19507d",
            products, 1708560000L, "Safira Sudrajat");

        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("bankName", "");
        paymentData.put("deliveryFee", "24434");

        Payment returnedPayment = paymentService.addPayment(order,
            PaymentMethod.BANK_TRANSFER.getValue(), paymentData);

        assertEquals(PaymentMethod.BANK_TRANSFER.getValue(), returnedPayment.getMethod());
        assertEquals(PaymentStatus.REJECTED.getValue(), returnedPayment.getStatus());
        assertEquals(paymentData, returnedPayment.getPaymentData());
    }

    @Test
    void testSetStatusToRejected() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP_12345678_A");

        String orderId = "ac1c32b3-d0f8-40ba-97d9-2f77ef19507d";
        Order order = new Order(orderId, products, 1708560000L, "Safira Sudrajat");
        Payment payment = paymentService.addPayment(order,
            PaymentMethod.VOUCHER_CODE.getValue(), paymentData);

        when(orderRepository.findById(orderId)).thenReturn(order);
        
        Payment returnedPayment = paymentService.setStatus(payment, PaymentStatus.REJECTED.getValue());

        assertEquals(payment, returnedPayment);
        assertEquals(PaymentStatus.REJECTED.getValue(), returnedPayment.getStatus());
        assertEquals(OrderStatus.FAILED.getValue(), order.getStatus());
    }

    @Test
    void testSetStatusToSuccess() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "1234567890");

        String orderId = "ac1c32b3-d0f8-40ba-97d9-2f77ef19507d";
        Order order = new Order(orderId, products, 1708560000L, "Safira Sudrajat");
        Payment payment = paymentService.addPayment(order,
            PaymentMethod.VOUCHER_CODE.getValue(), paymentData);

        when(orderRepository.findById(orderId)).thenReturn(order);
        
        Payment returnedPayment = paymentService.setStatus(payment, PaymentStatus.SUCCESS.getValue());

        assertEquals(payment, returnedPayment);
        assertEquals(PaymentStatus.SUCCESS.getValue(), returnedPayment.getStatus());
        assertEquals(OrderStatus.SUCCESS.getValue(), order.getStatus());
    }

    @Test
    void testGetPayment() {
        String paymentId = "5051617f-b2bf-4384-b124-311641234dd9";
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP_12345678_A");
        Payment payment = new Payment(paymentId, PaymentMethod.VOUCHER_CODE.getValue(),
            PaymentStatus.SUCCESS.getValue(), paymentData);

        when(paymentRepository.findById(paymentId)).thenReturn(payment);

        Payment returnedPayment = paymentService.getPayment(paymentId);

        assertEquals(payment, returnedPayment);
    }

    @Test
    void testGetPaymentIfNoSuchPayment() {
        String orderId = "ac1c32b3-d0f8-40ba-97d9-2f77ef19507d";

        when(paymentRepository.findById(orderId)).thenThrow(NoSuchElementException.class);

        assertThrows(NoSuchElementException.class,
            () -> paymentService.getPayment(orderId));
    }

    @Test
    void testGetAllPayments() {
        List<Payment> paymentList = new ArrayList<>();

        Map<String, String> paymentData1 = new HashMap<>();
        paymentData1.put("voucherCode", "ESHOP_12345678_A");
        Payment payment1 = new Payment("5051617f-b2bf-4384-b124-311641234dd9", PaymentMethod.VOUCHER_CODE.getValue(),
            PaymentStatus.SUCCESS.getValue(), paymentData1);
        paymentList.add(payment1);

        Map<String, String> paymentData2 = new HashMap<>();
        paymentData2.put("voucherCode", "ESHOP_87654321_C");
        Payment payment2 = new Payment("7ec990bf-989e-4299-ace8-68885aa14793", PaymentMethod.VOUCHER_CODE.getValue(),
            PaymentStatus.SUCCESS.getValue(), paymentData2);
        paymentList.add(payment2);

        when(paymentRepository.findAll()).thenReturn(paymentList);

        List<Payment> paymentData = paymentService.getAllPayments();
        ListIterator<Payment> paymentDataIterator = paymentData.listIterator();

        assertTrue(paymentDataIterator.hasNext());

        Payment returnedPayment1 = paymentDataIterator.next();

        assertEquals(PaymentMethod.VOUCHER_CODE.getValue(), returnedPayment1.getMethod());
        assertEquals(PaymentStatus.SUCCESS.getValue(), returnedPayment1.getStatus());
        assertEquals(paymentData1, returnedPayment1.getPaymentData());

        Payment returnedPayment2 = paymentDataIterator.next();

        assertEquals(PaymentMethod.VOUCHER_CODE.getValue(), returnedPayment2.getMethod());
        assertEquals(PaymentStatus.SUCCESS.getValue(), returnedPayment2.getStatus());
        assertEquals(paymentData2, returnedPayment2.getPaymentData());

        assertFalse(paymentDataIterator.hasNext());
    }
}

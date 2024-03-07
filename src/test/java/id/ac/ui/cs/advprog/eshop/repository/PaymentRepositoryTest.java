package id.ac.ui.cs.advprog.eshop.repository;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import id.ac.ui.cs.advprog.eshop.enums.PaymentMethod;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import id.ac.ui.cs.advprog.eshop.model.Payment;

class PaymentRepositoryTest {
    PaymentRepository paymentRepository;

    @BeforeEach
    void setUp() {
        paymentRepository = new PaymentRepository();
    }

    @Test
    void testSave() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP_1234567890");
        Payment payment = new Payment("5051617f-b2bf-4384-b124-311641234dd9", PaymentMethod.VOUCHER_CODE.getValue(),
            PaymentStatus.SUCCESS.getValue(), paymentData);

        Payment returnedPayment = paymentRepository.save(payment);

        assertEquals(payment, returnedPayment);
    }

    @Test
    void testSaveIfIdAlreadyExists() {
        Map<String, String> paymentData1 = new HashMap<>();
        paymentData1.put("voucherCode", "ESHOP_1234567890");
        Payment payment1 = new Payment("5051617f-b2bf-4384-b124-311641234dd9", PaymentMethod.VOUCHER_CODE.getValue(),
            PaymentStatus.SUCCESS.getValue(), paymentData1);

        Payment returnedPayment1 = paymentRepository.save(payment1);

        assertEquals(payment1, returnedPayment1);

        Map<String, String> paymentData2 = new HashMap<>();
        paymentData2.put("voucherCode", "ESHOP_0987654321");
        Payment payment2 = new Payment("5051617f-b2bf-4384-b124-311641234dd9", PaymentMethod.VOUCHER_CODE.getValue(),
            PaymentStatus.SUCCESS.getValue(), paymentData2);

        Payment returnedPayment2 = paymentRepository.save(payment2);

        assertEquals(payment2, returnedPayment2);
    }

    @Test
    void testFindById() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP_1234567890");
        Payment payment = new Payment("5051617f-b2bf-4384-b124-311641234dd9", PaymentMethod.VOUCHER_CODE.getValue(),
            PaymentStatus.SUCCESS.getValue(), paymentData);
        paymentRepository.save(payment);

        Payment returnedPayment = paymentRepository.findById("5051617f-b2bf-4384-b124-311641234dd9");

        assertEquals(payment, returnedPayment);
    }

    @Test
    void testFindByIdIfNoSuchPayment() {
        assertThrows(NoSuchElementException.class, () ->
            paymentRepository.findById("5051617f-b2bf-4384-b124-311641234dd9"));
    }

    @Test
    void testFindAll() {
        Map<String, String> paymentData1 = new HashMap<>();
        paymentData1.put("voucherCode", "ESHOP_1234567890");
        Payment payment1 = new Payment("5051617f-b2bf-4384-b124-311641234dd9", PaymentMethod.VOUCHER_CODE.getValue(),
            PaymentStatus.SUCCESS.getValue(), paymentData1);
        paymentRepository.save(payment1);

        Map<String, String> paymentData2 = new HashMap<>();
        paymentData2.put("voucherCode", "ESHOP_0987654321");
        Payment payment2 = new Payment("7ec990bf-989e-4299-ace8-68885aa14793", PaymentMethod.VOUCHER_CODE.getValue(),
            PaymentStatus.SUCCESS.getValue(), paymentData2);
        paymentRepository.save(payment2);

        List<Payment> paymentData = paymentRepository.findAll();

        ListIterator<Payment> paymentDataIterator = paymentData.listIterator();
        assertTrue(paymentDataIterator.hasNext());

        Payment returnedPayment1 = paymentDataIterator.next();
        assertEquals(payment1, returnedPayment1);

        Payment returnedPayment2 = paymentDataIterator.next();
        assertEquals(payment2, returnedPayment2);

        assertFalse(paymentDataIterator.hasNext());
    }
}

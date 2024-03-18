package id.ac.ui.cs.advprog.eshop.model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

import id.ac.ui.cs.advprog.eshop.enums.PaymentMethod;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;

class PaymentTest {

    @Test
    void testCreateInvalidStatus() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP_1234567890");

        assertThrows(IllegalArgumentException.class, () ->
            new Payment("5051617f-b2bf-4384-b124-311641234dd9", PaymentMethod.VOUCHER_CODE.getValue(),
                "Diterima", paymentData));
    }

    @Test
    void testCreateVoucherCodeSuccessStatus() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP_1234567890");
        Payment payment = new Payment("5051617f-b2bf-4384-b124-311641234dd9", PaymentMethod.VOUCHER_CODE.getValue(),
            PaymentStatus.SUCCESS.getValue(), paymentData);

        assertEquals("5051617f-b2bf-4384-b124-311641234dd9", payment.getId());
        assertEquals(PaymentMethod.VOUCHER_CODE.getValue(), payment.getMethod());
        assertEquals(PaymentStatus.SUCCESS.getValue(), payment.getStatus());
        assertNotNull(payment.getPaymentData());
    }
    
    @Test
    void testCreateVoucherCodeRejectedStatus() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "1234567890");
        Payment payment = new Payment("5051617f-b2bf-4384-b124-311641234dd9", PaymentMethod.VOUCHER_CODE.getValue(),
            PaymentStatus.REJECTED.getValue(), paymentData);

        assertEquals("5051617f-b2bf-4384-b124-311641234dd9", payment.getId());
        assertEquals(PaymentMethod.VOUCHER_CODE.getValue(), payment.getMethod());
        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
        assertNotNull(payment.getPaymentData());
    }

    @Test
    void testCreateBankTransferSuccessStatus() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("bankName", "Bank Udah Bank");
        paymentData.put("referenceCode", "24434");
        Payment payment = new Payment("5051617f-b2bf-4384-b124-311641234dd9", PaymentMethod.BANK_TRANSFER.getValue(),
            PaymentStatus.SUCCESS.getValue(), paymentData);

        assertEquals("5051617f-b2bf-4384-b124-311641234dd9", payment.getId());
        assertEquals(PaymentMethod.BANK_TRANSFER.getValue(), payment.getMethod());
        assertEquals(PaymentStatus.SUCCESS.getValue(), payment.getStatus());
        assertNotNull(payment.getPaymentData());
    }

    @Test
    void testCreateBankTransferRejectedStatus() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("bankName", "Bank Udah Bank");
        paymentData.put("referenceCode", "");
        Payment payment = new Payment("5051617f-b2bf-4384-b124-311641234dd9", PaymentMethod.BANK_TRANSFER.getValue(),
            PaymentStatus.REJECTED.getValue(), paymentData);

        assertEquals("5051617f-b2bf-4384-b124-311641234dd9", payment.getId());
        assertEquals(PaymentMethod.BANK_TRANSFER.getValue(), payment.getMethod());
        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
        assertNotNull(payment.getPaymentData());
    }

    @Test
    void testCreateInvalidMethod() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP_1234567890");

        assertThrows(IllegalArgumentException.class, () ->
            new Payment("5051617f-b2bf-4384-b124-311641234dd9", "Kode_voucher",
                PaymentStatus.SUCCESS.getValue(), paymentData));
    }
}

package id.ac.ui.cs.advprog.eshop.model;

import static org.junit.jupiter.api.Assertions.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;

class PaymentTest {

    @Test
    void testCreateAcceptedStatus() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP_1234567890");
        Payment payment = new Payment("5051617f-b2bf-4384-b124-311641234dd9", "VOUCHER_CODE",
            "ACCEPTED", paymentData);

        assertEquals("5051617f-b2bf-4384-b124-311641234dd9", payment.getId());
        assertEquals("VOUCHER_CODE", payment.getMethod());
        assertEquals("ACCEPTED", payment.getStatus());
        assertNotNull(payment.getPaymentData());
    }

    @Test
    void testCreateRejectedStatus() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP_1234567890");
        Payment payment = new Payment("5051617f-b2bf-4384-b124-311641234dd9", "VOUCHER_CODE",
            "REJECTED", paymentData);

        assertEquals("5051617f-b2bf-4384-b124-311641234dd9", payment.getId());
        assertEquals("VOUCHER_CODE", payment.getMethod());
        assertEquals("REJECTED", payment.getStatus());
        assertNotNull(payment.getPaymentData());
    }

    @Test
    void testCreateInvalidStatus() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP_1234567890");

        assertThrows(IllegalArgumentException.class, () ->
            new Payment("5051617f-b2bf-4384-b124-311641234dd9", "VOUCHER_CODE",
                "Diterima", paymentData));
    }

    @Test
    void testCreateVoucherCodeMethod() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP_1234567890");
        Payment payment = new Payment("5051617f-b2bf-4384-b124-311641234dd9", "VOUCHER_CODE",
            "ACCEPTED", paymentData);

        assertEquals("5051617f-b2bf-4384-b124-311641234dd9", payment.getId());
        assertEquals("VOUCHER_CODE", payment.getMethod());
        assertEquals("ACCEPTED", payment.getStatus());
        assertNotNull(payment.getPaymentData());
    }

    @Test
    void testCreateBankTransferMethod() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("bankName", "Bank Udah Bank");
        paymentData.put("referenceCode", "24434");
        Payment payment = new Payment("5051617f-b2bf-4384-b124-311641234dd9", "BANK_TRANSFER",
            "ACCEPTED", paymentData);

        assertEquals("5051617f-b2bf-4384-b124-311641234dd9", payment.getId());
        assertEquals("BANK_TRANSFER", payment.getMethod());
        assertEquals("ACCEPTED", payment.getStatus());
        assertNotNull(payment.getPaymentData());
    }

    @Test
    void testCreateInvalidMethod() {
        Map<String, String> paymentData = new HashMap<>();
        paymentData.put("voucherCode", "ESHOP_1234567890");

        assertThrows(IllegalArgumentException.class, () ->
            new Payment("5051617f-b2bf-4384-b124-311641234dd9", "Kode_voucher",
                "ACCEPTED", paymentData));
    }
}

package id.ac.ui.cs.advprog.eshop.service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import id.ac.ui.cs.advprog.eshop.enums.OrderStatus;
import id.ac.ui.cs.advprog.eshop.enums.PaymentMethod;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import id.ac.ui.cs.advprog.eshop.exceptions.payment.InvalidPaymentDataException;
import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.repository.OrderRepository;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;

@Service
public class PaymentServiceImpl implements PaymentService {
    private PaymentRepository paymentRepository;
    private OrderRepository orderRepository;

    @Autowired
    public PaymentServiceImpl(PaymentRepository paymentRepository, OrderRepository orderRepository) {
        this.paymentRepository = paymentRepository;
        this.orderRepository = orderRepository;
    }

    private void validateVoucherCode(String voucherCode) {
        if (!voucherCode.startsWith("ESHOP") || voucherCode.length() != 16)
            throw new InvalidPaymentDataException();

        int voucherCodeLength = voucherCode.length();
        int numericalCharCount = 0;

        for (int i = 0; i < voucherCodeLength; i++) {
            if (voucherCode.charAt(i) >= '0' && voucherCode.charAt(i) <= '9')
                numericalCharCount++;
        }

        if (numericalCharCount != 8)
            throw new InvalidPaymentDataException();
    }

    private void validateBankTransfer(String bankName, String deliveryFee) {
        if (
            bankName == null ||
            deliveryFee == null ||
            bankName.isEmpty() ||
            deliveryFee.isEmpty()
        )
            throw new InvalidPaymentDataException();
    }

    private void validatePaymentData(String method, Map<String, String> paymentData) {
        if (method.equals(PaymentMethod.VOUCHER_CODE.getValue())) {
            if (!paymentData.containsKey("voucherCode"))
                throw new InvalidPaymentDataException();

            String voucherCode = paymentData.get("voucherCode");

            validateVoucherCode(voucherCode);
        } else if (method.equals(PaymentMethod.BANK_TRANSFER.getValue())) {
            if (
                !paymentData.containsKey("bankName") ||
                !paymentData.containsKey("deliveryFee")
            )
                throw new InvalidPaymentDataException();

            String bankName = paymentData.get("bankName");
            String deliveryFee = paymentData.get("deliveryFee");

            validateBankTransfer(bankName, deliveryFee);
        }
    }

    public Payment addPayment(Order order, String method, Map<String, String> paymentData) {
        String status;
        try {
            validatePaymentData(method, paymentData);
            status = PaymentStatus.SUCCESS.getValue();
        } catch (InvalidPaymentDataException exception) {
            status = PaymentStatus.REJECTED.getValue();
        }

        Payment payment = new Payment(order.getId(), method, status, paymentData);
        paymentRepository.save(payment);

        return payment;
    }

    public Payment setStatus(Payment payment, String paymentStatus) {
        try {
            payment.setStatus(paymentStatus);
        } catch (IllegalArgumentException exception) {
            throw new IllegalArgumentException(exception);
        }

        String paymentId = payment.getId();
        Order order = orderRepository.findById(paymentId);

        if (paymentStatus.equals(PaymentStatus.SUCCESS.getValue()))
            order.setStatus(OrderStatus.SUCCESS.getValue());
        else if (paymentStatus.equals(PaymentStatus.REJECTED.getValue()))
            order.setStatus(OrderStatus.FAILED.getValue());

        return payment;
    }

    public Payment getPayment(String paymentId) {
        return paymentRepository.findById(paymentId);
    }

    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }
}

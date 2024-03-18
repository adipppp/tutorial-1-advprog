package id.ac.ui.cs.advprog.eshop.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.stereotype.Repository;

import id.ac.ui.cs.advprog.eshop.model.Payment;

@Repository
public class PaymentRepository {
    private List<Payment> paymentList = new ArrayList<>();

    public Payment save(Payment payment) {
        int i = 0;
        for (Payment savedPayment : paymentList) {
            if (savedPayment.getId().equals(payment.getId())) {
                paymentList.remove(i);
                paymentList.add(i, payment);
                return payment;
            }
            i += 1;
        }

        paymentList.add(payment);
        return payment;
    }

    public Payment findById(String id) {
        for (Payment savedPayment : paymentList) {
            if (savedPayment.getId().equals(id)) {
                return savedPayment;
            }
        }
        throw new NoSuchElementException();
    }

    public List<Payment> findAll() {
        return paymentList;
    }
}

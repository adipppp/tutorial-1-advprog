package id.ac.ui.cs.advprog.eshop.model;

import java.util.Arrays;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Payment {
    String id;
    String method;
    String status;
    Map<String, String> paymentData;

    public Payment(String id, String method, String status, Map<String, String> paymentData) {
        this.id = id;
        this.paymentData = paymentData;
        setMethod(method);
        setStatus(status);
    }

    public void setMethod(String method) {
        String[] methodList = {"VOUCHER_CODE", "BANK_TRANSFER"};
        if (Arrays.stream(methodList).noneMatch(item -> (item.equals(method)))) {
            throw new IllegalArgumentException();
        }
        this.method = method;
    }

    public void setStatus(String status) {
        String[] statusList = {"ACCEPTED", "REJECTED"};
        if (Arrays.stream(statusList).noneMatch(item -> (item.equals(status)))) {
            throw new IllegalArgumentException();
        }
        this.status = status;
    }
}

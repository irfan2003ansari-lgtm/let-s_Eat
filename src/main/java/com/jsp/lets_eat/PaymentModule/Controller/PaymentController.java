package com.jsp.lets_eat.PaymentModule.Controller;

import com.jsp.lets_eat.PaymentModule.Dto.PaymentRequest;
import com.jsp.lets_eat.PaymentModule.Dto.PaymentResponse;
import com.jsp.lets_eat.PaymentModule.Service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/lets-eat/payment")
public class PaymentController {
    private final PaymentService paymentService;

    @GetMapping("/{paymentId}")
    public ResponseEntity<PaymentResponse>
    getPaymentById(@PathVariable Long paymentId) {

        return ResponseEntity.ok(
                paymentService.getPaymentById(paymentId));
    }

    @GetMapping("/order/{orderId}")
    public ResponseEntity<PaymentResponse>
    getPaymentByOrderId(@PathVariable Long orderId) {

        return ResponseEntity.ok(
                paymentService.getPaymentByOrderId(orderId));
    }
}

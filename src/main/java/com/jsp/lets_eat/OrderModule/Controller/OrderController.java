package com.jsp.lets_eat.OrderModule.Controller;

import com.jsp.lets_eat.OrderModule.Dto.OrderRequest;
import com.jsp.lets_eat.OrderModule.Dto.OrderResponse;
import com.jsp.lets_eat.OrderModule.Model.Status;
import com.jsp.lets_eat.OrderModule.Service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/lets-eat/order")
public class OrderController {
    private final OrderService orderService;

     @PostMapping("/placed")
    public ResponseEntity<OrderResponse> createOrder(@RequestBody OrderRequest request) {
         return ResponseEntity.status(HttpStatus.CREATED).body(orderService.createOrder(request));
     }

     @GetMapping("/{orderId}")
     public ResponseEntity<OrderResponse> getOrderById(@PathVariable Long orderId) {
         return ResponseEntity.status(HttpStatus.OK).body(orderService.getOrderById(orderId));
     }

     @GetMapping("/ordersByUserId")
     public ResponseEntity<List<OrderResponse>> findOrdersByUserId(@RequestParam Integer userId) {
         return ResponseEntity.status(HttpStatus.OK).body(orderService.findOrdersByUserId(userId));
     }

     @PutMapping("/cancel")
     public ResponseEntity<OrderResponse> cancelOrder(@RequestParam Long orderId) {
         return ResponseEntity.status(HttpStatus.OK).body(orderService.cancelOrder(orderId));
     }

     @GetMapping("/allOrders")
     public ResponseEntity<List<OrderResponse>> findAllOrders() {
         return ResponseEntity.status(HttpStatus.OK).body(orderService.findAllOrders());
     }

     @PatchMapping("/statusUpdate")
     public ResponseEntity<OrderResponse> updateStatus(@RequestParam Long orderId, @RequestParam Status status) {
         return ResponseEntity.status(HttpStatus.OK).body(orderService.updateStatus(orderId,status));
     }
}

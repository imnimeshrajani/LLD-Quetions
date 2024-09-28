package com.assignment.controllers;

import com.assignment.dtos.MakePaymentRequestDto;
import com.assignment.dtos.MakePaymentResponseDto;
import com.assignment.dtos.ResponseStatus;
import com.assignment.models.Payment;
import com.assignment.services.PaymentService;

public class PaymentController {

    private PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    public MakePaymentResponseDto makePayment(MakePaymentRequestDto makePaymentRequestDto) {
        return null;
    }
}
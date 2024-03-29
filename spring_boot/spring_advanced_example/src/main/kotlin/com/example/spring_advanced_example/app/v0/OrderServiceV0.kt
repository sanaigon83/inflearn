package com.example.spring_advanced_example.app.v0

import org.springframework.stereotype.Service

@Service
class OrderServiceV0(
    private val orderRepositoryV0: OrderRepositoryV0
) {

    fun orderItem(itemId: String) {
        orderRepositoryV0.save(itemId)
    }
}
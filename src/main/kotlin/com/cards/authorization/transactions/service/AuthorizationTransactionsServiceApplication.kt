package com.cards.authorization.transactions.service

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients

@SpringBootApplication
@EnableFeignClients
class AuthorizationTransactionsServiceApplication

fun main(args: Array<String>) {
	runApplication<AuthorizationTransactionsServiceApplication>(*args)
}

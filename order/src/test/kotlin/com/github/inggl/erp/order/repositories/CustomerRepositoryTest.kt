package com.github.inggl.erp.order.repositories

import com.github.inggl.erp.order.entities.Address
import com.github.inggl.erp.order.entities.Customer
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import java.util.*

@DataJpaTest
class CustomerRepositoryTest {
    @Autowired
    lateinit var entityManager: TestEntityManager

    @Autowired
    lateinit var customerRepository: CustomerRepository

    @Test
    fun `When FindById then return Customer`() {
        val customer = Customer(
            UUID.randomUUID(),
            "User",
            Address(UUID.randomUUID(), "Grzybowska 62, Warszawa, Poland", "00855")
        )

        entityManager.persist(customer)
        entityManager.flush()

        val result = customerRepository.findById(customer.id)

        Assertions.assertTrue(result.isPresent)
        Assertions.assertEquals(customer.id, result.get().id)
    }
}
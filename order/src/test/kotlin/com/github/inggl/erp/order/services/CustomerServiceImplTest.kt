package com.github.inggl.erp.order.services

import com.github.inggl.erp.order.entities.Address
import com.github.inggl.erp.order.entities.Customer
import com.github.inggl.erp.order.repositories.CustomerRepository
import io.mockk.*
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import org.junit.jupiter.api.*
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.test.context.junit.jupiter.SpringExtension
import java.util.*

@ExtendWith(SpringExtension::class)
internal class CustomerServiceImplTest {

    @MockK
    private lateinit var customerRepository: CustomerRepository

    @InjectMockKs
    private lateinit var customerService: CustomerServiceImpl

    private lateinit var customer: Customer

    @BeforeEach
    fun setUp() {
        MockKAnnotations.init(this)

        customer = Customer(
            UUID.randomUUID(),
            "User",
            Address(UUID.randomUUID(), "Grzybowska 62, Warszawa, Poland", "00855")
        )
    }

    @AfterEach
    fun tearDown() {
    }

    @Nested
    @DisplayName("Find customer")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class FindCustomer {
        @Test
        fun findAll() {
            every { customerRepository.findAll() } returns listOf(customer)

            val findAll = customerService.findAll()

            verify(exactly = 1) {customerRepository.findAll()}

            Assertions.assertEquals(1, findAll.size)
        }

        @Test
        fun findById() {
            every { customerRepository.findById(any()) } returns Optional.of(customer)

            val findById = customerService.findById(UUID.randomUUID())

            verify(exactly = 1) {customerRepository.findById(allAny())}

            Assertions.assertNotNull(findById)
        }
    }

    @Nested
    @DisplayName("Save customer")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class SaveCustomer {
        @Test
        fun save() {
            every { customerRepository.save(any()) } returns customer
        }
    }

    @Nested
    @DisplayName("Update customer")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class UpdateCustomer {
        @Test
        fun update() {
            every { customerRepository.save(any()) } returns customer
        }
    }

    @Nested
    @DisplayName("Delete customer")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class DeleteCustomer {
        @Test
        fun deleteById() {
            every { customerRepository.deleteAllById(any()) } returns mockk()
        }
    }
}
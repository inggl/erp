package com.github.inggl.erp.order.controllers

import com.github.inggl.erp.order.dtos.AddressDto
import com.github.inggl.erp.order.dtos.CustomerDto
import com.github.inggl.erp.order.services.CustomerService
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.*
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
import org.springframework.context.ApplicationContext
import org.springframework.http.MediaType
import org.springframework.security.test.context.support.WithMockUser
import org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.csrf
import org.springframework.security.test.web.reactive.server.SecurityMockServerConfigurers.springSecurity
import org.springframework.test.context.junit.jupiter.SpringExtension
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.web.reactive.function.BodyInserters
import java.util.*

@ExtendWith(SpringExtension::class)
@WebFluxTest(CustomerController::class)
@WithMockUser
internal class CustomerControllerTest {
    private val baseUrl = "/customers"

    @Autowired
    private lateinit var applicationContext: ApplicationContext

    @Autowired
    private lateinit var webTestClient: WebTestClient

    @MockkBean
    private lateinit var customerService: CustomerService

    private val customerDto: CustomerDto = CustomerDto(
        UUID.randomUUID(),
        "User",
        AddressDto(UUID.randomUUID(), "Grzybowska 62, Warszawa, Poland", "00855")
    )

    @BeforeEach
    fun setUp() {
        webTestClient = WebTestClient.bindToApplicationContext(applicationContext)
            .apply(springSecurity()).build().mutateWith(csrf())
    }

    @AfterEach
    fun tearDown() {
    }


    @Nested
    @DisplayName("Find all customers")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class FindCustomers {
        @Test
        fun `should return all customers`() {
            val customerDtos = listOf(customerDto, customerDto)

            every { customerService.findAll() } returns customerDtos

            webTestClient.get()
                .uri(baseUrl)
                .accept(MediaType.APPLICATION_JSON)
                .exchange()
                .expectStatus().isOk
        }
    }


    @Nested
    @DisplayName("Find customer by id")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    inner class FindCustomerById {
        @Test
        fun `should return customer with given id`() {
            every { customerService.findById(customerDto.id) } returns Optional.of(customerDto)

            webTestClient.get()
                .uri("$baseUrl/${customerDto.id}")
                .exchange()
                .expectStatus().isOk
                .expectBody()
                .jsonPath("$.name").isEqualTo(customerDto.name)
        }

        @Test
        fun `should return not found if customer does not exist`() {
            // Generate not existing customer ID
            var customerId = UUID.randomUUID()

            while (customerDto.id == customerId) {
                customerId = UUID.randomUUID()
            }

            every { customerService.findById(customerId) } returns Optional.empty()

            webTestClient.get()
                .uri("$baseUrl/$customerId")
                .exchange()
                .expectStatus().isNotFound
        }
    }

    @Nested
    @DisplayName("Save customer")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    @WithMockUser(roles = ["ADMIN"])
    inner class SaveCustomer {
        @Test
        fun `should save customer`() {
            every { customerService.save(any()) } returns customerDto

            webTestClient.post()
                .uri(baseUrl)
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(customerDto))
                .exchange()
                .expectStatus().isOk
                .expectBody()
                .jsonPath("$.name").isEqualTo(customerDto.name)
        }
    }

    @Nested
    @DisplayName("Update customer")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    @WithMockUser(roles = ["ADMIN"])
    inner class UpdateCustomer {
        @Test
        fun `should update customer`() {
            every { customerService.findById(any()) } returns Optional.of(customerDto)
            every { customerService.update(any()) } returns customerDto

            webTestClient.put()
                .uri(baseUrl)
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromValue(customerDto))
                .exchange()
                .expectStatus().isOk
                .expectBody()
                .jsonPath("$.name").isEqualTo(customerDto.name)
        }
    }

    @Nested
    @DisplayName("Delete customer")
    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    @WithMockUser(roles = ["ADMIN"])
    inner class DeleteCustomer {
        @Test
        fun `should delete customer by id`() {
            every { customerService.findById(customerDto.id) } returns Optional.of(customerDto)
            every { customerService.deleteById(customerDto.id) } returns mockk()

            webTestClient.delete()
                .uri("$baseUrl/${customerDto.id}")
                .exchange()
                .expectStatus().isOk
        }

        @Test
        fun `should return not found if customer does not exist`() {
            val customerId: UUID = UUID.randomUUID()

            every { customerService.findById(any()) } returns Optional.empty()
            every { customerService.deleteById(any()) } returns mockk()

            webTestClient.delete()
                .uri("$baseUrl/${customerId}")
                .exchange()
                .expectStatus().isNotFound
        }
    }
}
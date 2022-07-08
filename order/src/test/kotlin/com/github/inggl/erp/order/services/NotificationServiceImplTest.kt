package com.github.inggl.erp.order.services

import okhttp3.mockwebserver.MockWebServer
import org.junit.jupiter.api.*
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
internal class NotificationServiceImplTest {
    private var mockWebServer: MockWebServer = MockWebServer()

    @BeforeAll
    fun beforeAll() {
        mockWebServer.start()
    }

    @AfterAll
    fun afterAll() {
        mockWebServer.shutdown()
    }

    @BeforeEach
    fun setUp() {
    }

    @AfterEach
    fun tearDown() {
    }

    @Test
    fun send() {

    }
}
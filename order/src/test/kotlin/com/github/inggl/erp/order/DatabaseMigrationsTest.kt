package com.github.inggl.erp.order

import org.junit.Test
import org.testcontainers.containers.PostgreSQLContainer
import org.testcontainers.junit.jupiter.Container
import org.testcontainers.junit.jupiter.Testcontainers

@Testcontainers
class DatabaseMigrationsTest {
    @Container
    val postgreSQLContainer = PostgreSQLContainer("postgres:latest").withDatabaseName("db")
        .withUsername("postgres")
        .withPassword("postgres")

    @Test
    fun testMigration() {

    }
}
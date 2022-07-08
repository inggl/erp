package com.github.inggl.erp.order.repositories

import com.github.inggl.erp.order.entities.Dashboard
import org.springframework.data.mongodb.repository.ReactiveMongoRepository
import org.springframework.stereotype.Repository

@Repository
interface DashboardRepository: ReactiveMongoRepository<Dashboard, String> {
}
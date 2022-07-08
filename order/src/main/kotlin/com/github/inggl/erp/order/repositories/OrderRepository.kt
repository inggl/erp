package com.github.inggl.erp.order.repositories

import com.github.inggl.erp.order.entities.Order
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.JpaSpecificationExecutor
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface OrderRepository : JpaRepository<Order, UUID>, JpaSpecificationExecutor<Order>
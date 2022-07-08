package com.github.inggl.erp.order.repositories

import com.github.inggl.erp.order.entities.Supplier
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface SupplierRepository : JpaRepository<Supplier, UUID>
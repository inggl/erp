package com.github.inggl.erp.order.repositories

import com.github.inggl.erp.order.entities.Address
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.PagingAndSortingRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface AddressRepository : JpaRepository<Address, UUID>
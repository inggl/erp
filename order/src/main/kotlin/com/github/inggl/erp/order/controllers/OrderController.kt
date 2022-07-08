package com.github.inggl.erp.order.controllers

import com.github.inggl.erp.order.dtos.OrderDto
import com.github.inggl.erp.order.enums.FilterOperator
import com.github.inggl.erp.order.services.OrderService
import com.github.inggl.erp.order.specifications.FilterCriteria
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*
import java.util.*
import javax.validation.Valid

/**
 * Order API
 * */
@RestController
@RequestMapping("/orders")
class OrderController(private val orderService: OrderService) {
    /**
     * Find all orders or page
     * */
    @GetMapping("/")
    fun findPage(
        @RequestParam page: Int?,
        @RequestParam size: Int?,
        @RequestParam sortBy: String?,
        @RequestParam direction: String?,
        @RequestParam filterBy: String?,
        @RequestParam filterValue: String?,
        @RequestParam filterOperator: FilterOperator?
    ): ResponseEntity<Page<OrderDto>> {
        var pageRequest: PageRequest? = null

        if (page != null && size != null) {
            pageRequest = PageRequest.of(page, size)
        }

        if (sortBy != null && direction != null) {
            val sort: Sort = if (direction == "asc") {
                Sort.by(sortBy).ascending()
            } else {
                Sort.by(sortBy).descending()
            }

            checkNotNull(pageRequest) {
                "Cannot sort request without pagination"
            }

            pageRequest = pageRequest.withSort(sort)
        }

        var filter: FilterCriteria? = null
        if (!filterBy.isNullOrEmpty() && filterOperator != null) {
            filter = FilterCriteria(filterBy, filterValue, filterOperator)
        }

        if (pageRequest != null && filter != null) {
            return ResponseEntity.ok().body(orderService.findPage(pageRequest, listOf(filter)))
        }

        if (pageRequest != null) {
            return ResponseEntity.ok().body(orderService.findPage(pageRequest))
        }

        return ResponseEntity.ok().body(PageImpl(orderService.findAll()))
    }

    /**
     * Get order by order id
     * */
    @GetMapping("/{orderId}")
    fun findById(@PathVariable orderId: UUID): ResponseEntity<OrderDto> {
        return orderService.findById(orderId).map { ResponseEntity.ok(it) }
            .orElse(ResponseEntity.notFound().build())
    }

    /**
     * Create order
     */
    @PostMapping("/")
    @PreAuthorize("hasRole('ADMIN')")
    fun save(@Valid @RequestBody orderDto: OrderDto): ResponseEntity<OrderDto> =
        ResponseEntity.ok(orderService.save(orderDto))

    /**
     * Update order
     * */
    @PutMapping("/")
    @PreAuthorize("hasRole('ADMIN')")
    fun update(@Valid @RequestBody orderDto: OrderDto): ResponseEntity<OrderDto> {
        return orderService.findById(orderDto.id).map {
            ResponseEntity.ok().body(orderService.update(orderDto))
        }.orElse(ResponseEntity.notFound().build())
    }

    /**
     * Delete order by id
     * */
    @DeleteMapping("/{orderId}")
    @PreAuthorize("hasRole('ADMIN')")
    fun deleteById(@PathVariable orderId: UUID): ResponseEntity<Void> {
        return orderService.findById(orderId).map {
            orderService.deleteById(orderId)
            ResponseEntity<Void>(HttpStatus.OK)
        }.orElse(ResponseEntity.notFound().build())
    }

}
package com.github.inggl.erp.order.services

import com.github.inggl.erp.order.dtos.DashboardDto
import com.github.inggl.erp.order.mappers.DashboardMapper
import com.github.inggl.erp.order.repositories.DashboardRepository
import org.mapstruct.factory.Mappers
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux

@Service
class DashboardServiceImpl(val dashboardRepository: DashboardRepository) : DashboardService {
    companion object {
        val dashboardMapper: DashboardMapper = Mappers.getMapper(DashboardMapper::class.java)
    }

    override fun findAll(): Flux<DashboardDto> {
        return dashboardRepository.findAll().map {
            dashboardMapper.toDto(it)
        }
    }
}
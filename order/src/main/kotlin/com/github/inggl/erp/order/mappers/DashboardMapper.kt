package com.github.inggl.erp.order.mappers

import com.github.inggl.erp.order.dtos.DashboardDto
import com.github.inggl.erp.order.entities.Dashboard
import org.mapstruct.Mapper

@Mapper
interface DashboardMapper {
    fun toDto(dashboard: Dashboard): DashboardDto
    fun toEntity(dashboardDto: DashboardDto): Dashboard
}
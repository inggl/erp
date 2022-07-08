package com.github.inggl.erp.order.entities

import java.math.BigDecimal
import java.time.LocalDateTime
import javax.persistence.*

@Entity
@Table(name = "SUPPLIER_PRODUCT")
class SupplierProduct(
    @EmbeddedId
    @AttributeOverrides(
        AttributeOverride(name = "supplierId", column = Column(name = "ID")),
        AttributeOverride(name = "productId", column = Column(name = "ID"))
    )
    var id: SupplierProductId,

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("supplierId")
    var supplier: Supplier,

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("productId")
    var product: Product,

    @Column(name = "QUANTITY")
    var quantity: Int,

    @Column(name = "UNIT_PRICE")
    var unitPrice: BigDecimal
) : BaseEntity()

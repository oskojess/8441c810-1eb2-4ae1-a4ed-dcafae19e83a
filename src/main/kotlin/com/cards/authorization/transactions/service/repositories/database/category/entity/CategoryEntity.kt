package com.cards.authorization.transactions.service.repositories.database.category.entityimport com.cards.authorization.transactions.service.domain.account.entites.types.AccountCategoryTypeimport com.cards.authorization.transactions.service.domain.category.entity.Categoryimport org.hibernate.annotations.CreationTimestampimport jakarta.persistence.Columnimport jakarta.persistence.Enumeratedimport jakarta.persistence.EnumTypeimport jakarta.persistence.Entityimport jakarta.persistence.Idimport jakarta.persistence.Tableimport jakarta.persistence.Temporalimport jakarta.persistence.TemporalTypeimport org.hibernate.annotations.UpdateTimestampimport java.time.LocalDateTime@Entity@Table(name = "category")data class CategoryEntity(    @Id    @Column(name = "id", nullable = false)    val id: String,    @Column(nullable = false)    @Enumerated(EnumType.STRING)    val type: AccountCategoryType? = null,    @CreationTimestamp    @Temporal(TemporalType.TIMESTAMP)    @Column(name = "created_at", updatable = false)    val createdAt: LocalDateTime,    @UpdateTimestamp    @Temporal(TemporalType.TIMESTAMP)    @Column(name = "updated_at")    val updatedAt: LocalDateTime)fun CategoryEntity.toDomain() = Category(    id = id,    type = type,    createdAt = createdAt,    updatedAt = updatedAt)
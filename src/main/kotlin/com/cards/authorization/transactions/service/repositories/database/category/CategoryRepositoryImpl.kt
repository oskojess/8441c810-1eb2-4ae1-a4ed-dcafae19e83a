package com.cards.authorization.transactions.service.repositories.database.categoryimport com.cards.authorization.transactions.service.domain.category.CategoryRepositoryimport com.cards.authorization.transactions.service.domain.category.entity.Categoryimport com.cards.authorization.transactions.service.domain.exceptions.ResourceNotFoundExceptionimport com.cards.authorization.transactions.service.repositories.database.category.entity.toDomainimport org.springframework.stereotype.Componentimport org.springframework.transaction.annotation.Transactional@Component@Transactional(readOnly = true)class CategoryRepositoryImpl(    private val categoryRepositoryJpa: CategoryRepositoryJpa) : CategoryRepository {    override fun findById(id: String): Category {        return categoryRepositoryJpa.findByCategoryId(id)?.toDomain()            ?: throw ResourceNotFoundException("Category not found for ${id}")    }    override fun findByCategoryType(type: String): Category? {        return categoryRepositoryJpa.findByCategoryType(type)?.toDomain()            ?: throw ResourceNotFoundException("Category not found for ${type}")    }}
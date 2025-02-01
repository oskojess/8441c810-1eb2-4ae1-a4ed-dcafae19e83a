package com.cards.authorization.transactions.service.domain.accountimport com.cards.authorization.transactions.service.common.AbstractInitializeTestimport com.cards.authorization.transactions.service.domain.account.entites.types.AccountCategoryType.CASHimport com.cards.authorization.transactions.service.domain.category.CategoryRepositoryimport com.cards.authorization.transactions.service.domain.exceptions.ResourceNotFoundExceptionimport com.cards.authorization.transactions.service.repositories.database.account.entity.toDomainimport com.cards.authorization.transactions.service.repositories.database.category.entity.toDomainimport com.cards.authorization.transactions.service.common.AccountBuilder.buildAccountimport com.cards.authorization.transactions.service.common.CategoryBuilder.buildCategoryimport io.mockk.everyimport io.mockk.mockkimport io.mockk.unmockkAllimport org.junit.jupiter.api.AfterEachimport org.junit.jupiter.api.Testimport org.junit.jupiter.api.assertThrowsimport kotlin.test.assertEqualsinternal class AccountServiceTest : AbstractInitializeTest() {    private val accountRepository = mockk<AccountRepository>()    private val categoryRepository = mockk<CategoryRepository>()    private val accountService = AccountService(accountRepository, categoryRepository)    @AfterEach    fun tearDown() {        unmockkAll()    }    @Test    fun `must be a valid type when category exists then return the account successfully`() {        val categoryType = CASH.name        val category = buildCategory().toDomain()        val account = buildAccount().toDomain()        every { categoryRepository.findByCategoryType(categoryType) } returns category        every { accountRepository.findAccountByCategoryId(category.id) } returns account        val result = accountService.findAccountByCategoryType(categoryType)        assertEquals(account, result)    }    @Test    fun `must be invalid id then throw an exception`() {        val categoryType = "invalid"        every { categoryRepository.findByCategoryType(categoryType) } returns null        assertThrows<ResourceNotFoundException> {            accountService.findAccountByCategoryType(categoryType)        }    }}
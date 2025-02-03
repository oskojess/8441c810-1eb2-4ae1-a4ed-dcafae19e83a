package com.cards.authorization.transactions.service.application.serviceimport com.cards.authorization.transactions.service.application.config.VisaMccPropertiesConfigimport com.cards.authorization.transactions.service.application.service.dto.TransactionDTOimport com.cards.authorization.transactions.service.application.service.strategy.CashCategoryAuthorizationStrategyimport com.cards.authorization.transactions.service.application.service.strategy.PrimaryCategoryAuthorizationStrategyimport com.cards.authorization.transactions.service.application.service.type.AuthorizationCode.INSUFFICIENT_BALANCEimport com.cards.authorization.transactions.service.application.service.type.AuthorizationCode.OTHER_ERRORimport com.cards.authorization.transactions.service.domain.account.AccountServiceimport com.cards.authorization.transactions.service.domain.account.entites.Accountimport com.cards.authorization.transactions.service.domain.account.entites.types.AccountCategoryType.CASHimport com.cards.authorization.transactions.service.domain.category.CategoryRepositoryimport org.springframework.stereotype.Serviceimport org.springframework.transaction.annotation.Transactional@Serviceclass AuthorizationServiceImpl(    private val primaryCategoryAuthorizationStrategy: PrimaryCategoryAuthorizationStrategy,    private val cashCategoryAuthorizationStrategy: CashCategoryAuthorizationStrategy,    private val visaMccPropertiesConfig: VisaMccPropertiesConfig,    private val categoryRepository: CategoryRepository,    private val accountService: AccountService) : AuthorizationService<Account, TransactionDTO> {    @Transactional    override fun authorize(account: Account, transaction: TransactionDTO): String {        val getCodeTransactionsDetails = visaMccPropertiesConfig.getTypeOperations(transaction.mcc)        val categoryTypeTransaction = getCodeTransactionsDetails?.first?.uppercase()        val categoryType = categoryRepository.findById(account.category).type?.name        val cashAccount = accountService.findAccountByCategoryType(CASH.name)        val cashMakeDebit = cashCategoryAuthorizationStrategy.evaluateAuthorization(cashAccount, transaction)        val primaryMakeDebit = primaryCategoryAuthorizationStrategy.evaluateAuthorization(account, transaction)        return when {            getCodeTransactionsDetails == null -> cashMakeDebit            categoryType == categoryTypeTransaction -> primaryMakeDebit            primaryMakeDebit == INSUFFICIENT_BALANCE || categoryType == CASH.name -> cashMakeDebit            else -> OTHER_ERROR        }    }}
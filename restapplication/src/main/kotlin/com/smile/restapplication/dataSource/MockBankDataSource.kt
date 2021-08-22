package com.smile.restapplication.dataSource

import com.smile.restapplication.model.Bank
import org.springframework.stereotype.Repository

@Repository
class MockBankDataSource :DataSource {
    private val banks=listOf(element = Bank("",0.0,1))

    override fun getBanks(): Collection<Bank> = banks
}

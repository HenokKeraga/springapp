package com.smile.restapplication.dataSource

import org.assertj.core.api.Assertions

import org.junit.jupiter.api.Test

internal class MockBankDataSourceTest {
    private val mockBankDataSource: MockBankDataSource = MockBankDataSource()

    @Test
    fun `should provide a collection of bank `() {
        //given

        //when
        val banks = mockBankDataSource.getBanks();

        //then
        Assertions.assertThat(banks).isNotEmpty
    }

    @Test
    fun `should provide some mock data`() {
        //given

        //when
        val banks = mockBankDataSource.getBanks();
        //then
        Assertions.assertThat(banks).anyMatch { it.accountNumber.isNotBlank() }
        Assertions.assertThat(banks).anyMatch { it.trust != 0.0 }
        Assertions.assertThat(banks).anyMatch { it.transactionFee != 0 }

    }
}

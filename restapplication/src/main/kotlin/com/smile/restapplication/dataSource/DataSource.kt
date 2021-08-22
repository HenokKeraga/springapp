package com.smile.restapplication.dataSource

import com.smile.restapplication.model.Bank

interface DataSource {
    fun getBanks():Collection<Bank>
}

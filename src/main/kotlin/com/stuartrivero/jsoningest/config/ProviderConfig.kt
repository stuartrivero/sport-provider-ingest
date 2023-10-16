package com.stuartrivero.jsoningest.config

import com.stuartrivero.jsoningest.model.SportProvider
import com.stuartrivero.jsoningest.service.DataSource1Transformer
import com.stuartrivero.jsoningest.service.DataSource2Transformer
import com.stuartrivero.jsoningest.service.DataSourceTransformer
import org.springframework.stereotype.Component

@Component
class ProviderConfig {
    fun transformerFor(sportProvider: SportProvider): DataSourceTransformer =
        when (sportProvider) {
            SportProvider.PROV1 -> DataSource1Transformer()
            SportProvider.PROV2 -> DataSource2Transformer()
        }
}
package `in`.abhranilnxt.cryptocore.crypto.data.networking.dto

import `in`.abhranilnxt.cryptocore.crypto.domain.CoinPrice
import kotlinx.serialization.Serializable

@Serializable
data class CoinsHistoryDto(
    val data: List<CoinPriceDto>
)

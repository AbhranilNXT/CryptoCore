package `in`.abhranilnxt.cryptocore.crypto.domain

import `in`.abhranilnxt.cryptocore.core.domain.util.NetworkError
import `in`.abhranilnxt.cryptocore.core.domain.util.Result
import java.time.ZonedDateTime

interface CoinDataSource {
    suspend fun getCoins(): Result<List<Coin>, NetworkError>
    suspend fun getCoinHistory(
        coinId: String,
        start: ZonedDateTime,
        end: ZonedDateTime
    ): Result<List<CoinPrice>, NetworkError>
}
package `in`.abhranilnxt.cryptocore.crypto.data.networking

import `in`.abhranilnxt.cryptocore.core.data.networking.constructUrl
import `in`.abhranilnxt.cryptocore.core.data.networking.safeCall
import `in`.abhranilnxt.cryptocore.core.domain.util.NetworkError
import `in`.abhranilnxt.cryptocore.core.domain.util.Result
import `in`.abhranilnxt.cryptocore.core.domain.util.map
import `in`.abhranilnxt.cryptocore.crypto.data.mappers.toCoin
import `in`.abhranilnxt.cryptocore.crypto.data.mappers.toCoinPrice
import `in`.abhranilnxt.cryptocore.crypto.data.networking.dto.CoinsHistoryDto
import `in`.abhranilnxt.cryptocore.crypto.data.networking.dto.CoinsResponseDto
import `in`.abhranilnxt.cryptocore.crypto.domain.Coin
import `in`.abhranilnxt.cryptocore.crypto.domain.CoinDataSource
import `in`.abhranilnxt.cryptocore.crypto.domain.CoinPrice
import io.ktor.client.HttpClient
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import java.time.ZoneId
import java.time.ZonedDateTime

class RemoteCoinDataSource(
    private val httpClient: HttpClient
): CoinDataSource {
    override suspend fun getCoins(): Result<List<Coin>, NetworkError> {
        return safeCall<CoinsResponseDto> {
            httpClient.get(
                urlString = constructUrl("/assets")
            )
        }.map { response ->
            response.data.map { it.toCoin() }
        }
    }

    override suspend fun getCoinHistory(
        coinId: String,
        start: ZonedDateTime,
        end: ZonedDateTime
    ): Result<List<CoinPrice>, NetworkError> {
        val startMillis = start
            .withZoneSameInstant(ZoneId.of("UTC"))
            .toInstant()
            .toEpochMilli()
        val endMillis = end
            .withZoneSameInstant(ZoneId.of("UTC"))
            .toInstant()
            .toEpochMilli()
        return safeCall<CoinsHistoryDto> {
            httpClient.get(
                urlString = constructUrl("/assets/$coinId/history")
            ) {
                parameter("interval", "h6")
                parameter("start", startMillis)
                parameter("end", endMillis)
            }
        }.map { response ->
            response.data.map {
                it.toCoinPrice()
            }
        }
    }
}
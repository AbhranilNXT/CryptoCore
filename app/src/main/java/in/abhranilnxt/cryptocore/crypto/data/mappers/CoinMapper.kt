package `in`.abhranilnxt.cryptocore.crypto.data.mappers

import `in`.abhranilnxt.cryptocore.crypto.data.networking.dto.CoinDto
import `in`.abhranilnxt.cryptocore.crypto.data.networking.dto.CoinPriceDto
import `in`.abhranilnxt.cryptocore.crypto.domain.Coin
import `in`.abhranilnxt.cryptocore.crypto.domain.CoinPrice
import java.time.Instant
import java.time.ZoneId

fun CoinDto.toCoin(): Coin {
    return Coin(
        id = id,
        rank = rank,
        name = name,
        symbol = symbol,
        marketCapUsd = marketCapUsd,
        priceUsd = priceUsd,
        changePercent24Hr = changePercent24Hr
    )
}

fun CoinPriceDto.toCoinPrice(): CoinPrice {
    return CoinPrice(
        priceUsd = priceUsd,
        dateTime = Instant
            .ofEpochMilli(time)
            .atZone(ZoneId.systemDefault())
    )
}
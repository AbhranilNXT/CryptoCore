package `in`.abhranilnxt.cryptocore.crypto.presentation.models

import androidx.annotation.DrawableRes
import `in`.abhranilnxt.cryptocore.core.presentation.util.getDrawableIdForCoin
import `in`.abhranilnxt.cryptocore.crypto.domain.Coin
import `in`.abhranilnxt.cryptocore.crypto.domain.CoinPrice
import `in`.abhranilnxt.cryptocore.crypto.presentation.coin_detail.DataPoint
import java.text.NumberFormat
import java.util.Locale

data class CoinUi(
    val id: String,
    val rank: Int,
    val name: String,
    val symbol: String,
    val marketCapUsd: DisplayableNumber,
    val priceUsd: DisplayableNumber,
    val changePercent24Hr: DisplayableNumber,
    @DrawableRes val iconRes: Int,
    val coinPriceHistory: List<DataPoint> = emptyList()
)

data class DisplayableNumber(
    val value: Double,
    val formattedVariant: String
)

fun Coin.toCoinUi() : CoinUi {
    return CoinUi(
        id = id,
        name = name,
        symbol = symbol,
        rank = rank,
        priceUsd = priceUsd.toDisplayableNumber(),
        marketCapUsd = marketCapUsd.toDisplayableNumber(),
        changePercent24Hr = changePercent24Hr.toDisplayableNumber(),
        iconRes = getDrawableIdForCoin(symbol)
    )
}

fun Double.toDisplayableNumber() : DisplayableNumber {
    val formatter = NumberFormat.getNumberInstance(Locale.getDefault()).apply {
        maximumFractionDigits = 2
        minimumFractionDigits = 2
    }
    return DisplayableNumber(
        value = this,
        formattedVariant = formatter.format(this)
    )
}
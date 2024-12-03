package `in`.abhranilnxt.cryptocore.crypto.presentation.coin_list

import `in`.abhranilnxt.cryptocore.core.domain.util.NetworkError

sealed interface CoinListEvent {
    data class Error(val error: NetworkError): CoinListEvent
}
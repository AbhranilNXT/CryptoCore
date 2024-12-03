package `in`.abhranilnxt.cryptocore.crypto.presentation.coin_list

import `in`.abhranilnxt.cryptocore.crypto.presentation.models.CoinUi

sealed interface CoinListAction {
    data class OnCoinClick(val coinUi: CoinUi): CoinListAction
}
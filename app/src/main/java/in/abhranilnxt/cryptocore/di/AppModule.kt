package `in`.abhranilnxt.cryptocore.di

import `in`.abhranilnxt.cryptocore.core.data.networking.HttpClientFactory
import `in`.abhranilnxt.cryptocore.crypto.data.networking.RemoteCoinDataSource
import `in`.abhranilnxt.cryptocore.crypto.domain.CoinDataSource
import `in`.abhranilnxt.cryptocore.crypto.presentation.coin_list.CoinListViewModel
import io.ktor.client.engine.cio.CIO
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val appModule = module {
    single { HttpClientFactory.create(CIO.create()) }
    singleOf(::RemoteCoinDataSource).bind<CoinDataSource>()

    viewModelOf(::CoinListViewModel)
}
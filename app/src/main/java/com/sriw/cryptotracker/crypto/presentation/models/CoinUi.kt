package com.sriw.cryptotracker.crypto.presentation.models

import androidx.annotation.DrawableRes
import com.sriw.cryptotracker.core.presentation.util.getDrawableIdForCoin
import com.sriw.cryptotracker.crypto.domain.Coin
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
)


data class DisplayableNumber(
    val value: Double,
    val formatted: String,
)

fun Double.toDisplayableNumber(): DisplayableNumber {
    val formatter = NumberFormat.getNumberInstance(Locale.getDefault()).apply {
        minimumIntegerDigits = 2
        maximumIntegerDigits = 2
    }
    return DisplayableNumber(
        value = this,
        formatted = formatter.format(this)
    )
}

fun Coin.toCoinUi(): CoinUi {
    return CoinUi(
        id = id,
        name = name,
        symbol = symbol,
        rank = rank,
        priceUsd = priceUsd.toDisplayableNumber(),
        changePercent24Hr = changePercent24Hr.toDisplayableNumber(),
        marketCapUsd = marketCapUsd.toDisplayableNumber(),
        iconRes = getDrawableIdForCoin(symbol)
    )
}

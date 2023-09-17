package com.cmesquita.realstation.extensions

import android.icu.text.NumberFormat
import android.icu.util.Currency
import java.math.BigDecimal

fun BigDecimal.formatCurrency(currencyCode: String = "EUR"): String {
    val format: NumberFormat = NumberFormat.getCurrencyInstance()
    format.maximumFractionDigits = 0
    format.currency = Currency.getInstance(currencyCode)

    return format.format(this)
}

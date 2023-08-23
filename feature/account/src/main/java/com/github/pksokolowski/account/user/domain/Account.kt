package com.github.pksokolowski.account.user.domain

internal data class Account(
    val salt1: List<Byte>,
    val salt2: List<Byte>,
)
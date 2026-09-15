package com.waxd.messaging.ui.classzero.model

internal sealed interface ClassZeroScreenEffect {
    data object Finish : ClassZeroScreenEffect
}

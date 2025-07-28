package com.kturker.core.presentation.progresscentricnotification

sealed class ProgressCentricOrderState {
    data object Confirmed : ProgressCentricOrderState()
    data object Preparing : ProgressCentricOrderState()
    data object Enroute : ProgressCentricOrderState()
    data object Arriving : ProgressCentricOrderState()
    data object Delivered : ProgressCentricOrderState()
}
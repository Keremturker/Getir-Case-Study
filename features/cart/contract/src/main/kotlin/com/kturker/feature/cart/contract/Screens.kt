@file:Suppress("MatchingDeclarationName", "AnnotationOnSeparateLine")

package com.kturker.feature.cart.contract

import com.kturker.navigation.NavigationCommand
import kotlinx.serialization.Serializable

@Serializable
data object CartScreenDestination : NavigationCommand.Destination

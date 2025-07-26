package com.kturker.core.presentation

import android.net.Uri
import android.os.Bundle
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.SizeTransform
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.lifecycle.SavedStateHandle
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDeepLink
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavType
import androidx.navigation.compose.composable
import com.kturker.navigation.NavigationCommand
import kotlinx.serialization.KSerializer
import kotlinx.serialization.json.Json
import kotlin.reflect.KType

@OptIn(ExperimentalAnimationApi::class)
inline fun <reified T : NavigationCommand> NavGraphBuilder.ktAnimatedDestination(
    typeMap: Map<KType, @JvmSuppressWildcards NavType<*>> = emptyMap(),
    deepLinks: List<NavDeepLink> = emptyList(),
    noinline enterTransition:
    (AnimatedContentTransitionScope<NavBackStackEntry>.() -> @JvmSuppressWildcards
    EnterTransition?)? =
        { slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Start, tween(500)) },
    noinline exitTransition:
    (AnimatedContentTransitionScope<NavBackStackEntry>.() -> @JvmSuppressWildcards
    ExitTransition?)? =
        { slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Start, tween(500)) },
    noinline popEnterTransition:
    (AnimatedContentTransitionScope<NavBackStackEntry>.() -> @JvmSuppressWildcards
    EnterTransition?)? =
        { slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.End, tween(500)) },
    noinline popExitTransition:
    (AnimatedContentTransitionScope<NavBackStackEntry>.() -> @JvmSuppressWildcards
    ExitTransition?)? =
        { slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.End, tween(500)) },
    noinline sizeTransform:
    (AnimatedContentTransitionScope<NavBackStackEntry>.() -> @JvmSuppressWildcards
    SizeTransform?)? =
        null,
    noinline content: @Composable AnimatedContentScope.(NavBackStackEntry) -> Unit
) {
    composable(
        T::class,
        typeMap,
        deepLinks,
        enterTransition,
        exitTransition,
        popEnterTransition,
        popExitTransition,
        sizeTransform,
        content
    )
}

inline fun <reified T> SavedStateHandle.getTypedArg(key: String = "args"): T {
    val json = this[key] as? String
        ?: error("Missing argument for key: $key")
    return Json.decodeFromString(json)
}

class CustomNavType<T>(
    private val serializer: KSerializer<T>
) : NavType<T>(isNullableAllowed = false) {

    override fun put(bundle: Bundle, key: String, value: T) {
        bundle.putString(key, Json.encodeToString(serializer, value))
    }

    override fun get(bundle: Bundle, key: String): T? {
        return bundle.getString(key)?.let {
            Json.decodeFromString(serializer, it)
        }
    }

    override fun parseValue(value: String): T {
        return Json.decodeFromString(serializer, Uri.decode(value))
    }

    override fun serializeAsValue(value: T): String {
        return Uri.encode(Json.encodeToString(serializer, value))
    }
}
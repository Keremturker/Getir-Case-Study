package com.kturker.core.presentation

import androidx.lifecycle.SavedStateHandle
import kotlinx.serialization.json.Json

const val KEY_ARGS = "ARGS"

inline fun <reified T> SavedStateHandle.putJson(key: String, value: T, json: Json = Json) {
    this[key] = json.encodeToString(value)
}

inline fun <reified T> SavedStateHandle.getJson(key: String, json: Json = Json): T {
    val raw = checkNotNull(this[key] as? String) { "Missing saved state for key=$key" }
    return json.decodeFromString(raw)
}

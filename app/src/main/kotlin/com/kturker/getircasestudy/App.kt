package com.kturker.getircasestudy

import android.app.Application
import android.content.Intent
import androidx.core.content.pm.ShortcutInfoCompat
import androidx.core.content.pm.ShortcutManagerCompat
import androidx.core.graphics.drawable.IconCompat
import com.kturker.uikit.R
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
internal class App : Application() {

    override fun onCreate() {
        super.onCreate()
        createShareShortcut()
    }

    private fun createShareShortcut(): Boolean {
        val shortcut = ShortcutInfoCompat.Builder(this, SHORTCUT_ID)
            .setLongLived(true)
            .setShortLabel(getString(com.kturker.getircasestudy.R.string.goToCartShortCut))
            .setIcon(
                IconCompat.createWithResource(this, R.drawable.placeholder)
            )
            .setIntent(
                Intent(this, MainActivity::class.java).apply {
                    action = Intent.ACTION_VIEW
                    putExtra(SHORTCUT_ID, ShortCutType.GotoCart.type)
                }
            )
            .build()
        return ShortcutManagerCompat.setDynamicShortcuts(this, listOf(shortcut))
    }
}

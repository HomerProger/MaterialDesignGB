package com.example.materialdesigngb.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.annotation.StyleRes
import com.example.materialdesigngb.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setTheme(ThemeHolder.theme)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .addToBackStack("")
                .replace(R.id.container, PODFragment.newInstance())
                .commit()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.appbar_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        return when (item.itemId) {
            R.id.settings -> {
                supportFragmentManager.beginTransaction()
                    .addToBackStack("settings")
                    .replace(R.id.container, SettingsFragment.newInstance())
                    .commit()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    fun changeTheme(@StyleRes themeID: Int) {
        ThemeHolder.theme = themeID
        recreate()
    }

}

object ThemeHolder {
    var theme = R.style.Theme_MaterialDesignGB
}
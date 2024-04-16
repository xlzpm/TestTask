package com.example.testtask.data.preferences

import android.content.Context
import androidx.core.content.edit
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import com.example.testtask.model.Token

class TimeToken(context: Context) {

    private val sharedPreferences = EncryptedSharedPreferences.create(
        context,
        FILE,
        MasterKey.Builder(context).setKeyScheme(MasterKey.KeyScheme.AES256_GCM).build(),
        EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
        EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
    )

    fun saveToken(token: Token){
        sharedPreferences.edit {
            putString(KEY, token.accessToken)
            commit()
        }
    }

    fun getToken() = sharedPreferences.getString(KEY, "token not found")

    companion object {
        const val FILE = "pet_auth_file"
        const val KEY = "pet_auth_key"
    }
}
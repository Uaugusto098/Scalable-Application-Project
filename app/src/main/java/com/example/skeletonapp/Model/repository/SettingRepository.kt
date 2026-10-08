package com.example.skeletonapp.Model.repository

import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.skeletonapp.Model.osc.OscDestiny
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton


// Arquivo responsável pelo assunto do app, como Cena. O SettingsRepository guarda e lê as preferências do operador.
 private val Context.dataStore by preferencesDataStore(name = "settings")


@Singleton
class SettingRepository @Inject constructor(@ApplicationContext private val context: Context) {



    private object Chaves{
        val IP=stringPreferencesKey("osc_ip")
        val PORT=intPreferencesKey("osc_port")
    }

    val destino:Flow<OscDestiny?> = context.dataStore.data.map { prefs->
        val ipSave=prefs[Chaves.IP]
        val portSave=prefs[Chaves.PORT]
        if(ipSave!=null && portSave!=null) OscDestiny(ipSave=ipSave,portSave=portSave) else null
    }


    suspend fun savePrefsDestiny(destino: OscDestiny){
        context.dataStore.edit{prefs->
            prefs[Chaves.IP]=destino.ipSave
            prefs[Chaves.PORT]=destino.portSave
        }
    }



}



package com.example.skeletonapp.Model.repository

import com.example.skeletonapp.Model.api.ConfigApi
import com.example.skeletonapp.Model.api.ConfigDto
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.serialization.json.Json
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ConfigRepository @Inject constructor(
    private val api: ConfigApi,
    private val settings: SettingRepository,
    private val json: Json
) {
    /** Última config salva no tablet (funciona sem rede). */
    val config: Flow<ConfigDto?> = settings.configJson.map { texto ->
        texto?.let { runCatching { json.decodeFromString<ConfigDto>(it) }.getOrNull() }
    }

    /** Busca no servidor e salva. Se a rede falhar, devolve null e o app segue com a config salva. */
    suspend fun atualizar(arquivo: String): ConfigDto? =
        try {
            val novo = api.buscarConfig(arquivo)
            settings.saveConfigJson(json.encodeToString(novo))
            novo
        } catch (e: CancellationException) {
            throw e
        } catch (e: Exception) {
            null
        }
}
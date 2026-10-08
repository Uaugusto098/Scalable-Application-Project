package com.example.skeletonapp.Model.repository

import com.example.skeletonapp.Model.osc.OscDestiny
import com.example.skeletonapp.Model.osc.OscSender
import javax.inject.Inject
import javax.inject.Singleton


// Arquivo responsável pela fase de repository, executar a função limpa utilizando os outros arquivos

@Singleton
class MainRepository @Inject constructor(private val oscSender: OscSender) {



     suspend fun sendCues(destino: OscDestiny, cena: String){
         oscSender.send(destino.ipSave, destino.portSave,cena,1)
    }





    }




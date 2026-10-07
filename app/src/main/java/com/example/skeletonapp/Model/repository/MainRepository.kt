package com.example.skeletonapp.Model.repository

import com.example.skeletonapp.Model.osc.OscSender
import javax.inject.Inject
import javax.inject.Singleton


// Arquivo responsável pela fase de repository, executar a função limpa utilizando os outros arquivos

@Singleton
class MainRepository @Inject constructor(private val oscSender: OscSender) {



     suspend fun DisparoCenas(ip:String,porta:Int,cena: String){
         oscSender.send(ip, porta,cena,1)
    }



}
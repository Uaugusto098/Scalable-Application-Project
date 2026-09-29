package com.example.skeletonapp.Model.osc

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.net.DatagramPacket
import java.net.DatagramSocket
import java.net.InetAddress
import java.nio.ByteBuffer

//Arquivo responsável em determinar a lógica do envio OSC/UDP.



interface OscSender {

    suspend fun send(ip:String,port: Int,address:String,value: Number,onResult:(String)->Unit)
    fun buildOscMessage(address: String,numberArg: Number):ByteArray
    fun padOscString(value:String): ByteArray }

class UdpOscSender(ip:String,port:Int,address:String,value:Number,onResult: (String) -> Unit):
    OscSender {

    override fun padOscString(value: String): ByteArray {
        val raw = value.toByteArray(Charsets.US_ASCII) + byteArrayOf(0)
        val padded = ByteArray(((raw.size + 3) / 4) * 4)
        System.arraycopy(raw, 0, padded, 0, raw.size)
        return padded
    }

    override fun buildOscMessage(address: String, numberArg: Number): ByteArray {
        val addr = padOscString(address)
        val (tags, args) = when (numberArg) {
            is Int -> {
                val tagsByte = padOscString(",i")
                val argsByte = ByteBuffer.allocate(4).putInt(numberArg).array()
                Pair(tagsByte, argsByte)}
            is Float -> {
                val tagsByte2 = padOscString(",f")
                val argsByte2 =ByteBuffer.allocate(4).putFloat(numberArg).array()
                Pair(tagsByte2,argsByte2)}
            else -> throw Exception("A construção do endereço OSC não teve êxito")
            }
        return addr+tags+args
        }

    override suspend fun send(
        ip: String,
        port: Int,
        address: String,
        value: Number,
        onResult: (String) -> Unit
    ) {
        withContext(Dispatchers.IO) {
            val packet = buildOscMessage(address, value)
            val socket = DatagramSocket()
            socket.send(DatagramPacket(packet, packet.size, InetAddress.getByName(ip), port))
            socket.close()
        }
    }




    }












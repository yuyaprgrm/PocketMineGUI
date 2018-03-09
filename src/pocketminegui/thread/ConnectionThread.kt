package pocketminegui.thread

import com.fasterxml.jackson.databind.ObjectMapper
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.*

class ConnectionThread : Runnable {

    var server : ServerSocket? = null
    var sock : Socket? = null

    override fun run() {
        server = ServerSocket()
        server!!.bind(InetSocketAddress(InetAddress.getByName("localhost"), 20132))

        sock = server!!.accept()
        val reader = BufferedReader(InputStreamReader(sock!!.getInputStream()))
        while (true) {
            val data: String? = reader.readLine() ?: return
            var mapper = ObjectMapper()
        }
    }

}
package pocketminegui.io

import java.io.File
import java.io.IOException

class ProcessHolder() {

    var procBuilder : ProcessBuilder
    var process : Process? = null

    init {
        // This is for test code :)
        procBuilder = ProcessBuilder()
//        procBuilder.directory(File("/_Server/PocketMine-MP"))
    }

    @Throws(IOException::class)
    fun start() {
        process = procBuilder.command("bin/php/php.exe", "src/pocketmine/PocketMine.php").start()
    }
}
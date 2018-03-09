package pocketminegui.io

import javafx.collections.ObservableArray
import javafx.collections.ObservableList
import pocketminegui.thread.ConsoleThread
import java.io.File
import java.io.IOException

open class ProcessHolder() {

    var procBuilder : ProcessBuilder
    var process : Process? = null
    var output : ObservableList<String>? = null

    init {
        // This is for test code :)
        procBuilder = ProcessBuilder()
//        procBuilder.directory(File("/_Server/PocketMine-MP"))
    }

    @Throws(IOException::class)
    fun start() {
        process = procBuilder.command("bin/php/php.exe", "src/pocketmine/PocketMine.php").start()
        Thread(ConsoleThread(this)).start()
    }

    fun stop() {
        process?.destroy()
    }

}
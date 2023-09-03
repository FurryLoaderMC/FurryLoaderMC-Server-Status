package FurryLoaderMC.ServerStatus

import FurryLoaderMC.ServerStatus.Command.Executor
import FurryLoaderMC.ServerStatus.Command.Completer
import org.bukkit.configuration.file.FileConfiguration
import org.bukkit.plugin.java.JavaPlugin


class Main : JavaPlugin() {

    companion object {

        lateinit var pluginConfig: FileConfiguration
        lateinit var instance: JavaPlugin
        lateinit var status: Status
        lateinit var socketIO: SocketIO

        fun outputLoggerInfo(content: String) {
            this.instance.logger.info(content)
        }

    }


    override fun onLoad() {
        pluginConfig = config
        instance = this
        status = Status()
        socketIO = SocketIO()
    }


    override fun onEnable() {
        saveDefaultConfig()
        getCommand("status")?.setExecutor(Executor())
        getCommand("status")?.tabCompleter = Completer()
        socketIO.connect()
    }


    override fun onDisable() {
        socketIO.disconnect()
    }

}

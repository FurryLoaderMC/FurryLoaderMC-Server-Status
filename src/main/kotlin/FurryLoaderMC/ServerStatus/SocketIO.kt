package FurryLoaderMC.ServerStatus

import dev.icerock.moko.socket.Socket
import dev.icerock.moko.socket.SocketEvent
import dev.icerock.moko.socket.SocketOptions
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json


class SocketIO {

    private val socket = Socket(
        endpoint = Main.pluginConfig.getString("websocket.url")!!,
        config = SocketOptions(
            queryParams = null,
            transport = SocketOptions.Transport.WEBSOCKET
        )
    ) {
        on(SocketEvent.Connect) {
            Main.outputLoggerInfo("已连接 Socket.io 服务器")
        }
        on(SocketEvent.Disconnect) {
            Main.outputLoggerInfo("已断开 Socket.io 服务器")
        }
        on(SocketEvent.Error) {
            Main.outputLoggerInfo("与 Socket.io 服务器连接发生错误：${it.message}")
        }
        on("motd") {
            val data = Main.status.getMotd()
            this.emit("motd", data)
        }
        on("players") {
            val data = Json.encodeToString(Main.status.getPlayers())
            this.emit("players", data)
        }
        on("version") {
            val data = Main.status.getVersion()
            this.emit("version", data)
        }
        on("performance") {
            val data = Json.encodeToString(Main.status.getPerformance())
            this.emit("performance", data)
        }
    }


    fun connect() {
        if (this.socket.isConnected().not()) {
            this.socket.connect()
        }
    }


    fun disconnect() {
        if (this.socket.isConnected()) {
            this.socket.disconnect()
        }
    }

}

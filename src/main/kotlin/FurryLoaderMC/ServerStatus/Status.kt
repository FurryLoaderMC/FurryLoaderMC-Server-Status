package FurryLoaderMC.ServerStatus

import net.kyori.adventure.text.serializer.plain.PlainTextComponentSerializer


class Status {

    private val serializer = PlainTextComponentSerializer.plainText()


    fun getMotd(): String {
        val motd = Main.instance.server.motd()
        return this.serializer.serialize(motd)
    }


    fun getPlayers(): Players {
        val maximum = Main.instance.server.maxPlayers
        val players = mutableListOf<Player>()
        Main.instance.server.onlinePlayers.forEach {
            val content = Player(it.name, it.uniqueId.toString())
            players.add(content)
        }
        return Players(players.size, maximum, players)
    }


    fun getVersion(): String {
        return Main.instance.server.version
    }


    fun getPerformance(): Performance {
        val tps = Main.instance.server.tps[0]
        val mspt = Main.instance.server.averageTickTime
        return Performance(tps, mspt)
    }

}

package FurryLoaderMC.ServerStatus.Command

import FurryLoaderMC.ServerStatus.Main
import net.kyori.adventure.text.Component
import net.kyori.adventure.text.JoinConfiguration
import net.kyori.adventure.text.format.NamedTextColor
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender


class Executor : CommandExecutor {

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        if (args.isEmpty()) {
            return this.help(sender)
        }

        when (args[0]) {
            "help" -> return this.help(sender)
            "motd" -> return this.motd(sender)
            "players" -> return this.players(sender)
            "version" -> return this.version(sender)
            "performance" -> return this.performance(sender)
        }

        return false
    }


    private fun help(sender: CommandSender):Boolean {
        sender.sendMessage(Component.join(
            JoinConfiguration.separator(Component.newline()),
            Component.text("--------------- FurryLoaderMC - Server Status ---------------"),
            Component.text("指令用法：").color(NamedTextColor.AQUA),
            Component.join(
                JoinConfiguration.separator(Component.empty()),
                Component.text("帮助：").color(NamedTextColor.GOLD),
                Component.text("/status help")
            ),
            Component.join(
                JoinConfiguration.separator(Component.empty()),
                Component.text("描述：").color(NamedTextColor.GOLD),
                Component.text("/status motd")
            ),
            Component.join(
                JoinConfiguration.separator(Component.empty()),
                Component.text("玩家：").color(NamedTextColor.GOLD),
                Component.text("/status players")
            ),
            Component.join(
                JoinConfiguration.separator(Component.empty()),
                Component.text("版本：").color(NamedTextColor.GOLD),
                Component.text("/status version")
            ),
            Component.join(
                JoinConfiguration.separator(Component.empty()),
                Component.text("性能：").color(NamedTextColor.GOLD),
                Component.text("/status performance")
            ),
            Component.text("--------------- FurryLoaderMC - Server Status ---------------")
        ))

        return true
    }


    private fun motd(sender: CommandSender):Boolean {
        sender.sendMessage(Main.status.getMotd())
        return true
    }


    private fun players(sender: CommandSender):Boolean {
        val nameComponents = mutableListOf<Component>()
        val players = Main.status.getPlayers()
        players.players.forEach {
            val nameComponent = Component.text(it.name)
            nameComponents.add(nameComponent)
        }
        sender.sendMessage(Component.join(
            JoinConfiguration.separator(Component.newline()),
            Component.join(
                JoinConfiguration.separator(Component.empty()),
                Component.text("最大玩家数量：").color(NamedTextColor.GOLD),
                Component.text(players.maximum)
            ),
            Component.join(
                JoinConfiguration.separator(Component.empty()),
                Component.text("当前玩家数量：").color(NamedTextColor.GOLD),
                Component.text(players.current)
            ),
            Component.join(
                JoinConfiguration.separator(Component.empty()),
                Component.text("在线玩家列表：").color(NamedTextColor.GOLD),
                Component.join(
                    JoinConfiguration.separator(Component.space()),
                    nameComponents
                )
            )
        ))
        return true
    }


    private fun version(sender: CommandSender):Boolean {
        sender.sendMessage(Main.status.getVersion())
        return true
    }


    private fun performance(sender: CommandSender):Boolean {
        val performance = Main.status.getPerformance()
        sender.sendMessage(Component.join(
            JoinConfiguration.separator(Component.newline()),
            Component.join(
                JoinConfiguration.separator(Component.empty()),
                Component.text("TPS：").color(NamedTextColor.GOLD),
                Component.text(performance.tps)
            ),
            Component.join(
                JoinConfiguration.separator(Component.empty()),
                Component.text("MSPT：").color(NamedTextColor.GOLD),
                Component.text(performance.mspt)
            )
        ))
        return true
    }

}

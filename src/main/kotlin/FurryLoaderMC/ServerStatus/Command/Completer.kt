package FurryLoaderMC.ServerStatus.Command

import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.command.TabCompleter


class Completer : TabCompleter {
    override fun onTabComplete(
        sender: CommandSender,
        command: Command,
        label: String,
        args: Array<out String>
    ): MutableList<String> {
        val completions = mutableListOf<String>()

        if (command.name == "status") {
            if (args.size == 1) {
                completions.addAll(listOf(
                    "help",
                    "motd",
                    "players",
                    "version",
                    "performance"
                ))
            }
        }

        return completions
    }
}

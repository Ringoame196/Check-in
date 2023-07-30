package com.github.Ringoame196

import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.event.Cancellable

class Allowed {
    fun Command(command: String, e: org.bukkit.event.Event, sender: CommandSender) {
        if (!command.contains("tag")) { return }
        if (!command.contains("Allowed")) { return }
        val commandPlayer = GET().PlayerNameFromCommand(command)
        val targetPlayer = Bukkit.getPlayer(commandPlayer!!)
        if (targetPlayer !is Player) { return }
        when {
            command.contains("add Allowed") && !targetPlayer.scoreboardTags.contains("Allowed") -> Allowed().addCommand(e, sender, command)
            command.contains("remove Allowed") && targetPlayer.scoreboardTags.contains("Allowed") -> Allowed().removeCommand(e, sender, command)
        }
    }
    fun addCommand(e: org.bukkit.event.Event, sender: CommandSender, command: String) {
        if (command.contains("execute")) {
            if (e !is Cancellable) { return }
            e.isCancelled = true
            sender.sendMessage("${ChatColor.RED}認証コマンドをexecuteで実行することは禁止されています")
            return
        }
        val commandPlayer = GET().PlayerNameFromCommand(command)
        Bukkit.broadcastMessage("${ChatColor.YELLOW}[認証] ${sender.name}が${commandPlayer}さんの認証を許可しました")

        val targetPlayer = Bukkit.getPlayer(commandPlayer!!)
        if (targetPlayer !is Player) { return }
        targetPlayer.teleport(targetPlayer.world.spawnLocation)
        targetPlayer.gameMode = Bukkit.getDefaultGameMode()
    }
    fun removeCommand(e: org.bukkit.event.Event, sender: CommandSender, command: String) {
        if (command.contains("execute")) {
            if (e !is Cancellable) { return }
            e.isCancelled = true
            sender.sendMessage("${ChatColor.RED}認証コマンドをexecuteで実行することは禁止されています")
            return
        }
        val commandPlayer = GET().PlayerNameFromCommand(command)
        Bukkit.broadcastMessage("${ChatColor.RED}[認証] ${sender.name}が${commandPlayer}さんの認証を削除しました")
        val targetPlayer = Bukkit.getPlayer(commandPlayer!!)
        if (targetPlayer !is Player) { return }
        Unauthorized().join(targetPlayer)
    }
}

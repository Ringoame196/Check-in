package com.github.Ringoame196

import net.md_5.bungee.api.chat.ClickEvent
import net.md_5.bungee.api.chat.TextComponent
import org.bukkit.Bukkit
import org.bukkit.ChatColor
import org.bukkit.GameMode
import org.bukkit.Location
import org.bukkit.entity.Player
import org.bukkit.event.Cancellable
import org.bukkit.event.Event

class Unauthorized {
    fun join(player: Player) {
        player.gameMode = GameMode.SPECTATOR
        val location = player.location
        player.teleport(Location(player.world, location.x, 10000.0, location.z))
        player.sendTitle("${ChatColor.RED}認証待ち中...", "${ChatColor.RED}しばらくお待ち下さい", 20, 300, 20)
        confirmation(player.name)
    }
    fun confirmation(PlayerName: String) {
        for (loopPlayer in Bukkit.getServer().onlinePlayers) {
            loopPlayer.sendMessage("${ChatColor.YELLOW}[参加チェック] ${PlayerName}さんが認証待ち中です")
            if (loopPlayer.isOp) {
                loopPlayer.sendMessage("${ChatColor.AQUA}-----[認証 OPメニュー]-----")
                // 許可コマンドのリンク
                val allowMessage = TextComponent("${ChatColor.GREEN}${PlayerName}さんの認証を許可[クリック]")
                allowMessage.clickEvent = ClickEvent(ClickEvent.Action.RUN_COMMAND, "/tag $PlayerName add Allowed")
                loopPlayer.spigot().sendMessage(allowMessage)

                // 禁止コマンドのリンク
                val banMessage = TextComponent("${ChatColor.RED}${PlayerName}さんをBANする[クリック]")
                banMessage.clickEvent = ClickEvent(ClickEvent.Action.SUGGEST_COMMAND, "/ban $PlayerName")
                loopPlayer.spigot().sendMessage(banMessage)

                loopPlayer.sendMessage("${ChatColor.AQUA}-----------------------")
            }
        }
    }
    fun cancel(e: Event) {
        if (e !is Cancellable) { return }
        e.isCancelled = true
    }
}

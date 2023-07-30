package com.github.Ringoame196

import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.PlayerCommandPreprocessEvent
import org.bukkit.event.player.PlayerJoinEvent
import org.bukkit.event.player.PlayerMoveEvent
import org.bukkit.event.server.ServerCommandEvent

class Events() : Listener {
    @EventHandler
    fun onPlayerJoin(e: PlayerJoinEvent) {
        val player = e.player
        if (!GET().PlayerTag(player)) {
            Unauthorized().join(player)
        }
    }

    @EventHandler
    fun onPlayerMove(e: PlayerMoveEvent) {
        val player = e.player
        if (!GET().PlayerTag(player)) {
            Unauthorized().cancel(e)
        }
    }

    @EventHandler
    fun onPlayerCommandPreprocess(e: PlayerCommandPreprocessEvent) {
        val player = e.player
        val command = e.message
        Allowed().Command(command, e, player)
    }

    @EventHandler
    fun onServerCommand(e: ServerCommandEvent) {
        val sender = e.sender
        val command = e.command
        Allowed().Command(command, e, sender)
    }
}

package com.github.Ringoame196

import org.bukkit.entity.Player

class GET {
    fun PlayerTag(player: Player): Boolean {
        var have = false
        if (player.scoreboardTags.contains("Allowed")) { have = true }
        return have
    }
    fun PlayerNameFromCommand(command: String): String? {
        val regex = "^tag (\\w+) (add|remove) Allowed\$".toRegex()
        val matchResult = regex.find(command.replace("/", ""))
        return matchResult?.groupValues?.getOrNull(1)
    }
}

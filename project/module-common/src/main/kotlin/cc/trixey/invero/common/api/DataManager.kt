package cc.trixey.invero.common.api

import org.bukkit.entity.Player
import taboolib.expansion.DataContainer

/**
 * Invero
 * cc.trixey.invero.common.api.DataManager
 *
 * @author Arasple
 * @since 2023/2/1 17:08
 */
interface DataManager {

    fun getDatabaseType(): Type

    fun getGlobalData(): DataContainer

    fun getPlayerData(player: Player): DataContainer

    enum class Type {

        SQLITE,

        SQL

    }

}
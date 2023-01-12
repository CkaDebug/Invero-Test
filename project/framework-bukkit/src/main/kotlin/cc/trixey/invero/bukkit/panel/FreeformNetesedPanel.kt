package cc.trixey.invero.bukkit.panel

import cc.trixey.invero.bukkit.BukkitPanel
import cc.trixey.invero.common.Element
import cc.trixey.invero.common.Panel
import cc.trixey.invero.common.Pos
import cc.trixey.invero.common.Scale
import cc.trixey.invero.common.event.WindowClickEvent
import cc.trixey.invero.common.event.WindowDragEvent
import cc.trixey.invero.common.event.WindowItemsMoveEvent
import cc.trixey.invero.common.panel.FreeformPanel
import cc.trixey.invero.common.panel.PanelContainer
import cc.trixey.invero.common.panel.PanelWeight

/**
 * Invero
 * cc.trixey.invero.bukkit.panel.FreeformNetesedPanel
 *
 * @author Arasple
 * @since 2023/1/10 21:46
 */
class FreeformNetesedPanel(
    parent: PanelContainer,
    weight: PanelWeight,
    scale: Scale,
    locate: Pos
) : BukkitPanel(parent, weight, scale, locate), PanelContainer, FreeformPanel {

    override var viewport: Pos = Pos.NIL
        set(value) {
            field = value
            rerender()
        }

    override val panels = arrayListOf<Panel>()

    override fun render() = panels.forEach { it.render() }

    override fun isElementValid(element: Element) = panels.any { it.isElementValid(element) }

    override fun handleClick(pos: Pos, e: WindowClickEvent) = panels.any {
        val clicked = pos + viewport

        if (clicked in it.area) {
            it.handleClick(clicked - it.locate, e)
            true
        } else false
    }

    override fun handleDrag(positions: List<Pos>, e: WindowDragEvent): Boolean {
        val affected = positions.map { it + viewport }

        panels
            .find { panel -> affected.all { it in panel.area } }
            ?.let {
                return it.handleDrag(affected, e)
            }

        return false
    }

    override fun handleItemsMove(pos: Pos, e: WindowItemsMoveEvent) = panels.any {
        val clicked = pos + viewport

        if (clicked in it.area) {
            it.handleItemsMove(clicked - it.locate, e)
            true
        } else false
    }

}
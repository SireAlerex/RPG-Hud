package net.spellcraftgaming.rpghud.gui.hud.element.hotbar;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.util.Identifier;
import net.spellcraftgaming.rpghud.gui.hud.element.HudElement;
import net.spellcraftgaming.rpghud.gui.hud.element.HudElementType;
import net.spellcraftgaming.rpghud.settings.Settings;

@Environment(EnvType.CLIENT)
public class HudElementArmorHotbar extends HudElement {

	public HudElementArmorHotbar() {
		super(HudElementType.ARMOR, 0, 0, 0, 0, true);
	}

	@Override
	public boolean checkConditions() {
		return this.mc.interactionManager.hasStatusBars();
	}

	@Override
	public void drawElement(DrawContext gui, float zLevel, float partialTicks, int scaledWidth, int scaledHeight) {
		int left = (this.settings.getBoolValue(Settings.render_player_face) ? 46 : 22) + this.settings.getPositionValue(Settings.armor_position)[0];
		int top = scaledHeight - 64 + this.settings.getPositionValue(Settings.armor_position)[1];
		int level = this.mc.player.getArmor();
		Identifier missing = new Identifier("rpghud:textures/missing_texture.png");
		for (int i = 1; level > 0 && i < 20; i += 2) {
			if (i < level) {
				gui.drawTexture(missing,left + 62, top - 2, 34, 9, 9, 9);
			} else if (i == level) {
				gui.drawTexture(missing,left + 62, top - 2, 25, 9, 9, 9);
			} else if (i > level) {
				gui.drawTexture(missing,left + 62, top - 2, 16, 9, 9, 9);
			}
			left += 8;
		}
	}

}

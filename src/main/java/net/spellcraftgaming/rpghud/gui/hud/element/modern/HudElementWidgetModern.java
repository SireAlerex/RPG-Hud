package net.spellcraftgaming.rpghud.gui.hud.element.modern;

import com.mojang.blaze3d.systems.RenderSystem;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;
import net.spellcraftgaming.rpghud.gui.hud.element.HudElement;
import net.spellcraftgaming.rpghud.gui.hud.element.HudElementType;
import net.spellcraftgaming.rpghud.main.ModRPGHud;
import net.spellcraftgaming.rpghud.settings.Settings;

@Environment(EnvType.CLIENT)
public class HudElementWidgetModern extends HudElement {

	public HudElementWidgetModern() {
		super(HudElementType.WIDGET, 0, 0, 0, 0, true);
	}

	@Override
	public boolean checkConditions() {
		return this.mc.interactionManager.hasStatusBars() && ModRPGHud.instance.settings.getBoolValue(Settings.render_player_face);
	}

	@Override
	public void drawElement(DrawContext gui, float zLevel, float partialTicks, int scaledWidth, int scaledHeight) {
		int posX = this.settings.getPositionValue(Settings.face_position)[0];
		int posY = this.settings.getPositionValue(Settings.face_position)[1];
		drawRect(gui, posX + 2, posY + 2, 20, 20, 0xA0000000);
		RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
		RenderSystem.enableBlend();
//		bind(getPlayerSkin(this.mc.player));
		Identifier player_skin = getPlayerSkin(this.mc.player);
		MatrixStack ms = gui.getMatrices();
		RenderSystem.disableDepthTest();
		ms.scale(0.5f, 0.5f, 0.5f);
		gui.drawTexture(player_skin, posX * 2 + 8, posY * 2 + 8, 32, 32, 32, 32);
		gui.drawTexture(player_skin, posX * 2 + 8, posY * 2 + 8, 160, 32, 32, 32);
		ms.scale(2f, 2f, 2f);
	}
}

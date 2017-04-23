package net.spellcraftgaming.rpghud.gui.hud.element.extended;

import net.minecraft.client.gui.Gui;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.spellcraftgaming.lib.GameData;
import net.spellcraftgaming.rpghud.gui.hud.element.HudElementBarred;
import net.spellcraftgaming.rpghud.gui.hud.element.HudElementType;

public class HudElementFoodExtended extends HudElementBarred {

	public HudElementFoodExtended() {
		super(HudElementType.FOOD, 0, 0, 0, 0, true);
	}

	@Override
	public boolean checkConditions() {
		return this.mc.playerController.shouldDrawHUD();
	}

	@Override
	public void drawElement(Gui gui, float zLevel, float partialTicks) {
		int stamina = GameData.getPlayerFood();
		int foodMax = GameData.getPlayerMaxFood();
		int posX = this.settings.render_player_face ? 49 : 25;
		int posY = this.settings.render_player_face ? 22 : 18;
		ItemStack itemMain = GameData.getMainhand();
		ItemStack itemSec = GameData.getOffhand();

		if (GameData.doesPlayerNeedFood() && this.settings.show_hunger_preview) {
			float value = 0;
			if (itemMain != GameData.nullStack() && itemMain.getItem() instanceof ItemFood) {
				value = ((ItemFood) itemMain.getItem()).getHealAmount(itemMain);
			} else if (itemSec != GameData.nullStack() && itemSec.getItem() instanceof ItemFood) {
				value = ((ItemFood) itemSec.getItem()).getHealAmount(itemSec);
			}
			if (value > 0) {
				int bonusHunger = (int) (value + stamina);
				if (bonusHunger > foodMax)
					bonusHunger = foodMax;
				int colorPreview = offsetColor(this.settings.color_stamina, OFFSET_PREVIEW);
				drawCustomBar(posX, posY, 110, 12, bonusHunger / (double) foodMax * 100.0D, -1, -1, colorPreview, offsetColorPercent(colorPreview, OFFSET_PERCENT));
			}
		}

		if (GameData.isPlayerHungered()) {
			drawCustomBar(posX, posY, 110, 12, stamina / (double) foodMax * 100.0D, -1, -1, this.settings.color_hunger, offsetColorPercent(this.settings.color_hunger, OFFSET_PERCENT));
		} else {
			drawCustomBar(posX, posY, 110, 12, stamina / (double) foodMax * 100.0D, -1, -1, this.settings.color_stamina, offsetColorPercent(this.settings.color_stamina, OFFSET_PERCENT));
		}
		String staminaString = stamina + "/" + foodMax;
		if (this.settings.show_numbers_stamina)
			gui.drawCenteredString(this.mc.fontRendererObj, staminaString, posX + 55, posY + 2, -1);
	}

}

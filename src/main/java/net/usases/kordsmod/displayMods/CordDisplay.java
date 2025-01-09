package net.usases.kordsmod.displayMods;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;

public class CordDisplay {

    private static final Logger LOGGER = LoggerFactory.getLogger(CordDisplay.class);

    public CordDisplay() {
        // Регистрация события рендеринга HUD
        HudRenderCallback.EVENT.register(this::renderCoordinates);
    }

    /**
     * Метод отображения координат игрока на экране
     */
    private void renderCoordinates(DrawContext context, float tickDelta) {
        MinecraftClient client = MinecraftClient.getInstance();

        if (client.player == null) return;

        // Получение координат игрока
        double x = client.player.getX();
        double y = client.player.getY();
        double z = client.player.getZ();

        // Форматирование координат для отображения
        String coordinatesText = String.format("X: %.2f Y: %.2f Z: %.2f", x, y, z);

        // Отрисовка координат на экране
        renderText(context, coordinatesText, 10, client.getWindow().getScaledHeight() - 50);
    }

    /**
     * Метод отображения текста на экране
     */
    private void renderText(DrawContext context, String text, int x, int y) {
        MinecraftClient client = MinecraftClient.getInstance();
        TextRenderer textRenderer = client.textRenderer;

        // Отрисовка текста с тенью
        context.drawTextWithShadow(textRenderer, text, x, y, 0xFFFFFF); // Белый цвет
    }
}

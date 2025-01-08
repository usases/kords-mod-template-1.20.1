package net.usases.kordsmod.displayMods;

import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;

public class FpsDisplayMod {

    public FpsDisplayMod() {
        // Регистрируем HUD рендеринг через Fabric API
        HudRenderCallback.EVENT.register(this::renderFps);
    }

    /**
     * Метод для рендеринга FPS на экране
     */
    private void renderFps(DrawContext context, float tickDelta) {
        MinecraftClient client = MinecraftClient.getInstance();

        if (client == null || client.textRenderer == null) return;

        // Получение текущего значения FPS
        int fps = client.getCurrentFps();
        String fpsText = "FPS: " + fps;

        // Рендеринг текста на экране
        renderText(context, fpsText, 10, 10);
    }

    /**
     * Метод для отображения текста
     */
    private void renderText(DrawContext context, String text, int x, int y) {
        MinecraftClient client = MinecraftClient.getInstance();

        if (client != null && client.textRenderer != null) {
            context.drawTextWithShadow(client.textRenderer, text, x, y, 0xFFFFFF); // Белый цвет
        }
    }
}


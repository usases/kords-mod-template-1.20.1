package net.usases.kordsmod.displayMods;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import net.fabricmc.fabric.api.client.rendering.v1.HudRenderCallback;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.font.TextRenderer;
import net.minecraft.client.gui.DrawContext;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;

public class CordDisplay {

    private static final Logger LOGGER = LoggerFactory.getLogger(CordDisplay.class); // Инициализация логгера

    private Socket socket;
    private PrintWriter out;

    public CordDisplay() {
        // Регистрация события рендеринга HUD
        HudRenderCallback.EVENT.register(this::renderCoordinates);

        // Подключение к серверу (например, localhost, порт 12345)
        try {
            socket = new Socket("localhost", 12345);
            out = new PrintWriter(socket.getOutputStream(), true);
        } catch (Exception e) {
            LOGGER.error("Ошибка при подключении к сокету: {}", e.getMessage(), e); // Логирование ошибки
        }
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

        // Форматирование координат
        String coordinatesText = String.format("X: %.2f Y: %.2f Z: %.2f", x, y, z);

        // Отправка координат в консольное приложение через сокет
        if (out != null) {
            out.println(coordinatesText); // Отправляем координаты
        }

        // Отрисовка координат на экране
        renderText(context, coordinatesText, 10, client.getWindow().getScaledHeight() - 50); // Позиция на экране
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

    // Закрытие сокета при завершении работы
    public void close() {
        try {
            if (out != null) {
                out.close();
            }
            if (socket != null) {
                socket.close();
            }
        } catch (Exception e) {
            LOGGER.error("Ошибка при закрытии сокета: {}", e.getMessage(), e); // Логирование ошибки
        }
    }
}



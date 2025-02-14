package gui;

import javax.swing.*;
import java.awt.*;

public class SortingPanel extends JPanel {
    private int[] array;

    public SortingPanel() {
        setPreferredSize(new Dimension(800, 500));
        setDoubleBuffered(true); // Enable double buffering
    }

    public void setArray(int[] array) {
        this.array = array;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (array == null) return;

        Graphics2D g2d = (Graphics2D) g;
        int width = getWidth();
        int height = getHeight();
        int paddingTop = 50; // Padding to prevent overlapping with control panel

        int barWidth = width / array.length;
        int leftPadding = barWidth; // Leave space of one bar width on the left

        // Draw gradient background
        GradientPaint backgroundGradient = new GradientPaint(0, 0, Color.DARK_GRAY, width, height, Color.BLACK);
        g2d.setPaint(backgroundGradient);
        g2d.fillRect(0, 0, width, height);

        Font font = new Font("Arial", Font.BOLD, 12);
        g2d.setFont(font);

        for (int i = 0; i < array.length; i++) {
            int barHeight = (int) ((double) array[i] / array.length * (height - paddingTop));
            int barX = i * barWidth + leftPadding;
            int barY = height - barHeight;

            // Draw bar with gradient effect
            GradientPaint barGradient = new GradientPaint(barX, barY, new Color(0, 102, 204), barX, barY + barHeight, new Color(51, 153, 255));
            g2d.setPaint(barGradient);
            g2d.fillRect(barX, barY, barWidth, barHeight);

            // Draw border
            g2d.setColor(Color.BLACK);
            g2d.drawRect(barX, barY, barWidth, barHeight);

            // Draw number in circular container above the bar
            String number = String.valueOf(array[i]);
            int stringWidth = g2d.getFontMetrics().stringWidth(number);
            int stringHeight = g2d.getFontMetrics().getHeight();

            int circleDiameter = Math.max(stringWidth, stringHeight) + 10;
            int circleX = barX + (barWidth - circleDiameter) / 2;
            int circleY = barY - circleDiameter - 5;

            g2d.setColor(new Color(255, 69, 0));
            g2d.fillOval(circleX, circleY, circleDiameter, circleDiameter);
            g2d.setColor(Color.BLACK);
            g2d.drawOval(circleX, circleY, circleDiameter, circleDiameter);

            int stringX = circleX + (circleDiameter - stringWidth) / 2;
            int stringY = circleY + (circleDiameter + stringHeight) / 2 - 4;

            g2d.setColor(Color.WHITE);
            g2d.drawString(number, stringX, stringY);
        }
    }
}
 
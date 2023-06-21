package com.ict5.client;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class NotificationPanel extends JPanel {
	
    public NotificationPanel(String message, boolean isNew, Date timestamp) {
        setPreferredSize(new Dimension(400, 100));

        Border border = BorderFactory.createLineBorder(Color.BLACK);
        setBorder(border);

        JLabel messageLabel = new JLabel(message);
        if (isNew) {
            messageLabel.setForeground(Color.RED);
            messageLabel.setFont(messageLabel.getFont().deriveFont(Font.BOLD));
        }
        add(messageLabel);

        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
        String formattedTimestamp = dateFormat.format(timestamp);

        JLabel timestampLabel = new JLabel(formattedTimestamp);
        timestampLabel.setFont(timestampLabel.getFont().deriveFont(30f));
        add(timestampLabel);

        JLabel cautionLabel = new JLabel("늦지 않도록 주의하세요.");
        cautionLabel.setFont(cautionLabel.getFont().deriveFont(16f));
        add(cautionLabel);
    }
}

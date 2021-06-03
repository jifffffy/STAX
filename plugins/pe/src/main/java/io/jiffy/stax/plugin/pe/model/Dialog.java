package io.jiffy.stax.plugin.pe.model;

import io.jiffy.stax.plugin.pe.action.DialogAction;
import org.apache.commons.lang3.StringUtils;


import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.File;
import java.io.IOException;


public class Dialog extends JDialog {

    private int dialogWidth = 800;
    private int dialogHeight = 380;

    private final DialogAction action;
    private final String image;
    private final String description;
    private final String labelText;

    public Dialog(String labelText, String description, DialogAction action, String image) {

        this.action = action;
        this.image = image;
        this.labelText = labelText;
        this.description = description;

        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        setResizable(false);

        setPreferredSize(new Dimension(dialogWidth, dialogHeight));

        this.setTitle("提示");

        addContent();

        addButtons();

        addImage();

        pack();

        setLocationRelativeTo(null);

        setVisible(true);
    }

    private void addImage() {
        if (hasImage()) {
            try {
                JLabel imageLabel = new JLabel(new ImageIcon(ImageIO.read(new File(this.image))));
                imageLabel.setBorder(new EmptyBorder(new Insets(5, 5, 5, 5)));
                this.add(new JScrollPane(imageLabel), BorderLayout.CENTER);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private boolean hasImage() {
        return StringUtils.isNotEmpty(this.image);
    }

    private void addContent() {

        JPanel contentPanel = new JPanel(new BorderLayout());

        JLabel label = new JLabel();
        label.setText(labelText);
        label.setBorder(new EmptyBorder(new Insets(5, 5, 5, 5)));

        contentPanel.add(label);

        JTextArea textArea = new JTextArea(description);
        textArea.setFont(textArea.getFont().deriveFont(32.0f));
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setEditable(false);

        JScrollPane scrollPane = new JScrollPane(textArea);

        scrollPane.setBorder(new EmptyBorder(new Insets(5, 5, 5, 5)));

        contentPanel.add(scrollPane, BorderLayout.CENTER);

        this.add(contentPanel, hasImage()? BorderLayout.NORTH : BorderLayout.CENTER);
    }


    private void addButtons() {
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        JButton okButton = new JButton("是");
        JButton noButton = new JButton("否");

        okButton.setPreferredSize(new Dimension(120, 60));
        noButton.setPreferredSize(new Dimension(120, 60));

        okButton.addActionListener(e -> action.changeState(DialogAction.State.YES, Dialog.this));
        noButton.addActionListener(e -> action.changeState(DialogAction.State.NO, Dialog.this));

        buttonPanel.add(okButton);
        buttonPanel.add(noButton);

        this.add(buttonPanel, BorderLayout.SOUTH);
    }


}

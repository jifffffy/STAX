package io.jiffy.stax.plugin.pe.model;

import com.ibm.staf.service.stax.STAXAction;
import com.ibm.staf.service.stax.STAXThread;
import io.jiffy.stax.plugin.pe.action.DialogAction;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;


public class Dialog extends JDialog {
    private int dialogWidth = 800;
    private int dialogHeight = 380;

    private JLabel iconLabel = new JLabel();

    // is error panel opened up
    private boolean open = false;

    private JLabel label = new JLabel();
    private JTextArea textArea = new JTextArea("");

    private JButton okButton = new JButton("是");

    private JButton noButton = new JButton("否");

    private JPanel topPanel = new JPanel(new BorderLayout());

    private final DialogAction action;

    public Dialog(String labelText, String description,  DialogAction action) {
        this.action = action;

        setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);

        setSize(dialogWidth, dialogHeight);

        setResizable(false);

        setLocationRelativeTo(null);

        textArea.setText(description);
        textArea.setFont (textArea.getFont().deriveFont (32.0f));

        label.setText(labelText);

        iconLabel.setBorder(new EmptyBorder(new Insets(10, 10, 10, 10)));

        iconLabel.setIcon(UIManager.getIcon("OptionPane.warningIcon"));
        setupUI();

        okButton.addActionListener(e -> action.changeState(DialogAction.State.YES, Dialog.this));

        noButton.addActionListener(e -> action.changeState(DialogAction.State.NO, Dialog.this));
    }


    public void setupUI() {

        this.setTitle("提示");

        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setEditable(false);

        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));

        okButton.setPreferredSize(new Dimension(120, 60));
        noButton.setPreferredSize(new Dimension(120, 60));
        buttonPanel.add(okButton);
        buttonPanel.add(noButton);

        textArea.setBackground(iconLabel.getBackground());

        JScrollPane textAreaSP = new JScrollPane(textArea);

        textAreaSP.setBorder(new EmptyBorder(new Insets(5, 5, 5, 5)));

        label.setBorder(new EmptyBorder(new Insets(5, 5, 5, 5)));


        topPanel.add(iconLabel, BorderLayout.WEST);

        JPanel p = new JPanel(new BorderLayout());
        p.add(label, BorderLayout.NORTH);
        p.add(textAreaSP);

        topPanel.add(p);

        this.add(topPanel);

        this.add(buttonPanel, BorderLayout.SOUTH);
    }



}

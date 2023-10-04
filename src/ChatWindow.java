import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class ChatWindow extends JFrame {
    static private final int WINDOW_HEIGHT = 640;
    static private final int WINDOW_WIDTH = 720;
    static private final int WINDOW_POS_X = 300;
    static private final int WINDOW_POS_Y = 100;
    static private final String WINDOW_NAME = "Чат с сервером";

    JTextArea textOutput = new JTextArea("");
    JLabel label = new JLabel("Введите сообщение серверу: ");
    JTextField textInput = new JTextField();
    JButton buttonSend = new JButton("Отправить");
    JButton buttonExit = new JButton("Выход");

    JPanel gridButton = new JPanel(new GridLayout(1, 2));
    JPanel grid = new JPanel(new GridLayout(4,1));

    LogOperation logOperation = new LogOperation();

    ChatWindow(String login, ArrayList<String> historyChat){
        fillHistoryOfChat(historyChat);

        //свойства окна
        setTitle(WINDOW_NAME);
        setBounds(WINDOW_POS_X, WINDOW_POS_Y, WINDOW_WIDTH, WINDOW_HEIGHT);

        gridButton.add(buttonSend);
        gridButton.add(buttonExit);

        textOutput.setEditable(false);
        textOutput.setBackground(Color.GRAY);
        grid.add(textOutput);
        grid.add(label);
        grid.add(textInput);
        grid.add(gridButton);

        // Нажатие на кнопку "Отправить"
        buttonSend.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                String message = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yy/MM/dd HH:mm:ss")).toString()
                        + " " + login + " : " + textInput.getText();
                textOutput.append(message + "\n");
                logOperation.saveMessageToLog(message);
                textInput.setText("");
            }
        });

        // Нажатие на клавишу Enter
        textInput.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {}

            @Override
            public void keyPressed(KeyEvent e) {}

            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    String message = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yy/MM/dd HH:mm:ss")).toString()
                            + " " + login + " : " + textInput.getText();
                    textOutput.append(message + "\n");
                    logOperation.saveMessageToLog(message);
                    textInput.setText("");
                }
            }
        });

        // Нажатие на кнопку "Выход"
        buttonExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });

        add(grid);
        setVisible(true);
    }

    public void fillHistoryOfChat(ArrayList<String> historyChat) {
        for (String storage : historyChat) {
            textOutput.append(storage + "\n");
        }
    }

}
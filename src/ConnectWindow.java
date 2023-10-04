// – Создать окно клиента чата. 
// Окно должно содержать JtextField для ввода логина, 
// пароля, IP-адреса сервера, порта подключения к серверу, 
// область ввода сообщений, 
// JTextArea область просмотра сообщений чата и 
// JButton подключения к серверу и отправки сообщения в чат. 
// Желательно сразу сгруппировать
// компоненты, относящиеся к серверу сгруппировать 
// на JPanel сверху экрана, а
// компоненты, относящиеся к отправке сообщения – 
// на JPanel снизу.

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ConnectWindow extends JFrame {

    static private final int WINDOW_HEIGHT = 640;
    static private final int WINDOW_WIDTH = 720;
    static private final int WINDOW_POS_X = 300;
    static private final int WINDOW_POS_Y = 100;
    static private final String WINDOW_NAME = "Подключение к серверу";

    JLabel loginFieldTitle = new JLabel("Введите ваш логин: ");
    JTextField loginField = new JTextField("");
    JLabel passwordFieldTitle = new JLabel("Введите ваш пароль: ");
    JTextField passwordField = new JTextField("");
    JLabel serverFieldTitle = new JLabel("Введите адрес сервера и порт для подключения: ");
    JTextField serverField = new JTextField("");
    JButton buttonConnect = new JButton("Подключиться");
    JButton buttonExit = new JButton("Выход");

    JPanel gridButton = new JPanel(new GridLayout(1, 2));
    JPanel grid = new JPanel(new GridLayout(7,1));

    LogOperation logOperation = new LogOperation();

    ConnectWindow(){
        //свойства окна
        setTitle(WINDOW_NAME);
        setBounds(WINDOW_POS_X, WINDOW_POS_Y, WINDOW_WIDTH, WINDOW_HEIGHT);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        gridButton.add(buttonConnect);
        gridButton.add(buttonExit);

        grid.add(loginFieldTitle);
        grid.add(loginField);
        grid.add(passwordFieldTitle);
        grid.add(passwordField);
        grid.add(serverFieldTitle);
        grid.add(serverField);
        grid.add(gridButton);

        add(grid);

        // Нажатие на кнопку "Подключиться"
        buttonConnect.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e){
                String login = loginField.getText();
                if (login.length() > 0) {
                    ArrayList<String> historyOfChat = logOperation.readMessageFromLog();
                    new ChatWindow(login, historyOfChat);
                } else {
                    JOptionPane.showMessageDialog(ConnectWindow.this, "Не указан логин!");
                }
            }
        });

        // Нажатие на кнопку "Выход"
        buttonExit.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        setVisible(true);
    }
}
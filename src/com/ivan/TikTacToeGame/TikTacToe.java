package com.ivan.TikTacToeGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class TikTacToe implements ActionListener {

    Random random = new Random();
    JFrame frame = new JFrame();
    JPanel title_panel = new JPanel();
    JPanel button_panel = new JPanel();
    JLabel textField = new JLabel();
    JButton restartButton = new JButton();

    JButton[] buttons = new JButton[9];

    boolean player1_turn = false;

    public TikTacToe() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 800);
        frame.getContentPane().setBackground(Color.BLACK);
        frame.setLayout(new BorderLayout());
        frame.setVisible(true);

        textField.setBackground(new Color(25,25,25));
        textField.setForeground(new Color(25,255,0));
        textField.setFont(new Font("Ink Fee", Font.BOLD, 75));
        textField.setHorizontalAlignment(JLabel.CENTER);
        textField.setText("Tic Tac Toe");
        textField.setOpaque(true);

        restartButton.setText("Restart Game");
        restartButton.setSize(20,10);
        restartButton.setVisible(false);
        restartButton.setEnabled(false);
        restartButton.addActionListener(this);

        title_panel.setLayout(new BorderLayout());
        title_panel.setBounds(0, 0, 800 ,100);
        title_panel.add(restartButton, BorderLayout.EAST);

        button_panel.setLayout(new GridLayout(3,3));
        button_panel.setBackground(new Color(150, 150, 150));

        for (int i = 0; i<9 ; i++) {
            buttons[i] = new JButton();
            button_panel.add(buttons[i]);
            buttons[i].setFont(new Font("Times New Roman", Font.BOLD, 140));
            buttons[i].setFocusable(false);
            buttons[i].addActionListener(this);
        }

        title_panel.add(textField);
        frame.add(title_panel, BorderLayout.NORTH);
        frame.add(button_panel);

        firstTurn();
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        for (int i = 0; i < 9; i++) {
            if (e.getSource().equals(buttons[i]) ) {
                if (player1_turn) {
                    if (buttons[i].getText().equals("")) {
                        buttons[i].setForeground(Color.BLUE);
                        buttons[i].setText("X");
                        player1_turn = false;
                        textField.setText("O turn");
                        check();
                        }
                    } else {
                        if (buttons[i].getText().equals("")) {
                            buttons[i].setForeground(Color.RED);
                            buttons[i].setText("O");
                            player1_turn = true;
                            textField.setText("X turn");
                            check();
                        }
                    }
                }
            }
        if (e.getSource().equals(restartButton))
        {
            redrawBoard();
        }
    }

    public void firstTurn() {

        try {
            Thread.sleep(500);
        } catch (InterruptedException ex){
            ex.printStackTrace();
        }

        if (random.nextInt(2) == 0){
            player1_turn = true;
            textField.setText("X turn");
        }else{
            player1_turn = false;
            textField.setText("O turn");
        }
    }

    public void check() {

        // Check if X wins

        if (
                (buttons[0].getText().equals("X")) &&
                (buttons[1].getText().equals("X"))&&
                (buttons[2].getText().equals("X"))
            ) {
            xWins(0,1,2);
        }
        if (
                (buttons[3].getText().equals("X")) &&
                (buttons[4].getText().equals("X")) &&
                (buttons[5].getText().equals("X"))
        ){
            xWins(3,4,5);
        }
        if (
                (buttons[6].getText().equals("X")) &&
                (buttons[7].getText().equals("X")) &&
                (buttons[8].getText().equals("X"))
        ){
            xWins(6,7,8);
        }
        if (
                (buttons[0].getText().equals("X")) &&
                (buttons[3].getText().equals("X")) &&
                (buttons[6].getText().equals("X"))
        ){
            xWins(0,3,6);
        }
        if (
                (buttons[1].getText().equals("X")) &&
                (buttons[4].getText().equals("X")) &&
                (buttons[7].getText().equals("X"))
        ){
            xWins(1,4,7);
        }
        if (
                (buttons[2].getText().equals("X")) &&
                (buttons[5].getText().equals("X")) &&
                (buttons[8].getText().equals("X"))
        ){
            xWins(2,5,8);
        }
        if (
                (buttons[0].getText().equals("X")) &&
                (buttons[4].getText().equals("X")) &&
                (buttons[8].getText().equals("X"))
        ){
            xWins(0,4,8);
        }
        if (
                (buttons[2].getText().equals("X")) &&
                (buttons[4].getText().equals("X")) &&
                (buttons[6].getText().equals("X"))
        ){
            xWins(2,4,6);
        }

        // Check if O wins

        if (
                (buttons[0].getText().equals("O")) &&
                        (buttons[1].getText().equals("O"))&&
                        (buttons[2].getText().equals("O"))
        ) {
            oWins(0,1,2);
        }
        if (
                (buttons[3].getText().equals("O")) &&
                        (buttons[4].getText().equals("O")) &&
                        (buttons[5].getText().equals("O"))
        ){
            oWins(3,4,5);
        }
        if (
                (buttons[6].getText().equals("O")) &&
                        (buttons[7].getText().equals("O")) &&
                        (buttons[8].getText().equals("O"))
        ){
            oWins(6,7,8);
        }
        if (
                (buttons[0].getText().equals("O")) &&
                        (buttons[3].getText().equals("O")) &&
                        (buttons[6].getText().equals("O"))
        ){
            oWins(0,3,6);
        }
        if (
                (buttons[1].getText().equals("O")) &&
                        (buttons[4].getText().equals("O")) &&
                        (buttons[7].getText().equals("O"))
        ){
            oWins(1,4,7);
        }
        if (
                (buttons[2].getText().equals("O")) &&
                        (buttons[5].getText().equals("O")) &&
                        (buttons[8].getText().equals("O"))
        ){
            oWins(2,5,8);
        }
        if (
                (buttons[0].getText().equals("O")) &&
                        (buttons[4].getText().equals("O")) &&
                        (buttons[8].getText().equals("O"))
        ){
            oWins(0,4,8);
        }
        if (
                (buttons[2].getText().equals("O")) &&
                        (buttons[4].getText().equals("O")) &&
                        (buttons[6].getText().equals("O"))
        ){
            oWins(2,4,6);
        }
        checkDraw();
    }

    public void xWins(int a, int b, int c) {
        buttons[a].setBackground(Color.GREEN);
        buttons[b].setBackground(Color.GREEN);
        buttons[c].setBackground(Color.GREEN);

        for(int i = 0; i < 9; i++){
            buttons[i].setEnabled(false);
        }
        textField.setText("X wins");
        restartButton.setVisible(true);
        restartButton.setEnabled(true);
    }

    public void oWins(int a, int b, int c) {
        buttons[a].setBackground(Color.GREEN);
        buttons[b].setBackground(Color.GREEN);
        buttons[c].setBackground(Color.GREEN);

        for(int i = 0; i < 9; i++){
            buttons[i].setEnabled(false);
        }
        textField.setText("O wins");
        restartButton.setVisible(true);
        restartButton.setEnabled(true);
    }

    public void checkDraw(){

        int count = 0;
        for (int i = 0; i < 9; i++){
            if(!buttons[i].getText().equals("")){
                count++;
            }
        }
        if (count == 9) {
            for (int i = 0; i < 9; i++) {
                buttons[i].setEnabled(false);
            }
            textField.setText("Draw!");
            restartButton.setVisible(true);
            restartButton.setEnabled(true);
        }
    }

    public void redrawBoard(){
        button_panel.removeAll();

        for (int i = 0; i<9 ; i++) {
            buttons[i] = new JButton();
            button_panel.add(buttons[i]);
            buttons[i].setFont(new Font("Times New Roman", Font.BOLD, 140));
            buttons[i].setFocusable(false);
            buttons[i].addActionListener(this);
        }

        button_panel.updateUI();

        restartButton.setVisible(false);
        restartButton.setEnabled(false);
        
        firstTurn();
    }
}

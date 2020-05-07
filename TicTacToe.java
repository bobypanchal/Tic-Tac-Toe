import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class TicTacToe extends JFrame{
	JPanel top = null;
	JLabel win = null;
	JButton reset = null;
	JPanel center = null;
	JButton[] button = new JButton[9];
	int[] a = {0,0,0,0,0,0,0,0,0};
	ImageIcon img_1 = null;
	ImageIcon img_2 = null;
	int player = 1;
	public TicTacToe() {
		setSize(400,400);
		setTitle("Tic-Tac-Toe");
		//TOP
		top = new JPanel();
		add(top, BorderLayout.NORTH);
		reset = new JButton("Reset");
		win = new JLabel();
		win.setText("GAME IS ON");
		top.add(win);
		top.add(reset);
		//CENTER
		center = new JPanel();
		add(center, BorderLayout.CENTER);
		button = new JButton[9];
		center.setSize(300,300);
		center.setLayout(new GridLayout(3,3));
		for(int j=0; j<9; j++) {
			button[j] = new JButton();
			center.add(button[j]);
		}
		
		initilizeGame(0);

		for(int i=0; i<9; i++){		
			final int n = i;
			button[i].addActionListener(new ActionListener(){
				public void actionPerformed(ActionEvent e){
					change(n);
				}
			});
		}
		reset.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				initilizeGame(1);
			}
		});

		setVisible(true);
		setDefaultCloseOperation(DISPOSE_ON_CLOSE);
	}

	public void initilizeGame(int check){
		img_1 = new ImageIcon("image\\X.png");
		img_2 = new ImageIcon("image\\O.png");
		
		player = 1;
		win.setText("GAME IS ON");
		top.setBackground(null);
		if(check == 1)
			for(int i=0; i<9; i++){
				button[i].setIcon(null);
				button[i].setEnabled(true);
				a[i] = 0;
			}
	}


	public void change(int i) {
		if(player == 1) {
			button[i].setIcon(img_1);
			a[i] = 1;//1 for X
			player = 2;
		}
		else {
			button[i].setIcon(img_2);
			a[i] = 2;//2 for O
			player = 1;
		}
		button[i].setEnabled(false);
		int var = final_check();
		if(var == 1 || var == 2) {
			top.setBackground(Color.GREEN);
			win.setText("PLAYER " + var + " IS WIN");
			for(int m=0; m<button.length; m++) {
				button[m].setEnabled(false);
			}
		}
		top.add(reset);
	}

	public int final_check() {
		int check_var[][] = {{0,1,2},{3,4,5},{6,7,8},{0,3,6},{1,4,7},{2,5,8},{0,4,8},{2,4,6}};
		for(int i=0; i<check_var.length; i++) {
			if((a[check_var[i][0]] == 1) && (a[check_var[i][1]] == 1) && (a[check_var[i][2]] == 1)) {
				return 1;
			}
			else if((a[check_var[i][0]] == 2) && (a[check_var[i][1]] == 2) && (a[check_var[i][2]] == 2)) {
				win.setText("PLAYER 2 IS WIN");
				top.setBackground(Color.GREEN);
				return 2;
			}
		}
		for(int i=0; i<9; i++){
			if(button[i].isEnabled())
				return 0;
			else if(i == 8){
				win.setText("GAME IS DROW!");
				top.setBackground(Color.RED);
			}
		}
		return 0;
	}
	public static void main(String []a) throws Exception{
		new TicTacToe();
	}
}

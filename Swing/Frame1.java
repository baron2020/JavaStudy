package test0827;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;

public class Frame {
	public static void main(String[] args) {
		//System.out.println("test");
		JFrame frame = new JFrame("test");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//閉じるボタンを押した時に終了する
		frame.setSize(800, 900);//画面サイズ
		frame.setLocation(1100, 50);//画面の立ち上がり位置
		//レイアウトを設定
		frame.setLayout(new FlowLayout());
		JButton button1 = new JButton("ボタン");
		//ボタンを押した時の処理
		button1.addActionListener(e -> {
			System.out.println("ボタンを押しました。");
		});
		frame.add(button1);
		frame.setVisible(true);//画面に見えるようにする
	}
}

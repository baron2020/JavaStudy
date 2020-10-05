package swing;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

//フレームを作成し、ラベルとボタンを任意の位置に配置する。
public class Frame1 {
	public static void main(String[] args) {
		Frame1 f1 = new Frame1();
		f1.run();
	}
	public void run() {
		JFrame frame = new JFrame("テスト");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//閉じるボタンを押した時に終了する
		frame.setSize(700, 800);//画面サイズwh
		frame.setLocation(1150, 100);//画面の立ち上がり位置
		JPanel p = new JPanel();
		frame.setContentPane(p);
		p.setLayout(null);
		JLabel label1= new JLabel("ラベル");
		label1.setBounds(20, 20, 100, 50);
		JButton button1 = new JButton("ボタン");
		button1.setBounds(20, 80, 100, 50);//配置x,配置y,サイズwidth,サイズheight
		//ボタンを押した時の処理
		button1.addActionListener(e -> {
			System.out.println("ボタンを押しました。");
		});
		p.add(label1);
		p.add(button1);
		frame.setVisible(true);//画面に見えるようにする
	}
}

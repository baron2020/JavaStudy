import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Calculator {
	//フレームを作成し、ラベルとボタンを任意の位置に配置する。
	public static void main(String[] args) {
		Calculator den = new Calculator();
		den.run();
	}

	public void run() {
		JFrame frame = new JFrame("テスト");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//閉じるボタンを押した時に終了する
		frame.setSize(700, 800);//画面サイズwh
		frame.setLocation(1150, 100);//画面の立ち上がり位置
		JPanel p = new JPanel();
		frame.setContentPane(p);
		p.setLayout(null);
		JLabel label1 = new JLabel("計算機");
		label1.setBounds(30, 20, 390, 50);
		//ボタン20種類
		JButton b1 = new JButton("（");
		JButton b2 = new JButton("）");
		JButton b3 = new JButton("％");
		JButton b4 = new JButton("ＡＣ");
		JButton b5 = new JButton("７");
		JButton b6 = new JButton("８");
		JButton b7 = new JButton("９");
		JButton b8 = new JButton("÷");
		JButton b9 = new JButton("４");
		JButton b10 = new JButton("５");
		JButton b11 = new JButton("６");
		JButton b12 = new JButton("×");
		JButton b13 = new JButton("１");
		JButton b14 = new JButton("２");
		JButton b15 = new JButton("３");
		JButton b16 = new JButton("－");
		//配置x,配置y,サイズwidth,サイズheight
		b1.setBounds(30, 80, 120, 80);
		b2.setBounds(150, 80, 120, 80);
		b3.setBounds(270, 80, 120, 80);
		b4.setBounds(390, 80, 120, 80);
		b5.setBounds(30, 160, 120, 80);
		b6.setBounds(150, 160, 120, 80);
		b7.setBounds(270, 160, 120, 80);
		b8.setBounds(390, 160, 120, 80);
		b9.setBounds(30, 240, 120, 80);
		b10.setBounds(150, 240, 120, 80);
		b11.setBounds(270, 240, 120, 80);
		b12.setBounds(390, 240, 120, 80);
		b13.setBounds(30, 320, 120, 80);
		b14.setBounds(150, 320, 120, 80);
		b15.setBounds(270, 320, 120, 80);
		b16.setBounds(390, 320, 120, 80);
		//ボタンを押した時の処理
		b1.addActionListener(e -> {
			System.out.println(b1.getText() + "ボタンを押しました。");
			label1.setText(b1.getText());
		});
		p.add(label1);
		p.add(b1);
		p.add(b2);
		p.add(b3);
		p.add(b4);
		p.add(b5);
		p.add(b6);
		p.add(b7);
		p.add(b8);
		p.add(b9);
		p.add(b10);
		p.add(b11);
		p.add(b12);
		p.add(b13);
		p.add(b14);
		p.add(b15);
		p.add(b16);

		frame.setVisible(true);//画面に見えるようにする
	}
}

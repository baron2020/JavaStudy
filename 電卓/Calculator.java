package swing0922;

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
		JFrame frame = new JFrame("シンプル電卓");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//閉じるボタンを押した時に終了する
		frame.setSize(700, 800);//画面サイズwh
		frame.setLocation(1150, 100);//画面の立ち上がり位置
		JPanel p = new JPanel();
		frame.setContentPane(p);
		p.setLayout(null);
		//計算表示ラベル
		JLabel label1 = new JLabel("表示画面");
		//配置x,配置y,サイズwidth,サイズheight
		int dispWidth = 480;//表示画面の横幅
		int dispHeight = 80;//ボタンの高さ
		int dispMarginX = 110;//余白X
		int dispMarginY = 70;//余白Y
		label1.setBounds(dispMarginX, dispMarginY, dispWidth, dispHeight);
		//ボタンのテキスト
		String[] s = { "Ｃ", "√", "％", "÷", "７", "８", "９", "×", "４", "５", "６", "－",
				"１", "２", "３", "＋", "０", "００", "．", "＝" };
		JButton[] JButtonArray = new JButton[20];
		//ボタン20種類のインスタンス生成とイベントの設定
		for (int i = 0; i < JButtonArray.length; i++) {
			JButtonArray[i] = new JButton(s[i]);
			String temp = s[i];//ラベルの文字列
			//ボタンを押した時の処理
			JButtonArray[i].addActionListener(e -> {
				System.out.println(temp + "ボタンを押しました。");
				label1.setText(temp);
			});
		}

		int bWidth = 120;//ボタンの横幅
		int bHeight = 80;//ボタンの高さ
		int bMarginX = 110;//余白X
		int bMarginY = 150;//余白Y

		//配置x,配置y,サイズwidth,サイズheight
		//ボタン一段目
		JButtonArray[0].setBounds(bMarginX, bMarginY, bWidth, bHeight);
		JButtonArray[1].setBounds(230, bMarginY, bWidth, bHeight);
		JButtonArray[2].setBounds(350, bMarginY, bWidth, bHeight);
		JButtonArray[3].setBounds(470, bMarginY, bWidth, bHeight);
		//ボタン二段目
		JButtonArray[4].setBounds(bMarginX, 230, bWidth, bHeight);
		JButtonArray[5].setBounds(230, 230, 120, 80);
		JButtonArray[6].setBounds(350, 230, 120, 80);
		JButtonArray[7].setBounds(470, 230, 120, 80);
		//ボタン三段目
		JButtonArray[8].setBounds(bMarginX, 310, 120, 80);
		JButtonArray[9].setBounds(230, 310, 120, 80);
		JButtonArray[10].setBounds(350, 310, 120, 80);
		JButtonArray[11].setBounds(470, 310, 120, 80);
		//ボタン四段目
		JButtonArray[12].setBounds(bMarginX, 390, 120, 80);
		JButtonArray[13].setBounds(230, 390, 120, 80);
		JButtonArray[14].setBounds(350, 390, 120, 80);
		JButtonArray[15].setBounds(470, 390, 120, 80);
		//ボタン五段目
		JButtonArray[16].setBounds(bMarginX, 470, 120, 80);
		JButtonArray[17].setBounds(230, 470, 120, 80);
		JButtonArray[18].setBounds(350, 470, 120, 80);
		JButtonArray[19].setBounds(470, 470, 120, 80);

		p.add(label1);//ラベルの配置
		for (int i = 0; i < JButtonArray.length; i++) {
			p.add(JButtonArray[i]);//ボタンの配置
		}
		frame.setVisible(true);//画面に見えるようにする
	}
}

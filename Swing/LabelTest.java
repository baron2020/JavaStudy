package swing;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

//ラベルの操作(色,後色,サイズ,罫線,文字色,文字サイズ,文字位置、変更)
public class LabelTest {
	public static void main(String[] args) {
		LabelTest lt = new LabelTest();
		lt.run();
	}

	public void run() {
		JFrame frame = new JFrame("図形テスト1");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//閉じるボタンを押した時に終了する
		frame.setSize(700, 800);//画面サイズwh
		frame.setLocation(1150, 100);//画面の立ち上がり位置
		JPanel p = new JPanel();
		frame.setContentPane(p);
		p.setLayout(null);
		//ラベル関連
		JLabel label1 = new JLabel("●");
		JLabel label2 = new JLabel("●");
		int labelW = 75;//表示画面の横幅
		int labelH = 75;//表示画面の高さ
		int displayMarginX = 50;//余白X
		int displayMarginY = 75;//余白Y

		//配置x,配置y,サイズwidth,サイズheight
		label1.setBounds(displayMarginX, displayMarginY, labelW, labelH);
		label2.setBounds(displayMarginX, displayMarginY + 73, labelW, labelH);
		label1.setFont(new Font("MSゴシック", Font.PLAIN, 71));//MSゴシック,メイリオ,HGゴシック,MS明朝,游ゴシック,游明朝
		label2.setFont(new Font("MSゴシック", Font.PLAIN, 71));
		label1.setForeground(Color.black);//文字の色変更
		label2.setForeground(Color.white);
		label1.setOpaque(true);//バックグラウンドカラーの設定を許可する。
		label2.setOpaque(true);
		label1.setBackground(Color.green);//バックグラウンドカラーの設定
		label2.setBackground(Color.green);
		LineBorder br = new LineBorder(Color.black, 2, true);//罫線の色,太さ
		label1.setBorder(br);
		label2.setBorder(br);
		label1.setHorizontalAlignment(JLabel.CENTER);//水平位置
		label2.setHorizontalAlignment(JLabel.CENTER);//水平位置

		p.add(label1);//ラベルの配置
		p.add(label2);
		frame.setVisible(true);//画面に見えるようにする
	}
}
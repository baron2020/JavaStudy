package swing;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class Othello {
	public static void main(String[] args) {
		Othello othello = new Othello();
		othello.run();
	}

	public void run() {
		JFrame frame = new JFrame("オセロ");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//閉じるボタンを押した時に終了する
		frame.setSize(700, 800);//画面サイズwh
		frame.setLocation(1150, 100);//画面の立ち上がり位置
		JPanel p = new JPanel();
		frame.setContentPane(p);
		p.setLayout(null);

		//手番表示ラベル
		int marginX = 50;//余白X
		int marginY = 45;//余白Y
		int tdW = 75;//表示画面の横幅
		int tdH = 50;//表示画面の高さ
		JLabel tebanDisp = new JLabel("手番:");
		tebanDisp.setBounds(marginX, marginY, tdW, tdH);
		tebanDisp.setFont(new Font("MSゴシック", Font.PLAIN, 24));
		tebanDisp.setOpaque(true);
		tebanDisp.setBackground(Color.white);//バックグラウンドカラーの設定
		tebanDisp.setHorizontalAlignment(JLabel.CENTER);//水平位置
		p.add(tebanDisp);//手番表示ラベル
		//石
		//JLabel stone = new JLabel("●");
		//オセロ盤(マスの作成)
		int banMarginX = 50;//余白X
		int banMarginY = 100;//余白Y
		int masuW = 75;//表示画面の横幅
		int masuH = 75;//表示画面の高さ
		JLabel[][] banArray = new JLabel[8][8];
		LineBorder br = new LineBorder(Color.black, 2, true);//罫線の色,太さ
		//マス64個の配置位置(X8行＊Y8列)//罫線を考慮
		int[] setX = { banMarginX, 125-2, 200-4, 275-6, 350-8, 425-10, 500-12, 575-14 };
		int[] setY = { banMarginY, 175-2, 250-4, 325-6, 400-8, 475-10, 550-12, 625-14 };
		//マスの配置＆設定
		for (int y = 0; y < banArray.length; y++) {
			for (int x = 0; x < banArray[0].length; x++) {
				banArray[x][y] = new JLabel("");
				banArray[x][y].setBounds(setX[x], setY[y], masuW, masuH);//配置x,配置y,width,height
				banArray[x][y].setFont(new Font("MSゴシック", Font.PLAIN, 71));//フォントスタイル,文字の大きさ
				//banArray[x][y].setForeground(Color.black);//文字の色変更
				banArray[x][y].setOpaque(true);//バックグラウンドカラーの設定を許可する。
				banArray[x][y].setBackground(Color.green);//バックグラウンドカラーの設定
				banArray[x][y].setBorder(br);
				banArray[x][y].setHorizontalAlignment(JLabel.CENTER);//水平位置
				p.add(banArray[x][y]);//盤の配置
			}
		}
		frame.setVisible(true);//画面に見えるようにする
	}
}

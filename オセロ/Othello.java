package swing;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class Othello implements MouseListener {
	JLabel xyLabel;//座標ラベル
	String currentMasu;//クリックした現在のマス

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
		//座標取得イベント
		p.addMouseListener(this);
		p.setLayout(null);

		JLabel tebanDisp = new JLabel("手番:");
		xyLabel = new JLabel("座標:");
		xyLabel.setBounds(220, 45, 300, 50);
		xyLabel.setFont(new Font("MSゴシック", Font.PLAIN, 24));
		xyLabel.setOpaque(true);
		xyLabel.setBackground(Color.white);//バックグラウンドカラーの設定
		xyLabel.setHorizontalAlignment(JLabel.CENTER);//水平位置
		p.add(xyLabel);//手番表示ラベル

		//手番表示ラベル
		int marginX = 50;//余白X
		int marginY = 45;//余白Y
		int tdW = 75;//表示画面の横幅
		int tdH = 50;//表示画面の高さ
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
		int[] setX = { banMarginX, 125 - 2, 200 - 4, 275 - 6, 350 - 8, 425 - 10, 500 - 12, 575 - 14 };
		int[] setY = { banMarginY, 175 - 2, 250 - 4, 325 - 6, 400 - 8, 475 - 10, 550 - 12, 625 - 14 };
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

	@Override
	public void mouseClicked(MouseEvent e) {
		Point point = e.getPoint();
		System.out.println("p：" + point);
		//xyLabel.setText("座標取得:" + " " + "x:" + point.x + " " + "y:" + point.y);
		//x:52～123,125～196,198～269,271～342,344～415,417～488,490～561,563～634
		//y:102～173,175～246,248～319,321～392,394～465,467～538,540～611,613～684
		String clickX;
		String clickY;
		if ((point.x >= 52) && (point.x <= 123)) {
			clickX = "1";
		} else if ((point.x >= 125) && (point.x <= 196)) {
			clickX = "2";
		} else if ((point.x >= 198) && (point.x <= 269)) {
			clickX = "3";
		} else if ((point.x >= 271) && (point.x <= 342)) {
			clickX = "4";
		} else if ((point.x >= 344) && (point.x <= 415)) {
			clickX = "5";
		} else if ((point.x >= 417) && (point.x <= 488)) {
			clickX = "6";
		} else if ((point.x >= 490) && (point.x <= 561)) {
			clickX = "7";
		} else if ((point.x >= 563) && (point.x <= 634)) {
			clickX = "8";
		} else {
			clickX = "0";
			//currentMasu = "None";//盤外
		}
		if ((point.y >= 102) && (point.y <= 173)) {
			clickY = "1";
		} else if ((point.y >= 175) && (point.y <= 246)) {
			clickY = "2";
		} else if ((point.y >= 248) && (point.y <= 319)) {
			clickY = "3";
		} else if ((point.y >= 321) && (point.y <= 392)) {
			clickY = "4";
		} else if ((point.y >= 394) && (point.y <= 465)) {
			clickY = "5";
		} else if ((point.y >= 467) && (point.y <= 538)) {
			clickY = "6";
		} else if ((point.y >= 540) && (point.y <= 611)) {
			clickY = "7";
		} else if ((point.y >= 613) && (point.y <= 684)) {
			clickY = "8";
		} else {
			clickY = "0";
		}
		if ((clickX.equals("0")) || (clickY.equals("0"))) {
			currentMasu = "None";//盤外
		} else {
			currentMasu = "d" + clickY + "s" + clickX;
		}
		System.out.println("クリックしたマス：" + currentMasu);
		xyLabel.setText("マス:" + currentMasu);
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}
}

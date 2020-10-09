package swing;

import java.awt.Color;
import java.awt.Font;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class Othello implements MouseListener {
	Map<String, String> gameRecord;//盤面情報
	JLabel[][] masuArray;//マス配列
	JLabel tebanDisp;
	JLabel xyLabel;//座標ラベル
	String currentMasu;//クリックしたマス
	String teban = "black";//手番

	public static void main(String[] args) {
		Othello othello = new Othello();
		othello.run();
	}

	public void run() {
		JFrame frame = new JFrame("オセロ");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//閉じるボタンを押した時に終了する
		frame.setSize(700, 800);//画面サイズwh
		frame.setLocation(1150, 100);//画面の立ち上がり位置
		setUpJPanel(frame);//JPanelの準備
		setUpGameRecord();//盤面情報の初期化
		setUpStone();//初期石の配置
		frame.setVisible(true);//画面に見えるようにする
	}

	public void setUpJPanel(JFrame frame) {
		//JPanelの準備
		JPanel p = new JPanel();
		frame.setContentPane(p);
		p.addMouseListener(this);//マウスクリックイベントの設定
		p.setLayout(null);//レイアウト設定の初期化

		tebanDisp = new JLabel("手番:");
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

		//オセロ盤(マスの作成)
		int banMarginX = 50;//余白X
		int banMarginY = 100;//余白Y
		int masuW = 75;//表示画面の横幅
		int masuH = 75;//表示画面の高さ
		LineBorder br = new LineBorder(Color.black, 2, true);//罫線の色,太さ
		//マス64個の配置位置(X8行＊Y8列)//罫線を考慮
		int[] setX = { banMarginX, 125 - 2, 200 - 4, 275 - 6, 350 - 8, 425 - 10, 500 - 12, 575 - 14 };
		int[] setY = { banMarginY, 175 - 2, 250 - 4, 325 - 6, 400 - 8, 475 - 10, 550 - 12, 625 - 14 };
		masuArray = new JLabel[8][8];
		//マスの配置＆設定
		for (int y = 0; y < masuArray.length; y++) {
			for (int x = 0; x < masuArray[0].length; x++) {
				masuArray[x][y] = new JLabel("");
				masuArray[x][y].setBounds(setX[x], setY[y], masuW, masuH);//配置x,配置y,width,height
				masuArray[x][y].setFont(new Font("MSゴシック", Font.PLAIN, 71));//フォントスタイル,文字の大きさ
				masuArray[x][y].setOpaque(true);//バックグラウンドカラーの設定を許可する。
				masuArray[x][y].setBackground(Color.green);//バックグラウンドカラーの設定
				masuArray[x][y].setBorder(br);
				masuArray[x][y].setHorizontalAlignment(JLabel.CENTER);//水平位置
				p.add(masuArray[x][y]);//盤の配置
			}
		}
	}

	public void setUpGameRecord() {
		//盤面情報の初期化
		String[] masuArray = { "d1s1", "d1s2", "d1s3", "d1s4", "d1s5", "d1s6", "d1s7", "d1s8",
				"d2s1", "d2s2", "d2s3", "d2s4", "d2s5", "d2s6", "d2s7", "d2s8",
				"d3s1", "d3s2", "d3s3", "d3s4", "d3s5", "d3s6", "d3s7", "d3s8",
				"d4s1", "d4s2", "d4s3", "d4s4", "d4s5", "d4s6", "d4s7", "d4s8",
				"d5s1", "d5s2", "d5s3", "d5s4", "d5s5", "d5s6", "d5s7", "d5s8",
				"d6s1", "d6s2", "d6s3", "d6s4", "d6s5", "d6s6", "d6s7", "d6s8",
				"d7s1", "d7s2", "d7s3", "d7s4", "d7s5", "d7s6", "d7s7", "d7s8",
				"d8s1", "d8s2", "d8s3", "d8s4", "d8s5", "d8s6", "d8s7", "d8s8" };
		gameRecord = new HashMap<>();
		for (int i = 0; i < masuArray.length; i++) {
			if ((masuArray[i].equals("d4s4")) || (masuArray[i].equals("d5s5"))) {
				gameRecord.put(masuArray[i], "white");
			} else if ((masuArray[i].equals("d4s5")) || (masuArray[i].equals("d5s4"))) {
				gameRecord.put(masuArray[i], "black");
			} else {
				gameRecord.put(masuArray[i], "None");
			}
		}
	}

	public void setUpStone() {
		//初期石の配置
		masuArray[3][3].setText("●");
		masuArray[3][3].setForeground(Color.white);//石の色
		masuArray[4][4].setText("●");
		masuArray[4][4].setForeground(Color.white);
		masuArray[3][4].setText("●");
		masuArray[3][4].setForeground(Color.black);
		masuArray[4][3].setText("●");
		masuArray[4][3].setForeground(Color.black);
	}

	public void changeTeban() {
		if (teban == "black") {
			teban = "white";
		} else {
			teban = "black";
		}
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
			currentMasu = "盤外";//盤外
		} else {
			currentMasu = "d" + clickY + "s" + clickX;
		}
		System.out.println("クリックしたマス：" + currentMasu);
		xyLabel.setText("マス:" + currentMasu);

		if (currentMasu != "盤外") {
			if (gameRecord.get(currentMasu) == "None") {
				masuArray[Integer.parseInt(clickX) - 1][Integer.parseInt(clickY) - 1].setText("●");

				Color targetColor;//石の色
				if(teban=="black") {
					targetColor=Color.black;
				}else {
					targetColor=Color.white;
				}

				masuArray[Integer.parseInt(clickX) - 1][Integer.parseInt(clickY) - 1].setForeground(targetColor);
				gameRecord.replace(currentMasu, teban);

				changeTeban();//手番の交代
				System.out.println("currentMasu：" + gameRecord.get(currentMasu));
			} else {
				System.out.println("石があります。");
			}
		} else {
			System.out.println("盤外です。");
		}
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

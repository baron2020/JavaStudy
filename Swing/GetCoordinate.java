package swing;

import java.awt.Font;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

//座標取得
public class GetCoordinate implements MouseListener {
	JLabel JLabel1;

	public static void main(String[] args) {
		GetCoordinate gc = new GetCoordinate();
		gc.run();
	}

	public void run() {
		JFrame frame = new JFrame("座標取得");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//閉じるボタンを押した時に終了する
		frame.setSize(700, 800);//画面サイズwh
		frame.setLocation(1150, 100);//画面の立ち上がり位置
		JPanel p = new JPanel();
		frame.setContentPane(p);
		//座標取得イベント
		p.addMouseListener(this);
		p.setLayout(null);
		//ラベル関連
		JLabel1 = new JLabel("座標");
		int labelW = 300;//表示画面の横幅
		int labelH = 100;//表示画面の高さ
		int displayMarginX = 110;//余白X
		int displayMarginY = 55;//余白Y
		//配置x,配置y,サイズwidth,サイズheight
		JLabel1.setBounds(displayMarginX, displayMarginY, labelW, labelH);
		JLabel1.setFont(new Font("MSゴシック", Font.PLAIN, 24));
		p.add(JLabel1);//ラベルの配置
		frame.setVisible(true);//画面に見えるようにする
	}

	public void mouseClicked(MouseEvent e) {
		Point point = e.getPoint();
		System.out.println("p：" + point);
		JLabel1.setText("座標取得:" + " " + "x:" + point.x + " " + "y:" + point.y);
	}

	public void mousePressed(MouseEvent e) {
	}

	public void mouseReleased(MouseEvent e) {
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}
}
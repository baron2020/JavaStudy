package swing0922;

import java.awt.Font;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Calculator {
	//シンプル電卓
	public static void main(String[] args) {
		Calculator dentaku = new Calculator();
		dentaku.run();
	}

	//実行
	public void run() {
		JFrame frame = new JFrame("シンプル電卓");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//閉じるボタンを押した時に終了する
		frame.setSize(700, 800);//画面サイズwh
		frame.setLocation(1150, 100);//画面の立ち上がり位置
		JPanel p = new JPanel();
		frame.setContentPane(p);
		p.setLayout(null);
		//計算表示ラベル
		JLabel label1 = new JLabel();//"表示画面"
		label1.setFont(new Font("MSゴシック", Font.PLAIN, 24));
		//配置x,配置y,サイズwidth,サイズheight
		int displayWidth = 480;//表示画面の横幅
		int displayHeight = 80;//表示画面の高さ
		int displayMarginX = 110;//余白X
		int displayMarginY = 70;//余白Y
		label1.setBounds(displayMarginX, displayMarginY, displayWidth, displayHeight);
		//ボタンのテキスト
		String[] bValueArray = { "Ｃ", "√", "％", "÷", "７", "８", "９", "×", "４", "５", "６", "－",
				"１", "２", "３", "＋", "０", "００", "．", "＝" };
		JButton[] JButtonArray = new JButton[20];
		//ボタン20種類のインスタンス生成とイベントの設定
		for (int i = 0; i < JButtonArray.length; i++) {
			JButtonArray[i] = new JButton(bValueArray[i]);
			String bValue = bValueArray[i];//ボタンの値
			JButtonArray[i].setFont(new Font("MSゴシック", Font.PLAIN, 24));
			//ボタンを押した時の処理
			JButtonArray[i].addActionListener(e -> {
				//System.out.println(bValue + "ボタンを押しました。");
				if (bValue.equals("Ｃ")) {
					label1.setText("");
					return;
				}
				if (bValue.equals("＝")) {
					equalResult(label1);
					return;
				}
				String disp = label1.getText() + bValue;
				label1.setText(disp);
			});
		}
		//ボタン配置関連(X4行＊Y5段)
		int bWidth = 120;//ボタンの横幅
		int bHeight = 80;//ボタンの高さ
		int bMarginX = 110;//余白X
		int bMarginY = 150;//余白Y
		//ボタン20個の配置(X4行＊Y5段)
		int[] setX = { bMarginX, 230, 350, 470 };
		int[] setY = { bMarginY, 230, 310, 390, 470 };
		int bIndex = 0;//0～19個のボタンの添え字
		for (int y = 0; y < setY.length; y++) {
			for (int x = 0; x < setX.length; x++) {
				//配置x,配置y,サイズwidth,サイズheight
				JButtonArray[bIndex].setBounds(setX[x], setY[y], bWidth, bHeight);
				bIndex++;
			}
		}
		p.add(label1);//ラベルの配置
		for (int i = 0; i < JButtonArray.length; i++) {
			p.add(JButtonArray[i]);//ボタンの配置
		}
		frame.setVisible(true);//画面に見えるようにする
	}
	//=が押された時の処理
	public void equalResult(JLabel target) {
		String tempUse = target.getText();//表示されている計算式
		List<Integer> iSiki = new ArrayList<Integer>();//数字
		List<String> sSiki = new ArrayList<String>();//記号
		List<Integer> kigouIti = new ArrayList<Integer>();//記号の位置
		//System.out.println("計算式" + tempUse);
		//System.out.println("計算式の長さ" + tempUse.length());
		if (tempUse.length() == 0) {
			//計算式が空の時
			return;
		}
		//正規表現
		String regex1 = "([０-９]+)";//全角数字
		//String regex2 = "([0-9]+)";//半角数字
		Pattern p1 = Pattern.compile(regex1);
		Matcher m1 = p1.matcher(tempUse);
		//1個以上の数字のパターンを抽出
		while (m1.find()) {
			iSiki.add(Integer.parseInt(m1.group()));
			//System.out.println("抽出数字:" + m1.group());
		}
		String regex3 = "([^０-９])";//半角数字以外
		Pattern p2 = Pattern.compile(regex3);
		Matcher m2 = p2.matcher(tempUse);
		//数字以外のパターンを抽出＆一致した位置を取得
		while (m2.find()) {
			kigouIti.add(m2.start());
			sSiki.add(m2.group());
			//System.out.println("一致した位置:" + m2.start() + ",抽出記号:" + m2.group());
		}
		target.setText(tempUse);
		System.out.println("数字配列:" + iSiki);
		System.out.println("記号配列:" + sSiki);
		System.out.println("記号位置配列:" + kigouIti);
	}
}
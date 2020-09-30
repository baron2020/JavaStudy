package swing0922;

import java.awt.Font;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Calculator {
	//シンプル電卓
	public static void main(String[] args) {
		Calculator den = new Calculator();
		den.run();
	}

	public int existSymbolIndex(String target) {
		String check1 = "＋";
		String check2 = "－";
		String check3 = "×";
		String check4 = "÷";
		int minimumExist = 8635;
		if (target.indexOf(check1) != -1) {
			minimumExist = target.indexOf(check1);
			System.out.println(minimumExist + "+");
		}
		if ((target.indexOf(check2) != -1) & (target.indexOf(check2) < minimumExist)) {
			minimumExist = target.indexOf(check2);
			System.out.println(minimumExist + "-");
		}
		if ((target.indexOf(check3) != -1) & (target.indexOf(check3) < minimumExist)) {
			minimumExist = target.indexOf(check3);
			System.out.println(minimumExist + "*");
		}
		if ((target.indexOf(check4) != -1) & (target.indexOf(check4) < minimumExist)) {
			minimumExist = target.indexOf(check4);
			System.out.println(minimumExist + "%");
		}
		//System.out.println(minimumExist + "番目に存在します");
		return minimumExist;
	}

	public void equalResult(JLabel target) {
		String dispTemp = target.getText();
		List<String>sikiTemp=new ArrayList<String>();
		System.out.println("計算式" + dispTemp);
		System.out.println("計算式の長さ" + dispTemp.length());
		int existIndex = existSymbolIndex(dispTemp);//計算記号が最小の何番目にあるか？

		if (existIndex == 8635) {
			System.out.println("見つかりませんでした");
			target.setText(dispTemp);
			return;
		}else {
			//int siki1=Integer.parseInt(dispTemp.substring(0,checkIndex));
			String siki1=dispTemp.substring(0,existIndex);
			sikiTemp.add(siki1);
			System.out.println("式①"+sikiTemp.get(0));//式の一項
			sikiTemp.add(dispTemp.substring(existIndex,existIndex+1));
			System.out.println(existIndex + "番目に計算記号は存在します");
			System.out.println("式②"+sikiTemp.get(1));
			sikiTemp.add(dispTemp.substring(existIndex+1,dispTemp.length()));
			System.out.println("式③"+sikiTemp.get(2));//残りの式
		}
		//System.out.println(str1.substring(0,3));//二文字切り取り
		target.setText(dispTemp);
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
		JLabel label1 = new JLabel();//"表示画面"
		label1.setFont(new Font("MSゴシック", Font.PLAIN, 24));
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
			String temp = s[i];//ラベルの文字
			JButtonArray[i].setFont(new Font("MSゴシック", Font.PLAIN, 24));
			//ボタンを押した時の処理
			JButtonArray[i].addActionListener(e -> {
				System.out.println(temp + "ボタンを押しました。");
				if (temp.equals("Ｃ")) {
					label1.setText("");
					return;
				}
				if (temp.equals("＝")) {
					equalResult(label1);
					return;
				}
				//System.out.println(label1.getText()+"です");
				String disp = label1.getText() + temp;
				label1.setText(disp);
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

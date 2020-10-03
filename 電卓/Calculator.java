package swing0922;

import java.awt.Font;
import java.util.ArrayList;
import java.util.Arrays;
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

		List<String> NumberOrSymbolArray = new ArrayList<String>();//数字か記号か？

		//ラベル関連
		JLabel[] JLabelArray = new JLabel[4];
		//ラベルのテキスト
		String[] jbValueArray = { "入力：", "", "結果：", "" };
		//ラベルのインスタンス生成
		for (int i = 0; i < JLabelArray.length; i++) {
			JLabelArray[i] = new JLabel(jbValueArray[i]);
			JLabelArray[i].setFont(new Font("MSゴシック", Font.PLAIN, 24));
		}
		JLabel inputDisplay = JLabelArray[1];//"入力式表示ラベル"
		JLabel resultDisplay = JLabelArray[3];//"計算結果表示ラベル"
		int displayW = 480;//表示画面の横幅
		int displayH = 80;//表示画面の高さ
		int displayMarginX = 110;//余白X
		int displayMarginY = 55;//余白Y
		//配置x,配置y,サイズwidth,サイズheight
		JLabelArray[0].setBounds(displayMarginX, displayMarginY, 80, displayH);
		JLabelArray[2].setBounds(displayMarginX, displayMarginY * 2, 80, displayH);
		inputDisplay.setBounds(displayMarginX + 75, displayMarginY, displayW, displayH);
		resultDisplay.setBounds(displayMarginX + 75, displayMarginY * 2, displayW, displayH);

		//ボタン関連
		JButton[] JButtonArray = new JButton[20];
		String[] bValueArray = { "Ｃ", "√", "％", "÷", "７", "８", "９", "×",
				"４", "５", "６", "－", "１", "２", "３", "＋", "０", "００", "．", "＝" };
		String[] numberArray = { "１", "２", "３", "４", "５", "６", "７", "８", "９", "０", "００" };
		String[] kigouArray = { "＋", "－", "×", "÷" };
		//ボタン20種類のインスタンス生成とイベントの設定
		for (int i = 0; i < JButtonArray.length; i++) {
			JButtonArray[i] = new JButton(bValueArray[i]);
			String bValue = bValueArray[i];//ボタンの値
			JButtonArray[i].setFont(new Font("MSゴシック", Font.PLAIN, 24));
			//ボタンを押した時の処理
			JButtonArray[i].addActionListener(e -> {
				//System.out.println(bValue + "ボタンを押しました。");
				if (bValue.equals("Ｃ")) {
					inputDisplay.setText("");
					resultDisplay.setText("");
					NumberOrSymbolArray.clear();
					return;
				}
				if (bValue.equals("＝")) {
					if (inputDisplay.getText().length() == 0) {
						//計算式が空の時
						return;
					}
					equalResult(inputDisplay);
					return;
				}
				if (Arrays.asList(kigouArray).contains(bValue)) {
					// "＋", "－", "×", "÷"を押した時の処理
					if ((inputDisplay.getText().length() == 0) & (bValue != "－")) {
						//計算式が空の時は演算子は"－"以外受け付けない
						return;
					}
					if (inputDisplay.getText().length() != 0) {
						//記号連続確認
						String s = inputDisplay.getText().substring(0, inputDisplay.getText().length() - 1);
						String sEnd = inputDisplay.getText().substring(inputDisplay.getText().length() - 1);
						//System.out.println("s:" + s);
						//System.out.println("send:" + sEnd);
						if ((sEnd.equals("×")) && (bValue == "－")) {
							System.out.println("特殊1パターン");
						}
						if ((sEnd.equals("÷")) && (bValue == "－")) {
							System.out.println("特殊2パターン");
						}
					}
					NumberOrSymbolArray.add("記号");
				}
				if (Arrays.asList(numberArray).contains(bValue)) {
					// 数字を押した時の処理
					if ((NumberOrSymbolArray.size() == 0) ||
							(NumberOrSymbolArray.get(NumberOrSymbolArray.size() - 1) == "記号")) {
						NumberOrSymbolArray.add("数字");
					}
				}
				String display = inputDisplay.getText() + bValue;
				inputDisplay.setText(display);

				System.out.println("NorS：" + NumberOrSymbolArray);
			});
		}
		//ボタン配置関連(X4行＊Y5段)
		int bWidth = 120;//ボタンの横幅
		int bHeight = 80;//ボタンの高さ
		int bMarginX = 110;//余白X
		int bMarginY = 200;//余白Y
		//ボタン20個の配置(X4行＊Y5段)
		int[] setX = { bMarginX, 230, 350, 470 };
		int[] setY = { bMarginY, 280, 360, 440, 520 };
		int bIndex = 0;//0～19個のボタンの添え字
		for (int y = 0; y < setY.length; y++) {
			for (int x = 0; x < setX.length; x++) {
				//配置x,配置y,サイズwidth,サイズheight
				JButtonArray[bIndex].setBounds(setX[x], setY[y], bWidth, bHeight);
				bIndex++;
			}
		}
		p.add(JLabelArray[0]);
		p.add(JLabelArray[2]);
		p.add(inputDisplay);//入力式ラベルの配置
		p.add(resultDisplay);//計算結果ラベルの配置
		for (int i = 0; i < JButtonArray.length; i++) {
			p.add(JButtonArray[i]);//ボタンの配置
		}
		frame.setVisible(true);//画面に見えるようにする
	}

	//＝が押された時の処理
	public void equalResult(JLabel target) {
		String tempSiki = target.getText();//表示されている計算式
		List<Integer> iSiki = new ArrayList<Integer>();//数字
		List<String> enzansiArray = new ArrayList<String>();//記号
		List<Integer> enzansiPositionArray = new ArrayList<Integer>();//記号の位置
		//正規表現
		String regex1 = "([０-９]+)";//全角数字
		Pattern p1 = Pattern.compile(regex1);
		Matcher m1 = p1.matcher(tempSiki);
		//1個以上の数字のパターンを抽出
		while (m1.find()) {
			iSiki.add(Integer.parseInt(m1.group()));
			//System.out.println("抽出数字:" + m1.group());
		}
		String regex3 = "([^０-９])";//全角数字以外
		Pattern p2 = Pattern.compile(regex3);
		Matcher m2 = p2.matcher(tempSiki);
		//数字以外のパターンを抽出＆一致した位置を取得
		while (m2.find()) {
			enzansiArray.add(m2.group());
			enzansiPositionArray.add(m2.start());
			//System.out.println("一致した位置:" + m2.start() + ",抽出記号:" + m2.group());
		}
		target.setText(tempSiki);
		System.out.println("数字配列:" + iSiki);
		System.out.println("演算子配列:" + enzansiArray);
		System.out.println("演算子位置配列:" + enzansiPositionArray);
	}
}
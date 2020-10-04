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
				int inpLen = inputDisplay.getText().length();//入力式の長さ
				if (bValue.equals("Ｃ")) {
					inputDisplay.setText("");
					resultDisplay.setText("");
					NumberOrSymbolArray.clear();
					return;
				}
				if (bValue.equals("＝")) {
					if (inpLen == 0) {
						//計算式が空の時
						return;
					}
					if (NumberOrSymbolArray.get(NumberOrSymbolArray.size() - 1).equals("記号")) {
						System.out.println("記号で終わっています。");
						return;
					}
					equalResult(NumberOrSymbolArray, inputDisplay);
					return;
				}
				// "＋", "－", "×", "÷"を押した時の処理
				if (Arrays.asList(kigouArray).contains(bValue)) {
					if (inpLen == 0) {
						if (bValue.equals("－")) {
							NumberOrSymbolArray.add("記号");
							inputDisplay.setText(bValue);
							return;
						} else {
							//計算式が空の時は演算子は"－"以外受け付けない
							return;
						}
					}

					String tempInp1 = inputDisplay.getText().substring(0, inpLen - 1);//末尾を除いた式
					String end1 = inputDisplay.getText().substring(inpLen - 1);//末尾
					if (NumberOrSymbolArray.size() == 1) {
						if (NumberOrSymbolArray.get(NumberOrSymbolArray.size() - 1).equals("記号")) {
							if (bValue.equals("＋")) {
								inputDisplay.setText("");
								NumberOrSymbolArray.clear();
								return;
							} else {
								return;
							}
						}
					}
					if (NumberOrSymbolArray.size() != 0) {
						if (NumberOrSymbolArray.get(NumberOrSymbolArray.size() - 1).equals("数字")) {
							NumberOrSymbolArray.add("記号");
							inputDisplay.setText(inputDisplay.getText() + bValue);
							return;
						}
						if (inpLen >= 3) {
							String tempInp2 = inputDisplay.getText().substring(0, inpLen - 2);//末尾二文字を除いた式
							String end2 = inputDisplay.getText().substring(inpLen - 2, inpLen - 1);//末尾から二文字目
							if (((end2.equals("×")) && (end1.equals("－"))) ||
									((end2.equals("÷")) && (end1.equals("－")))) {
								System.out.println("2記号→1記号");
								NumberOrSymbolArray.remove(NumberOrSymbolArray.size() - 1);
								inputDisplay.setText(tempInp2 + bValue);
								return;
							}
						}
						if (((end1.equals("×")) && (bValue.equals("－"))) ||
								((end1.equals("÷")) && (bValue.equals("－")))) {
							System.out.println("1記号→2記号");
							NumberOrSymbolArray.add("記号");
							inputDisplay.setText(inputDisplay.getText() + bValue);
							return;
						}
						if (Arrays.asList(kigouArray).contains(end1)) {
							System.out.println("1記号→1記号");
							inputDisplay.setText(tempInp1 + bValue);
							return;
						}
					}
				}
				// 数字を押した時の処理
				if (Arrays.asList(numberArray).contains(bValue)) {
					if ((NumberOrSymbolArray.size() == 0) ||
							(NumberOrSymbolArray.get(NumberOrSymbolArray.size() - 1).equals("記号"))) {
						NumberOrSymbolArray.add("数字");
					}
					inputDisplay.setText(inputDisplay.getText() + bValue);
				}
				//System.out.println("NorS：" + NumberOrSymbolArray);
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
	public void equalResult(List<String> NumOrSimArray, JLabel target) {
		String tempSiki = target.getText();//表示されている計算式
		List<Integer> numberArray = new ArrayList<Integer>();//数字
		List<String> enzansiArray = new ArrayList<String>();//記号
		List<Integer> enzansiPositionArray = new ArrayList<Integer>();//記号の位置
		//正規表現
		String regex1 = "([０-９]+)";//全角数字
		Pattern p1 = Pattern.compile(regex1);
		Matcher m1 = p1.matcher(tempSiki);
		//1個以上の数字のパターンを抽出
		while (m1.find()) {
			numberArray.add(Integer.parseInt(m1.group()));
		}
		String regex3 = "([^０-９])";//全角数字以外
		Pattern p2 = Pattern.compile(regex3);
		Matcher m2 = p2.matcher(tempSiki);
		//数字以外のパターンを抽出＆一致した位置を取得
		while (m2.find()) {
			enzansiArray.add(m2.group());
			enzansiPositionArray.add(m2.start());
		}
		target.setText(tempSiki);
		System.out.println("数字配列:" + numberArray);
		System.out.println("演算子配列:" + enzansiArray);
		//System.out.println("演算子位置配列:" + enzansiPositionArray);
		System.out.println("数・記配列:" + NumOrSimArray);
		//式の変換①
		List<String> changeArray1 = new ArrayList<String>();//式の変換
		int nIndex = 0;
		int eIndex = 0;
		for (int i = 0; i < NumOrSimArray.size(); i++) {
			if (NumOrSimArray.get(i).equals("数字")) {
				changeArray1.add(String.valueOf(numberArray.get(nIndex)));
				nIndex++;
				continue;
			}
			if (NumOrSimArray.get(i).equals("記号")) {
				changeArray1.add(enzansiArray.get(eIndex));
				eIndex++;
				continue;
			}
		}
		System.out.println("変換配列①:" + changeArray1);
		//式の変換②式から"－"を変換
		List<String> changeArray2 = new ArrayList<String>();//式から"－"を変換
		for (int i = 0; i < changeArray1.size(); i++) {
			if (changeArray1.get(i).equals("－")) {
				changeArray2.add("－" + changeArray1.get(i + 1));
				i++;
			} else {
				changeArray2.add(changeArray1.get(i));
			}
		}
		System.out.println("変換配列②:" + changeArray2);
		//"×"計算
		List<String> changeArray3 = new ArrayList<String>();//計算
//		for (int i = 0; i < changeArray2.size(); i++) {
//			if (changeArray2.get(i).equals("×")) {
//				String taget1=changeArray2.get(i-1);
//				String taget2=changeArray2.get(i+1);
//				int result=Integer.parseInt(taget1)*Integer.parseInt(taget2);
//				changeArray2.set(i+1, String.valueOf(result));
//				changeArray3.add(String.valueOf(result));
//				i++;
//			}
//		}
		System.out.println("×計算結果:" + changeArray3);
	}
}
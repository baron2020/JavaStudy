package swing0922;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

//正規表現で一致するパターンを抽出する。
//1個以上の数字,数字以外,一致した位置を探す。
public class Seiki {
	public static void main(String[] args) {
		String test = "--325+36-123-456";//テスト文字列
		String regex1 = "([0-9]+)";//半角数字
		String regex2 = "([０-９]+)";//全角数字
		Pattern p = Pattern.compile(regex1);
		Matcher m = p.matcher(test);
		//1個以上の数字のパターンを抽出
		while (m.find()) {
			System.out.println("抽出数字:" + m.group());
		}
		String regex3 = "([^0-9])";//半角数字以外
		Pattern p2 = Pattern.compile(regex3);
		Matcher m2 = p2.matcher(test);
		//数字以外のパターンを抽出＆一致した位置を取得
		while (m2.find()) {
			System.out.println("一致した位置:" + m2.start()+",抽出記号:" + m2.group());
		}
	}
}

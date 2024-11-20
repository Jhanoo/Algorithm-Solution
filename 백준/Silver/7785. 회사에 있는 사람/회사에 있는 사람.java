import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.TreeSet;

public class Main {

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();

		int n = Integer.parseInt(br.readLine());

		// 정렬된 Set
		TreeSet<String> set = new TreeSet<>();

		String[] str;
		for (int i = 0; i < n; i++) {
			str = br.readLine().split(" ");
			
			if (str[1].equals("enter"))
				set.add(str[0]);
			else
				set.remove(str[0]);
		}

		// 역순 탐색
		for (String name : set.descendingSet()) {
			sb.append(name).append("\n");
		}
		
		System.out.println(sb);
	}

}
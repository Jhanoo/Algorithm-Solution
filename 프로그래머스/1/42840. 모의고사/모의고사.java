class Solution {
    public int[] solution(int[] answers) {
        int[] answer = {};

        // 1번 12345 ~
        // 2번 21 23 24 25 ~
        // 3번 33 11 22 44 55 ~
        // answers.length <= 10000
        int[] no1 = new int[] { 1, 2, 3, 4, 5 };
        int[] no2 = new int[] { 2, 1, 2, 3, 2, 4, 2, 5 };
        int[] no3 = new int[] { 3, 3, 1, 1, 2, 2, 4, 4, 5, 5 };

        int cnt1 = 0;
        int cnt2 = 0;
        int cnt3 = 0;

        int idx1 = 0;
        int idx2 = 0;
        int idx3 = 0;

        for (int i = 0; i < answers.length; i++) {
            if (answers[i] == no1[idx1++]) {
                cnt1++;
            }
            if (idx1 == no1.length) {
                idx1 = 0;
            }

            if (answers[i] == no2[idx2++]) {
                cnt2++;
            }
            if (idx2 == no2.length) {
                idx2 = 0;
            }

            if (answers[i] == no3[idx3++]) {
                cnt3++;
            }
            if (idx3 == no3.length) {
                idx3 = 0;
            }
        }

        if (cnt1 > cnt2) {
            if (cnt1 > cnt3) {
                answer = new int[] { 1 };
            } else if (cnt1 < cnt3) {
                answer = new int[] { 3 };
            } else {
                answer = new int[] { 1, 3 };
            }
        } else if (cnt1 < cnt2) {
            if (cnt2 > cnt3) {
                answer = new int[] { 2 };
            } else if (cnt2 < cnt3) {
                answer = new int[] { 3 };
            } else {
                answer = new int[] { 2, 3 };
            }
        } else {
            if (cnt2 > cnt3) {
                answer = new int[] { 1, 2 };
            } else if (cnt2 < cnt3) {
                answer = new int[] { 3 };
            } else {
                answer = new int[] { 1, 2, 3 };
            }
        }

        return answer;
    }
	
}
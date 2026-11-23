import java.util.*;

class Solution {
    
    private static final int[] RATES = {10, 20, 30, 40};
    
    private static int[][] users;
    private static int[] emoticons;

    private static int bestPlus = 0;
    private static int bestSales = 0;
    
    public int[] solution(int[][] users, int[] emoticons) {
        this.users = users;
        this.emoticons = emoticons;
        
        int[] discounts = new int[emoticons.length];
        
        dfs(0, discounts);
        
        return new int[] {bestPlus, bestSales};
    }
    
    private static void dfs(int depth, int[] discounts) {
        if (depth == discounts.length) {
            findBest(discounts);
            return;
        }
        
        for (int rate : RATES) {
            discounts[depth] = rate;
            dfs(depth + 1, discounts);
        }
    }
    
    private static void findBest(int[] discounts) {
        int plus = 0;
        int sales = 0;
        
        for (int[] user : users) {
            int price = 0;
            
            for (int i = 0; i < discounts.length; i++) {
                if (discounts[i] < user[0]) continue;
                
                price += emoticons[i] * (100 - discounts[i]) / 100;
            }
            
            if (price >= user[1]) plus++;
            else sales += price;
        }
        
        if (plus < bestPlus) return;
        else if (plus == bestPlus) {
            bestSales = Math.max(sales, bestSales);
        }
        else {
            bestPlus = plus;
            bestSales = sales;
        }
    }
}
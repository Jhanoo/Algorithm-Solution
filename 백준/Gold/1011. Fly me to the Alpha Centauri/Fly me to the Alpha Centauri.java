import java.util.Scanner;

public class Main {

    public static int binarySearch(int[] arr, int n){
        int start = 0;
        int end = 92680;
        int mid;
        
        while(start <= end){
            mid = (start + end) / 2;
            
            if(n == arr[mid])
                return mid;
            else if(n < arr[mid])
                end = mid - 1;
            else
                start = mid + 1;
        }
        return end;
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int t = sc.nextInt();    // Test case
        
        int x, y;    // x = 현재위치, y = 목표위치. 0<=x<y<2^31
        int distance;
        
        int a[] = new int[92681];
        a[0] = 1;
        for(int i = 1; i < 92681; i++){
            a[i] = a[i-1]+(i+1)/2;
        }
        
        for(int i = 0; i < t; i++){
            x = sc.nextInt();
            y = sc.nextInt();
            
            distance = y-x;
            System.out.println(binarySearch(a, distance)+1);
        }
        
        
    }
}

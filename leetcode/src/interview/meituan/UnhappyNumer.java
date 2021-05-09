package interview.meituan;

import java.util.Scanner;

public class UnhappyNumer {
    public static void main(String[] args) {
        UnhappyNumer unhappyNumer =new UnhappyNumer();
        unhappyNumer.getUnhappyNum();
    }

    public void getUnhappyNum(){
        Scanner scanner = new Scanner(System.in);
        String requires=scanner.nextLine();
        String[] array = requires.split(" ");
        int count = Integer.valueOf(array[0]);
        String k = array[1];

        int unHappyCount = 0;

        String[] nums = scanner.nextLine().split(" ");
        for(int i=0;i<count;i++){
            if(fun(Integer.valueOf(nums[i])).indexOf(k)!=-1)
                unHappyCount++;
        }
        System.out.println(unHappyCount);


    }

    private  String fun(int num) {
        String combination = "";
        if(num == 1) {
            combination+="1";
            return combination;
        }
        combination += "1";
        for(int i= 2;i<= Math.sqrt(num);i++) {
            while(num%i ==0) {
                combination += i;
                num /=i;
            }
        }

        if(num >1) {
            combination += num;
        }
        return combination;
    }
}

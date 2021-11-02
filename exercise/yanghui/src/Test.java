import java.util.*;

/**
 * User: czc
 * Date: 2021-09-26
 */
public class Test {
    public static void main(String[] args) {
        read();
    }

    public static void read(){
        boolean flag = true;
        Scanner scanner = new Scanner(System.in);
        while (flag){
            System.out.println("请输入，以回车结束");
            System.out.println("1、1~10以内的数字");
            System.out.println("2、quit结束程序\n");
            if (scanner.hasNextLine()){
                String str = scanner.nextLine();
                try {
                    int number = Integer.parseInt(str);
                    if(number > 10 || number < 0){
                        System.out.println(">>>>>>>输入有误，请重新输入<<<<<<<");
                        continue;
                    }
                    handle(number);
                } catch (NumberFormatException e) {
                    if(str.equalsIgnoreCase("quit")){
                        flag = false;
                    }else{
                        System.out.println(">>>>>>>输入有误，请重新输入<<<<<<<");
                    }
                }
            }
        }
    }

    public static void handle(int number) {
        int[][] yangHui ;
        yangHui = new int[number][];
        for (int i = 0; i < yangHui.length; i++) {
            yangHui[i] = new int[i + 1];
            yangHui[i][0] = yangHui[i][i] = 1;
            for(int j = 1;j < yangHui[i].length - 1;j++){
                yangHui[i][j] = yangHui[i-1][j-1] + yangHui[i-1][j];
            }
        }
        HashMap<Integer, Integer> s = new HashMap<>();
        int tempSum;
        for(int i = 0;i < yangHui.length;i++){
            tempSum = 0;
            for(int j = 0;j < yangHui[i].length;j++){
                System.out.print(yangHui[i][j] + "\t");
                tempSum += yangHui[i][j];
            }
            s.put(i+1,tempSum);
            System.out.println();
        }
        Set<Map.Entry<Integer, Integer>> entries = s.entrySet();
        Iterator<Map.Entry<Integer, Integer>> iterator = entries.iterator();
        while (iterator.hasNext()){
            Map.Entry<Integer, Integer> next = iterator.next();
            System.out.println(next.getKey()+":"+next.getValue());
        }
    }
}

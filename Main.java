package chucknorris;
import java.util.Scanner;
public class Main {
    static String a;
    static StringBuilder unary= new StringBuilder();
    static StringBuilder binary= new StringBuilder();
    static StringBuilder chuck= new StringBuilder();


    public static boolean isAscii(int code) {
        return code >= 0 && code <= 127;
    }

    static void stage1() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        String[] str = input.split("");
        for (int i = 0; i < input.length(); i++) {
            System.out.print(str[i] + " ");
        }
    }

    // conversion the input from char to binary
    static void toBinary(String input, String a){

        char[] c= input.toCharArray();
        for (int i=0; i<input.length();i++){
            a= Integer.toBinaryString(c[i]);
            if(a.length()!=7){
                a=0+a;
            }
            binary.append(a);
        }
        binary.append(" ");
    }

    //conversion from binary to chuck encipher code
    static void toChuckCode(){
        chuck.delete(0, chuck.length());
        for(int i=0; i<binary.length()-1;i++){
            if(binary.charAt(i)==binary.charAt(i+1)){
                unary.append(0);
            }
            else if (binary.charAt(i)!=binary.charAt(i+1)) {
                if(binary.charAt(i)=='1'){
                    unary.append("0");
                    chuck.append(" 0 "+unary);
                    unary.delete(0,unary.length());
                } else if (binary.charAt(i)=='0') {
                    unary.append("0");
                    chuck.append(" 00 "+unary);
                    unary.delete(0,unary.length());
                }
            }
        }
        chuck.delete(0,1);
        System.out.println(chuck);
    }

    //conversion from chuck code to binary
    static void toASCII(String input){

       // to binary
       String[] chuckIn= input.split(" ");
       try {
           for (int i = 0; i < chuckIn.length; i++) {
               if (i % 2 == 0 && chuckIn[i].equals("0")) {

                   for (int j = 0; j < chuckIn[i + 1].length(); j++) {
                       binary.append('1');
                   }
               } else if (i % 2 == 0 && chuckIn[i].equals("00")) {
                   for (int j = 0; j < chuckIn[i + 1].length(); j++) {
                       binary.append('0');
                   }
               }
           }


           //to ASCII
           String str = binary.toString();
           String[] chunks = str.split("(?<=\\G.{" + 7 + "})");
          

            //this is to debug the program whenever there is an invalid input
           if(!str.contains("1") || str.length()%7 != 0){
               System.out.println("not valid.");
           }
           else {
               System.out.println("Decoded string:");
           for (int i = 0; i < str.length() / 7; i++) {
               char h = (char) Integer.parseInt(chunks[i], 2);
                   System.out.print(h);
           }
           System.out.println();
       }}
       catch(Exception e){
           System.out.println("not valid.");
       }

    }
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String operation;
        String input;

     do{
         System.out.println("Please input operation (encode/decode/exit):");
         operation = scanner.nextLine();

        if (operation.equals("encode")) {
            System.out.println("Input string:");
            input = scanner.nextLine();
            System.out.println("Encoded string:");
            toBinary(input, a);
            toChuckCode();
        } else if (operation.equals("decode")) {
            System.out.println("Input encoded string:");
            input = scanner.nextLine();

            //if the string is not an encoded text
            if(!input.contains("0") || input.contains("1") || !input.contains(" ")){
                System.out.println("not valid");
                continue;
            }

            //System.out.println("Decoded string:");
            toASCII(input);
        }
        else if(!operation.equals("exit")){
            System.out.println("There is no '"+operation+"' operation");
        }
         binary.delete(0, binary.length());
         chuck.delete(0, chuck.length());
         unary.delete(0, unary.length());
    }while(!operation.equals("exit"));
        System.out.println("Bye!");


    }

}
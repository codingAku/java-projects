
import java.util.ArrayList;
import java.util.StringTokenizer;

// Your First Program
public class Main{
    public static void main(String[] args) {
        String ece = "4+(4*3+5)*7";
        //here I handle parantheses
        int fi = ece.indexOf("(", 0);
        int fis = ece.indexOf(")", 0);
        String ali = ece.substring(fi+1, fis);
        //this part is just testing
        ali = aticine(ali);
        ece = ece.substring(0, fi) + ali + ece.substring(fis+1);
        System.out.println(ece);
        ece = aticine(ece);
        System.out.println(ece);
        
        
    }
//this method takes an array and does the calculations inside it 
public static void calculation(ArrayList<String> islem){
    while(islem.size() != 1){
        int i = 0;
        System.out.println("load falan filan &t bişe");
        int a = Integer.parseInt(islem.get(i));
        System.out.println("load falan filan &t bişe");
        int b = Integer.parseInt(islem.get(i+2));
        String exp = islem.get(i+1);
        switch (exp){

            case "*": 
            System.out.println("çarpma falan filan &t bişe");
            String e = Integer.toString(a*b);
            islem.set(0, e);
            islem.remove(i+1);
            islem.remove(i+1);
            break;
            case "/":
            System.out.println("bölme falan filan &t bişe");
            String e1 = Integer.toString(a/b);
            islem.set(0, e1);
            islem.remove(i+1);
            islem.remove(i+1);
            break;
            case "+":
            System.out.println("toplama falan filan &t bişe");
            String e3 = Integer.toString(a+b);
            islem.set(0, e3);
            islem.remove(i+1);
            islem.remove(i+1);
            break;
            case "-":
            System.out.println("çıkarma falan filan &t bişe");
            String e4 = Integer.toString(a-b);
            islem.set(0, e4);
            islem.remove(i+1);
            islem.remove(i+1);
            break;
        }

     }
}
//this method handles calculations while replacing the results with calculations like 4*3 is replaced with 12
public static String aticine(String ali){
    ArrayList<String> islem = new ArrayList<String>();
    String son = "";

        //first tokening to + -
        if(ali.contains("+") ||ali.contains("-") ){
            StringTokenizer parser = new StringTokenizer(ali, "+-", false);
            while(parser.hasMoreTokens()){
                String ayse = parser.nextToken();
                if(ayse.contains("*") || ayse.contains("/")){
                    StringTokenizer parser2 = new StringTokenizer(ayse, "*/", true);
                    while(parser2.hasMoreTokens()){
                        islem.add(parser2.nextToken());
                       
                    }
                    
                    calculation(islem);
                
                
                // I cant replace the czlculation with string here
                String birlesme = "";
                son = islem.get(0) + birlesme;
                islem.remove(0);
                }
                
            }



        }
        //if code is here, this means there is no * or / left
        while(son.contains("+") || son.contains("-")){
            StringTokenizer parser = new StringTokenizer(son, "+-", true);
            while(parser.hasMoreTokens()){
                String kayboldum = parser.nextToken();
                islem.add(kayboldum);
            }
            calculation(islem);
                son =islem.get(0);
                islem.remove(0);

            
        
        }
        
        ali = son;
        return ali;
       // System.out.println(ali);

}
}
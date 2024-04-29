import java.io.*;

public class Exp2 {
    static int numKeywords=0,numoperators=0,numIdentifiers=0;
    static final String[] keywords ={"if","else","for","while","int","char","String","boolean","float","return","count","num","sum"};
    static final String operators = "+-/*%<>=()";
    static final int MAx_TOKEN_SIZE = 100;

public static void main(String[] args) {
try{
    BufferedReader reader = new BufferedReader(new FileReader("exp2InputCodes.txt"));
    String line;
    while((line=reader.readLine())!=null){
        String[] tokens= line.split("\\s+");
        for(String token : tokens){
            if(isKeyword(token)){
                System.out.println("Token: "+token+"\tType:keyword");
                numKeywords++;
            }
            else if(isoperator(token.charAt(0))){
                System.out.println("Token: "+token+"\tType:keyword");
                numoperators++;
            }
            else if(Character.isLetter(token.charAt(0))){
                System.out.println("Token: "+token+"\tType:identifier");
                numIdentifiers++;
            }
            else{
                System.out.println("Token: "+token+"\tType:others");
            }

        }


    }   
    reader.close();
    System.out.println("\nSummary:\n");
    System.out.println("Identifiers: "+numIdentifiers);
    System.out.println("Keywords: "+numKeywords);
    System.out.println("Operators: "+numoperators);
    
    
}
catch(IOException e){
    System.out.println("Could not open file.");
    e.printStackTrace();
}

}
static boolean isKeyword(String token){
    for(String keyword:keywords){
        if(token.equals(keyword)){
            return true;
        }
    }
    return false;
}
static boolean isoperator(char token){
    if(operators.indexOf(token)!=-1) {return true;}
    return false;
}
}

package compiler;

import java.io.File;
import java.io.FileNotFoundException;
import static java.lang.Character.isDigit;
import static java.lang.Character.isLetter;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Lex {

    public static String Comments(String text)
    {
        String buffer = "";
        for(int i = 0; i < text.length(); i++){
            switch (text.charAt(i)) {
                case '!':
                    if(text.charAt(i+1) == '!'){
                        while(text.charAt(i) != '\n'){
                            i++;
                        }
                    }   break;
                case '/':
                    if(text.charAt(i+1)=='!'){
                        while(!(text.charAt(i)=='!' && text.charAt(i+1)=='/')){
                            i++;
                        }
                    }   break;
                default:
                    buffer += text.charAt(i);
                    break;
            }
        }
        return buffer;
    }
    
    public static String tokenization(String text)
    {
    String buffer = "";
    int i ;
    for(i = 0 ; i < text.length();){
            while(isWhite(text.charAt(i)))
            {
                i++;
                if(i == text.length())break;
            }
            if(i == text.length())break;
            String token = "";
            if(isChar(text.charAt(i)))
            {   
                while(isChar(text.charAt(i)))
                {   
                    token += text.charAt(i);i++;
                    if(i==text.length())break;
                }
            }
        if (isId(token))
            buffer += token + "  ID\n";
        else if(isNumber(token))
            buffer += token + "  NUM\n";
        else if(isReal(token)) 
            buffer += token + "  REAL\n";
        else if(isOperator(token)) 
            buffer += token + "  OPERATOR\n";
        else if(isKeyWord(token)) 
            buffer += token + "  KEYWORD\n";
        else if(isString(token)) 
            buffer += token + "  Is String\n";
        else
            buffer += token + "  Unknown\n";
    } 
    
    return buffer;
    }
    
    public static boolean isWhite(char w)
    {
        return (w == '\n')||(w == '\t')||(w == ' ');
    }
    
    public static boolean isChar(char ch)
    {
    return (ch != '\n')&&(ch != '\t')&&(ch != ' ');
    }
    
    public static String readFromFile(String filename){
        String buff="";
        File file = new File(filename);
        Scanner sc = null;
        try {
            sc = new Scanner(file);
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Lex.class.getName()).log(Level.SEVERE, null, ex);
        }
        while(sc.hasNextLine()){
        buff += sc.nextLine() + "\n";
        }
        return buff;
    }
    
    public static boolean isOperator(String token)
    {
        return    token.length()==1 && (token.charAt(0)=='+'||token.charAt(0)=='='
                ||token.charAt(0)=='/'||token.charAt(0)=='%'||token.charAt(0)=='*'
                ||token.charAt(0)=='$'||token.charAt(0)=='#'||token.charAt(0)=='!'
                ||token.charAt(0)=='-'||token.charAt(0)=='&'||token.charAt(0)=='^'); 
    } 
    
    public static boolean isReal(String word){
    char symbol = word.charAt(0);
    int i = 0;
    if(symbol == '.' && word.length() == 1) return false;
    if( !isDigit(symbol) && word.charAt(i) != '.')return false;
    else {
        while(isDigit(symbol)){
            i++;
            symbol = word.charAt(i);
        }
        if(word.charAt(i) != '.')return false;
        else {
            i++;
            if (i != word.length()) 
                symbol = word.charAt(i);
            else 
                return true;
            if(!isDigit(symbol) || i == word.length())return false;
            else {                    
                while(isDigit(symbol) && i != word.length())
                    {
                        symbol = word.charAt(i);
                        i++;
                    }
                return true;
                }
            }
        }   
    }
    
    public static boolean isNumber(String token)
    {
        if(!isDigit(token.charAt(0)))
            return false;
        else {
            for(int i = 1; i < token.length();i++)
            {
                if(!(isDigit(token.charAt(i))))
                    return false;
            }
            return true;
        }
    }
    public static boolean isId(String token)
    {
        if((!isLetter(token.charAt(0)) && token.charAt(0) != '_') || isKeyWord(token))
            return false;
        else {
            for(int i = 1; i < token.length();i++)
            {
                if(!(isLetter(token.charAt(i))) && !(token.charAt(i) == '_') && !(isDigit(token.charAt(i))))
                    return false;
            }
            return true;
        }
    }    
    
    public static boolean isKeyWord(String token)
    {
        String[] keywords = {"if", "while", "for", "int", "String", "var", "double", 
        "import", "do", "boolean", "const", "public", "private", "void", "class", "return", "Begin"
        , "End"};
        
        List <String> keyWordsList = Arrays.asList(keywords);
        
        return keyWordsList.contains(token); 
    }

    public static boolean isString(String token)
    {   int i ;
        if(!(token.charAt(0)=='\"'))
            return false;
        else {
            for(i = 1; i < token.length();i++)
            {
                if(token.charAt(i)=='\"')
                    break;
            }
            return i==token.length()-1;
        }
    }
    
    public static void main(String[] args) {
        String BUFFER = readFromFile("sc.txt");
        String BUFFER2 = Comments(BUFFER);
        String BUFFER3 = tokenization(BUFFER2);
        System.out.println(BUFFER);
        System.out.println(BUFFER2);
        System.out.println(BUFFER3);
        System.out.println();
    }
    
}

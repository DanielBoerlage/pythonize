// this code really doesnt work with comments                                                                                                                       
// shhhh                                                                                                                                                            
import java.nio.file.Files                                                                                                                                          ;
import java.nio.file.Paths                                                                                                                                          ;
import java.io.IOException                                                                                                                                          ;
                                                                                                                                                                    
public class Main                                                                                                                                                   {
                                                                                                                                                                    
    public static void main(String[] args)                                                                                                                          {
        if(args.length < 1)                                                                                                                                         {
            System.err.println("You must specifiy an input file")                                                                                                   ;
            System.exit(1)                                                                                                                                          ;}
        String inFile = args[0]                                                                                                                                     ;
        String contents = ""                                                                                                                                        ;
        try                                                                                                                                                         {
            if(!Files.exists(Paths.get(inFile)))                                                                                                                    {
                System.err.println("File does not exist")                                                                                                           ;
                System.exit(2)                                                                                                                                      ;}
            contents = new String(Files.readAllBytes(Paths.get(inFile)))                                                                                            ;}
        catch (IOException e)                                                                                                                                       {
            System.err.println("IO Error reading from file " + inFile + ".")                                                                                        ;
            System.exit(3)                                                                                                                                          ;}
        try                                                                                                                                                         {
            Files.write(Paths.get(inFile), pythonize(contents).getBytes())                                                                                          ;}
        catch(IOException e)                                                                                                                                        {
            System.err.println("IO Error writing to file " + inFile + ".")                                                                                          ;
            System.exit(4)                                                                                                                                          ;}}
                                                                                                                                                                    
    public static int[] usefullarray = {4, 5, 7, 0}                                                                                                                 ;
                                                                                                                                                                    
    public static String pythonize(String src)                                                                                                                      {
        src = src.replaceAll("\\t", "    ")                                                                                                                         ;
                                                                                                                                                                    
        int longestLine = 0                                                                                                                                         ;
        String[] lines = src.split("\\r\\n")                                                                                                                        ;
        for(String line : lines)                                                                                                                                    {
            if(line.length() > longestLine)                                                                                                                         
                longestLine = line.length()                                                                                                                         ;}
                                                                                                                                                                    
        int barWidth = Math.max(80, longestLine + 5)                                                                                                                ;
                                                                                                                                                                    
        String out = ""                                                                                                                                             ;
                                                                                                                                                                    
        String[] additions = new String[lines.length]                                                                                                               ;
        boolean[] emptyLines = new boolean[lines.length]                                                                                                            ;
                                                                                                                                                                    
        for(int i = 0; i < additions.length; i++)                                                                                                                   
            emptyLines[i] = lines[i].trim().length() == 0                                                                                                           ;
                                                                                                                                                                    
        for(int i = 0; i < additions.length; i++)                                                                                                                   
            additions[i] = ""                                                                                                                                       ;
                                                                                                                                                                    
        for(int i = 0; i < lines.length; i++)                                                                                                                       {
            String line = lines[i]                                                                                                                                  ;
            if(lines[i].trim().endsWith("{"))                                                                                                                       {
                additions[i] += "{"                                                                                                                                 ;
                lines[i] = lines[i].substring(0, lines[i].lastIndexOf("{")) + (lines[i].length() > 1 ? lines[i].substring(lines[i].lastIndexOf("{") + 1) : "")      ;}
            else if(lines[i].trim().endsWith(";"))                                                                                                                  {
                additions[i] += ";"                                                                                                                                 ;
                lines[i] = lines[i].substring(0, lines[i].lastIndexOf(";")) + (lines[i].length() > 1 ? lines[i].substring(lines[i].lastIndexOf(";") + 1) : "")      ;}
            if(lines[i].trim().startsWith("}") && i > 0)                                                                                                            {
                additions[i - 1] += "}"                                                                                                                             ;
                lines[i] = lines[i].substring(0, lines[i].indexOf("}")) + (lines[i].length() > 1 ? lines[i].substring(lines[i].indexOf("}") + 1).trim() : "")       ;}}
                                                                                                                                                                    
        //for(int i = 0; i < lines.length; i++)                                                                                                                     {
        //    if (lines[i].contains("//") && additions[i].length() > 0)                                                                                             {
        //        lines[i] = lines[i].substring(0, lines[i].indexOf("//")) + "/*" + lines[i].substring(lines[i].indexOf("//") + 2) + "*/"                           ;
        //    }                                                                                                                                                     
        //}                                                                                                                                                         
                                                                                                                                                                    
        for(int i = additions.length - 1; i > 0; i--)                                                                                                               {
            if(lines[i].trim().length() == 0)                                                                                                                       {
                for(char c : additions[i].toCharArray())                                                                                                            
                    additions[i-1] += c                                                                                                                             ;
                additions[i] = ""                                                                                                                                   ;}}
                                                                                                                                                                    
        for(int i = 0; i < lines.length; i++)                                                                                                                       {
            if(!emptyLines[i] && lines[i].trim().length() == 0)                                                                                                     {}
                                                                                                                                                                    
            else                                                                                                                                                    {
                out += lines[i] + nSpaces(barWidth - lines[i].length()) + additions[i] + "\r\n"                                                                     ;}}
                                                                                                                                                                    
        return out                                                                                                                                                  ;}
                                                                                                                                                                    
    public static String nSpaces(int n)                                                                                                                             {
        String out = ""                                                                                                                                             ;
        for(int i = 0; i < n; i++)                                                                                                                                  
            out += " "                                                                                                                                              ;
        return out                                                                                                                                                  ;}}

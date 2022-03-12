// File reading code from https://howtodoinjava.com/java/io/java-read-file-to-string-examples/
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;

public class MarkdownParse {
    public static ArrayList<String> getLinks(String markdown) {
        ArrayList<String> toReturn = new ArrayList<>();
        // find the next [, then find the ], then find the (, then take up to
        // the next )
        int currentIndex = 0;
        //run through entire string
        while(currentIndex < markdown.length()) {
            //("", x) the first time it shows up after certain index x
            int nextOpenBracket = markdown.indexOf("[", currentIndex);
            int nextCloseBracket = markdown.indexOf("]", nextOpenBracket);
            //link open
            int openParen = markdown.indexOf("(", nextCloseBracket);
            int closeParen = markdown.indexOf(")", openParen);
            //not find -> return -1
            if(openParen<0 || closeParen<0){
                break;
            }

            if(nextOpenBracket!=0 && markdown.charAt(nextOpenBracket-1) == '!' ){
                currentIndex = closeParen + 1;
                continue;
            }
            //end
            toReturn.add(markdown.substring(openParen + 1, closeParen));
            //System.out.println(currentIndex); // 0 43
            //System.out.println(markdown.charAt(currentIndex));
            //look forward from that point instead of backward
            currentIndex = closeParen + 1;
            //
            //System.out.println(currentIndex); // 43 76
            //charAt
            
        }
        return toReturn;
    }
    public static void main(String[] args) throws IOException {
		Path fileName = Path.of(args[0]);
        //reads the file, parse it into the string
	    String contents = Files.readString(fileName);
        //call from main
        ArrayList<String> links = getLinks(contents);
        System.out.println(links);
    }
}

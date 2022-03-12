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
        char ImageIndicator='!';
        while(currentIndex < markdown.length()) {
            int nextOpenBracket = markdown.indexOf("[", currentIndex);
            int nextCloseBracket = markdown.indexOf("]", nextOpenBracket);
            int openParen = markdown.indexOf("(", nextCloseBracket);
            int closeParen = markdown.indexOf(")", openParen);
            if(nextOpenBracket==-1||nextCloseBracket==-1||openParen==-1||closeParen==-1
            ||markdown.charAt(nextCloseBracket+1)!='('){
                return toReturn;
            }
            if(nextOpenBracket==0){
                if(openParen!=closeParen-1){
                    toReturn.add(markdown.substring(openParen + 1, closeParen));
                }
                currentIndex = closeParen + 1;
                System.out.println(currentIndex);
            }
            else{
                System.out.println("Reached first else");
                System.out.println(markdown.charAt(nextOpenBracket-1));
                if(markdown.charAt(nextOpenBracket-1)==ImageIndicator){
                    System.out.println("Reached image check");
                    currentIndex = closeParen + 1;
                    System.out.println(currentIndex);
                }
                else{
                    if(openParen!=closeParen-1){
                        toReturn.add(markdown.substring(openParen + 1, closeParen));
                    }
                    currentIndex = closeParen + 1;
                    System.out.println(currentIndex);
                }

            }
            
        }
        return toReturn;
    }
    public static void main(String[] args) throws IOException {
		Path fileName = Path.of(args[0]);
	    String contents = Files.readString(fileName);
        ArrayList<String> links = getLinks(contents);
        System.out.println(links);
    }
}

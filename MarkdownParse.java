// File reading code from https://howtodoinjava.com/java/io/java-read-file-to-string-examples/
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;


public class MarkdownParse {
    static String[] imageExtensions = {".png", ".jpeg", ".gif", ".csv", ".jpg", ".svg", ".pdf"};

    public static ArrayList<String> getLinks(String markdown) {
        ArrayList<String> toReturn = new ArrayList<>();
       
	int currentIndex = 0;
        int nextOpenBracket = 0;
        int nextCloseBracket = markdown.indexOf("]");
        int openParen = markdown.indexOf("(");
        int closeParen = 0;
        while(currentIndex < markdown.length()) {
            // Fix for tests 2 and 8
            int prevIndex = currentIndex;
            int prevOpenBracket = nextOpenBracket;
            int prevCloseBracket = nextCloseBracket;
            int prevOpenParen = openParen;
            int prevCloseParen = closeParen;
            //System.out.println("Hello");

            if (nextCloseBracket > openParen) break;
            nextOpenBracket = markdown.indexOf("[", currentIndex);
            nextCloseBracket = markdown.indexOf("]", nextOpenBracket);
            openParen = markdown.indexOf("(", nextCloseBracket);
            closeParen = markdown.indexOf(")", openParen);
            //System.out.println("Hello");

            // Fix for tests 2 and 8
            if(nextOpenBracket == -1 || nextCloseBracket == -1 || 
            openParen < prevOpenParen || closeParen < prevCloseParen){
                break;
            }
            if (!checkExtension(markdown.substring(openParen +1, closeParen)) && openParen-nextCloseBracket==1)
            {
                if(markdown.substring(openParen + 1, closeParen).indexOf(".")!=-1 && markdown.indexOf(")", closeParen) == -1){
                    toReturn.add(markdown.substring(openParen + 1, closeParen));
            }
            currentIndex = closeParen + 1;
        }
        return toReturn;
    }

    public static boolean checkExtension(String substring) {
        for (int i = 0; i < imageExtensions.length; ++i) {
            if (substring.contains(imageExtensions[i])) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) throws IOException {
		Path fileName = Path.of(args[0]);
	    String contents = Files.readString(fileName);
        ArrayList<String> links = getLinks(contents);
        System.out.println(links);
    }

		
}

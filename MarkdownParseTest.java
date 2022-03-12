import static org.junit.Assert.*;
import org.junit.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class MarkdownParseTest {

    /**
    @Test
    public void snipTest1() throws IOException {
        String file=Files.readString(Path.of("snippet-1.md"));
        assertEquals(MarkdownParse.getLinks(file), 
        List.of("google.com, google.com, ucsd.edu"));
    }

    @Test
    public void snipTest2() throws IOException {
        String content = Files.readString(Path.of("snippet-2.md"));
        ArrayList<String> sLink = MarkdownParse.getLinks(content);
        assertEquals(List.of("a.com, a.com(()), example.com"), sLink);
    }

    @Test
    public void snipTest3() throws IOException {
        String content = Files.readString(Path.of("snippet-3.md"));
        ArrayList<String> sLink = MarkdownParse.getLinks(content);
        assertEquals(List.of("https://www.twitter.com, https://ucsd-cse15l-w22.github.io, https://cse.ucsd.edu"), sLink);
    }
    */

   
    @Test
    public void testFile1() throws IOException {
        String contents= Files.readString(Path.of("./test-file.md"));
        List<String> expect = List.of("https://something.com", "some-page.html");
        assertEquals(MarkdownParse.getLinks(contents), expect);
    }

    @Test
    public void testFile2() throws IOException {
        String contents= Files.readString(Path.of("./test-file2.md"));
        List<String> expect = List.of("https://something.com", "some-page.html");
        assertEquals(MarkdownParse.getLinks(contents), expect);
    }

    @Test
    public void testSingleImage() throws IOException {
        String contents= Files.readString(Path.of("./test-single-image.md"));
        List<String> expect = List.of();
        assertEquals(MarkdownParse.getLinks(contents), expect);
    }

    @Test
    public void testLinkAtBeginning() {
        String contents= "[link title](a.com)";
        List<String> expect = List.of("a.com");
        assertEquals(MarkdownParse.getLinks(contents), expect);
    }

    @Test
    public void testSpaceInURL() {
        String contents = "[title](space in-url.com)";
        List<String> expect = List.of();
        assertEquals(MarkdownParse.getLinks(contents), expect);
    }
    @Test
    public void testSpaceAfterParen() {
        String contents = "[title]( space-in-url.com)";
        List<String> expect = List.of("space-in-url.com");
        assertEquals(expect, MarkdownParse.getLinks(contents));
    }
    @Test
    public void testSpaceBeforeParen() {
        String contents = "[title]   (should-not-count.com)";
        List<String> expect = List.of();
        assertEquals(MarkdownParse.getLinks(contents), expect);
    }
   
    
}

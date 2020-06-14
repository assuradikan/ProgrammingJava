import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class BookReader {
    private File book_file;
    public String name;
    private String content;

    public BookReader(File book_file, String name) {
        this.book_file = book_file;
        this.name = name;
    }

    public void load() throws IOException {
        System.out.println("Starting to load the book...");

        BufferedReader br = new BufferedReader(new FileReader(this.book_file));
        String line = br.readLine();
        while (line != null) {
            content = content + line + " ";
            line = br.readLine();
        }

        System.out.println("The book has loaded successfully!");
    }

    public void makeAllLowerCase() {
        this.content = this.content.toLowerCase();

        System.out.println("Lowercasing is done");
    }

    public void noiseRemoval() {
        this.content = this.content.replaceAll("-", " ");
        this.content = this.content.replaceAll("[^а-я0-9\\s+]", "");
        System.out.println("Removal is done");
    }

    public void letterFrequency(){
        Map<Character,Integer> frequencies = new HashMap<>();
        for (char ch : this.content.toCharArray())
            frequencies.put(ch, frequencies.getOrDefault(ch, 0) + 1);


        for (char ch: frequencies.keySet())
            System.out.println(ch + " -> " + frequencies.get(ch));
    }

    public void longestWord(){
        Optional<String> max = Arrays.stream(this.content.split(" "))
                .max((a, b) -> a.length() - b.length());
        System.out.println("The Longest Word is " + max.toString());
    }

    public void countWord() {
        int count = this.content.split(" ").length;
        System.out.println("The count of words : " + count);
    }


}

class Start {
    public static void main(String[] args) throws IOException {
        File file = new File("C:\\Users\\hp\\IdeaProjects\\Assura\\src\\book.txt");
        BookReader bookReader = new BookReader(file, "Преступление и Наказание");

        bookReader.load();
        bookReader.makeAllLowerCase();
        bookReader.noiseRemoval();

        bookReader.letterFrequency();
        bookReader.longestWord();
        bookReader.countWord();

    }
}

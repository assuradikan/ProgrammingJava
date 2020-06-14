import javafx.util.Pair;

import java.io.*;
import java.util.*;

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


    private boolean isPalindrome(String s) {
        if (s.length() <= 2) return false;

        int i = 0, j = s.length() - 1;

        while (i < j) {
            if (s.charAt(i) != s.charAt(j))
                return false;
            i++;
            j--;
        }
        return true;
    }

    public void findPalindromes() {
        String[] allwords = this.content.split(" ");

        HashSet<String> palidromes = new HashSet<>();

        for (String str: allwords)
            if (isPalindrome(str)) palidromes.add(str);


        System.out.println("There are " + palidromes.size() + " palindromes words");
    }

    private boolean isAnagrams(String s1, String s2) {

        if (s1.length() <= 1 || s2.length() <= 1) return false;
        if (s1.length() != s2.length()) return false;

        char[] ch1 = s1.toCharArray();
        char[] ch2 = s2.toCharArray();

        Arrays.sort(ch1);
        Arrays.sort(ch2);

        return Arrays.equals(ch1, ch2);
    }

    public void findAnagrams() {
        String[] allwords = this.content.split(" ");
        HashSet<Pair<String, String>> anagrams = new HashSet<>();

        for (int i = 0; i < allwords.length - 1; i++) {
            for (int j = i + 1; j < allwords.length; j++) {
                if(isAnagrams(allwords[i], allwords[j])) anagrams.add(new Pair<>(allwords[i], allwords[j]));
            }
        }

        System.out.println("There are " + anagrams.size() + " pair of anagram words");
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
        bookReader.findPalindromes();
        bookReader.findAnagrams();

    }
}

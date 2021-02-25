import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Selection;
import edu.princeton.cs.algs4.StdOut;
import edu.princeton.cs.algs4.TST;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

class WordFrequency implements Comparable<WordFrequency> {
    String word;
    int frequency;

    public WordFrequency(String word, int frequency) {
        this.word = word;
        this.frequency = frequency;
    }

    public int compareTo(WordFrequency w) {
        return w.frequency - this.frequency;
    }
}

public class cCapCounter {

    public static void main(String[] args) {
        String regexp = args[0];
        In in = new In(args[1]);

        TST<Integer> st = new TST<Integer>();
        int cnt = 0;
        int word_count = 0;

        String input = in.readAll();

        Pattern pattern1 = Pattern.compile("(\\w+)");
        Matcher matcher1 = pattern1.matcher(input);

        while (matcher1.find()) {
            String key = matcher1.group();
            word_count++;

            if( st.contains(key))
                cnt = st.get(key);
            else
                cnt = 0;
            st.put(key, cnt + 1);
        }

//        for (int i = 0; !in.isEmpty(); i++) {
//            word_count++;
//
//            String key = in.readString();
//
//            if( st.contains(key))
//                cnt = st.get(key);
//            else
//                cnt = 0;
//            st.put(key, cnt + 1);
//        }

        StdOut.println("File has " + word_count + " words");

        int word_unique_count = 0;

        for (String key : st.keys()) {
            word_unique_count++;
        }
        StdOut.println("File has " + word_unique_count + " unique words");

        Pattern pattern = Pattern.compile(regexp);

        List<WordFrequency> word_frequency = new ArrayList<>();
        for (String key : st.keys()) {
            if( key.toUpperCase().equals(key) ) // ALL CAPS
                continue;

            Matcher matcher = pattern.matcher(key);
            if( matcher.find() == false )
                continue;

            cnt = st.get(key);

            WordFrequency w = new WordFrequency(key, cnt);

            word_frequency.add(w);
        }

        int i = 0;
        WordFrequency[] new_list = new WordFrequency[word_frequency.size()];
        for(WordFrequency w : word_frequency)
        {
            new_list[i] = w;
            i++;
        }

        Selection.sort(new_list);

        StdOut.println("Rankings:");
        int size = 10;
        if( size > word_frequency.size() )
            size = word_frequency.size();

        for(i = 0; i < size; i++)
        {
            WordFrequency w = new_list[i];
            StdOut.println(String.format(" %d - %s was used %d times", (i + 1), w.word, w.frequency));
        }
    }



}

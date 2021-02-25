import edu.princeton.cs.algs4.*;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class cCapCounter {

    public static void main(String[] args) {
        String regexp = args[0];
        In in = new In(args[1]);

        // use Trie structure
        TST<Integer> st = new TST<Integer>();
        int cnt = 0;
        int word_count = 0;

        String string = in.readAll();

        // find all words in sentence
        Pattern pattern = Pattern.compile("(\\w+)");
        Matcher matcher = pattern.matcher(string);

        while (matcher.find()) {
            String key = matcher.group();
            word_count++; // count word

            // count for each word
            if( st.contains(key))
                cnt = st.get(key);
            else
                cnt = 0;
            st.put(key, cnt + 1);
        }

        StdOut.println("File has " + word_count + " words");

        // count unique word count based on trie key
        int word_unique_count = 0;

        for (String key : st.keys()) {
            word_unique_count++;
        }
        StdOut.println("File has " + word_unique_count + " unique words");

        Pattern capPattern = Pattern.compile(regexp);

        List<WordHistogram> word_histogram = new ArrayList<>();
        for (String key : st.keys()) {
            if( key.toUpperCase().equals(key) ) // ignore if ALL CAPS
                continue;
            
            Matcher capMatcher = capPattern.matcher(key);
            if( capMatcher.find() == false )    // if not cap word, ignore
                continue;

            cnt = st.get(key);

            WordHistogram w = new WordHistogram(key, cnt);

            word_histogram.add(w);
        }

        int i = 0;
        WordHistogram[] array_hist = new WordHistogram[word_histogram.size()];
        for(i = 0; i < array_hist.length; i++)
            array_hist[i] = word_histogram.get(i);

        Insertion.sort(array_hist);

        StdOut.println("Rankings:");
        int size = 10;
        if( size > word_histogram.size() )
            size = word_histogram.size();

        for(i = 0; i < size; i++)
        {
            WordHistogram w = array_hist[i];
            StdOut.println(String.format(" %d - %s was used %d times", (i + 1), w.w, w.count));
        }
    }



}


class WordHistogram implements Comparable<WordHistogram> {
    String w;
    int count;

    public WordHistogram(String w, int cnt) {
        this.w = w;
        this.count = cnt;
    }

    public int compareTo(WordHistogram w) {
        return (w.count - this.count);
    }
}

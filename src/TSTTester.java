import edu.princeton.cs.algs4.*;

public class TSTTester {
    public static void main(String[] args) {
        // build symbol table from standard input
        In in = new In(args[0]);

        Stopwatch timer = new Stopwatch();

        TST<Integer> st = new TST<Integer>();
        int cnt = 0;
        for (int i = 0; !in.isEmpty(); i++) {
            String key = in.readString();
            if( st.contains(key))
                cnt = st.get(key);
            else
                cnt = 0;
            st.put(key, cnt + 1);
        }

        // print results
        int word_count = 0;
        int max_count = 0;
        String word = "";
        StdOut.println("keys(\"\"):");

        for (String key : st.keys()) {
            cnt = st.get(key);
            if( cnt > max_count )
            {
                max_count = cnt;
                word = key;
            }

            word_count++;
        }

        StdOut.println("File has " + word_count + " unique words");

        StdOut.println("Most frequent word:");
        StdOut.println("Word \"" + word + "\" was used " + max_count + " times.");


        String []prefix = {"z", "q", "x", "j"};
        for(String c : prefix)
        {
            StdOut.println("Words that begin with(\"" + c + "\"):");
            for (String s : st.keysWithPrefix(c))
                StdOut.print(s + " ");
            StdOut.println();
        }

        double time = timer.elapsedTime();
        StdOut.println("Total processing Time: " + time);
    }

}

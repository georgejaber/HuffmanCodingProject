package huffman_proj;

import java.io.IOException;
import java.util.PriorityQueue;
import java.util.Scanner;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author jaber
 */
public class Huffman_proj {

    static String q = "";

    public static void main(String[] args) throws IOException {
        JFileChooser chooser = new JFileChooser();
        FileNameExtensionFilter  fef = new FileNameExtensionFilter("TEXT FILES", "txt", "text","george");
        chooser.setFileFilter(fef);
         Scanner sc = new Scanner(System.in);
         

        while (true) {
            System.out.println("    >--select a number--<");
            System.out.println("1 : select a file to compress");
            System.out.println("2 : select a file to uncompress");
            System.out.println("3 : close");

            int select = sc.nextInt();
            switch (select) {

                case 1: {

                    int returnVal = chooser.showOpenDialog(null);

                    if (returnVal == JFileChooser.APPROVE_OPTION) {
                        file_reader fr = new file_reader(chooser.getSelectedFile().getPath());
                        int numarr[] = fr.get_numarr();
                        char chararr[] = fr.get_chararr();
                       

                        PriorityQueue<node> pq = new PriorityQueue<>(chararr.length, new cm());

                        for (int i = 0; i < chararr.length; i++) {
                            node n = new node();

                            n.c = chararr[i];
                            n.data = numarr[i];

                            n.left = null;
                            n.right = null;

                            pq.add(n);

                        }

                        node root = null;

                        while (pq.size() > 1) {

                            node x = pq.poll();

                            node y = pq.poll();

                            node xy = new node();

                            xy.data = x.data + y.data;

                            xy.left = y;

                            xy.right = x;

                            root = xy;

                            pq.add(xy);

                        }

                        hash h = new hash();

                        h.hash(root, "");

                        String ctree = h.encodetree(root, "");

                        int ctl = ctree.length();

                        q = ctree;

                        compress fw = new compress(chooser.getSelectedFile().getPath());
                        System.out.println("--------------------------------");
                        System.out.println("|your file have been compressed|");
                        System.out.println("--------------------------------");
                    }
                    break;
                }
                case 2: {
                    int returnVal1 = chooser.showOpenDialog(null);

                    if (returnVal1 == JFileChooser.APPROVE_OPTION) {

                        decompress dc = new decompress(chooser.getSelectedFile().getPath());
                    }
                     System.out.println("----------------------------------");
                        System.out.println("|your file have been uncompressed|");
                        System.out.println("----------------------------------");
                    break;
                }
                case 3: {
                    return;
                }
                default:
                    break;
            }
        }
    }

}

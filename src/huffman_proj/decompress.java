/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package huffman_proj;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

/**
 *
 * @author jaber
 */
public class decompress {

    static int binarylen;

    int i = 0;
    int o = 0;

    static String tempstr = "";
    node main = new node();
    FileWriter fw;

    String codetochar(node root, String t) throws IOException {
        StringBuilder s = new StringBuilder(t);
        node mains = root;

        while (s.length() > 0 && binarylen >= 0)
        {

            if (s.charAt(0) == '0')
            {
                if (root.left == null)
                {
                    tempstr = "";
                    fw.write(root.c);
                    root = mains;
                } else
                {
                    tempstr += s.charAt(0);
                    s.deleteCharAt(0);
                    binarylen--;
                    root = root.left;
                    if (s.length() < 1)
                    {
                        binarylen += tempstr.length();
                        return tempstr;
                    }
                }
            } else if (s.charAt(0) == '1')
            {
                if (root.left == null)
                {
                    tempstr = "";
                    fw.write(root.c);
                    root = mains;

                } else
                {
                    tempstr += s.charAt(0);
                    s.deleteCharAt(0);
                    binarylen--;
                    root = root.right;
                    if (s.length() < 1)
                    {
                        binarylen += tempstr.length();
                        return tempstr;
                    }

                }
            }
        }
        return "";
    }

    decompress(String file_bath) throws IOException {
        fw = new FileWriter(file_bath.concat(".txt"));

        File f1 = new File(file_bath);
        FileReader fr = new FileReader(f1);

        BufferedReader bfr = new BufferedReader(fr);

        hash hsh = new hash();

        BufferedReader br = new BufferedReader(fr);
        StringBuilder sb = new StringBuilder();
        StringBuilder sb1 = new StringBuilder();
        int s;

        String tree = "";
        String text = "";

        String h = String.valueOf(sb1);

        int treelenindex = Integer.parseInt((char) bfr.read() + "", 10);

        String treelens = "";

        for (int i = 1; i <= treelenindex; i++)
        {
            treelens += (char) bfr.read();
        }

        int treelen = Integer.parseInt(treelens, 10);

        String f = String.valueOf((char) (bfr.read()));

        int cx = Integer.parseInt(f, 10);

        String rem = "";

        for (int i = treelenindex; i < (treelenindex + cx); i++)
        {
            rem += (char) bfr.read();
        }
        binarylen = Integer.parseInt(rem, 10);

        for (int i = treelenindex + cx + 2; i < treelen + treelenindex + cx + 2; i++)
        {
            tree += (char) bfr.read();
        }

        while ((s = bfr.read()) != -1)
        {

            char c = (char) s;

            sb1.append(c);
        }

        text = String.valueOf(sb1);

        for (int i = 0; i < text.length(); i++)
        {

            sb.append(String.format("%8s", Integer.toBinaryString((Integer.valueOf(text.charAt(i)) + 256) % 256)).replace(' ', '0'));

        }

        main = hsh.decodenode(tree);

        int j = 0;

        while (true)
        {
            if (j * 10000 + 10000 > sb.length())
            {

                codetochar(main, sb.substring(j * 10000 - tempstr.length(), sb.length()));

                tempstr="";
                break;
            } else
            {

                codetochar(main, sb.substring(j * 10000 - tempstr.length(), j * 10000 + 10000));
                j++;
            }

        }
        fw.close();

    }
}

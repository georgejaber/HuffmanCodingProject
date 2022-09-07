/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package huffman_proj;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;

/**
 *
 * @author waeel
 */
public class compress {

    static int olen;

    static Random rd = new Random();

    int a;

    Huffman_proj hf = new Huffman_proj();

    hash h = new hash();

    int c = 0;
    String t;

    public compress(String file_path) throws FileNotFoundException, IOException {

        File f = new File(file_path);
        FileReader fr = new FileReader(f);

        BufferedReader br = new BufferedReader(fr);

        String b = file_path.replace(".txt", ".george");

        File f1 = new File(b);

        FileWriter fw = new FileWriter(f1);

        hash h = new hash();

        int s = 0;
        int j = 0;
        int p = 0;
        StringBuilder o = new StringBuilder();
        OutputStream os
                = new FileOutputStream(b);

        while (((s = br.read()) != -1)) 
        {
            char ch = (char) s;

            {
                int x = ch;

                o.append(hash.str[x]);

            }

            j++;
        }

        StringBuilder remaining = new StringBuilder();

        olen = o.length();
        int a = olen - (olen % (8));

        for (int i = olen - 1; i >= a; i--) {

            remaining.append(String.valueOf(o.charAt(i)));

        }

        while (remaining.length() != 8) {
            remaining.append("0");
        }

        int treesize = hf.q.length();
        int treesizeindex = String.valueOf(treesize).length();

        int olenindex = String.valueOf(olen).length();

        fw.write(String.valueOf(treesizeindex));
        fw.write(String.valueOf(treesize));
        fw.write(String.valueOf(olenindex));
        fw.write(String.valueOf(olen));

        fw.write(hf.q);

        fw.write(getbytes(o).toString());

        fw.write(String.valueOf((char) getbytes(remaining).charAt(0)));

        fw.close();

    }

    private static StringBuilder getbytes(StringBuilder bitString) {
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < bitString.length() / 8; i++) {
            s.append((char) Integer.parseInt(bitString.substring(i * 8, i * 8 + 8), 2));
        }
        return s;
    }
}

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package huffman_proj;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

class file_reader {

    int csize = 0;

   File f;

    int numar[] = new int[256];
    char charar[] = new char[256];

    int numarr[];
    char chararr[];

    public char[] get_chararr() {
        return chararr;
    }

    public int[] get_numarr() {
        return numarr;
    }

    private boolean ifcontains(char ch) {
        for (int i = 0; i < charar.length; i++) {
            if (charar[i] == ch) {
                return true;
            }

        }
        return false;
    }

    public file_reader(String file_path) throws FileNotFoundException, IOException {
         f = new File(file_path);
         FileReader fr = new FileReader(f);
        BufferedReader br = new BufferedReader(fr);

        int s = 0;
        int j = 0;

        while (((s = br.read()) != -1)) {
            char c = (char) s;

            if (!(ifcontains(c))) {
                csize++;

                charar[j] = c;

                numar[j]++;
                j++;

            } else {
                int a = new String(charar).indexOf(c);

                numar[a]++;
            }

        }

        int k = 0;
        numarr = new int[csize];
        chararr = new char[csize];

        for (int i = 0; i < charar.length; i++) {
            int h = (int) charar[i];
            if (h != '\u0000') {

                chararr[k++] = charar[i];

            }

        }
        k = 0;

        for (int i = 0; i < charar.length; i++) {
            if (numar[i] != 0) {

                numarr[k++] = numar[i];

            }

        }

        fr.close();
    }

}

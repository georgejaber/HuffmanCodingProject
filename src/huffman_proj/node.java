/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package huffman_proj;

import java.util.Comparator;

public class node {

    int data;
    char c;

    node left = null;
    node right = null;

    node(char c, node left, node right) {

        this.c = c;
        this.left = left;
        this.right = right;
    }

    node() {
    }

    node(node left, node right) {
        this.left = left;
        this.right = right;
    }

}

class cm implements Comparator<node> {

    @Override
    public int compare(node x, node y) {
        if (x.data < y.data) {
            return -1;
        } else if (x.data > y.data) {
            return 1;
        } else {
            return 0;
        }
    }
}

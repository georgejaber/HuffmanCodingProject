/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package huffman_proj;

import java.io.IOException;
import java.util.Stack;

/**
 *
 * @author jaber
 */
class hash {

    public int getcount() {
        return count;
    }

    static int count = 0;

    static String[] str = new String[256];

    public void inorder(node root) {
        if (root == null) {
            return;
        }

        inorder(root.left);
        System.out.print("[" + root.data + "-" + root.c + "]" + ",");

        inorder(root.right);

    }

    public void hash(node root, String s) throws IOException {

        if (root.left == null && root.right == null) {
            int i = root.c;

            str[i] = s;
            return;
        }

        hash(root.left, s + "0");
        hash(root.right, s + "1");
    }
    static String r = "";
    static String g = "";
    static String bs = "";

    public String encodetree(node root, String bs) {

        if (root.left == null && root.right == null) {

            g = g + ((bs + "1") + root.c);

        } else {

            encodetree(root.left, bs + "0");
            bs = "";
            encodetree(root.right, bs);
        }
        return g;

    }

    public node decodenode(String g) throws IOException {

        node root1 = new node();

        Stack<node> stack = new Stack<>();

        stack.push(root1);
        for (int i = 0; i < g.length(); i++) {
            node newnode = new node();

            if (!stack.isEmpty()) {
                newnode = stack.pop();
            }
            if (g.charAt(i) == '0') {
                newnode.right = new node();
                newnode.left = new node();
                stack.push(newnode.right);
                stack.push(newnode.left);
            } else {
                if (i + 1 < g.length()) {
                    newnode.c = g.charAt(i + 1);
                }
                i++;
            }

       
        }
        return root1;
    }

}

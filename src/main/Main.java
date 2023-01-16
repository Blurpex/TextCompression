package main;

import java.io.*;
import java.util.BitSet;

public class Main {
    public static void main(String[] args) {
        try {

            // get the file to encode from
            FileReader fr = new FileReader("src/sample_text2.txt");
            Heap heap = new Heap();
            int length = 0;
            int read;
            while((read=fr.read()) != -1) {
                heap.insert((char) read);
                length++;
            }
            fr.close();
            heap.getArr().convertFrequency(length);
            heap.buildHeap();

            // build a huffman tree
            Huffman hfm = new Huffman(heap);
            hfm.buildTree();
            hfm.encodePath();
            hfm.printTree();
            hfm.storePath();

            // convert text into binary code
            FileReader fr2 = new FileReader("src/sample_text2.txt");
            StringBuilder encode  = new StringBuilder();
            while((read=fr2.read()) != -1)
                encode.append(hfm.arr.search((char)read).path);

            // write to binary file
            BitSet bs = new BitSet(encode.length());
            FileOutputStream fos = new FileOutputStream("src/output.bin");
            for(int i=0; i<encode.length(); i++) {
                if(encode.charAt(i) == '1')
                    bs.set(i);
            }
            fos.write(bs.toByteArray());

            // decode
            FileInputStream fis = new FileInputStream("src/output.bin");
            StringBuilder decode = new StringBuilder();
            byte[] bytes = fis.readAllBytes();
            for(byte b : bytes) {
                StringBuilder x = new StringBuilder(Integer.toBinaryString(b & 0xFF)).reverse();
                x.append("0".repeat(Math.max(0, 8 - x.length())));
                decode.append(x);
            }

            // write decoded text to file
            FileWriter fw = new FileWriter("src/output.txt");
            fw.write(hfm.decoder(String.valueOf(decode)));


        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

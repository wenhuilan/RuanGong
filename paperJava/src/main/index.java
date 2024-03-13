package main;

import utils.SimhashOperation;
import utils.TxtOperation;

public class index {
    public static void main(String[] args) {
        TxtOperation txt = new TxtOperation();
        String origTxt = txt.readTxtTest("orig.txt");
        String addTxt = txt.readTxtTest("orig_0.8_add.txt");
        String delTxt = txt.readTxtTest("orig_0.8_del.txt");
        System.out.println(origTxt);
        SimhashOperation simHashOperation = new SimhashOperation();
        long hash1 = simHashOperation.simHash(origTxt, 64);//原文
        long hash2 = simHashOperation.simHash(addTxt, 64);//添加
        long hash3 = simHashOperation.simHash(delTxt, 64);//删除
        int dis12 = simHashOperation.hammingDistance(hash1, hash2);
        int dis13 = simHashOperation.hammingDistance(hash1, hash3);
        System.out.println("Hamming Distance12:" + dis12);
        System.out.println("Hamming Distance12:" + dis13);
    }
}

package utils;

public class SimhashOperation {
    /**
     * 计算 SimHash 值
     *
     * @param tokens    输入文本
     * @param hashbits  哈希位数
     * @return SimHash 值
     */
    public long simHash(String tokens, int hashbits) {

            int[] v = new int[hashbits];
//            分词
            String[] terms = tokens.split(" ");

            for (String term : terms) {
                // 计算每个词的哈希值
                int hash = term.hashCode();

                // 加权
                for (int i = 0; i < hashbits; i++) {
                    if (((hash >> i) & 1) == 1) {
                        v[i] += 1; // 对于1的位，将权值加1
                    } else {
                        v[i] -= 1; // 对于0的位，将权值减1
                    }
                }
            }

            // 计算SimHash值
        // 合并降维
            long fingerprint = 0;
            for (int i = 0; i < hashbits; i++) {
                if (v[i] >= 0) {
                    fingerprint |= (1L << i);
                }
            }

            return fingerprint;
        }
    /**
     * 计算汉明距离
     *
     * @param hash1  SimHash 值1
     * @param hash2  SimHash 值2
     * @return 汉明距离
     */
    public int hammingDistance(long hash1, long hash2) {
        long xor = hash1 ^ hash2;
        int distance = 0;
        while (xor != 0) {
            distance += 1;
            xor &= xor - 1;
        }
        return distance;
    }


}
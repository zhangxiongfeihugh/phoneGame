package telephoneGame;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

public class WordList {
    static SinglyLinkedList<String> wordList = new SinglyLinkedList<String>();
    static SinglyLinkedList<SinglyLinkedList> list = new SinglyLinkedList<SinglyLinkedList>();

    static {
        initWordList();
        orderWordList();
    }

    public static void initWordList() {
        try {
            Scanner scanner = new Scanner(new File("wordlist.txt"));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (!line.isEmpty())
                    wordList.insert(line);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static void orderWordList() {
        SinglyLinkedList<String> smallWordList = new SinglyLinkedList<String>();
        SinglyLinkedList<String> midWordList = new SinglyLinkedList<String>();
        SinglyLinkedList<String> largeWordList = new SinglyLinkedList<String>();
        SinglyLinkedList<String> extraWordList = new SinglyLinkedList<String>();
        SinglyLinkedNode<String> curr = wordList.getHead();
        do {
            String tmp = curr.getData();
            int length = tmp.length();
            if (length <= 3) {
                smallWordList.insert(tmp);
            } else if (length > 3 && length <= 5) {
                midWordList.insert(tmp);
            } else if (length > 5 && length <= 8) {
                largeWordList.insert(tmp);
            } else {
                extraWordList.insert(tmp);
            }
        } while ((curr = curr.getNext()) != null);
        list.insert(smallWordList);
        list.insert(midWordList);
        list.insert(largeWordList);
        list.insert(extraWordList);
    }

    public static int editDistance(String word1, String word2) {
        int m = word1.length();
        int n = word2.length();
        int[][] d = new int[m + 1][n + 1];
        for (int i = 0; i <= m; i++)
            d[i][0] = i;
        for (int i = 0; i <= n; i++)
            d[0][i] = i;
        for (int i = 1; i <= m; i++)
            for (int j = 1; j <= n; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1))
                    d[i][j] = d[i - 1][j - 1];
                else
                    d[i][j] = 1 +
                            Math.min(d[i - 1][j - 1],
                                    Math.min(d[i][j - 1], d[i - 1][j]));
            }

        return d[m][n];
    }

    public static String dropLetters(String word) {
        Random r = new Random();
        int length = word.length();
        int drop = r.nextInt(Math.min(3,length+1));  //不能掉段 3 ~ 5 ~ 8 ,否则找不到相似词
        if(isNotSameLengthRange(length-drop,length)){  //掉段直接
            return word;
        }
        int count = 0;
        while (count < drop) {
            int index = r.nextInt(length);
            word= word.substring(0, index)+ word.substring(index + 1);
            length = word.length();
            count++;
        }
        return word;
    }

    private static boolean isNotSameLengthRange(int i, int j) {
        if((i<=3&&j>3)||(i<=5&&j>5)||(i<=8&&j>8)){
            return true;
        }
        return false;
    }

    public static String findSimilarWord(String origin, String drop) {
        String res = null;
        int length = origin.length();
        SinglyLinkedNode<String> node;
        SinglyLinkedNode<SinglyLinkedList> startingList;
        if (length <= 3) {
            startingList = list.getHead();
        } else if (length > 3 && length <= 5) {
            startingList = list.getHead().getNext();
        } else if (length > 5 && length <= 8) {
            startingList = list.getHead().getNext().getNext();
        } else {
            startingList = list.getHead().getNext().getNext().getNext();
        }
        node = startingList.getData().getHead();
        int min_dist = 5;
        while (true) {
            if (node == null)
                break;
            int dist = editDistance(node.getData(), drop);
            if (dist < min_dist && !node.getData().equals(origin))  //保证不与原词相同
            {
                min_dist = dist;
                res = node.getData();
            }
            node = node.getNext();
        }
      //  System.out.println("origin ["+origin+"] drop ["+drop+"] similarResult ["+res+"]");
        return res;
    }

    /**
     * 随机选取5个词组成句子
     * 考虑到链表的索引需要遍历，故先生成5个随机数，一次遍历拿出5个词
     *
     * @return
     */
    public static SinglyLinkedList<Word> getNewRandomSentence() {
        SinglyLinkedList<Word> resultSentence = new SinglyLinkedList<Word>();
        //获取5个随机数
        int wordListSize = wordList.size;
        SinglyLinkedList<Integer> indexes = getRandomNumbers(wordListSize, 5);

        //取出这五个数对应在wordList中的word
        SinglyLinkedNode<String> wordNode = wordList.getHead();
        int count = 0;
        int index = 0;  //链表下标
        while (true) {
            if (wordNode == null || count == 5 || indexes.size==0) {
                break;
            }
            SinglyLinkedNode<Integer> indexNode = indexes.getHead();
            while (true) {
                if (indexNode == null) {
                    break;
                }
                if (index == indexNode.getData()) {
                    resultSentence.insert(new Word("___", wordNode.getData()));
                    count++;
                    indexes.remove(indexNode.getData());
                    break;
                }
                indexNode = indexNode.getNext();
            }
            index++;
            wordNode = wordNode.getNext();
        }


        return resultSentence;
    }

    /**
     * 获取n个指定范围内不重复随机正整数
     *
     * @param range 范围
     * @param count 个数
     * @return
     */
    private static SinglyLinkedList<Integer> getRandomNumbers(int range, int count) {
        Random random = new Random();
        SinglyLinkedList<Integer> resultList = new SinglyLinkedList<Integer>();
        out:
        do {
            int randomNumber = random.nextInt(range);
            SinglyLinkedNode<Integer> node = resultList.getHead();
            while (true) {    //保证不重复
                if (node == null) {
                    break;
                }
                if (randomNumber == node.getData()) {
                    continue out;
                }
                node = node.getNext();
            }
            resultList.insert(randomNumber);
        } while (resultList.size() < count);

        return resultList;
    }

}

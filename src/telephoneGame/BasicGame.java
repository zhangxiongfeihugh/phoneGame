/**
 * 
 */
package telephoneGame;

import java.util.Scanner;

/**
 * @author nicolezhang
 *
 */
public class BasicGame {
	public static SinglyLinkedList<Person> players;

	public static void main(String[] args) {
        players = GamePeopleListFactory.getPeopleList();
        //游戏初始化输入第一个人,测试
        System.out.println("Please enter a sentence to start");
        Person firstPlayer = players.getHead().getData();
		Scanner scanner = new Scanner(System.in);   //控制台获取第一个人说的话
		for(String word : scanner.nextLine().split(" ") ){
            firstPlayer.getSentence().insert(new Word("", word.toLowerCase()));
		}

        SinglyLinkedNode<Person> currentPlayerNode = players.getHead(); //从第一个人开始游戏
        SinglyLinkedList<Word> lastWordsSaid =currentPlayerNode.getData().speakToAnotherPerson(currentPlayerNode.getNext().getData());
        double speakingClarityOfLastPlayer = currentPlayerNode.getData().getSpeakingClarity(); //上个人的speakingClarity

        while((currentPlayerNode =currentPlayerNode.getNext()) != null){
            Person currentPlayer = currentPlayerNode.getData();
            Person nextPlayer = currentPlayerNode.getNext()==null?null:currentPlayerNode.getNext().getData();

            currentPlayer.listen(lastWordsSaid,speakingClarityOfLastPlayer);  //当前人听

            lastWordsSaid = currentPlayer.speakToAnotherPerson(nextPlayer); //当前人对下个人说
            speakingClarityOfLastPlayer = currentPlayerNode.getData().getSpeakingClarity();
        }
		scanner.close();
	}

	}



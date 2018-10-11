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
	public static DoublyLinkedList<Person> players;

	public static void main(String[] args) {
        players = GamePeopleListFactory.getPeopleList();
        //游戏初始化输入第一个人
        System.out.println("Please enter a sentence to start");
        Person firstPlayer = players.getHead().getData();
		Scanner scanner = new Scanner(System.in);   //控制台获取第一个人说的话
		for(String word : scanner.nextLine().split(" ") ){
            firstPlayer.getSentence().insert(new Word("", word.toLowerCase()));
		}

        DoublyLinkedNode<Person> currentPlayerNode = players.getHead(); //从第一个人开始游戏
        SinglyLinkedList<Word> lastWordsSaid =currentPlayerNode.getData().speakToAnotherPerson(currentPlayerNode.getNext().getData());
        double speakingClarityOfLastPlayer = currentPlayerNode.getData().getSpeakingClarity(); //上个人的speakingClarity

        while((currentPlayerNode =currentPlayerNode.getNext()) != null){
            Person currentPlayer = currentPlayerNode.getData();
            Person nextPlayer = currentPlayerNode.getNext()==null?null:currentPlayerNode.getNext().getData();

            if(!currentPlayer.listen(lastWordsSaid,speakingClarityOfLastPlayer)) {  //当前人没听清
                if(currentPlayer.getAskRepeatTimes()<5){  //该玩家问了上一个玩家小于5次
                    DoublyLinkedNode<Person> previous = currentPlayerNode.getPrevious();
                    currentPlayer.askAnotherPersonToRepeat(previous.getData());
                    currentPlayer.setAskRepeatTimes(currentPlayer.getAskRepeatTimes()+1);

                    currentPlayerNode = previous;   //回退到上个玩家
                    currentPlayer = currentPlayerNode.getData();
                    nextPlayer = currentPlayerNode.getNext().getData();
                }else{                                       //重新生成5词，游戏继续
                    currentPlayer.setSentence(WordList.getNewRandomSentence());
                }
            }
            lastWordsSaid = currentPlayer.speakToAnotherPerson(nextPlayer); //当前人对下个人说
            speakingClarityOfLastPlayer = currentPlayerNode.getData().getSpeakingClarity(); //speakingClarity
        }
		scanner.close();
	}

	}



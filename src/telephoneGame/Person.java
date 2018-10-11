package telephoneGame;

public class Person {
	private double speakingClarity;
	private double listeningAbility;
	private String name;
	private int askRepeatTimes = 0;
	private  SinglyLinkedList<Word> sentence = new SinglyLinkedList<>();

	public Person(double speakingClarity, double listeningAbility, String name){
		this.setSpeakingClarity(speakingClarity);
		this.setListeningAbility(listeningAbility);
		this.setName(name);
	}

	public double getSpeakingClarity() {
		return speakingClarity;
	}

	public void setSpeakingClarity(double speakingClarity) {
		this.speakingClarity = speakingClarity;
	}

	public double getListeningAbility() {
		return listeningAbility;
	}

	public void setListeningAbility(double listeningAbility) {
		this.listeningAbility = listeningAbility;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public SinglyLinkedList<Word> getSentence() {
		return sentence;
	}

    public void setSentence(SinglyLinkedList<Word> sentence) {
        this.sentence = sentence;
    }

    public int getAskRepeatTimes() {
        return askRepeatTimes;
    }

    public void setAskRepeatTimes(int askRepeatTimes) {
        this.askRepeatTimes = askRepeatTimes;
    }

    @Override
	public String toString() {
		return "Person{" +
				"speakingClarity=" + speakingClarity +
				", listeningAbility=" + listeningAbility +
				", name='" + name + '\'' +
				", sentence=" + sentence +
				'}';
	}

    public SinglyLinkedList<Word> speakToAnotherPerson(Person anotherPerson) {
        String result = name + " says \"";
        //组织语言
        organizeSentence();
        //说
        SinglyLinkedNode<Word> wordNode = sentence.getHead();
        boolean isFirstWord = true;
        do{
            Word word = wordNode.getData();
            result += (isFirstWord?"":" ") + word.getCurrent();
            isFirstWord = false;
        }while ((wordNode = wordNode.getNext())!=null);
        if(anotherPerson==null){
            result += "\"";
        }else{
            result += "\" to " + anotherPerson.getName();
        }
        System.out.println(result);
        return sentence;
    }


    /**
     * 3词以上没听清返回false 否则返回true
     * @param wordsSaid
     * @param speakingClarityOfLastPlayer
     * @return
     */
    public boolean listen(SinglyLinkedList<Word> wordsSaid, double speakingClarityOfLastPlayer) {
        SinglyLinkedNode<Word> wordNode = wordsSaid.getHead();
        int  misheardWordsCount = 0;
        sentence =  new SinglyLinkedList<>();
        do{
            Word word = wordNode.getData();
            //插入听到的话
            if(Math.random() >  this.listeningAbility*speakingClarityOfLastPlayer) {
                sentence.insert(new Word(word.getCurrent(), "___"));
                misheardWordsCount++;
            } else{
                sentence.insert(new Word(word.getCurrent(), word.getCurrent()));
            }
        }while ((wordNode = wordNode.getNext())!=null);
        String result = "";
        result += name + " hears \"";
        boolean isFirstWord = true;
        wordNode = sentence.getHead();
        do{
            result += (isFirstWord?"":" ") + wordNode.getData().getCurrent();
            isFirstWord = false;
        }while ((wordNode = wordNode.getNext())!=null);
        result += "\"";
        System.out.println(result);
        if(misheardWordsCount>3){
            return false;
        }
        return true;
    }


    /**
     * 说话之前组织语言，补全听到的话
     */
    private void organizeSentence() {
        SinglyLinkedNode<Word> wordNode = sentence.getHead();
        int wordsCount = 0;
        //组织语言
        do{
            Word word = wordNode.getData();
            //如果说话的人之前没听清，说话的时候根据WordList进行替代
            if(word.getCurrent().equals("___")) {
                String origin = word.getOrigin();
                String drop =  WordList.dropLetters(origin);
                String similar = WordList.findSimilarWord(origin,drop);
                if(similar == null) { //找不到则移除
                    sentence.remove(word);
                    continue;
                }else{
                    word.setCurrent(similar);
                }
            }
            wordsCount++;
        }while ((wordNode = wordNode.getNext())!=null);
        if(wordsCount==0){  //全被移除 随机找5个新词组成句子
            sentence = WordList.getNewRandomSentence();
        }
    }

    public void askAnotherPersonToRepeat(Person anotherPerson) {
        System.out.println(name+" asks "+anotherPerson.getName()+" to repeat what they said.");
    }
}

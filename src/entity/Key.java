package entity;

import java.util.List;

/**
 * @author KGZ
 * @date 2019/1/4 10:53
 */
public class Key {

    private String word;

    private List<String> link;

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public List<String> getLink() {
        return link;
    }

    public void setLink(List<String> link) {
        this.link = link;
    }
}

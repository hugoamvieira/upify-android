package a029589.ismai.pt.upify;

/**
 * Created by Spanker Desktop on 21/05/2017.
 */

public class Review {

    /**
     * star_amount : 5
     * hashtag_id : 1
     * givenby_userid : 5
     */

    private int star_amount;
    private int hashtag_id;
    private int givenby_userid;

    public int getStar_amount() {
        return star_amount;
    }

    public void setStar_amount(int star_amount) {
        this.star_amount = star_amount;
    }

    public int getHashtag_id() {
        return hashtag_id;
    }

    public void setHashtag_id(int hashtag_id) {
        this.hashtag_id = hashtag_id;
    }

    public int getGivenby_userid() {
        return givenby_userid;
    }

    public void setGivenby_userid(int givenby_userid) {
        this.givenby_userid = givenby_userid;
    }
}

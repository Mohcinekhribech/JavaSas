package DTO;

import java.util.ArrayList;
import java.util.List;

public class Borrower {
    private int id;
    private String name;
    private int memberNum;
    List<Book> books = new ArrayList<>();
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMemberNum() {
        return memberNum;
    }

    public void setMemberNum(int memberNum) {
        this.memberNum = memberNum;
    }
}

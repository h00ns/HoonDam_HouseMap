package com.ssafy.happyhouse.model.dto;

public class CommentDto {
    private String idcomment;
    private String aptno;
    private String writer;
    private String content;
    public String getIdcomment() {
        return idcomment;
    }
    public void setIdcomment(String idcomment) {
        this.idcomment = idcomment;
    }
    public String getAptno() {
        return aptno;
    }
    public void setAptno(String aptno) {
        this.aptno = aptno;
    }
    public String getWriter() {
        return writer;
    }
    public void setWriter(String writer) {
        this.writer = writer;
    }
    public String getContent() {
        return content;
    }
    public void setContent(String content) {
        this.content = content;
    }


}    
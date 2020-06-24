package com.myapp.memotutorial.data;

public class CardData {
    private String cardTitle, cardBody, cardDate;

    public CardData(String cardTitle, String cardBody, String cardDate) {
        this.cardTitle = cardTitle;
        this.cardBody = cardBody;
        this.cardDate = cardDate;
    }

    public String getCardTitle() {
        return cardTitle;
    }

    public void setCardTitle(String cardTitle) {
        this.cardTitle = cardTitle;
    }

    public String getCardBody() {
        return cardBody;
    }

    public void setCardBody(String cardBody) {
        this.cardBody = cardBody;
    }

    public String getCardDate() {
        return cardDate;
    }

    public void setCardDate(String cardDate) {
        this.cardDate = cardDate;
    }
}

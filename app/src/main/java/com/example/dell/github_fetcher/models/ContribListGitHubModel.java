package com.example.dell.github_fetcher.models;

public class ContribListGitHubModel {


    private String cardViewImageListRepo;
    private String cardViewTitleListRepo;


    public ContribListGitHubModel(String cardViewImageListRepo, String cardViewTitleListRepo) {

        this.cardViewImageListRepo = cardViewImageListRepo;
        this.cardViewTitleListRepo = cardViewTitleListRepo;
    }

    public String getCardViewImageListRepo() {
        return cardViewImageListRepo;
    }

    public void setCardViewImageListRepo(String cardViewImageListRepo) {
        this.cardViewImageListRepo = cardViewImageListRepo;
    }

    public String getCardViewTitleListRepo() {
        return cardViewTitleListRepo;
    }

    public void setCardViewTitleListRepo(String cardViewTitleListRepo) {
        this.cardViewTitleListRepo = cardViewTitleListRepo;
    }
}

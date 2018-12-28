package com.example.dell.github_fetcher.models;

public class RepoListGitHubModel {

    private String userName;
    private String repoName;
    private String imageURL;

    public String getRepoName() {
        return repoName;
    }

    public RepoListGitHubModel(String userName, String repoName, String imageURL) {
        this.userName = userName;
        this.repoName = repoName;
        this.imageURL = imageURL;
    }

    public void setRepoName(String repoName) {
        this.repoName = repoName;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public String toString() {
        return "RepoListGitHubModel{" +
                "userName='" + userName + '\'' +
                ", repoName='" + repoName + '\'' +
                ", imageURL='" + imageURL + '\'' +
                '}';
    }
}

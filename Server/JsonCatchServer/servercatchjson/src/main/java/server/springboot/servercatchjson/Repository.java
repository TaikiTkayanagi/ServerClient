package server.springboot.servercatchjson;

public class Repository {
    private String name;
    private String htmlUrl;

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return this.name;
    }

    public void setHtmlUrl(String htmlUrl){
        this.htmlUrl = htmlUrl;
    }

    public String getHtmlUrl(){
        return this.htmlUrl;
    }
}

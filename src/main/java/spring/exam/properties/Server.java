package spring.exam.properties;

public class Server {
    String url;
    int port;

    public Server(String url, int port) {
        this.url = url;
        this.port = port;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    @Override
    public String toString() {
        return "Server{" +
                "url='" + url + '\'' +
                ", port=" + port +
                '}';
    }
}

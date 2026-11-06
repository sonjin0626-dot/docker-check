import com.sun.net.httpserver.HttpServer;
import java.net.InetSocketAddress;

public class Main {
    public static void main(String[] args) throws Exception {
        HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
        server.createContext("/", exchange -> {
            String html = "<html><head><title>Docker確認課題</title></head>"
                    + "<body><h1>GitHubとDockerの確認課題</h1>"
                    + "<p>Javaプログラムから表示しています</p></body></html>";
            byte[] bytes = html.getBytes("UTF-8");
            exchange.getResponseHeaders().set("Content-Type", "text/html; charset=UTF-8");
            exchange.sendResponseHeaders(200, bytes.length);
            try (var output = exchange.getResponseBody()) {
                output.write(bytes);
            }
        });
        server.start();
        System.out.println("Server started on port 8080");
    }
}

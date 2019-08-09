package com.github.nmyphp.mytomcat;


import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

/**
 * 封装HTTP响应
 *
 * @author nmyphp
 */
@Getter
@Setter
@Slf4j
public class HttpResponse {

    private OutputStream outputStream;
    private StatusCode statusCode;
    private ContentType contentType;
    private Charset charset = Charset.forName("UTF-8");

    public HttpResponse(OutputStream outputStream, ContentType contentType) {
        this.outputStream = outputStream;
        this.contentType = contentType;
    }

    public HttpResponse(OutputStream outputStream, ContentType contentType, Charset charset) {
        this.outputStream = outputStream;
        this.contentType = contentType;
        this.charset = charset;
    }

    public void write(String content) {
        try {
            StringBuilder builder = new StringBuilder();
            builder.append(statusCode)
                .append("\n")
                .append(contentType)
                .append("\n");
            if (contentType == ContentType.TEXT) {
                builder.append("<html><body>")
                    .append(content)
                    .append("</body></html>");
            } else if (contentType == ContentType.JSON) {
                builder.append(content);
            }
            outputStream.write(builder.toString().getBytes(charset));
        } catch (Exception ex) {
            log.error("Occur exception when write response. ", ex);
        } finally {
            try {
                outputStream.close();
            } catch (IOException ex) {
                // ignore
            }
        }
    }
}

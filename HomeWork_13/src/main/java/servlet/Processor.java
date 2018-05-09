package servlet;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.Map;

import static servlet.Constants.HTML_DIR;

public class Processor {

    private static Processor instance = new Processor();

    private final Configuration configuration;

    private Processor() {
        configuration = new Configuration();
        configuration.setClassForTemplateLoading(this.getClass(), "/tml/");
    }

    static Processor instance() {
        return instance;
    }

    String getPage(String filename, Map<String, Object> data) throws IOException {
        try (Writer stream = new StringWriter()) {
            Template template = configuration.getTemplate(filename);
            template.process(data, stream);
            return stream.toString();
        } catch (TemplateException e) {
            throw new IOException(e);
        }
    }

}

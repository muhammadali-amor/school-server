package ali.school_server.config;

import org.springframework.core.io.ClassPathResource;

import javax.swing.*;
import java.util.Properties;

public class InitConfig {
    public static boolean isStart() {
        Properties properties = new Properties();
        try {
            properties.load(new ClassPathResource("/application.properties").getInputStream());
            if (properties.getProperty("spring.jpa.hibernate.ddl-auto").equals("update")) {
                return true;
            } else {
                String confirm = JOptionPane.showInputDialog("Malumot o'chib ketishi mumkin agarda malumotlarni o'chirib yuborsang o'lib ketasan. (O'chirish kodi ustozingdan sora)");
                if (confirm != null && confirm.equals("DELETE")) {
                    return true;
                }
            }
        } catch (Exception e) {
            System.err.println("run bolmadi qozi");
        }
        return false;
    }
}

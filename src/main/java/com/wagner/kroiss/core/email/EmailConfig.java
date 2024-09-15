package com.wagner.kroiss.core.email;

import com.wagner.kroiss.domain.service.EnvioEmailService;
import com.wagner.kroiss.infrastructure.service.email.FakeEnvioEmailService;
import com.wagner.kroiss.infrastructure.service.email.SandboxEnvioEmailService;
import com.wagner.kroiss.infrastructure.service.email.SmtpEnvioEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
public class EmailConfig {

    @Autowired
    private EmailProperties emailProperties;

    @Bean
    @Primary
    public EnvioEmailService envioEmailService() {

        switch (emailProperties.getImpl()) {
            case FAKE:
                return new FakeEnvioEmailService();
            case SMTP:
                return new SmtpEnvioEmailService();
            case SANDBOX:
                return new SandboxEnvioEmailService();
            default:
                return null;
        }
    }
}
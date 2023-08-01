package github.wozniak.bankingservice.configuration;

import github.wozniak.bankingservice.properties.SchedulingProperties;
import github.wozniak.bankingservice.service.RegistrationTokenService;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * @author : Jackson Wozniak
 * @created : 7/31/2023, Monday
 **/
@Configuration
@EnableScheduling
@AllArgsConstructor
//clear all database tokens at end of day
public class RegistrationTokenPurge {

    private final RegistrationTokenService registrationTokenService;
    private final Logger logger = LoggerFactory.getLogger(RegistrationTokenPurge.class);

    @Scheduled(cron = SchedulingProperties.runAtMidnight)
    public void execute(){
        long deleted = registrationTokenService.deleteExpiredTokens();
        if(deleted > 0){
            logger.info("Tokens purged: " + deleted);
        }
    }
}

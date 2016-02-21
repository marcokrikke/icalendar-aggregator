package nl.marcokrikke.icalendaraggregator.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.datetime.DateFormatterRegistrar;
import org.springframework.format.support.DefaultFormattingConversionService;
import org.springframework.format.support.FormattingConversionService;

@Configuration
public class ConversionConfig {

    @Bean
    public FormattingConversionService conversionService() {
        DefaultFormattingConversionService conversionService = new DefaultFormattingConversionService();
        
        DateFormatterRegistrar registrar = new DateFormatterRegistrar();
        registrar.registerFormatters(conversionService);

        return conversionService;
    }
}

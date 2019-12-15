package jobworld.security;

import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

public class AppSecurityInitializer extends AbstractSecurityWebApplicationInitializer {
    //nessun codice necessario; Spring "scopre" questa istanza durante lo scan e si configura per usare la cosiddetta springSecurityFilterChain
}

package ca.bc.gov.mof.wfpointid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

import ca.bc.gov.mof.wfpointid.util.SSLDisabler;

@SpringBootApplication
@EnableScheduling
@ComponentScan
public class PointIdServiceApplication {

	public  static final String HTTPS_PROTOCOLS = "TLSv1,TLSv1.1,TLSv1.2";
	
	private static Logger LOG = LoggerFactory.getLogger(PointIdServiceApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(PointIdServiceApplication.class, args);
	}


	public PointIdServiceApplication() {
		LOG.info("==================  Point ID Service started  ======================");
		System.setProperty("https.protocols", HTTPS_PROTOCOLS);
		disableSSL();
	}

	/**
	 * 
	 * Disable SSL verification to allow contacting GeoServers which may not have 
	 * accepted keycerts.
	 * 
	 * This should be safe, since the only access this service makes to them is:
	 * <ul>
	 * <li>Read-only
	 * <li>Only works against the limited GeoServer MWS/WFS protocol
	 * </ul>
	 * 
	 * @author mbdavis
	 *
	 */
	private static void disableSSL() {
		SSLDisabler.disableSSLViaTrustManager();
	}
	
}  

package neev.academic;



import com.neev.moh.logger.MohLogFactory;
import com.neev.moh.logger.MohLogger;

public class Test1 {

	static {
		System.setProperty("java.util.logging.manager", "org.apache.logging.log4j.jul.LogManager");
    }

	private static final MohLogger logger = MohLogFactory.getLoggerInstance(Test1.class.getName());
    
    public static void main(String[] args) {
		logger.log(MohLogger.DEBUG, "msg");
	}

}

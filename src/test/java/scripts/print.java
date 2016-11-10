package scripts;

public class print {
 public static void main(String[] args) {
		String homeDir = System.getProperty("user.dir");
		 String driverPathChrome = homeDir+"\\src\\main\\resources\\chromedriver";

System.out.println(driverPathChrome);
}
}

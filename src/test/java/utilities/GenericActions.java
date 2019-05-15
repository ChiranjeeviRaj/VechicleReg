package utilities;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import com.exercise.service.CSVDataReaderService;
import com.exercise.service.CSVDataReaderServiceImpl;
import com.exercise.service.FileReaderService;
import com.exercise.service.FileReaderServiceImpl;
import com.exercise.service.com.exercise.service.model.FileInfoModel;
import com.exercise.service.com.exercise.service.model.VehicleModel;
import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class GenericActions 
{
	public WebDriver driver;
	public String timeStamp;
	public static ExtentReports reports;
	public static ExtentTest logger;
	public static File f;
	public static FileWriter fw;
	public static BufferedWriter bw;
    private final static String FILE_CSV = ".csv";
	
	public GenericActions()
	{
	   try
	   {
			timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(java.util.Calendar.getInstance().getTime());
			
			// Initialize Reporting
			reports = new ExtentReports(Inputs.REPORTS_PATH+timeStamp+".html");
			logger = reports.startTest("Vechicle Registration Verification Testing");
			
			// Initialize Logs
			f = new File(Inputs.LOGS_PATH+timeStamp+".txt");
			fw = new FileWriter(f,true);
			bw = new BufferedWriter(fw);
	   }
	   catch(Exception ex)
	   {
		   ex.printStackTrace();
	   }
	}
	
	
	/**
	 * This below method using for reporting, both status and description updates automatically.
	 * @param status
	 * @param desc
	 */
	public void reporting(String status,String desc)
	{
		if(status.equalsIgnoreCase("pass"))
		{
			logger.log(LogStatus.PASS, desc);
		}
		else
		{
			logger.log(LogStatus.FAIL, desc);
		}
		reports.flush();
	}
	
	/**
	 * THe desc will go to logs
	 * @param desc
	 */
	public void writeIntoLogs(String desc)
	{
		  try
		  {
			bw.newLine();
			bw.write(desc);
			bw.flush();
		  }
		  catch(Exception ex)
		  {
			  ex.printStackTrace();
		  }
	}
	
	/**
	 * The below method config the browser data based on Inputs browser name.
	 */
	public void openBrowser()
	{
		if(Inputs.BROWSER_NAME.equalsIgnoreCase("chrome"))
		{
			System.setProperty("webdriver.chrome.driver", Inputs.CHROME_DRIVER_PATH);
			driver = new ChromeDriver();
			writeIntoLogs("Chrome Brpwser Launched");
		}
		else if(Inputs.BROWSER_NAME.equalsIgnoreCase("firefox"))
		{
			System.setProperty("webdriver.gecko.driver", Inputs.FIRE_FOX_DRIVER_PATH);
			driver = new FirefoxDriver();
			writeIntoLogs("Firefox Browser Launched");
		}
	}
	
	/**
	 * Maximise browser
	 */
	public void maximizeBrowser()
	{
		driver.manage().window().maximize();
		writeIntoLogs("Browser Maximized");
	}
	
	/**
	 * Thread waits for the given time
	 * @param num_of_milliSeconds
	 */
	public void wait(int num_of_milliSeconds)
	{
	   try
	   {
		Thread.sleep(num_of_milliSeconds);
	   }
	   catch(Exception ex)
	   {
		   ex.printStackTrace();
	   }
	}
	
	/**
	 * Close the browser
	 */
	public void closeBrowser()
	{
		driver.quit();
	}
	
	/**
	 * Navigate to Register Page
	 */
	public void navigateToRegistrationPage()
	{
		driver.get(Inputs.URL);
	}
	/**
	 * Navigate to Register Service Page	
	 */
	public void navigateToRegistrationServicePage()
	{
		driver.get(Inputs.REG_URL);
	}
	
	/**
	 * Enter data of the given reg and clicks submits
	 * @param reg
	 */
	public void clickGetStartedAndEnterRegDetailsAndSubmit(String reg){
		//driver.findElement(By.xpath(ObjectRepo.START_NOW)).click();
		driverWait(10);
		driver.findElement(By.xpath(ObjectRepo.REG_NUM_INPUT)).sendKeys(reg);
		driver.findElement(By.xpath(ObjectRepo.REG_NUM_SUBMIT)).click();
		driverWait(10);
	}
	
	/**
	 * Driver implicitlt waits until the browser loads
	 * @param sec
	 */
	private void driverWait(int sec) {
		driver.manage().timeouts().implicitlyWait(sec, TimeUnit.SECONDS);		
	}


	/**
	 * returns vehicle make text
	 * @return
	 */
	public String getVehicleMake(){
		return driver.findElement(By.xpath(ObjectRepo.MAKE)).getText();
	}
	
	/**
	 * return vehicle color text
	 * @return
	 */
	public String getVehicleColor(){
		return driver.findElement(By.xpath(ObjectRepo.COLOUR)).getText();
	}
	
	/**
	 * Fetch CSV Data
	 * @return
	 */
	public Map<String, VehicleModel> fetchCSVData(){


        FileReaderService service = new FileReaderServiceImpl();
        CSVDataReaderService csvService = new CSVDataReaderServiceImpl() ;
        List<FileInfoModel> fileInfoModels = null;
        List<VehicleModel> vehicleModels = new ArrayList<VehicleModel>();
        try {
            fileInfoModels = service.scanDirectory(Inputs.FILE_DIR);

            for (FileInfoModel fileInfoModel :fileInfoModels) {
                if(fileInfoModel.getExt().equalsIgnoreCase(FILE_CSV)){
                    vehicleModels.addAll(csvService.readCSVFile(fileInfoModel.getFileName()));
                }
            }

            System.out.println(vehicleModels);
        }catch(IOException e){
            e.printStackTrace();
            
        }
        return vehicleModels.stream().collect(Collectors.toMap(VehicleModel::getVehicleReg, Function.identity()));
    
	}
	
	/**
	 * This method takes screenshot of the screen and save on dest location
	 * @throws IOException
	 */
	public void takeScreenShot() throws IOException{
		 TakesScreenshot scrShot =((TakesScreenshot)driver);

	     File SrcFile=scrShot.getScreenshotAs(OutputType.FILE);
	     File DestFile=new File("./ScreenShot/" + timeStamp + RandomUtils.nextLong() +".png");
	     FileUtils.copyFile(SrcFile, DestFile);
	}
	
	
}

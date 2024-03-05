import java.io.File;
import java.io.IOException;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.Bucket;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import java.net.URL;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

public class UploadToS3 {
   
    public static void main(String[] args) throws InterruptedException,IOException{
   
   try{
    		          
		final AmazonS3Client amazonS3Client = new AmazonS3Client();
       
        AWSCredentials credentials = new BasicAWSCredentials(
				"AKIAJKH34IQOQY6FNC2A", 
				"Hzvq2G2j6FJEOOGMjI6UNhafRFk3dY0/rxJhj8IL");
          
        
        long lengthOfFileToUpload;
        
             
        File google;
        final String bucketName = "disktelugu";
        final String key ="google.jpg";
        {
        	
      //newBucket = amazonS3Client.createBucket((bucketName));
        	String imageUrl = "file:///C:/Users/MyUser/image.jpg";
        	
        URL urlToFile = new URL("C:\\Users\\home\\Downloads\\Softwares\\33.jpg");
        
        System.out.println("urlToFile::::::::"+urlToFile);
        
        String fullFilePath = urlToFile.getFile();
        google = new File(fullFilePath);
        lengthOfFileToUpload = google.length();        
        }
        
        {
        PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName,key,google);
        
            ObjectMetadata objectMetadata = new ObjectMetadata();
            
            objectMetadata.setContentLength(lengthOfFileToUpload);
            
            putObjectRequest.withMetadata(objectMetadata);
            
            amazonS3Client.putObject(putObjectRequest);
        }
                
        
    	}
    	catch(Exception e){
    		System.out.println("In Exception  "+e);
    	}
    }
    
}
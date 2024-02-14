import org.apache.commons.lang3.StringUtils;

public class StringAPKVersionComparsion {
	
	public static int VersionCompare(String userDbVersion,String userCurrentVersion)
	{
//		System.out.println("User db old version :::"+userDbVersion);		
//		System.out.println("User current version :::"+userCurrentVersion);
		
	    int v1Len=StringUtils.countMatches(userDbVersion,".");
	    int v2Len=StringUtils.countMatches(userCurrentVersion,".");

	    if(v1Len!=v2Len)
	    {
	        int count=Math.abs(v1Len-v2Len);
	        if(v1Len>v2Len)
	            for(int i=1;i<=count;i++)
	            	userCurrentVersion+=".0";
	        else
	            for(int i=1;i<=count;i++)
	            	userDbVersion+=".0";
	    }

	    if(userDbVersion.equals(userCurrentVersion))
	        return 0;

	    String[] v1Str=StringUtils.split(userDbVersion, ".");
	    String[] v2Str=StringUtils.split(userCurrentVersion, ".");
	    for(int i=0;i<v1Str.length;i++)
	    {
	        String str1="",str2="";
	        for (char c : v1Str[i].toCharArray()) {
	            if(Character.isLetter(c))
	            {
	                int u=c-'a'+1;
	                if(u<10)
	                    str1+=String.valueOf("0"+u);
	                else
	                    str1+=String.valueOf(u);
	            }
	            else
	                str1+=String.valueOf(c);
	        }            
	        for (char c : v2Str[i].toCharArray()) {
	            if(Character.isLetter(c))
	            {
	                int u=c-'a'+1;
	                if(u<10)
	                    str2+=String.valueOf("0"+u);
	                else
	                    str2+=String.valueOf(u);
	            }
	            else
	                str2+=String.valueOf(c);
	        }
	        v1Str[i]="1"+str1;
	        v2Str[i]="1"+str2;

	            int num1=Integer.parseInt(v1Str[i]);
	            int num2=Integer.parseInt(v2Str[i]);

	            if(num1!=num2)
	            {
	                if(num1>num2)
	                    return 1;
	                else
	                    return 2;
	            }
	    }
	    return -1;
}
	
	public static void main(String[] args) {
		
		//added by Harinder on 16-09-2021
		//To Compare android and iOS mobile user app version data
		//return 2 if greater value
		//return 1 if lesser value
		//return 0 if same value
		String userCurrentApkVersion="1.0";
		String dbApkLatestVersion="1.0.12";
		
		int i=VersionCompare(userCurrentApkVersion,dbApkLatestVersion);
		
		System.out.println("Response :: "+i); // 2 greater ., 0 for same          
		
		
	}
}

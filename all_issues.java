import org.eclipse.egit.github.core.Issue;
import org.eclipse.egit.github.core.Repository;
import org.eclipse.egit.github.core.User;
import org.eclipse.egit.github.core.client.GitHubClient;
import org.eclipse.egit.github.core.service.IssueService;
import org.eclipse.egit.github.core.service.RepositoryService;

import java.io.FileWriter;
import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;



public class all_Issues {

	/**
	 * @param args
	 */
	
	private static Map<String,String> test =    new HashMap <String, String>();
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

        GitHubClient client = new GitHubClient();
// Insert your Github and Password below:		
        client.setCredentials("login", "password");
        
        IssueService issueService = new IssueService(client);
        
        RepositoryService service = new RepositoryService(client);

		//Enter the repository owner details below:
		String repoOwner = "";
        
        //Enter the repository from which you want to fetch the issues
		String repoName = "junit";
		
		//The type of issues needed to be imported
		
        test.put("state","all");
      //  test.put("label","invalid");


        
        try {
        	
            String sFileName = "\\Issues-" + repoOwner + "-" + repoName + ".csv";
            FileWriter writer = new FileWriter(sFileName);
            
        for (Issue issue : issueService.getIssues(repoOwner, repoName, test)) {
            //String[] data = {String.valueOf(issue.getId()), issue.getTitle(), issue.getUser().getName(), issue.getAssignee().getName(), issue.getMilestone().getTitle(), issue.getState(), issue.getBodyText()};
        //    writer.append(String.valueOf(issue.getId()) + ",");
        	
        	
        	Integer issueNo = issue.getNumber();
        	User assignee = (issue.getAssignee());
        	String body = toStr(issue.getBody());
        	String bodyText = toStr(issue.getBodyText());
        	java.util.Date closedAt = issue.getClosedAt();
        	Integer comments = issue.getComments();
        	java.util.Date createdAt = issue.getCreatedAt();
        	String labels = toStr(String.valueOf(issue.getLabels()));
        	String milestone = toStr(String.valueOf(issue.getMilestone()));
        	String pullrequest = toStr(String.valueOf(issue.getPullRequest()));
        	String state = toStr(issue.getState());
        	String title = toStr(issue.getTitle());
        	java.util.Date updatedAtT = issue.getUpdatedAt();
        	User user = (issue.getUser());
        	
        	createCSV(writer, issueNo,
       			  assignee, body, bodyText,
       				 closedAt, comments,
       				 createdAt, labels,
       				 milestone, pullrequest,
       				 state, title,updatedAtT, user);
        	//String sFileName = "D:\\SonarQube\\IssuesTracking\\Issues.csv";
        	System.out.println(issueNo + "," + assignee + "," + body + "," + bodyText + "," 
        			+ closedAt + "\n");      	
        } 
        
    	writer.close();
        
        }catch (IOException ex) {
            System.out.println("An IOException has occured!"); }
	}

	 public static String toStr ( String str){
		 if (str == null) {
			 return str = "";
			 
		 }
		 else {
		 str = str.replace("\n", " ").replace("\r", " ").replace("\t", " ").replace("\"", "\"\"");
		 str = "\"" + str + "\"";
		 return str;
		 }
		 }
	 public static void createCSV(FileWriter writer,Integer issueNo,
			 User u,String body,String bodyText,
				java.util.Date closedAt,Integer comments,
				java.util.Date createdAt,String labels,
				String milestone,String pullrequest,
				String state,String title,java.util.Date updatedAtT,
				User user) throws IOException{
		 		 
		 writer.append(issueNo + ",");
		 writer.append(getCSVUser(u) + ",");
		 writer.append(body + ",");
		 writer.append(bodyText + ",");
		 writer.append(closedAt + ",");
		 writer.append(comments + ",");
		 writer.append(createdAt + ",");
		 writer.append(labels + ",");
		 writer.append(milestone + ",");
		 writer.append(pullrequest + ",");
		 writer.append(state + ",");
		 writer.append(title + ",");
		 writer.append(updatedAtT + ",");
		 writer.append(getCSVUser(user) + "\n");

		 
	 }
		public static String getCSVUser(User user){
			 Integer uId1 = 0;
			 String uLogin1 = "";
			 String uName1= "";
			 
		//	 User u = new User();
			 if (user == null){
				
			 }
			 else { uId1 = user.getId();
			  uLogin1 = toStr(user.getLogin());
			  uName1 = toStr(user.getName());}
			  
			  String userDetails = String.valueOf(uId1) + ", " + uLogin1 + ", " + uName1;
			  
			  return userDetails;
			
		}
	 }

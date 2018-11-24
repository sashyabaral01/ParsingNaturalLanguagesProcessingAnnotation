	package Browser;

import java.util.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;



import java.io.*;
import javafx.scene.text.Font;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;
import javafx.application.Application;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main extends Application {
	
	
	//private Scanner z;
    String everything;
    String text;
    TableView tab = new TableView();
	Label a1;
	Label b1;
	Label c1;
	Label d1;
	String file1;
	String start;
	String id;
	String content;
	long startNum;
	int startNumInt;
	long endNum;
	int endNumInt;
	String end;
	String agreement;
	String label;
	String type;
	int rows;
	
	BorderPane layout = new BorderPane();
	
	
	Annotation annotation = new Annotation(type,label,id,start,end,agreement,content);

	GridPane labels = new GridPane();
	public static void main(String[]args) {
		launch(args);
	}
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Browser");

		
		ListView<String> list = new ListView<String>();		
		
		WebView webView = new WebView();	
		// Create the WebEngine
		final WebEngine webEngine = webView.getEngine();
		
		
		// LOad the Start-Page
		
		
		webEngine.load("http://google.com");
		// Update the stage title when a new web page title is available
		layout.setCenter(webView);
		
		String Folder="C:\\Users\\Sshy\\Desktop\\JSON Folders";
       
		//create a list of file names
        ObservableList<? extends Object> items =FXCollections.observableArrayList (getFolderFiles(Folder));
		
			list.setItems((ObservableList<String>) items);
			list.setPrefWidth(300);
			list.setPrefHeight(600);
			
			
		    //data table view
   	        TableColumn typeColumn = new TableColumn("type");
   			typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));

   			TableColumn label1 = new TableColumn("Label");
   			
   			label1.setCellValueFactory(new PropertyValueFactory<>("label"));
   			
   			
   			TableColumn identificationColumn = new TableColumn("id");
   			
   			identificationColumn.setCellValueFactory(new PropertyValueFactory<>("Id"));
   			
   			
   			TableColumn startColumn = new TableColumn("start");
   			startColumn.setCellValueFactory(new PropertyValueFactory<>("start"));
   			
   			
   			TableColumn endColumn = new TableColumn("end");
   			endColumn.setCellValueFactory(new PropertyValueFactory<>("end"));
   			
   			
   			TableColumn agreementColumn = new TableColumn("Agreement");
   			agreementColumn.setCellValueFactory(new PropertyValueFactory<>("Agreement"));
   			
   			
   			TableColumn contentColumn = new TableColumn("Content");
   			contentColumn.setCellValueFactory(new PropertyValueFactory<>("Content"));

   			tab.getColumns().addAll(typeColumn, label1,	identificationColumn,startColumn,endColumn,agreementColumn,contentColumn);	
   			
   			

			
			list.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<String>() {
		
			    public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
			    	tab.getItems().clear();		
			    	
			        String file = Folder + "\\" + newValue;		        
			        if(list.equals(file));
			        {
			        	
			        	
			        	
			        	try {
			        		
			        		tab.getItems().clear();			
			        		
			        		//tab.getItems().clear();		
			        		 Object obj = new JSONParser().parse(new FileReader("C:\\Users\\Sshy\\Desktop\\JSON Folders\\" + newValue));			        	
			     	        JSONObject jo = (JSONObject) obj;
			     	        
			     	        JSONObject payload = (JSONObject)jo.get("payload");
			     	      
			     	        JSONObject textObject = (JSONObject)payload.get("text");
			     	     
			     	        JSONArray views = (JSONArray)payload.get("views");
			     	        JSONObject view = (JSONObject)views.get(0);
			     	        
  		     	            text = (String)textObject.get("@value");
			     	         
			     	    
			     	       
			     	        
			     	       JSONArray annotations = (JSONArray)view.get("annotations");
			            
			     	       
			     	       Iterator iter = annotations.iterator();
			     	       
			     	      
			     	      String html= text;
			     	      
			     	      
			     	     String htmlString =  "<html> <head> " + 			       			    
				       			    "<style>" + 
				       			    ".histology {color: red}" +
				       			    ".site {color: blue}" +
				       			    ".heading {color: green; background-color:yellow; display:block}" +
				       			    ".behavior {color: purple}" +
				       			    ".specimen {color: aqua}" +
				       			    "</style>" +
				       			    "</head>" + 
				       			    "<body>{{body}}" +   					       			
				       			    "</body>"+
				       			    "</html>";

			     	     while(iter.hasNext())
			     	       {
			     	    	   JSONObject annotationObjet = (JSONObject)iter.next();
			     	    	   id = (String)annotationObjet.get("id").toString();
			     	    	   start = (String)annotationObjet.get("start").toString();
			     	    	   startNum =  (long) annotationObjet.get("start");
			     	    	   startNumInt = (int)startNum;
			     	    	   end = (String)annotationObjet.get("end").toString();
			     	    		 endNum = (long) annotationObjet.get("end");
			     	    		endNumInt = (int)endNum;
			     	    	 
			     	    	    type = (String) annotationObjet.get("@type");
			     	    	   //System.out.println(annotation.get("label"));
			     	    	   
			     	    	    label = (String)annotationObjet.get("label");
			     	    	 	     	    	   			     	    	   			     	    	  			     	    	   
			     	    	   JSONObject feature = (JSONObject)annotationObjet.get("features");
			     	    	   agreement = (String)feature.get("agreement");
			     	    	   
			     	    	   content =  (String)feature.get("content");
			     	    	  				  
				     							     								     								     								     			
				     			annotation = new Annotation(type,label,id,start,end,agreement,content);
				     			tab.getItems().add(annotation);	
				     			
				     		   
				     		
				     			
			     				if(type.equals("Cancer Histology Subtype")) {
      			        			
      			        			html = html.replace(content,  "<span class='histology' title='Cancer Histology Subtype'> " + content + " </span>"); 
      			        		}
			       			   
			       			   else if(type.equals("Cancer Histology Type")) {
     			        			html = html.replace(content,  "<span class='histology' title='Cancer Histology Subtype'> " + content + " </span>"); 
     			        		}
			       			   
			       			   
			       			 else if(type.equals("Final Diagnosis Heading")) {
   			        			html = html.replace(content,  "<span class='heading' title='Final Diagnosis Heading'> " + content + " </span>"); 
  			        		}
			       			   
			       			 else if(type.equals("Macroscopic/Gross Description Heading")) {
	   			        			html = html.replace(content,  "<span class='heading' title='Macroscopic/Gross Description Heading'> " + content + " </span>"); 
  			        		}
			       			   
			       			   
			       			 else if(type.equals("Nature of Specimen Heading")) {
   			        			html = html.replace(content,  "<span class='heading' title='Nature of Specimen Heading'> " + content + " </span>"); 
  			        		}
			       			   
			       			 else if(type.equals("Neoplasm Behaviour")) {
	   			        			html = html.replace(content,  "<span class='behavior' title='Neoplasm Behaviour'> " + content + " </span>"); 
  			        		}
			       			   
			       			 else if(type.equals("Organ/Body Structure")) {
   			        			html = html.replace(content,  "<span class='site' title='Organ/Body Structure'> " + content + " </span>"); 
  			        		}
			       			   
			       			 else if(type.equals("Relative Location")) {
	   			        			html = html.replace(content,  "<span class='site' title='Relative Location'> " + content + " </span>"); 
  			        		}
			       			 else if(type.equals("Specimen Identifier")) {
	   			        			html = html.replace(content,  "<span class='specimen' title='Specimen Identifier'> " + content + " </span>"); 
  			        		}

			     	       }

				     	   
				     	
			     	     htmlString = htmlString.replace("{{body}}", html);
			     	     
			     	     System.out.println(htmlString);
			     	     webView.getEngine().loadContent(htmlString);
			     			tab.setRowFactory( tv -> {
			       			    TableRow<Annotation> row = new TableRow<>();
			       			    row.setOnMouseClicked(event -> {
			       			        if (event.getClickCount() == 1 && (! row.isEmpty()) ) {
			       			        	Annotation rowData = row.getItem();
			       			            System.out.println(rowData.getStart());
			       			          Integer startInteger =  Integer.parseInt(rowData.getStart());
			       			          Integer endInteger = Integer.parseInt(rowData.getEnd());
			       			       
			       			       String sub = text.substring(startInteger, endInteger);
			       			   String preKeyWord = text.substring(0, startInteger-1); 		       			 
				       			  String keyText = sub;				       			  
				       			  String postKeyWord = text.substring(endInteger+1, text.length()-1);
				       			  String bodyText = "";				       			  
				       			  String htmlText =  "<html> <head> " + 			       			    
   					       			    "<style>" + 
   					       			    ".histology {color: red}" +
   					       			    ".site {color: blue}" +
   					       			    ".heading {color: green; background-color:yellow; display:block}" +
   					       			    ".behavior {color: purple}" +
   					       			    ".specimen {color: aqua}" +
   					       			    "</style>" +
   					       			    "</head>" + 
   					       			    "<body>{{body}}" +   					       			
   					       			    "</body>"+
   					       			    "</html>";
				       			  
			       			   if(rowData.getType().equals("Cancer Histology Subtype")) {
      			        			
      			        			bodyText = preKeyWord + "<span class='histology' title='Cancer Histology Subtype'> " + sub + " </span>" + postKeyWord; 
      			        			htmlText = htmlText.replace("{{body}}", bodyText);
      			        			
      			        			
      			        		}
			       			   
			       			   else if(rowData.getType().equals("Cancer Histology Type")) {
			       				   
     			        			bodyText = preKeyWord + "<span class='histology' title='Cancer Histology Type'> " + sub + " </span>" + postKeyWord; 
  			        			htmlText = htmlText.replace("{{body}}", bodyText); 			        			
     			        		
     			        		}
			       			   
			       			   
			       			 else if(rowData.getType().equals("Final Diagnosis Heading")) {
			       				 
   			        			bodyText = preKeyWord + "<span class='heading' title='Final Diagnosis Heading'> " + sub + " </span>" + postKeyWord; 
  			        			htmlText = htmlText.replace("{{body}}", bodyText); 			        			
  			        			
  			        		}
			       			   
			       			 else if(rowData.getType().equals("Macroscopic/Gross Description Heading")) {
	   			        			bodyText = preKeyWord + "<span class='heading' title='Macroscopic/Gross Description Heading'> " + sub + " </span>" + postKeyWord; 
  			        			htmlText = htmlText.replace("{{body}}", bodyText); 		
  			        		}
			       			   
			       			   
			       			 else if(rowData.getType().equals("Nature of Specimen Heading")) {
	   			        			bodyText = preKeyWord + "<span class='heading' title='Nature of Specimen Heading'> " + sub + " </span>" + postKeyWord; 
  			        			htmlText = htmlText.replace("{{body}}", bodyText); 		
  			        		}
			       			   
			       			 else if(rowData.getType().equals("Neoplasm Behaviour")) {
	   			        			bodyText = preKeyWord + "<span class='behavior' title='Neoplasm Behaviour'> " + sub + " </span>" + postKeyWord; 
  			        			htmlText = htmlText.replace("{{body}}", bodyText); 		
  			        		}
			       			   
			       			 else if(rowData.getType().equals("Organ/Body Structure")) {
	   			        			bodyText = preKeyWord + "<span class='site' title='Organ/Body Structure'> " + sub + " </span>" + postKeyWord; 
  			        			htmlText = htmlText.replace("{{body}}", bodyText); 		
  			        		}
			       			   
			       			 else if(rowData.getType().equals("Relative Location")) {
	   			        			bodyText = preKeyWord + "<span class='site' title='Relative Location'> " + sub + " </span>" + postKeyWord; 
  			        			htmlText = htmlText.replace("{{body}}", bodyText); 		
  			        		}
			       			 else if(rowData.getType().equals("Specimen Identifier")) {
	   			        			bodyText = preKeyWord + "<span class='specimen' title='Specimen Identifier'> " + sub + " </span>" + postKeyWord; 

  			        			htmlText = htmlText.replace("{{body}}", bodyText);
  			        		}
			       			   
			       		
					        		webView.getEngine().loadContent(htmlText);
					        		
					        	
			       			        }
			       			    });
			       			    return row ;
			       			});
			        		;
			        		layout.setBottom(tab);
			        		
						} 
		       			   catch (IOException | ParseException e) {
							System.out.println("Failed");
						}
			        
			        } 
			        
			    
			    }
			});
			GridPane grid = new GridPane();
		
		grid.add(list, 0, 0);
		Navigation nav= new Navigation(webView, "http://google.com", true);
		layout.setTop(nav);
		layout.setLeft(grid);
		Scene scene = new Scene(layout,300,250);
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}
	public String readFileContent(String fileName) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(fileName));
		
		try {
		    StringBuilder sb = new StringBuilder();
		    String line = br.readLine();
		    

		    while (line != null) {
		        sb.append(line);
		        sb.append(System.lineSeparator());
		        line = br.readLine();
		    }
		     return sb.toString();
		     
		} finally {
		    br.close();
		}
		
	}
	public String[] getFolderFiles(String Folder) {
		   File path = new File(Folder);
		   
		    File [] files = path.listFiles();
		    String[] fileNames = new String[files.length];
		    for(int i = 0; i<files.length;i++)
		    {
		    	fileNames[i]=files[i].getName();
		    }
		    return fileNames;
	}
	

}

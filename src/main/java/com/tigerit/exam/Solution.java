package com.tigerit.exam;


import static com.tigerit.exam.IO.*; 
import java.io.BufferedReader;
import java.util.*;
/**
 * All of your application logic should be placed inside this class.
 * Remember we will load your application from our custom container.
 * You may add private method inside this class but, make sure your
 * application's execution points start from inside run method.
 */
public class Solution implements Runnable {
    
    HashMap hashmapT = new HashMap(); //hashmapT = hashmap object for table
    HashMap hashmapQ = new HashMap(); //hashmapQ = hashmap object for querry
	HashMap hashmapC = new HashMap(); // 
	HashMap hashmapA = new HashMap(); // 
	HashMap hashmapR_1 = new HashMap();
	HashMap hashmapR_2 = new HashMap();
	ArrayList<String> list_tName = new ArrayList<String>();
	@Override
    public void run() {
		
		Integer T = readLineAsInteger(); // T = Test case number
        for(int c=1; c<=T; c++){ // c = counter for T 
        	printLine("Test" + ":" + " " + c);
        	Integer nT = readLineAsInteger(); // nT = number of table
        	for(int n=0; n<nT; n++){ // n = counter for nT
        		String tName = readLine(); // tName = table name
	        	list_tName.add(tName);
        		String nC_nD = readLine(); // nC_nD = column and row number
	            String Str_arr[] = nC_nD.split(" ");
	        	int nC = Integer.parseInt(Str_arr[0]); // nC = number of column
	        	int nD = Integer.parseInt(Str_arr[1]); // nD = number of row
	            
	        		String data = new String(readLine()) ; // cHead = Cloumn Heading
	        	for(int i=0; i< nD; i++){
	        		data = data + "-" + readLine(); // data = nC*nD Data
	            }
                hashmapT.put(tName,data);
        	}
            
            Integer nQ = readLineAsInteger(); // nQ = number of Query
            for(int n=1; n<=nQ; n++){
            	String query = readLine() + "-" + readLine() + "-" + readLine() + "-" + readLine();	
            	if(n!=3){
            		readLine();
            	}
            	hashmapQ.put(n,query);
            }
            // Hashmap iteration
            Set set = hashmapQ.entrySet();
            Iterator i = set.iterator();
            while(i.hasNext()) {
              Map.Entry me = (Map.Entry)i.next();
              String Str = me.getValue().toString();
              String Str_arr1[] = Str.split("-");
              String s = "SELECT *";
              if(Str_arr1[0].length() == s.length()){
            	  String q = Str_arr1[1].replaceAll("[^a-zA-Z0-9-/-/-_/-/.]", ":");
            	  String [] arr_q = q.split(":");
              	if(arr_q.length == 2){
              		Query1(hashmapQ.get(1),hashmapT);
              		printLine('\n');
              	}
              	else{
              		Query2(hashmapQ.get(2),hashmapT);
              		printLine('\n');
                  	}
              	}
              else{
            	Query3(hashmapQ.get(3),hashmapT);
            	printLine('\n');
              	
              }
            }
          }
    }
	
// Method for Query set 1
	private void Query1(Object str,HashMap hm){
		String q = new String(str.toString());
		String query = q.replaceAll("[^a-zA-Z0-9-/-/-_/-/.]", ":");
		String q_arr[] = new String[4];// q_arr[] = query line
        int i=0;
        for(String r: query.split("-")){
        q_arr[i] = r;
        i++;
        }
        
        for(int j=0; j< q_arr.length; j++){
    	    hashmapA.put(j+1,q_arr[j].split(":+"));	
        }
        
           String [] s_arr = (String[])hashmapA.get(2);
           String t_name1 = s_arr[1];  //t_name1 = table name 1
    	   
    	   s_arr = (String[])hashmapA.get(3);
    	   String t_name2 = s_arr[1];//t_name2 = table name 2
    	   
    	   s_arr = (String[])hashmapA.get(4);
    	   String [] tc_name1 = s_arr[1].split("\\.");//tc_name1 = table column 1
    	   String [] tc_name2 = s_arr[2].split("\\.");//tc_name2 = table column 2
    	   String data=null;
    	   String [] data_row1 = null;
    	   String [] heading1=null;
    	   int index_1=-1;
    	   String table_1="";
    	   if(tc_name1[0].equals(t_name1)){
    		   table_1 = t_name1;
    		   data = (hashmapT.get(t_name1)).toString();
    		   data_row1 = data.split("-");
    		   for(int r=0;r<data_row1.length;r++){
    			  data_row1[r] = data_row1[r].replaceAll("[^a-zA-Z0-9-/-/-_/-/.]",":");
    		   }
    		   heading1 = data_row1[0].split(":");
    		   for(int c=0;c<heading1.length;c++){
    			  System.out.print(heading1[c] + " ");
    			if(heading1[c].equals(tc_name1[1])){
    			  index_1 = c;
    			}
    		  }
   			}else{
    		   table_1 = t_name2;
    		   data = (hashmapT.get(t_name2)).toString();
    		   data_row1 = data.split("-");
     		   for(int r=0;r<data_row1.length;r++){
     			data_row1[r] = data_row1[r].replaceAll("[^a-zA-Z0-9-/-/-_/-/.]",":");
     		   }
        		  heading1 = data_row1[0].split(":");
        		  for(int c=0;c<heading1.length;c++){
       			  System.out.print(heading1[c] + " ");     			  
     			  if(heading1[c].equals(tc_name1[1])){
     				  index_1 = c;
         			}
     		  }
        	 }
    	   // end of first if-else
    	   
    	   String [] data_row2 = null;
    	   String [] heading2=null;
    	   int index_2=-1;
    	   String table_2="";
    	   if(tc_name2[0].equals(t_name1)){
    		   table_2 = t_name1;
    		   data = (hashmapT.get(t_name1)).toString();
    		   data_row2 = data.split("-");
    		   for(int r=0;r<data_row2.length;r++){
    			  data_row2[r] = data_row2[r].replaceAll("[^a-zA-Z0-9-/-/-_/-/.]",":");
    		   }
    		   heading2 = data_row2[0].split(":");
    		   for(int c=0;c<heading2.length;c++){
    				  System.out.print(heading2[c] + " ");
    				  if(heading2[c].equals(tc_name2[1])){
    			  index_2 = c;
    			}
            		  System.out.println();
    		  }
    	   }else{
    		   table_2 = t_name2;
    		   data = (hashmapT.get(t_name2)).toString();
    		   data_row2 = data.split("-");
     		   for(int r=0;r<data_row2.length;r++){
     			data_row2[r] = data_row2[r].replaceAll("[^a-zA-Z0-9-/-/-_/-/.]",":");
     		   }
        		  heading2 = data_row2[0].split(":");
        		  for(int c=0;c<heading2.length;c++){
        			  System.out.print(heading2[c] + " ");  
     			  if(heading2[c].equals(tc_name2[1])){
     				  index_2 = c;
         			}
     		  }
        		  System.out.println();
    	   }
    	   // end of 2nd if-else
    	   HashMap hashmapR_1 = new HashMap();
    	   String [] str_1 = null;
    	   for(int n=1; n<=data_row1.length-1; n++){
    		   str_1 = data_row1[n].split(":");
    		   for(int x=0;x<str_1.length;x++){
    		   }
    		   hashmapR_1.put(n,str_1);
    	   }
    	   
    	   HashMap hashmapR_2 = new HashMap();
    	   String [] str_2 = null;
    	   for(int n=1; n<=data_row2.length-1; n++){
    		   str_2 = data_row2[n].split(":");
    		   for(int x=0;x<str_2.length;x++){
    		   }
    		   hashmapR_2.put(n,str_2);
    	   }
    	   String [] arr_1 = null;
    	   String [] arr_2 = null;
    	   for(int z=1; z<=hashmapR_1.size()&&z<=hashmapR_2.size(); z++){
    		   arr_1 = (String[])hashmapR_1.get(z);
    		   arr_2 = (String[])hashmapR_2.get(z);
    		   if((arr_1[0].equals(arr_2[0]))&&(arr_1[index_1].equals(arr_2[index_2]))){
    			   List<String> list = new ArrayList<String>(Arrays.asList(arr_1));
    			    list.addAll(Arrays.asList(arr_2));
    			    Object [] c = list.toArray();
    			    for(int f=0; f<c.length; f++){
    			        System.out.print(c[f] + " ");
    			        }
    			    System.out.println();
    		   }
    	   }
    }

	
	// Method for Query Set 2
	private void Query2(Object str,HashMap hm){
		String q = new String(str.toString());
		String query = q.replaceAll("[^a-zA-Z0-9-/-/-_/-/.]", ":");
		String q_arr[] = new String[4];// q_arr[] = query line
        int i=0;
        for(String r: query.split("-")){
        q_arr[i] = r;
        i++;
        }
        
        for(int j=0; j< q_arr.length; j++){
    	    hashmapA.put(j+1,q_arr[j].split(":+"));	
        }
    
           String [] s_arr = (String[])hashmapA.get(2);
           String t_name1 = s_arr[1];  //t_name1 = table name 1
    	   String s_name1 = s_arr[2]; //s_name1 = table short name 1
    	   
    	   s_arr = (String[])hashmapA.get(3);
    	   String t_name2 = s_arr[1];//t_name2 = table name 2
    	   String s_name2 = s_arr[2];//s_name2 = table short name 2
    	   
    	   s_arr = (String[])hashmapA.get(4);
    	   String [] tc_name1 = s_arr[1].split("\\.");//tc_name1 = table column 1
    	   String [] tc_name2 = s_arr[2].split("\\.");//tc_name2 = table column 2
           
    	   String data=null;
    	   String [] data_row1 = null;
    	   String [] heading1=null;
    	   int index_1=-1;
    	   String table_1="";
    	   if(tc_name1[0].equals(s_name1)){
    		   table_1 = t_name1;
    		   data = (hashmapT.get(t_name1)).toString();
    		   data_row1 = data.split("-");
    		   for(int r=0;r<data_row1.length;r++){
    			  data_row1[r] = data_row1[r].replaceAll("[^a-zA-Z0-9-/-/-_/-/.]",":");
    		   }
    		   heading1 = data_row1[0].split(":");
    		   for(int c=0;c<heading1.length;c++){
    			  System.out.print(heading1[c] + " ");
    			if(heading1[c].equals(tc_name1[1])){
    			  index_1 = c;
    			}
    		  }
   			}else{
    		   table_1 = t_name2;
    		   data = (hashmapT.get(t_name2)).toString();
    		   data_row1 = data.split("-");
     		   for(int r=0;r<data_row1.length;r++){
     			data_row1[r] = data_row1[r].replaceAll("[^a-zA-Z0-9-/-/-_/-/.]",":");
     		   }
        		  heading1 = data_row1[0].split(":");
        		  for(int c=0;c<heading1.length;c++){
       			  System.out.print(heading1[c] + " ");     			  
     			  if(heading1[c].equals(tc_name1[1])){
     				  index_1 = c;
         			}
     		  }
        	 }
    	   // end of first if-else
    	   
    	   String [] data_row2 = null;
    	   String [] heading2=null;
    	   int index_2=-1;
    	   String table_2="";
    	   if(tc_name2[0].equals(s_name1)){
    		   table_2 = t_name1;
    		   data = (hashmapT.get(t_name1)).toString();
    		   data_row2 = data.split("-");
    		   for(int r=0;r<data_row2.length;r++){
    			  data_row2[r] = data_row2[r].replaceAll("[^a-zA-Z0-9-/-/-_/-/.]",":");
    		   }
    		   heading2 = data_row2[0].split(":");
    		   for(int c=0;c<heading2.length;c++){
    				  System.out.print(heading2[c] + " ");
    				  if(heading2[c].equals(tc_name2[1])){
    			  index_2 = c;
    			}
            		  System.out.println();
    		  }
    	   }else{
    		   table_2 = t_name2;
    		   data = (hashmapT.get(t_name2)).toString();
    		   data_row2 = data.split("-");
     		   for(int r=0;r<data_row2.length;r++){
     			data_row2[r] = data_row2[r].replaceAll("[^a-zA-Z0-9-/-/-_/-/.]",":");
     		   }
        		  heading2 = data_row2[0].split(":");
        		  for(int c=0;c<heading2.length;c++){
        			  System.out.print(heading2[c] + " ");  
     			  if(heading2[c].equals(tc_name2[1])){
     				  index_2 = c;
         			}
     		  }
        		  System.out.println();
    	   }
    	   // end of 2nd if-else
    	   HashMap hashmapR_1 = new HashMap();
    	   String [] str_1 = null;
    	   for(int n=0; n<data_row1.length; n++){
    		   str_1 = data_row1[n].split(":");
    		   hashmapR_1.put(n,str_1);
    	   }
    	   
    	   HashMap hashmapR_2 = new HashMap();
    	   String [] str_2 = null;
    	   for(int n=0; n<data_row2.length; n++){
    		   str_2 = data_row2[n].split(":");
    		   hashmapR_2.put(n,str_2);
    	   }
    	   
    	   String [] arr_1 = null;
    	   String [] arr_2 = null;
    	   for(int z=1; z<hashmapR_1.size()&&z<hashmapR_2.size(); z++){
    		   arr_1 = (String[])hashmapR_1.get(z);
    		   arr_2 = (String[])hashmapR_2.get(z);
    		   if((arr_1[0].equals(arr_2[0]))&&(arr_1[index_1].equals(arr_2[index_2]))){
    			   List<String> list = new ArrayList<String>(Arrays.asList(arr_1));
    			    list.addAll(Arrays.asList(arr_2));
    			    Object [] c = list.toArray();
    			    for(int f=0; f<c.length; f++){
    			        System.out.print(c[f] + " ");
    			        }
    			    System.out.println();
    		   }
    	   }
    }

	// Method for Query Set 3
	private void Query3(Object str,HashMap hm){
		String q = new String(str.toString());
		String query = q.replaceAll("[^a-zA-Z0-9-/-/-_/-/.]", ":");
		String q_arr[] = new String[4];// q_arr[] = query line
        int i=0;
        for(String r: query.split("-")){
        q_arr[i] = r;
        i++;
        }
        for(int j=0; j< q_arr.length; j++){
    	    hashmapA.put(j+1,q_arr[j].split(":+"));	
        }
        String data = null;
        String [] s_arr = (String[])hashmapA.get(1);
        String [] tc_head = null;
        HashMap hashmapH = new HashMap();
        HashMap hashmapX = new HashMap();
        for(int u=1; u<s_arr.length; u++){
        	tc_head = s_arr[u].split("\\.");
            hashmapH.put(u,tc_head);
        }
           s_arr = (String[])hashmapA.get(2);
           String t_name1 = s_arr[1];  //t_name1 = table name 1
    	   String s_name1 = s_arr[2]; //s_name1 = table short name 1
    	   
    	   s_arr = (String[])hashmapA.get(3);
    	   String t_name2 = s_arr[1];//t_name2 = table name 2
    	   String s_name2 = s_arr[2];//s_name2 = table short name 2
    	   
    	   s_arr = (String[])hashmapA.get(4);
    	   String [] tc_name1 = s_arr[1].split("\\.");//tc_name1 = table column 1
    	   String [] tc_name2 = s_arr[2].split("\\.");//tc_name2 = table column 2
           String data_row []=null;
           String [] heading=null;

           for(int g=1; g<=hashmapH.size(); g++){
        	   ArrayList<String> array = new ArrayList<String>(3);
        	   String [] tc_h = (String [])hashmapH.get(g); // array afier spliting with \\.
           	if(tc_h[0].equals(s_name1)){ 
           		tc_h[0] = t_name1;
           		array.add(t_name1);
           		data = (hashmapT.get(t_name1)).toString();
           		data_row = data.split("-");
           		for(int r=0;r<data_row.length;r++){
           			data_row[r] = data_row[r].replaceAll("[^a-zA-Z0-9-/-/-_/-/.]",":");
           		}
           		heading = data_row[0].split(":");
           		for(int c=0;c<heading.length;c++){
           			if(heading[c].equals(tc_h[1])){
           				tc_h[1] = Integer.toString(c);
           				array.add(heading[c]);
           				array.add(tc_h[1]);
           			}
           		}
           	hashmapX.put(g,array);
           	
           	}else{
           		tc_h[0] = t_name2;
           		array.add(t_name2);
           		data = (hashmapT.get(t_name2)).toString();
           		data_row = data.split("-");
           		for(int r=0;r<data_row.length;r++){
           			data_row[r] = data_row[r].replaceAll("[^a-zA-Z0-9-/-/-_/-/.]",":");
           		}
           		heading = data_row[0].split(":");
           		for(int c=0;c<heading.length;c++){
           			if(heading[c].equals(tc_h[1])){
           				tc_h[1] = Integer.toString(c);
           				array.add(heading[c]);
           				array.add(tc_h[1]);
           			}
           		} 
           		hashmapX.put(g,array);
           	}
           }
           
    	  HashMap hashmapY = new HashMap();
           
    	   String [] data_row1 = null;
    	   String [] heading1=null;
    	   String [] tc_h = null;
    	   int index_1 = -1;
    	   int index_h_1 = -1;
    	   String table_1="";
    	   ArrayList<String> tc_H = null;
    	   
    	   if(tc_name1[0].equals(s_name1)){
    		   table_1 = t_name1;
    		   data = (hashmapT.get(t_name1)).toString();
    		   data_row1 = data.split("-");
    		   
    		   for(int r=0;r<data_row1.length;r++){
    			  data_row1[r] = data_row1[r].replaceAll("[^a-zA-Z0-9-/-/-_/-/.]",":");
    		   }
    		   
    		   heading1 = data_row1[0].split(":");
    		       		   
    		   for(int h=1; h<hashmapX.size(); h++){
    			    tc_H = (ArrayList<String>)hashmapX.get(h);
    			   if((tc_H.get(0)).equals(t_name1)){
    				   index_h_1 = Integer.parseInt(tc_H.get(2));
    			   }
    			   for(int c=0;c<heading1.length;c++){
    	    			  
        			   if(c == index_h_1){
        				  System.out.print(heading1[c] + " ");
        			  }
        			   if(heading1[c].equals(tc_name1[1])){
        	    			  index_1 = c;
        	    			}
        			   }
    		   }
    		   String [] str_1 = null;
           	   for(int n=0; n<data_row1.length; n++){
           		   str_1 = data_row1[n].split(":");
           		   hashmapR_1.put(n,str_1);
           	   }
           	   hashmapY.put(t_name1,hashmapR_1);
           	   }
    	   else{
    		   table_1 = t_name2;
    		   data = (hashmapT.get(t_name2)).toString();
    		   data_row1 = data.split("-");
     		   for(int r=0;r<data_row1.length;r++){
     			data_row1[r] = data_row1[r].replaceAll("[^a-zA-Z0-9-/-/-_/-/.]",":");
     		   }
        		  heading1 = data_row1[0].split(":");
        		  for(int h=1; h<hashmapX.size(); h++){
      			    tc_H = (ArrayList<String>)hashmapX.get(h);
      			   if((tc_H.get(0)).equals(t_name2)){
      				   index_h_1 = Integer.parseInt(tc_H.get(2));
      			   }
      			   for(int c=0;c<heading1.length;c++){
      	    			  
          			   if(c == index_h_1){
          				  System.out.print(heading1[c] + " ");
          			  }
          			   if(heading1[c].equals(tc_name2[1])){
          	    			  index_1 = c;
          	    			}
          			   }
      		    }
        	   String [] str_1 = null;
           	   for(int n=0; n<data_row1.length; n++){
           		   str_1 = data_row1[n].split(":");
           		   for(int x=0;x<str_1.length;x++){
       		   }
           		   hashmapR_1.put(n,str_1);
           	   }
           	   
           	   	hashmapY.put(t_name1,hashmapR_1);
    	   }
    	   // end of first if-else
    	   
    	   String [] data_row2 = null;
    	   String [] heading2=null;
    	   int index_2=-1;
    	   int index_h_2 = -1;
    	   String table_2="";
    	   
    	   if(tc_name2[0].equals(s_name1)){
    		   table_2 = t_name1;
    		   data = (hashmapT.get(t_name1)).toString();
    		   data_row2 = data.split("-");
    		   for(int r=0;r<data_row2.length;r++){
    			  data_row2[r] = data_row2[r].replaceAll("[^a-zA-Z0-9-/-/-_/-/.]",":");
    		   }
    		   heading2 = data_row2[0].split(":");
    		   
    		   for(int h=1; h<=hashmapX.size(); h++){
   			    tc_H = (ArrayList<String>)hashmapX.get(h);
   			   if((tc_H.get(0)).equals(t_name1)){
   				   index_h_2 = Integer.parseInt(tc_H.get(2));
   			   }
   			   for(int c=0;c<heading2.length;c++){
   	    			  
       			   if(c == index_h_2){
       				  System.out.print(heading2[c] + " ");
       			  }
       			   if(heading2[c].equals(tc_name2[1])){
       	    			  index_2 = c;
       	    			}
       			   }
   		   }
        	   String [] str_2 = null;
        	   for(int n=0; n<data_row2.length; n++){
        		   str_2 = data_row2[n].split(":");
        		   for(int x=0;x<str_2.length;x++){
        		   }
        		   hashmapR_2.put(n,str_2);
        	   }
        	      hashmapY.put(t_name2,hashmapR_2);   
    	   }else{
    		   table_2 = t_name2;
    		   data = (hashmapT.get(t_name2)).toString();
    		   data_row2 = data.split("-");
     		   for(int r=0;r<data_row2.length;r++){
     			data_row2[r] = data_row2[r].replaceAll("[^a-zA-Z0-9-/-/-_/-/.]",":");
     		   }
        		  heading2 = data_row2[0].split(":");
        		  
        		  for(int h=1; h<=hashmapX.size(); h++){
        			    tc_H = (ArrayList<String>)hashmapX.get(h);
        			   if((tc_H.get(0)).equals(t_name2)){
        				   index_h_2 = Integer.parseInt(tc_H.get(2));
        			   }
        			   for(int c=0;c<heading2.length;c++){
            			   if(c == index_h_2){
            				  System.out.print(heading2[c] + " ");
            			  }
            			   if(heading2[c].equals(tc_name2[1])){
            	    			  index_2 = c;
            	    			}
            			   }
        		    }
        		  System.out.println();
        		
           	   String [] str_2 = null;
           	   for(int n=0; n<data_row2.length; n++){
           		   str_2 = data_row2[n].split(":");
           		   for(int x=0;x<str_2.length;x++){
           		   }
           		   hashmapR_2.put(n,str_2);
           	   }
           	   hashmapY.put(t_name2,hashmapR_2);
        	    	
    	   }
    	   // end of 2nd if-else
    	  
    	   String [] arr_1 = null;
    	   String [] arr_2 = null;
    	   String [] arr = null;
    	   ArrayList<String> ArrList_1 = null;
    	   ArrayList<String> ArrList_2 = null;
    	   ArrayList<String> ArrList = null;
    	   ArrayList<String> Arr_List = new ArrayList<String>();
     	   String doc = null;
     	   String [] doc_arr = null;
    	   HashMap hash = new HashMap();
    	   int z1=0;
    	   for(int z=1; z<hashmapR_1.size()&&z<hashmapR_2.size(); z++){
    		   arr_1 = (String[])hashmapR_1.get(z);
    		   arr_2 = (String[])hashmapR_2.get(z);
    		   if((arr_1[0].equals(arr_2[0]))&&(arr_1[index_1].equals(arr_2[index_2]))){
    			    z1++;
    	    		ArrList = (ArrayList<String>)hashmapX.get(z1);
    	    		hash = (HashMap)hashmapY.get(ArrList.get(0));
    				for(int t=1; t<hash.size(); t++){
    	    		arr = (String[])hash.get(t);
    	    		int r = Integer.parseInt(ArrList.get(2));
    	    		int R = Integer.parseInt(arr[r]);
   		    		Arr_List.add(Integer.toString(R));
    				}
    			   }
    			}
    	    for(int f=0; f<Arr_List.size(); f++){
		        System.out.print(Arr_List.get(f) + " ");
		        if(f==2){
		        	System.out.println();
		        }
    	    }
		    System.out.println();
	   
    	   }
    	   
    
	
}

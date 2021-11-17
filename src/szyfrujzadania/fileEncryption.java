/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package szyfrujzadania;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.RandomAccessFile;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Arek
 */
public class fileEncryption {
    
    public List <MsgTemp> tasks;
    
    public fileEncryption()
    {
        tasks = new ArrayList<>();
    }
    
    public void encryptFile(String path, String key) throws FileNotFoundException, IOException
    {
        
                String line=null;
                String title = null;
                String querry = null;
                String recipients = null;
                String time = null;
                String repeat = null;
                String outputType = null;
       try{
            tasks.clear();
                
                FileInputStream fis = new FileInputStream(path);
                //FileInputStream fis = new FileInputStream(filename);
                BufferedReader in = new BufferedReader(new InputStreamReader(fis));
                
                while((line = in.readLine())!= null)
                {
                    
                    title = in.readLine();
                    querry = in.readLine();
                    recipients = in.readLine();
                    time = in.readLine();
                    repeat = in.readLine();
                    outputType = in.readLine();
                    
                    addTask(title,recipients.split(","),querry,time,repeat,outputType);
                }
               /* 
                title = AES.encrypt(title, key);
                querry = AES.encrypt(querry, key);
                recipients = AES.encrypt(recipients, key);
                time = AES.encrypt(time, key);
                repeat = AES.encrypt(repeat, key);
                outputType = AES.encrypt(outputType, key);
                
                System.out.println(line);
                System.out.println(title);
                System.out.println(querry);
                System.out.println(recipients);

                System.out.println(time);
                System.out.println(repeat);
                System.out.println(outputType);*/
    
    FileOutputStream fos = new FileOutputStream(path);
 
	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
        
        for(MsgTemp task: tasks)
        {  
                System.out.println(task.getTitle());
                System.out.println(task.getQuerry());
                System.out.println(task.getRecipients());
                System.out.println(task.getRep());

                System.out.println(task.getTime());
                System.out.println(task.outputType);
            title = AES.encrypt(task.getTitle(), key);
            String odbiorcy= new String();
            for(String recipient: task.getRecipients())
            {
                odbiorcy += recipient+",";
            }
            querry = AES.encrypt(task.getQuerry(), key);
            recipients = AES.encrypt(odbiorcy, key);
            int czas [] = task.getTime();
            time = AES.encrypt(czas[0]+":"+czas[1], key);
            repeat = AES.encrypt(String.valueOf(task.getRep()), key);
            outputType = AES.encrypt(task.outputType, key);
            
 
                bw.write("#");
		bw.newLine();
		bw.write(title);
		bw.newLine();
                bw.write(querry);
		bw.newLine();
                bw.write(recipients);
		bw.newLine();
                bw.write(time);
		bw.newLine();
                bw.write(repeat);
		bw.newLine();
                bw.write(outputType);
		bw.newLine();
	
        }       
	bw.close();
                
               
               
                
        }
        catch(FileNotFoundException e)
        {
            e.printStackTrace();
        } catch (IOException ex) {
            Logger.getLogger(Reader.class.getName()).log(Level.SEVERE, null, ex);
        }
              
    
    }
    
    
    public void encryptDB(String path, String key)
    {
                String type=null;
                String conn = null;
                String user = null;
                String pass = null;
                String email = null;
                String emailPass = null;
                String smtp = null;
                
                try{
                    
                    
                FileInputStream fis = new FileInputStream(path);
                    //FileInputStream fis = new FileInputStream(filename);
                    BufferedReader in = new BufferedReader(new InputStreamReader(fis));;
                    
                    

                        type = AES.encrypt(in.readLine(),key);//in.readLine();
                        conn = AES.encrypt(in.readLine(),key);//in.readLine();
                        //recipients = in.readLine();
                       // db = AES.encrypt(in.readLine(),key);
                        
                        user = AES.encrypt(in.readLine(),key);

                        pass = AES.encrypt(in.readLine(),key);//in.readLine();
                        
                        email = AES.encrypt(in.readLine(),key);
                        emailPass = AES.encrypt(in.readLine(),key);
                        smtp = AES.encrypt(in.readLine(),key);


                    
                    
                    
                    FileOutputStream fos = new FileOutputStream(path);
 
                    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
                    
                    
                    bw.write(type);
                    bw.newLine();
                    bw.write(conn);
                    bw.newLine();
                    bw.write(user);
                    bw.newLine();
                    bw.write(pass);
                    bw.newLine();
                    bw.write(email);
                    bw.newLine();
                    bw.write(emailPass);
                    bw.newLine();
                    bw.write(smtp);
                
                    bw.close();
                
                }catch(FileNotFoundException e)
                {
                    e.printStackTrace();
                } catch (IOException ex) {
                    Logger.getLogger(Reader.class.getName()).log(Level.SEVERE, null, ex);
                }
    
    
    }
    
    
    public void decryptDB(String path, String key)
    {
                String type=null;
                String conn = null;
                String user = null;
                String pass = null;
                String email = null;
                String emailPass = null;
                String smtp = null;
                
                try{
                    
                    
                FileInputStream fis = new FileInputStream(path);
                //FileInputStream fis = new FileInputStream(filename);
                BufferedReader in = new BufferedReader(new InputStreamReader(fis));;
                    
                        type = AES.decrypt(in.readLine(),key);//in.readLine();
                        conn = AES.decrypt(in.readLine(),key);//in.readLine();
                        //recipients = in.readLine();
                       // db = AES.encrypt(in.readLine(),key);
                        
                        user = AES.decrypt(in.readLine(),key);

                        pass = AES.decrypt(in.readLine(),key);//in.readLine();
                        
                        email = AES.decrypt(in.readLine(),key);
                        emailPass = AES.decrypt(in.readLine(),key);
                        smtp = AES.decrypt(in.readLine(),key);
                    
                    
                    FileOutputStream fos = new FileOutputStream(path);
 
                    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
                    
                    
                    bw.write(type);
                    bw.newLine();
                    bw.write(conn);
                    bw.newLine();
                    bw.write(user);
                    bw.newLine();
                    bw.write(pass);
                    bw.newLine();
                    bw.write(email);
                    bw.newLine();
                    bw.write(emailPass);
                    bw.newLine();
                    bw.write(smtp);
                
                    bw.close();
                
                }catch(FileNotFoundException e)
                {
                    e.printStackTrace();
                } catch (IOException ex) {
                    Logger.getLogger(Reader.class.getName()).log(Level.SEVERE, null, ex);
                }
    
    
    }
    
    public void decryptFile(String path, String key)
    {
                String line=null;
                String title = null;
                String querry = null;
                String recipients = null;
                String time = null;
                String repeat = null;
                String outputType = null;
       try{
                tasks.clear();
                 
                FileInputStream fis = new FileInputStream(path);
                //FileInputStream fis = new FileInputStream(filename);
                BufferedReader in = new BufferedReader(new InputStreamReader(fis));
                
                while((line = in.readLine())!= null)
                {
                    
                    title = AES.decrypt(in.readLine(),key);//in.readLine();
                    System.out.println(title);
                    querry = AES.decrypt(in.readLine(),key);//in.readLine();
                    System.out.println(querry);
                    //recipients = in.readLine();
                    recipients = AES.decrypt(in.readLine(),key);
                    System.out.println(recipients);
                            
                    time = AES.decrypt(in.readLine(),key);//in.readLine();
                    repeat = AES.decrypt(in.readLine(),key);//in.readLine();
                    outputType = AES.decrypt(in.readLine(),key);//in.readLine();
                    
                    System.out.println(line);
                System.out.println(title);
                System.out.println(querry);
                System.out.println(recipients);

                System.out.println(time);
                System.out.println(repeat);
                System.out.println(outputType);
                    
                    addTask(title,recipients.split(","),querry,time,repeat,outputType);
                }
                
               /* title = AES.decrypt(title, key);
                querry = AES.decrypt(querry, key);
                recipients = AES.decrypt(recipients, key);
                time = AES.decrypt(time, key);
                repeat = AES.decrypt(repeat, key);
                outputType = AES.decrypt(outputType, key);
                
                System.out.println(line);
                System.out.println(title);
                System.out.println(querry);
                System.out.println(recipients);

                System.out.println(time);
                System.out.println(repeat);
                System.out.println(outputType);*/
    
    FileOutputStream fos = new FileOutputStream(path);
 
	BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
        
        for(MsgTemp task: tasks)
        {
            /*title = AES.decrypt(task.getTitle(), key);
            String odbiorcy= new String();
            for(String recipient: task.getRecipients())
            {
                odbiorcy += recipient+",";
            }
            querry = AES.decrypt(task.getQuerry(), key);
            recipients = AES.decrypt(odbiorcy, key);
            int czas [] = task.getTime();
            time = AES.decrypt(czas[0]+":"+czas[1], key);
            repeat = AES.decrypt(String.valueOf(task.getRep()), key);
            outputType = AES.decrypt(task.outputType, key);*/
            
            
        
 
                bw.write("#");
		bw.newLine();
		bw.write(task.getTitle());
		bw.newLine();
                bw.write(task.getQuerry());
		bw.newLine();
                String odbiorcy= new String();
                for(String recipient: task.getRecipients())
            {
                odbiorcy += recipient+",";
            }
                bw.write(odbiorcy);
		bw.newLine();
                int czas [] = task.getTime();
                bw.write(czas[0]+":"+czas[1]);
		bw.newLine();
                bw.write(String.valueOf(task.getRep()));
		bw.newLine();
                bw.write(task.outputType);
		bw.newLine();
	
        }    
	bw.close();
                
               
               
                
        }
        catch(FileNotFoundException e)
        {
            e.printStackTrace();
        } catch (IOException ex) {
            Logger.getLogger(Reader.class.getName()).log(Level.SEVERE, null, ex);
        }
    
    }
    
    
    public void addTask(String t, String [] rec, String q, String tm,String rep,String ot)
    {
        tasks.add(new MsgTemp(t, rec, q, tm,rep,ot));
    }
 }
    


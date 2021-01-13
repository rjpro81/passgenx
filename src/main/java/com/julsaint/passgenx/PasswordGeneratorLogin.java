/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.julsaint.passgenx;

import java.io.*;
/**
 *
 * @author Ralph Julsaint
 * This class is used to create an object that will be used to access login data
 */
class PasswordGeneratorLogin {
    static long UserId;
    String UserName;
    String UserPassword;
    static final String LOGIN_FILE = "passgenx.xml";
    static final String XML_HEADER = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n";
    
    PasswordGeneratorLogin(){
        UserId++;
    }
  
    int CreateLogin(String UserName, String UserPassword){
        try(PrintWriter Writer = new PrintWriter(new BufferedWriter(new FileWriter(LOGIN_FILE, true)))){
            Writer.write(XML_HEADER); 
            Writer.write("<User>\n");
            Writer.printf("\t<UserId>%d</UserId>\n", UserId);
            Writer.printf("\t<UserName>%s</UserName>\n", UserName);
            Writer.printf("\t<UserPassword>%s</UserPassword>\n", UserPassword);
            Writer.write("</User>");
        }
        catch(IOException e){
            System.err.printf("IOException: %s", e.getMessage());
            return 1;
        }
        return 0;
    }
}

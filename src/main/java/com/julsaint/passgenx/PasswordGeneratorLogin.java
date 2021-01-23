/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.julsaint.passgenx;

import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.*;
import org.xml.sax.SAXException;
import java.security.SecureRandom;
/**
 *
 * @author Ralph Julsaint
 * This class is used to create an object that will be used to access login data
 */
class PasswordGeneratorLogin {
    static long UserId;
    static final String LOGIN_FILE = "masterpasswords.xml";
    static final String XML_HEADER = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n";
    
    PasswordGeneratorLogin(){
        SecureRandom Generator = new SecureRandom();
        int RandomNum = 111111 + Generator.nextInt(999999);
        UserId = RandomNum;
    }
    /**
     * Method used to create a user login for the application
     * @param UserName
     * @param UserPassword
     * @return return status code
     */
    int CreateNewUser(String UserPassword){
        try{
            String NewMasterPasswordXmlString = "";
            NewMasterPasswordXmlString = NewMasterPasswordXmlString.concat(PasswordGeneratorLogin.XML_HEADER);
            NewMasterPasswordXmlString = NewMasterPasswordXmlString.concat("<Users>\n");
            String PasswordsXmlStr = String.format("\t<User>\n\t\t<UserId>%s</UserId>\n\t\t<UserPassword>%s</UserPassword>\n\t</User>\n", UserId, UserPassword);
            NewMasterPasswordXmlString = NewMasterPasswordXmlString.concat(PasswordsXmlStr);
            NewMasterPasswordXmlString = NewMasterPasswordXmlString.concat(BuildNewUserXmlString());
            NewMasterPasswordXmlString = NewMasterPasswordXmlString.concat("</Users>");
            PrintWriter Writer = new PrintWriter(new BufferedWriter(new FileWriter(LOGIN_FILE, false)));
            Writer.printf(NewMasterPasswordXmlString);
            Writer.close();
        }
        catch(IOException e){
            System.err.printf("IOException: %s", e.getMessage());
            return 1;
        }
        return 0;
    }
    
    String BuildNewUserXmlString(){
        String UserIdent;
        String UserPassword;
        StringBuilder MasterPasswordsXmlStr = new StringBuilder();
        try{
            File InputFile = new File(LOGIN_FILE);
            DocumentBuilderFactory DocumentFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder DocumentBuilder = DocumentFactory.newDocumentBuilder();
            Document Doc = DocumentBuilder.parse(InputFile);
            Doc.getDocumentElement().normalize();
            NodeList NList = Doc.getElementsByTagName("User");
            for(int it = 0; it < NList.getLength(); it++){
                Node nNode = NList.item(it);
                if(nNode.getNodeType() == Node.ELEMENT_NODE){
                    Element eElement = (Element) nNode;
                    UserIdent = eElement.getElementsByTagName("UserId").item(0).getTextContent();
                    UserPassword = eElement.getElementsByTagName("UserPassword").item(0).getTextContent();
                    MasterPasswordsXmlStr.append(String.format("\t<User>\n\t\t<UserId>%s</UserId>\n\t\t<UserPassword>%s</UserPassword>\n\t</User>\n", UserIdent, UserPassword));
                }               
            } 
        }
        catch(IOException | ParserConfigurationException | SAXException e){
            System.err.printf("IOException: %s", e.getMessage());
        }
        return MasterPasswordsXmlStr.toString();
    }
    
    boolean GetUserCredentials(String UserPasswordInput){
        try{
            String userPassword = "";
            File InputFile = new File(LOGIN_FILE);
            DocumentBuilderFactory DocumentFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder DocumentBuilder = DocumentFactory.newDocumentBuilder();
            Document Doc = DocumentBuilder.parse(InputFile);
            Doc.getDocumentElement().normalize();
            NodeList NList = Doc.getElementsByTagName("User");           
            for(int it = 0; it < NList.getLength(); it++){
                Node nNode = NList.item(it);
                if(nNode.getNodeType() == Node.ELEMENT_NODE){
                    Element eElement = (Element) nNode;
                    userPassword = eElement.getElementsByTagName("UserPassword").item(0).getTextContent();                    
                }
                if(userPassword.equals(UserPasswordInput)){
                    return true;
                }
            }          
        }
        catch(IOException | ParserConfigurationException | SAXException e){
            System.err.printf("IOException: %s", e.getMessage());
            return false;
        }
        return false;
    }
}

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.julsaint.passgenx;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.SecureRandom;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
/**
 *
 * @author Ralph Julsaint
 */
class PasswordGenerator {
    private int length;//password length
    static StringBuilder PasswordsString = new StringBuilder();
    static int PasswordId;
    private static final String PASSWORDS_FILE = "passwords.xml";//passwords file
    private final char[] PasswordCharArray = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 
    'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't',
    'u', 'v', 'w', 'x', 'y', 'z', '1', '2', '3', '4', '5', '6',
    '7', '8', '9', '0', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H',
    'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T',
    'U', 'V', 'W', 'X', 'Y', 'Z', '~', '!', '@', '#', '$', '%',
    '^', '&', '*', '(', ')', '-', '+', '=', '_', '{', '}', '[',
    ']', '|', ':', ';', '/', '<', '>', ',', '.'};//character types used for password generation       
    
    String GeneratePassword(int length){
        SecureRandom generator = new SecureRandom();
        String GeneratedPassword = "";
        for(int it = 0; it < length; it++){
            int RandomNum = generator.nextInt(PasswordCharArray.length);
            GeneratedPassword += PasswordCharArray[RandomNum];  
        }
        return GeneratedPassword;
    }
    
    boolean SavePassword(int PasswordId, String Name, String Password){
        try(PrintWriter Writer = new PrintWriter(new BufferedWriter(new FileWriter(PASSWORDS_FILE, true)))){
            Writer.printf("<GeneratedPassword>\n");
            Writer.printf("\t<PasswordId>%d</PasswordId>\n", PasswordId);
            Writer.printf("\t<Name>%s</Name>\n", Name);
            Writer.printf("\t<Password>%s</Password>\n", Password);
            Writer.printf("</GeneratedPassword>\n");
        }
        catch(IOException e){
            System.err.printf("IOException: %s", e.getMessage());
            return false;
        }
        return true;
    }
    
    String GetPasswords(){
        String PasswordsId;
        String Name;
        String Password;
        String PasswordLineStr = "";
        try{
            File InputFile = new File(PASSWORDS_FILE);
            DocumentBuilderFactory DocumentFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder DocumentBuilder = DocumentFactory.newDocumentBuilder();
            Document Doc = DocumentBuilder.parse(InputFile);
            Doc.getDocumentElement().normalize();
            NodeList NList = Doc.getElementsByTagName("GeneratedPassword");
            
            for(int it = 0; it < NList.getLength(); it++){
                Node nNode = NList.item(it);
                if(nNode.getNodeType() == Node.ELEMENT_NODE){
                    Element eElement = (Element) nNode;
                    PasswordsId = eElement.getElementsByTagName("PasswordId").item(0).getTextContent();
                    Name = eElement.getElementsByTagName("Name").item(0).getTextContent();
                    Password = eElement.getElementsByTagName("Password").item(0).getTextContent();
                    PasswordLineStr = "Password Id: " + PasswordsId + "\nName: " + Name + "\nPassword: " + Password + "\n";
                    PasswordsString.append(PasswordLineStr);
                }
                
            }   
        }
        catch(IOException | ParserConfigurationException | SAXException e){
            System.err.printf("IOException: %s", e.getMessage());
        }
        System.out.println(PasswordsString.toString());
        return PasswordsString.toString();
    }
}

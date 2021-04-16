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
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
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
    java.util.List<String> PassIdList = new java.util.ArrayList<>();
    static javax.crypto.SecretKey key;
    static java.util.Map<String, javax.crypto.SecretKey> CiperTextMap = new java.util.HashMap<>();
    private static final String PASSWORDS_FILE = "passwords.xml";//passwords file
    private final char[] PasswordCharArray = {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 
    'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't',
    'u', 'v', 'w', 'x', 'y', 'z', '1', '2', '3', '4', '5', '6',
    '7', '8', '9', '0', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H',
    'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T',
    'U', 'V', 'W', 'X', 'Y', 'Z', '~', '!', '@', '#', '$', '%',
    '^', '&', '*', '(', ')', '-', '+', '_', '{', '}', '|', 
    '/', ',', '.'};//character types used for password generation       
    
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
        String NewPasswordXmlString = "";
        try{
            NewPasswordXmlString = NewPasswordXmlString.concat(PasswordGeneratorLogin.XML_HEADER);
            NewPasswordXmlString = NewPasswordXmlString.concat("<StoredPasswords>\n");
            String PasswordsXmlStr = String.format("\t<StoredPassword>\n\t\t<PasswordId>%d</PasswordId>\n\t\t<Name>%s</Name>\n\t\t<Password>%s</Password>\n\t</StoredPassword>\n", PasswordId, Name, Password);
            NewPasswordXmlString = NewPasswordXmlString.concat(PasswordsXmlStr);
            NewPasswordXmlString = NewPasswordXmlString.concat(BuildXmlString());
            NewPasswordXmlString = NewPasswordXmlString.concat("</StoredPasswords>");
            try (PrintWriter Writer = new PrintWriter(new BufferedWriter(new FileWriter(PASSWORDS_FILE, false)))) {
                Writer.printf(NewPasswordXmlString);
            }
        }
        catch(IOException e){
            System.err.printf("IOException: %s", e.getMessage());
            return false;
        }
        return true;
    }
    
    boolean DeletePassword(String PassId){
        String PasswordId = "";
        String Name;
        String Password;
        String NewPasswordXmlString = "";
        boolean HasPassId = false;
        try{
            File InputFile = new File(PASSWORDS_FILE);
            DocumentBuilderFactory DocumentFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder DocumentBuilder = DocumentFactory.newDocumentBuilder();
            Document Doc = DocumentBuilder.parse(InputFile);
            Doc.getDocumentElement().normalize();
            NodeList NList = Doc.getElementsByTagName("StoredPassword");            
            for(int it = 0; it < NList.getLength(); it++){
                Node nNode = NList.item(it);
                if(nNode.getNodeType() == Node.ELEMENT_NODE){
                    Element eElement = (Element) nNode;
                    PasswordId = eElement.getElementsByTagName("PasswordId").item(0).getTextContent();                  
                    Name = eElement.getElementsByTagName("Name").item(0).getTextContent();
                    Password = eElement.getElementsByTagName("Password").item(0).getTextContent();
                    /*Delete the stored password that user passes through as parameter*/
                    if(!PasswordId.equals(PassId)){
                        NewPasswordXmlString = NewPasswordXmlString.concat(String.format("\t<StoredPassword>\n\t\t<PasswordId>%s</PasswordId>\n\t\t<Name>%s</Name>\n\t\t<Password>%s</Password>\n\t</StoredPassword>\n", PasswordId, Name, Password));  
                    }
                }
                PassIdList.add(PasswordId);
            } 
        }
        catch(IOException | ParserConfigurationException | SAXException e){
            System.err.printf("IOException: %s", e.getMessage());
        }
        
        /*Go through list of password Id's and if exists break out of loop and delete the appropriate password*/
        for(String Id : PassIdList){
            if (PassId.equals(Id)){
                HasPassId = true;  
                break;
            }          
        }
        
        if(HasPassId == false){
            javax.swing.JOptionPane.showMessageDialog(null, "This Id does not exist!");
            return false;
        }

        try (PrintWriter Writer = new PrintWriter(new BufferedWriter(new FileWriter(PASSWORDS_FILE, false)))) {
                String PasswordXmlString = PasswordGeneratorLogin.XML_HEADER;
                PasswordXmlString = PasswordXmlString.concat("<StoredPasswords>\n");
                PasswordXmlString = PasswordXmlString.concat(NewPasswordXmlString);
                PasswordXmlString = PasswordXmlString.concat("</StoredPasswords>");
                Writer.printf(PasswordXmlString);               
        }
        catch(IOException e){
            System.err.printf("IOException: %s", e.getMessage());
        } 
        return true;
    }
    
    String GetPasswordsToDisplay() throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException{
        String PasswordId;
        String Name;
        String Password;
        String PasswordsXmlStr = "";
        try{
            File InputFile = new File(PASSWORDS_FILE);
            DocumentBuilderFactory DocumentFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder DocumentBuilder = DocumentFactory.newDocumentBuilder();
            Document Doc = DocumentBuilder.parse(InputFile);
            Doc.getDocumentElement().normalize();
            NodeList NList = Doc.getElementsByTagName("StoredPassword");            
            for(int it = 0; it < NList.getLength(); it++){
                Node nNode = NList.item(it);
                if(nNode.getNodeType() == Node.ELEMENT_NODE){
                    Element eElement = (Element) nNode;
                    PasswordId = eElement.getElementsByTagName("PasswordId").item(0).getTextContent();
                    Name = eElement.getElementsByTagName("Name").item(0).getTextContent();
                    Password = eElement.getElementsByTagName("Password").item(0).getTextContent();
                    byte[] PasswordPlainText;
                    String PlainTextToString = "";
                    /*Decrypt passwords*/
                    try{
                        javax.crypto.Cipher cipher = javax.crypto.Cipher.getInstance("AES");
                        key = CiperTextMap.get(Password);
                        cipher.init(Cipher.DECRYPT_MODE, key, new java.security.SecureRandom());
                        PasswordPlainText = cipher.doFinal(java.util.Base64.getDecoder().decode(Password));
                        PlainTextToString = new String(PasswordPlainText);
                    }
                    catch(NoSuchAlgorithmException | NoSuchPaddingException e){
                        System.err.println(e);
                    }
                    PasswordsXmlStr = PasswordsXmlStr.concat(String.format("Password Id: %s\nName: %s\nPassword: %s\n\n", PasswordId, Name, PlainTextToString));
                }               
            } 
        }
        catch(IOException | ParserConfigurationException | SAXException e){
            System.err.printf("IOException: %s", e.getMessage());
        }
        return PasswordsXmlStr;
    }
    
    String BuildXmlString(){
        String PasswordId;
        String Name;
        String Password;
        StringBuilder PasswordsXmlStr = new StringBuilder();
        try{
            File InputFile = new File(PASSWORDS_FILE);
            DocumentBuilderFactory DocumentFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder DocumentBuilder = DocumentFactory.newDocumentBuilder();
            Document Doc = DocumentBuilder.parse(InputFile);
            Doc.getDocumentElement().normalize();
            NodeList NList = Doc.getElementsByTagName("StoredPassword");
            for(int it = 0; it < NList.getLength(); it++){
                Node nNode = NList.item(it);
                if(nNode.getNodeType() == Node.ELEMENT_NODE){
                    Element eElement = (Element) nNode;
                    PasswordId = eElement.getElementsByTagName("PasswordId").item(0).getTextContent();
                    Name = eElement.getElementsByTagName("Name").item(0).getTextContent();
                    Password = eElement.getElementsByTagName("Password").item(0).getTextContent();
                    PasswordsXmlStr.append(String.format("\t<StoredPassword>\n\t\t<PasswordId>%s</PasswordId>\n\t\t<Name>%s</Name>\n\t\t<Password>%s</Password>\n\t</StoredPassword>\n", PasswordId, Name, Password));
                }               
            } 
        }
        catch(IOException | ParserConfigurationException | SAXException e){
            System.err.printf("IOException: %s", e.getMessage());
        }
        return PasswordsXmlStr.toString();   
    }
}

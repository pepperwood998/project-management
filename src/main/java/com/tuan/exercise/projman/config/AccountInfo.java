package com.tuan.exercise.projman.config;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.tuan.exercise.projman.entity.Account;

public class AccountInfo {
    private AccountInfo() {
    }
    
    private static final Logger LOG = LoggerFactory.getLogger(AccountInfo.class);

    private static Map<String, String> roleMap;
    private static List<Account> accountList;

    static {
        roleMap = new HashMap<>();
        accountList = new ArrayList<>();

        try {
            File fXmlFile = new File("./src/main/resources/account.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);

            doc.getDocumentElement().normalize();

            NodeList roleItemNodeList = doc.getElementsByTagName("role-item");

            // get role map
            for (int i = 0; i < roleItemNodeList.getLength(); i++) {
                Node roleItemNode = roleItemNodeList.item(i);

                if (roleItemNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element roleItemElem = (Element) roleItemNode;
                    roleMap.put(roleItemElem.getAttribute("key"), roleItemElem.getTextContent());
                }
            }

            // get account
            NodeList accNodeList = doc.getElementsByTagName("account");
            for (int i = 0; i < accNodeList.getLength(); i++) {
                Node accNode = accNodeList.item(i);

                if (accNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element accElem = (Element) accNode;

                    Account account = new Account();
                    account.setUsername(accElem.getElementsByTagName("username").item(0).getTextContent());
                    account.setPassword(accElem.getElementsByTagName("password").item(0).getTextContent());

                    List<String> roleList = new ArrayList<>();
                    NodeList assignedRoleNodeList = accElem.getElementsByTagName("role");
                    for (int j = 0; j < assignedRoleNodeList.getLength(); j++) {
                        Node assignedRoleNode = assignedRoleNodeList.item(j);

                        if (assignedRoleNode.getNodeType() == Node.ELEMENT_NODE) {
                            Element assignedRoleElem = (Element) assignedRoleNode;
                            roleList.add(assignedRoleElem.getTextContent());
                        }
                    }
                    account.setRoles(roleList.toArray(new String[0]));
                    accountList.add(account);
                }
            }

        } catch (Exception e) {
            LOG.error("Some exception", e);
        }
    }

    public static Map<String, String> getRoleMap() {
        return roleMap;
    }

    public static List<Account> getAccountList() {
        return accountList;
    }
}

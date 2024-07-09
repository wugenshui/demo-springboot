package com.wugenshui.github.ldap;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;

/**
 * @author : chenbo
 * @date : 2024-07-09
 */
public class App {
    public static DirContext login(String principal, String password) throws NamingException {
        // LDAP服务器地址
        String ldapHost = "ldap://192.168.0.32:389";
        Hashtable<String, String> env = new Hashtable<>();
        env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        env.put(Context.PROVIDER_URL, ldapHost);
        env.put(Context.SECURITY_PRINCIPAL, principal);
        env.put(Context.SECURITY_CREDENTIALS, password);
        // 尝试连接并认证，若用户名密码不对会抛出异常
        return new InitialDirContext(env);
    }

    public static List<String> getUserDN(DirContext dirContext, String username) throws NamingException {
        List<String> results = new ArrayList<>();
        // Perform search
        SearchControls cons = new SearchControls();
        cons.setSearchScope(SearchControls.SUBTREE_SCOPE);
        NamingEnumeration<SearchResult> answer = dirContext.search("OU=Company,DC=local,DC=test,DC=com", "(sAMAccountName=" + username + ")", cons);
        // Process results
        while (answer.hasMore()) {
            SearchResult result = answer.next();
            results.add(result.getNameInNamespace());
            System.out.println("getUserDN:" + result.getNameInNamespace());
        }
        return results;
    }

    public static boolean validLdapLogin(String username, String password) {
        boolean isValid = false;
        try {
            DirContext dirContext = login("CN=LDAP_Config,OU=Other,OU=Company,DC=local,DC=test,DC=com", "1q2w#E$R");
            List<String> listUserDN = getUserDN(dirContext, username);
            if (!listUserDN.isEmpty()) {
                login(listUserDN.get(0), password);
                isValid = true;
            }
        } catch (NamingException e) {
            System.out.println("ldap login error:" + e.getMessage());
        }
        System.out.println("validLdap:" + username + " isValid:" + isValid);
        return isValid;
    }

    public static void main(String[] args) {
        // 测试认证方法
        System.out.println(validLdapLogin("newnew", "1234"));
    }
}

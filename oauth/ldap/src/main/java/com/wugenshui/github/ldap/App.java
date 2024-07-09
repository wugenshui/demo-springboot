package com.wugenshui.github.ldap;

import javax.naming.AuthenticationException;
import javax.naming.Context;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import java.util.Hashtable;

/**
 * @author : chenbo
 * @date : 2024-07-09
 */
public class App {
    public static boolean authenticate(String username, String password) {
        // LDAP服务器地址
        String ldapHost = "ldap://your-ldap-server.com:389";
        // 完整的用户DN（Distinguished Name）
        String userDN = "CN=" + username + ",CN=Users,DC=yourdomain,DC=com";

        Hashtable<String, String> env = new Hashtable<>();
        env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.ldap.LdapCtxFactory");
        env.put(Context.PROVIDER_URL, ldapHost);
        env.put(Context.SECURITY_AUTHENTICATION, "simple");
        env.put(Context.SECURITY_PRINCIPAL, userDN);
        env.put(Context.SECURITY_CREDENTIALS, password);

        try {
            // 尝试连接并认证
            DirContext ctx = new InitialDirContext(env);
            System.out.println("Authentication successful");
            // 关闭连接
            ctx.close();
            return true;
        } catch (AuthenticationException e) {
            System.out.println("Authentication failed: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }
        return false;
    }

    public static void main(String[] args) {
        // 测试认证方法
        boolean isAuthenticated = authenticate("yourUsername", "yourPassword");
        System.out.println("Is authenticated: " + isAuthenticated);
    }
}

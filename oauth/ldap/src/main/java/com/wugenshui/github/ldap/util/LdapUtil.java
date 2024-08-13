package com.wugenshui.github.ldap.util;

import com.wugenshui.github.ldap.properties.LdapProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
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
 * @date : 2024-08-13
 */
@Component
@ConditionalOnProperty(name = "ldap.enable", havingValue = "true")
@Slf4j
public class LdapUtil {

    private static final String INITIAL_CONTEXT_FACTORY = "com.sun.jndi.ldap.LdapCtxFactory";

    @Resource
    private LdapProperties ldapProperties;

    public DirContext login(String principal, String password) throws NamingException {
        Hashtable<String, String> env = new Hashtable<>();
        env.put(Context.INITIAL_CONTEXT_FACTORY, INITIAL_CONTEXT_FACTORY);
        env.put(Context.PROVIDER_URL, ldapProperties.getHost());
        env.put(Context.SECURITY_PRINCIPAL, principal);
        env.put(Context.SECURITY_CREDENTIALS, password);
        // 尝试连接并认证，若用户名密码不对会抛出异常
        return new InitialDirContext(env);
    }

    public List<String> getUserDN(DirContext dirContext, String username) throws NamingException {
        List<String> results = new ArrayList<>();
        // Perform search
        SearchControls cons = new SearchControls();
        cons.setSearchScope(SearchControls.SUBTREE_SCOPE);
        NamingEnumeration<SearchResult> answer = dirContext.search(ldapProperties.getOuPath(), String.format(ldapProperties.getUserFilter(), username), cons);
        // Process results
        while (answer.hasMore()) {
            SearchResult result = answer.next();
            results.add(result.getNameInNamespace());
            log.info("getUserDN:" + result.getNameInNamespace());
        }
        return results;
    }

    public boolean validLdapLogin(String username, String password) {
        boolean isValid = false;
        try {
            DirContext dirContext = login(ldapProperties.getUsername(), ldapProperties.getPassword());
            List<String> listUserDN = getUserDN(dirContext, username);
            if (!listUserDN.isEmpty()) {
                login(listUserDN.get(0), password);
                isValid = true;
            }
        } catch (NamingException e) {
            log.error("ldap login error" , e);
        }
        log.info("validLdap:" + username + " isValid:" + isValid);
        return isValid;
    }
}

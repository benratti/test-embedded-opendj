/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ratti.test.embedded.opendj;

import java.util.Hashtable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.ClassRule;

//import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

/**
 *
 * @author bratti
 */
@LdapServer(ldifConfig = "opendj/config/config.ldif", serverHome = "opendj", ldifFile = "data.ldif")
public class EmbeddedLdapTest {

    @ClassRule
    public static OpenDJRule ldapRule = new OpenDJRule();

    public EmbeddedLdapTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void hello() {
        try {
            Hashtable<String, String> env = new Hashtable<String, String>(4);
            env.put(Context.INITIAL_CONTEXT_FACTORY,"com.sun.jndi.ldap.LdapCtxFactory");
            env.put(Context.PROVIDER_URL, "ldap://localhost:15389/o=mycompagny,o=org");
            env.put(Context.SECURITY_PRINCIPAL, "cn=Directory Manager");
            env.put(Context.SECURITY_CREDENTIALS, "password");
            DirContext ctx = new InitialDirContext(env);
            Attributes attrs = ctx.getAttributes("uid=myuser,ou=people");

            assertEquals("Paul", attrs.get("myFirstName").get());
            assertEquals("Smith", attrs.get("myLastName").get());

        } catch (NamingException ex) {
            fail(ex.getLocalizedMessage());
        }
    }
}
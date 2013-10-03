/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ratti.test.embedded.opendj;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 * @author bratti
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface LdapServer {
    
    String ldifConfig() default "config.ldif";
    String serverHome() default "opendj";
    String ldifFile();
    String backendID() default "opendj";
    String baseDN() default "o=mycompagny,o=org";
}

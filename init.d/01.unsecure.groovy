#!/usr/bin/env groovy
/*


This makes local jenkins password less


 */
import jenkins.model.*
import hudson.security.AuthorizationStrategy

if(Jenkins.instance.authorizationStrategy.class.simpleName != 'Unsecured') {
    Jenkins.instance.authorizationStrategy = new AuthorizationStrategy.Unsecured()
    Jenkins.instance.save()
    println 'Disabled Jenkins security for demo purposes.'
}
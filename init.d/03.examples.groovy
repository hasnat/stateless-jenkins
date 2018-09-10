/*


Adds examples folders as simple pipeline jobs


 */

import java.io.File
import jenkins.model.*
import hudson.security.*
import hudson.tasks.*
import jenkins.branch.*
import jenkins.plugins.git.*
import hudson.plugins.git.GitSCM
import org.jenkinsci.plugins.workflow.job.*
import org.jenkinsci.plugins.workflow.cps.CpsScmFlowDefinition

def instance = Jenkins.getInstance()

new File("/usr/local/jenkins-projects").eachDir() { dirItem ->
    def projectName = dirItem.getName()
    println "Set Example Project " + projectName
    try {
        WorkflowJob wp = instance.createProject(WorkflowJob.class, projectName)

        wp.setDefinition(
                new CpsScmFlowDefinition(new GitSCM("file:///usr/local/jenkins-projects/" + projectName), "Jenkinsfile")
        )
    } catch (IllegalArgumentException e) {
        println "Project already setup " + projectName
    }
}

#!/usr/bin/env groovy
import jenkins.model.*
import java.util.logging.Logger
def logger = Logger.getLogger("")
def installed = false
def initialized = false
def extraPlugins = System.getenv("JENKINS_PLUGINS") ?: ""
def defaultPlugins = "git \
pipeline-multibranch-defaults \
workflow-aggregator \
workflow-multibranch \
pipeline-aggregator-view \
workflow-durable-task-step \
workflow-basic-steps \
pipeline-stage-step \
rebuild"
def plugins = (defaultPlugins + ' ' + extraPlugins).split()
logger.info("" + plugins)
def instance = Jenkins.getInstance()
def pm = instance.getPluginManager()
def uc = instance.getUpdateCenter()
plugins.each {
    logger.info("Checking " + it)
    if (!pm.getPlugin(it)) {
        logger.info("Looking UpdateCenter for " + it)
        if (!initialized) {
            uc.updateAllSites()
            initialized = true
        }
        def plugin = uc.getPlugin(it)
        if (plugin) {
            logger.info("Installing " + it)
            def installFuture = plugin.deploy()
            while(!installFuture.isDone()) {
                logger.info("Waiting for plugin install: " + it)
                sleep(3000)
            }
            installed = true
        }
    }
}
if (installed) {
    logger.info("Plugins installed, initializing a restart!")
    instance.save()
    instance.restart()
}
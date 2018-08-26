#!groovy
import groovy.json.JsonSlurper
/**
 *
 * @param image
 * @param registry e.g. https://user:pass@registry.example.com
 * @param credentials e.g. user:password
 * @return choiceParameter
 */
def getDockerRegistryImageTagChoices(image, registry, auth) {
    def authString = auth.getBytes().encodeBase64().toString()

    def conn = new URL(registry + '/v2/' + image + '/tags/list').openConnection()
    conn.setRequestProperty( "Authorization", "Basic ${authString}" )

    def imageTags = new JsonSlurper().parseText(conn.content.text)
    return choice(name: image + '-version', choices: imageTags.tags, description: 'Select version for ' + image)
}

/**
 *
 * @param image
 * @param max_tags e.g. https://user:pass@registry.example.com
 * @return choiceParameter
 */
def getDockerHubImageTagChoices(image, max_tags = 30) {

    def response = new URL('https://hub.docker.com/v2/repositories/' + image + '/tags/?page_size=' + max_tags)
    def imageTags = new JsonSlurper().parseText(response.text)
    def tags = []
    for (tag in imageTags.results) {
        tags.add(tag.name)
    }
    return choice(name: image + '-version', choices: tags, description: 'Select version for ' + image)
}

return this
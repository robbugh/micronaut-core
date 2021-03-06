/*
 * Copyright 2017-2018 original authors
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package io.micronaut.docs.server.intro.groovycode

import io.micronaut.context.ApplicationContext
import io.micronaut.context.env.Environment
import io.micronaut.http.HttpRequest
import io.micronaut.http.client.HttpClient
import io.micronaut.runtime.server.EmbeddedServer
import spock.lang.AutoCleanup
import spock.lang.Shared
import spock.lang.Specification

/**
 * @author Graeme Rocher
 * @since 1.0
 */
class HelloControllerSpec extends Specification {

    @Shared @AutoCleanup EmbeddedServer embeddedServer =
            ApplicationContext.run(EmbeddedServer,
                    ['spec.name': HelloControllerSpec.simpleName,
                     'spec.lang': 'groovy'],
                     Environment.TEST)

    @Shared @AutoCleanup HttpClient client = HttpClient.create(embeddedServer.URL)

    void "test hello world response"() {
        expect:
        client.toBlocking()
              .retrieve(HttpRequest.GET('/hello')) == "Hello World"
    }
}

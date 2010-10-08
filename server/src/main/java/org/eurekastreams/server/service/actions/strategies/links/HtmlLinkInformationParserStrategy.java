/*
 * Copyright (c) 2009 Lockheed Martin Corporation
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.eurekastreams.server.service.actions.strategies.links;

import org.eurekastreams.server.domain.stream.LinkInformation;

/**
 * Parses information out of an HTML document. */
public interface HtmlLinkInformationParserStrategy
{
    /**
     * Parse the HTML.
     * 
     * @param htmlString
     *            the HTML as a string.
     * @param link
     *            the link.
     * @param inAccountId
     *            account id of the user requesting link information to be parsed.
     */
    void parseInformation(String htmlString, LinkInformation link, String inAccountId);
}

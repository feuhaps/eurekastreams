/*
 * Copyright (c) 2010 Lockheed Martin Corporation
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
package org.eurekastreams.server.persistence.mappers.db;

import static org.junit.Assert.assertEquals;

import org.eurekastreams.server.persistence.mappers.MapperTest;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Test for GetSerializableField mapper.
 * 
 */
public class GetSerializableFieldTest extends MapperTest
{
    /**
     * System under test. Autowired mapper is config'ed to grab ids from Person table.
     */
    @Autowired
    private GetSerializableField sut;

    /**
     * Test for person ids.
     */
    @Test
    public void test()
    {
        assertEquals(5, sut.execute(null).size());
    }

}

/*
 * Copyright (c) 2010-2011 Lockheed Martin Corporation
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
package org.eurekastreams.server.action.execution.notification.inapp;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.eurekastreams.commons.actions.context.ActionContext;
import org.eurekastreams.server.domain.UnreadInAppNotificationCountDTO;
import org.eurekastreams.server.persistence.mappers.DomainMapper;
import org.eurekastreams.server.persistence.mappers.db.notification.DeleteInAppNotificationsByDate;
import org.eurekastreams.server.persistence.mappers.db.notification.GetUserIdsWithUnreadInAppNotificationsByDate;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.integration.junit4.JUnit4Mockery;
import org.jmock.lib.legacy.ClassImposteriser;
import org.junit.Before;
import org.junit.Test;

/**
 * Tests execution strategy for GetApplicationAlerts.
 */
public class DeleteOldInAppNotificationsExecutionTest
{
    /**
     * Context for building mock objects.
     */
    private final Mockery context = new JUnit4Mockery()
    {
        {
            setImposteriser(ClassImposteriser.INSTANCE);
        }
    };

    /**
     * System under test.
     */
    private DeleteOldInAppNotificationsExecution sut;

    /**
     * Alert mapper mock.
     */
    private final DeleteInAppNotificationsByDate deleteMapper = context.mock(DeleteInAppNotificationsByDate.class);

    /**
     * Unread user ids mapper mock.
     */
    private final GetUserIdsWithUnreadInAppNotificationsByDate unreadMapper = context
            .mock(GetUserIdsWithUnreadInAppNotificationsByDate.class);

    /**
     * Sync mapper mock.
     */
    private final DomainMapper<Long, UnreadInAppNotificationCountDTO> syncMapper = context.mock(DomainMapper.class);

    /**
     * ActionContext mock.
     */
    private final ActionContext actionContext = context.mock(ActionContext.class);

    /**
     * Count of alerts.
     */
    private static final int COUNT = 14;

    /**
     * Setup.
     */
    @Before
    public final void setUp()
    {
        sut = new DeleteOldInAppNotificationsExecution(deleteMapper, unreadMapper, syncMapper, COUNT);
    }

    /**
     * Tests execute method.
     */
    @Test
    public void testExecute()
    {
        final Long user1 = 123L;
        final Long user2 = 456L;
        final List<Long> userIds = Arrays.asList(user1, user2);
        context.checking(new Expectations()
        {
            {
                oneOf(unreadMapper).execute(with(any(Date.class)));
                will(returnValue(userIds));

                oneOf(deleteMapper).execute(with(any(Date.class)));
                will(returnValue(8));

                oneOf(syncMapper).execute(user1);
                oneOf(syncMapper).execute(user2);
            }
        });

        sut.execute(actionContext);
        context.assertIsSatisfied();
    }
}

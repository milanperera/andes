/*
 *
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 *
 */
package org.wso2.andes.test.framework.localcircuit;

import org.wso2.andes.test.framework.*;

import org.wso2.andes.junit.extensions.util.ParsedProperties;

import javax.jms.MessageConsumer;
import javax.jms.MessageProducer;
import javax.jms.Session;

/**
 * Provides an implementation of the {@link Receiver} interface that wraps a single message producer and consumer on
 * a single controlSession, as a {@link CircuitEnd}. A local receiver also acts as a circuit end, because for a locally
 * located circuit the assertions may be applied directly, there does not need to be any inter process messaging
 * between the publisher and its single circuit end, in order to ascertain its status.
 *
 * <p/><table id="crc"><caption>CRC Card</caption>
 * <tr><th> Responsibilities <th> Collaborations
 * <tr><td> Provide a message producer for sending messages.
 * <tr><td> Provide a message consumer for receiving messages.
 * <tr><td> Provide assertion that the receivers received no exceptions.
 * <tr><td> Provide assertion that the receivers received all test messages sent to it.
 * </table>
 */
public class LocalReceiverImpl extends CircuitEndBase implements Receiver
{
    /** Holds a reference to the containing circuit. */
    private LocalCircuitImpl circuit;

    /**
     * Creates a circuit end point on the specified producer, consumer and controlSession. Monitors are also configured
     * for messages and exceptions received by the circuit end.
     *
     * @param producer The message producer for the circuit end point.
     * @param consumer The message consumer for the circuit end point.
     * @param session  The controlSession for the circuit end point.
     * @param messageMonitor   The monitor to notify of all messages received by the circuit end.
     * @param exceptionMonitor The monitor to notify of all exceptions received by the circuit end.
     */
    public LocalReceiverImpl(MessageProducer producer, MessageConsumer consumer, Session session,
        MessageMonitor messageMonitor, ExceptionMonitor exceptionMonitor)
    {
        super(producer, consumer, session, messageMonitor, exceptionMonitor);
    }

    /**
     * Creates a circuit end point from the producer, consumer and controlSession in a circuit end base implementation.
     *
     * @param end The circuit end base implementation to take producers and consumers from.
     */
    public LocalReceiverImpl(CircuitEndBase end)
    {
        super(end.getProducer(), end.getConsumer(), end.getSession(), end.getMessageMonitor(), end.getExceptionMonitor());
    }

    /**
     * Provides an assertion that the receivers encountered no exceptions.
     *
     * @param testProps The test configuration properties.
     *
     * @return An assertion that the receivers encountered no exceptions.
     */
    public Assertion noExceptionsAssertion(ParsedProperties testProps)
    {
        return new NotApplicableAssertion(testProps);
    }

    /**
     * Provides an assertion that the AMQP channel was forcibly closed by an error condition.
     *
     * @param testProps The test configuration properties.
     *
     * @return An assertion that the AMQP channel was forcibly closed by an error condition.
     */
    public Assertion channelClosedAssertion(ParsedProperties testProps)
    {
        return new NotApplicableAssertion(testProps);
    }

    /**
     * Provides an assertion that the receivers got all messages that were sent to it.
     *
     * @param testProps The test configuration properties.
     *
     * @return An assertion that the receivers got all messages that were sent to it.
     */
    public Assertion allMessagesReceivedAssertion(ParsedProperties testProps)
    {
        return new NotApplicableAssertion(testProps);
    }

    /**
     * Provides an assertion that the receivers got none of the messages that were sent to it.
     *
     * @param testProps The test configuration properties.
     *
     * @return An assertion that the receivers got none of the messages that were sent to it.
     */
    public Assertion noMessagesReceivedAssertion(ParsedProperties testProps)
    {
        return new NotApplicableAssertion(testProps);
    }

    /**
     * Provides an assertion that the receiver got a given exception during the test.
     *
     * @param testProps The test configuration properties.
     * @param exceptionClass The exception class to check for. @return An assertion that the receiver got a given exception during the test.
     */
    public Assertion exceptionAssertion(ParsedProperties testProps, Class<? extends Exception> exceptionClass)
    {
        return new NotApplicableAssertion(testProps);
    }

    /**
     * Sets the contianing circuit.
     *
     * @param circuit The containing circuit.
     */
    public void setCircuit(LocalCircuitImpl circuit)
    {
        this.circuit = circuit;
    }
}

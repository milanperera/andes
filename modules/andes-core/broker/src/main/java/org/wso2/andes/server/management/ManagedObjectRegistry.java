/*
 * Copyright (c) 2005-2014, WSO2 Inc. (http://www.wso2.org) All Rights Reserved.
 *
 * WSO2 Inc. licenses this file to you under the Apache License,
 * Version 2.0 (the "License"); you may not use this file except
 * in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package org.wso2.andes.server.management;

import javax.management.JMException;

import org.apache.commons.configuration.ConfigurationException;
import org.wso2.andes.common.Closeable;

import java.io.IOException;

/**
 * Handles the registration (and unregistration and so on) of managed objects.
 *
 * Managed objects are responsible for exposting attributes, operations and notifications. They will expose
 * these outside the JVM therefore it is important not to use implementation objects directly as managed objects.
 * Instead, creating inner classes and exposing those is an effective way of exposing internal state in a
 * controlled way.
 *
 * Although we do not explictly use them while targetting Java 5, the enhanced MXBean approach in Java 6 will
 * be the obvious choice for managed objects.
 *
 */
public interface ManagedObjectRegistry extends Closeable
{
    void start() throws IOException, ConfigurationException;

    void registerObject(ManagedObject managedObject) throws JMException;

    void unregisterObject(ManagedObject managedObject) throws JMException;
}

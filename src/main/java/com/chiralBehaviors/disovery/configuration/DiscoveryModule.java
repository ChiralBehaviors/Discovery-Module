/** 
 * (C) Copyright 2014 Chiral Behaviors, All Rights Reserved
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 *     
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, 
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. 
 * See the License for the specific language governing permissions and 
 * limitations under the License.
 */
package com.chiralBehaviors.disovery.configuration;

import java.net.InetSocketAddress;

import com.chiralBehaviors.slp.hive.configuration.EngineConfiguration;
import com.chiralBehaviors.slp.hive.configuration.EngineMixin;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.hellblazer.slp.config.ServiceScopeConfiguration;
import com.hellblazer.utils.deserializers.InetSocketAddressDeserializer;
import com.hellblazer.utils.fd.FailureDetectorFactory;
import com.hellblazer.utils.fd.json.FdFactoryMixin;

/**
 * @author hhildebrand
 * 
 */
public class DiscoveryModule extends SimpleModule {

    private static final long serialVersionUID = 1L;

    public DiscoveryModule() {
        super("Discovery Module");
    }

    /* (non-Javadoc)
     * @see com.fasterxml.jackson.databind.module.SimpleModule#setupModule(com.fasterxml.jackson.databind.Module.SetupContext)
     */
    @Override
    public void setupModule(SetupContext context) {
        setMixInAnnotation(EngineConfiguration.class, EngineMixin.class);
        setMixInAnnotation(ServiceScopeConfiguration.class,
                           DiscoveryMixin.class);
        setMixInAnnotation(FailureDetectorFactory.class, FdFactoryMixin.class);
        addDeserializer(InetSocketAddress.class,
                        new InetSocketAddressDeserializer());
        super.setupModule(context);
    }

}

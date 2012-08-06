/*
 * DO NOT ALTER OR REMOVE COPYRIGHT NOTICES OR THIS HEADER.
 *
 * Copyright (c) 2012 Oracle and/or its affiliates. All rights reserved.
 *
 * The contents of this file are subject to the terms of either the GNU
 * General Public License Version 2 only ("GPL") or the Common Development
 * and Distribution License("CDDL") (collectively, the "License").  You
 * may not use this file except in compliance with the License.  You can
 * obtain a copy of the License at
 * http://glassfish.java.net/public/CDDL+GPL_1_1.html
 * or packager/legal/LICENSE.txt.  See the License for the specific
 * language governing permissions and limitations under the License.
 *
 * When distributing the software, include this License Header Notice in each
 * file and include the License file at packager/legal/LICENSE.txt.
 *
 * GPL Classpath Exception:
 * Oracle designates this particular file as subject to the "Classpath"
 * exception as provided by Oracle in the GPL Version 2 section of the License
 * file that accompanied this code.
 *
 * Modifications:
 * If applicable, add the following below the License Header, with the fields
 * enclosed by brackets [] replaced by your own identifying information:
 * "Portions Copyright [year] [name of copyright owner]"
 *
 * Contributor(s):
 * If you wish your version of this file to be governed by only the CDDL or
 * only the GPL Version 2, indicate your decision by adding "[Contributor]
 * elects to include this software in this distribution under the [CDDL or GPL
 * Version 2] license."  If you don't indicate a single choice of license, a
 * recipient has the option to distribute your version of this file under
 * either the CDDL, the GPL Version 2 or to extend the choice of license to
 * its licensees as provided above.  However, if you add GPL Version 2 code
 * and therefore, elected the GPL Version 2 license, then the option applies
 * only if the new code is made subject to such option by the copyright
 * holder.
 */
package org.glassfish.jersey.osgi.test.basic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.ws.rs.client.Feature;

import org.glassfish.jersey.moxy.json.MoxyJsonBinder;
import org.glassfish.jersey.moxy.json.MoxyJsonFeature;

import org.glassfish.hk2.utilities.Binder;

import org.ops4j.pax.exam.Option;
import org.ops4j.pax.exam.junit.Configuration;
import static org.ops4j.pax.exam.CoreOptions.bootDelegationPackage;
import static org.ops4j.pax.exam.CoreOptions.mavenBundle;
import static org.ops4j.pax.exam.CoreOptions.options;

/**
 * @author Michal Gajdos (michal.gajdos at oracle.com)
 */
public class JsonMoxyTest extends AbstractJsonOsgiIntegrationTest {

    @Configuration
    public static Option[] configuration() {
        List<Option> options = new ArrayList<Option>();

        options.addAll(Arrays.asList(getCommonOsgiIntegrationTestOptions()));
        options.addAll(Arrays.asList(
           options(
                   bootDelegationPackage("javax.xml.bind"),
                   bootDelegationPackage("javax.xml.bind.*"),

                   mavenBundle().groupId("org.glassfish.jersey.media").artifactId("jersey-media-moxy").versionAsInProject(),

                   // jersey-json deps
                   mavenBundle().groupId("org.eclipse.persistence").artifactId("org.eclipse.persistence.moxy").versionAsInProject(),
                   mavenBundle().groupId("org.eclipse.persistence").artifactId("org.eclipse.persistence.antlr").versionAsInProject(),
                   mavenBundle().groupId("org.eclipse.persistence").artifactId("org.eclipse.persistence.core").versionAsInProject(),
                   mavenBundle().groupId("org.eclipse.persistence").artifactId("org.eclipse.persistence.asm").versionAsInProject()
           )
        ));

        return options.toArray(new Option[options.size()]);
    }

    @Override
    protected Feature getJsonProviderFeature() {
        return new MoxyJsonFeature();
    }

    @Override
    protected Binder getJsonProviderBinder() {
        return new MoxyJsonBinder();
    }
}

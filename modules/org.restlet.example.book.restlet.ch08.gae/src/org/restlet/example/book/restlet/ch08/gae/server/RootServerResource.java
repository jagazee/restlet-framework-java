/**
 * Copyright 2005-2012 Restlet S.A.S.
 * 
 * The contents of this file are subject to the terms of one of the following
 * open source licenses: Apache 2.0 or LGPL 3.0 or LGPL 2.1 or CDDL 1.0 or EPL
 * 1.0 (the "Licenses"). You can select the license that you prefer but you may
 * not use this file except in compliance with one of these Licenses.
 * 
 * You can obtain a copy of the Apache 2.0 license at
 * http://www.opensource.org/licenses/apache-2.0
 * 
 * You can obtain a copy of the LGPL 3.0 license at
 * http://www.opensource.org/licenses/lgpl-3.0
 * 
 * You can obtain a copy of the LGPL 2.1 license at
 * http://www.opensource.org/licenses/lgpl-2.1
 * 
 * You can obtain a copy of the CDDL 1.0 license at
 * http://www.opensource.org/licenses/cddl1
 * 
 * You can obtain a copy of the EPL 1.0 license at
 * http://www.opensource.org/licenses/eclipse-1.0
 * 
 * See the Licenses for the specific language governing permissions and
 * limitations under the Licenses.
 * 
 * Alternatively, you can obtain a royalty free commercial license with less
 * limitations, transferable or non-transferable, directly at
 * http://www.restlet.com/products/restlet-framework
 * 
 * Restlet is a registered trademark of Restlet S.A.S.
 */

package org.restlet.example.book.restlet.ch08.gae.server;

import org.restlet.data.MediaType;
import org.restlet.example.book.restlet.ch08.gae.common.RootResource;
import org.restlet.ext.wadl.DocumentationInfo;
import org.restlet.ext.wadl.MethodInfo;
import org.restlet.ext.wadl.RepresentationInfo;
import org.restlet.ext.wadl.WadlServerResource;
import org.restlet.representation.Variant;
import org.restlet.resource.ResourceException;

/**
 * Root resource implementation.
 */
public class RootServerResource extends WadlServerResource implements
        RootResource {

    @Override
    protected RepresentationInfo describe(MethodInfo methodInfo,
            Class<?> representationClass, Variant variant) {
        RepresentationInfo result = new RepresentationInfo(MediaType.TEXT_PLAIN);
        result.setIdentifier("root");

        DocumentationInfo doc = new DocumentationInfo();
        doc.setTitle("Mail application");
        doc.setTextContent("Simple string welcoming the user to the mail application");
        result.getDocumentations().add(doc);
        return result;
    }

    @Override
    protected void doInit() throws ResourceException {
        setAutoDescribing(false);
        setName("Root resource");
        setDescription("The root resource of the mail server application");
    }

    public String represent() {
        return "Welcome to the " + getApplication().getName() + " !";
    }

}

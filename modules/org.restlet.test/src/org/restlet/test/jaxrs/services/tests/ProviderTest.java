/*
 * Copyright 2005-2008 Noelios Consulting.
 * 
 * The contents of this file are subject to the terms of the Common Development
 * and Distribution License (the "License"). You may not use this file except in
 * compliance with the License.
 * 
 * You can obtain a copy of the license at
 * http://www.opensource.org/licenses/cddl1.txt See the License for the specific
 * language governing permissions and limitations under the License.
 * 
 * When distributing Covered Code, include this CDDL HEADER in each file and
 * include the License file at http://www.opensource.org/licenses/cddl1.txt If
 * applicable, add the following below this CDDL HEADER, with the fields
 * enclosed by brackets "[]" replaced with your own identifying information:
 * Portions Copyright [yyyy] [name of copyright owner]
 */

package org.restlet.test.jaxrs.services.tests;

import org.restlet.data.Form;
import org.restlet.data.Response;
import org.restlet.data.Status;
import org.restlet.resource.DomRepresentation;
import org.restlet.resource.Representation;
import org.restlet.resource.StringRepresentation;
import org.restlet.test.jaxrs.services.ProviderTestService;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class ProviderTest extends JaxRsTestCase {

    private static final Class<?> SERVICE_CLASS = ProviderTestService.class;

    private static Form createForm() {
        Form form = new Form();
        form.add("firstname", "Angela");
        form.add("lastname", "Merkel");
        return form;
    }

    /**
     * @param subPath
     * @throws IOException
     * @throws DOMException
     */
    private void getAndCheckJaxb(String subPath) throws Exception {
        Response response = get(subPath);
        assertEquals(Status.SUCCESS_OK, response.getStatus());
        DomRepresentation entity = response.getEntityAsDom();
        Node xml = entity.getDocument().getFirstChild();
        assertEquals("person", xml.getNodeName());
        NodeList nodeList = xml.getChildNodes();
        Node node = nodeList.item(0);
        assertEquals("firstname", node.getNodeName());
        assertEquals("Angela", node.getFirstChild().getNodeValue());
        node = nodeList.item(1);
        assertEquals("lastname", node.getNodeName());
        assertEquals("Merkel", node.getFirstChild().getNodeValue());
        assertEquals(2, nodeList.getLength());
    }

    @SuppressWarnings("unchecked")
    @Override
    protected Class<?> getRootResourceClass() {
        return SERVICE_CLASS;
    }

    /**
     * @param subPath
     * @throws IOException
     */
    private void postAndCheck(String subPath) throws Exception {
        Representation send = new DomRepresentation(
                new StringRepresentation(
                        "<person><firstname>Helmut</firstname><lastname>Kohl</lastname></person>\n"));
        Response response = post(subPath, send);
        assertEquals(Status.SUCCESS_OK, response.getStatus());
        Representation respEntity = response.getEntity();
        assertEquals("Helmut Kohl", respEntity.getText());
    }

    public void testByteArray() throws Exception {
        Response response = get("byteArray");
        assertEquals(Status.SUCCESS_OK, response.getStatus());
        Representation representation = response.getEntity();
        assertEquals(ProviderTestService.ALPHABET, representation.getText());

        Representation entity = new StringRepresentation("big test");
        response = post("byteArray", entity);
        assertEquals(Status.SUCCESS_OK, response.getStatus());
        representation = response.getEntity();
        assertEquals("big test", representation.getText());
    }

    public void testFile() throws Exception {
        Response response = get("file");
        assertEquals(Status.SUCCESS_OK, response.getStatus());
        Representation entity = response.getEntity();
        assertEquals(ProviderTestService.ALPHABET, entity.getText());

        response = post("file", new StringRepresentation("big test"));
        assertEquals(Status.SUCCESS_OK, response.getStatus());
        assertEquals("big test", response.getEntity().getText());
    }

    public void testForm() throws Exception {
        Response response = get("form");
        assertEquals(Status.SUCCESS_OK, response.getStatus());
        Representation entity = response.getEntity();
        assertEquals("firstname=Angela&lastname=Merkel", entity.getText());

        response = post("form", createForm().getWebRepresentation());
        assertEquals(Status.SUCCESS_OK, response.getStatus());
        Representation respEntity = response.getEntity();
        assertEquals("[firstname: Angela, lastname: Merkel]", respEntity
                .getText());
    }

    public void testInputStream() throws Exception {
        Response response = get("InputStream");
        assertEquals(Status.SUCCESS_OK, response.getStatus());
        Representation entity = response.getEntity();
        assertEquals(ProviderTestService.ALPHABET, entity.getText());

        entity = new StringRepresentation("big test");
        response = post("InputStream", entity);
        assertEquals(Status.SUCCESS_OK, response.getStatus());
        entity = response.getEntity();
        assertEquals("big test", entity.getText());
    }

    public void testJaxbElementGet() throws Exception {
        getAndCheckJaxb("jaxbElement");
    }

    // TODO JSR311: don't know how to implement the read of a JAXBElement.
    // public void testJaxbElementPost() throws Exception {
    // postAndCheck("jaxbElement");
    // }

    public void testJaxbGet() throws Exception {
        getAndCheckJaxb("jaxb");
    }

    public void testJaxbPost() throws Exception {
        postAndCheck("jaxb");
    }

    public void testMultivaluedMap() throws Exception {
        Response response = get("MultivaluedMap");
        assertEquals(Status.SUCCESS_OK, response.getStatus());
        Representation entity = response.getEntity();
        assertEquals("lastname=Merkel&firstname=Angela", entity.getText());

        response = post("MultivaluedMap", createForm().getWebRepresentation());
        assertEquals(Status.SUCCESS_OK, response.getStatus());
        Representation respEntity = response.getEntity();
        assertEquals("[lastname: Merkel, firstname: Angela]", respEntity
                .getText());
    }

    public void testXmlTransform() throws Exception {
        // TODO XmlTransform
    }
}
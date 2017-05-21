/*
 * Copyright (c) 2012-2017 Shailendra Singh <shailendra_01@outlook.com>
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
package in.koyad.piston.common.util.xml;

import java.io.StringReader;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.xml.sax.InputSource;

import in.koyad.piston.common.basic.exception.FrameworkException;
import in.koyad.piston.common.util.LogUtil;

/**
 * @author shailendra
 *
 */
public class XMLUtil {
	
	private static final LogUtil LOGGER = LogUtil.getLogger(XMLUtil.class);

	public static Document getDocument(String xml) throws FrameworkException {
		Document doc = null;
		try {
//			XPathFactory xpathFactory = XPathFactory.newInstance();
//			XPath xpath = xpathFactory.newXPath();
		
			InputSource source = new InputSource(new StringReader(xml));
//			return (Document) xpath.evaluate("/", source, XPathConstants.NODE);
		
			DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = builderFactory.newDocumentBuilder();
			doc = builder.parse(source);
		} catch (Exception ex) {
			LOGGER.logException(ex);
			throw new FrameworkException(ex.getMessage());
		}
		
		return doc;
	}
	
}

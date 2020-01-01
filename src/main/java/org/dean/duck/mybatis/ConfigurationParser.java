package org.dean.duck.mybatis;

import org.apache.ibatis.io.Resources;
import org.w3c.dom.Document;
import org.xml.sax.ErrorHandler;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;

/**
 * 配置文件解析
 */
public class ConfigurationParser {
	private static final String resource = "resources/nodelet_test.xml";


	public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException {
		InputStream inputStream = Resources.getResourceAsStream(resource);
		Document document = createDocument(new InputSource(inputStream));
		System.out.println(document.getPrefix());
		System.out.println(document.getXmlVersion());
		System.out.println(document.getDoctype().getPrefix());
	}

	private static Document createDocument(InputSource inputSource) throws IOException, SAXException, ParserConfigurationException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setValidating(false);
		factory.setNamespaceAware(false);
		factory.setIgnoringComments(true);
		factory.setIgnoringElementContentWhitespace(false);
		factory.setCoalescing(false);
		factory.setExpandEntityReferences(true);
		DocumentBuilder builder = factory.newDocumentBuilder();
		builder.setEntityResolver(null);
		builder.setErrorHandler(new ErrorHandler() {
			@Override
			public void warning(SAXParseException exception) throws SAXException {
			}

			@Override
			public void error(SAXParseException exception) throws SAXException {
				throw exception;
			}

			@Override
			public void fatalError(SAXParseException exception) throws SAXException {
				throw exception;
			}
		});
		return builder.parse(inputSource);
	}


}

package com.demirelenes.bankingapp.currency.service.parser;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;

public interface IParser {

    Document parse(InputStream source) throws IOException, SAXException, ParserConfigurationException;
}

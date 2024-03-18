package com.demirelenes.bankingapp.currency.service.reader;

import org.w3c.dom.Document;

import java.util.List;

public interface IReader<T> {

    List<T> read(Document source);
}

package com.bulk.po.service;

import java.io.InputStream;

import org.springframework.stereotype.Service;

@Service
public interface ExcelParserService {

	<T> T parseExcelFromByteStream(InputStream iostream, Class<T> objClass);
}

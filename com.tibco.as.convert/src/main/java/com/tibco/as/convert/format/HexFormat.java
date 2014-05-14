package com.tibco.as.convert.format;

import javax.xml.bind.DatatypeConverter;

public class HexFormat extends BlobFormat {

	private static final long serialVersionUID = -5241322851358589993L;

	@Override
	protected String format(byte[] bytes) {
		return DatatypeConverter.printHexBinary(bytes);
	}

	@Override
	protected byte[] parse(String string) {
		return DatatypeConverter.parseHexBinary(string);
	}
}
package com.tibco.as.xml;

import java.io.IOException;
import java.util.Arrays;

import javax.xml.bind.JAXBException;

import org.junit.Assert;
import org.junit.Test;

import com.tibco.as.space.ASException;
import com.tibco.as.space.FieldDef;
import com.tibco.as.space.FieldDef.FieldType;
import com.tibco.as.space.SpaceDef;

public class TestMetaspaceManager {

	@Test
	public void testGetMetaspaceByDisplayName() throws JAXBException,
			IOException, ASException {
		MetaspaceManager manager = MetaspaceManager.getInstance();
		com.tibco.as.xml.Metaspace metaspace = manager
				.getMetaspaceByDisplayName("ms1");
		Assert.assertEquals("ms1", metaspace.getName());
	}

	@Test
	public void testSpaceDef() {
		SpaceDef spaceDef = SpaceDef.create("space1", 0,
				Arrays.asList(FieldDef.create("field1", FieldType.LONG)));
		spaceDef.getKeyDef().setFieldNames("field1");
		Space space = MetaspaceManager.getXMLSpace(spaceDef);
		Assert.assertEquals(spaceDef.getName(), space.getName());
		FieldDef fieldDef = spaceDef.getFieldDefs().iterator().next();
		Field field = space.getField().get(0);
		Assert.assertEquals(fieldDef.getName(), field.getName());
		Assert.assertEquals(fieldDef.getType(), field.getType());
		Assert.assertEquals(true, field.isKey());
		Assert.assertEquals(spaceDef.getKeyDef().getFieldNames().iterator()
				.next(), field.getName());
		Assert.assertEquals(false, field.isDistribution());
		Assert.assertEquals(spaceDef.getCachePolicy(), space.getCachePolicy());
	}

}
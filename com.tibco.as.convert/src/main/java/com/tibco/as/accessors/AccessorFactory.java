package com.tibco.as.accessors;

import java.util.ArrayList;
import java.util.Collection;

import com.tibco.as.space.FieldDef;

/**
 * A factory for building/retrieving FieldAccessor instances.
 * 
 * @author Julien Ruaux
 */
public class AccessorFactory {

	public final static int DOUBLE_SIZE = Double.toString(Math.PI).length();
	public final static int FLOAT_SIZE = Float.toString((float) Math.PI)
			.length();
	public final static int LONG_SIZE = Long.toString(Long.MAX_VALUE).length();
	public final static int INTEGER_SIZE = Integer.toString(Integer.MAX_VALUE)
			.length();
	public final static int SHORT_SIZE = Short.toString(Short.MAX_VALUE)
			.length();

	public static ITupleAccessor create(FieldDef fieldDef) {
		if (fieldDef == null) {
			return null;
		}
		switch (fieldDef.getType()) {
		case BLOB:
			return new BlobAccessor(fieldDef);
		case BOOLEAN:
			return new BooleanAccessor(fieldDef);
		case CHAR:
			return new CharacterAccessor(fieldDef);
		case DATETIME:
			return new DateTimeAccessor(fieldDef);
		case DOUBLE:
			return new DoubleAccessor(fieldDef);
		case FLOAT:
			return new FloatAccessor(fieldDef);
		case INTEGER:
			return new IntegerAccessor(fieldDef);
		case LONG:
			return new LongAccessor(fieldDef);
		case SHORT:
			return new ShortAccessor(fieldDef);
		default:
			return new StringAccessor(fieldDef);
		}
	}

	public static Collection<ITupleAccessor> create(
			Collection<FieldDef> fieldDefs) {
		Collection<ITupleAccessor> accessors = new ArrayList<ITupleAccessor>();
		for (FieldDef fieldDef : fieldDefs) {
			accessors.add(create(fieldDef));
		}
		return accessors;
	}

}
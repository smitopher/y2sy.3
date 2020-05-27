package com.cmfirsttech.y2.api.model;

import org.jboss.logging.Logger;

import com.cmfirsttech.y2.api.model.impl.AdACTIONFormat;
import com.cmfirsttech.y2.api.model.impl.AdBLOCKFormat;
import com.cmfirsttech.y2.api.model.impl.AdCOMPAREFormat;
import com.cmfirsttech.y2.api.model.impl.AdCONDITFormat;
import com.cmfirsttech.y2.api.model.impl.AdCONTEXTFormat;
import com.cmfirsttech.y2.api.model.impl.AdPARAMFormat;
import com.cmfirsttech.y2.api.model.impl.AdSUBBLKFormat;
import com.cmfirsttech.y2.api.model.impl.AdUSREXPFormat;

public class ADElementFactory {
	private static final Logger logger = Logger.getLogger(ADElementFactory.class);
	public static IActionDiagram newInstance(String type) {
		logger.debugv("Create AD of type %1$s", type);
		return  switch (ADType.valueOf(type)) {
		case ACT -> new AdACTIONFormat();
		case BLK -> new AdBLOCKFormat();
		case CMP -> new AdCOMPAREFormat();
		case CND -> new AdCONDITFormat();
		case CTX -> new AdCONTEXTFormat();
		case EXP -> new AdUSREXPFormat();
		case PAR -> new AdPARAMFormat();
		case SUB -> new AdSUBBLKFormat();
		};

	}

}

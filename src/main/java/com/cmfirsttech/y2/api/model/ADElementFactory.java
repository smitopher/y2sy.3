package com.cmfirsttech.y2.api.model;

import java.util.Objects;
import java.util.Optional;

import org.jboss.logging.Logger;

import com.cmfirsttech.y2.api.entity.impl.Y2FunctionActionDiagram;
import com.cmfirsttech.y2.api.mapper.MappingException;
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
	public static IActionDiagram newInstance(Y2FunctionActionDiagram actionDiagramElement) {
		Objects.requireNonNull(actionDiagramElement, "Function Action Diagram element may not be null");
		Optional<ADType> adType = ADType.fromString(actionDiagramElement.elementType);
		if (adType.isEmpty()) {
			String message = String.format(
					"Action Diagram Element type mapping error: Function surrogate: %1$d, Element number: %2$d, ElementType: %3$s",
					actionDiagramElement.adId.functionSurrogate, 
					actionDiagramElement.adId.elementNo, 
					actionDiagramElement.elementType);
			logger.error(message);
			throw new MappingException(message);

		}
		IActionDiagram adElement = switch (adType.get()) {
		case ACT -> new AdACTIONFormat();
		case BLK -> new AdBLOCKFormat();
		case CMP -> new AdCOMPAREFormat();
		case CND -> new AdCONDITFormat();
		case CTX -> new AdCONTEXTFormat();
		case EXP -> new AdUSREXPFormat();
		case PAR -> new AdPARAMFormat();
		case SUB -> new AdSUBBLKFormat();
		};
		return adElement;
	}

}

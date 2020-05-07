/*******************************************************************************
 * Copyright 2020 christopher.smith
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License.  You may obtain a copy
 * of the License at
 * 
 *   http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations under
 * the License.
 ******************************************************************************/
package com.cmfirsttech.y2.api.constants;

import java.util.Arrays;
import java.util.Optional;

import com.fasterxml.jackson.annotation.JsonValue;

public enum ObjectAttribute {
	CBI,
	CBL,
	CDE,
	CLC,
	CLL,
	CLP,
	CMP,
	COL,
	CON,
	CPT,
	CTL,
	DAT,
	DBF,
	DDS,
	DSP,
	DT_,
	D8_,
	DTE,
	ERR,
	EXC,
	INF,
	IX1,
	LST,
	MDL,
	MOV,
	NAR,
	NBR,
	PHY,
	PNL,
	PRC,
	PRT,
	QTY,
	RCD,
	REF,
	RNG,
	RPG,
	RP4,
	RSQ,
	RTV,
	SDF,
	SGT,
	SPN,
	STR,
	STS,
	TM,
	TM_,
	TME,
	TRG,
	TS_,
	TXT,
	UPD,
	USR,
	VAL,
	VNM,
	Y2E,
	ALL;
	
	@JsonValue
	public final String jsonString;

	private ObjectAttribute() {
		jsonString = name().replace('_', '#');
	}
	
	public static Optional<ObjectAttribute> fromJsonValue(String s) {
		return Arrays.stream(ObjectAttribute.values()).filter(l -> s.equalsIgnoreCase(l.jsonString)).findAny();
	}

	@Override
	public String toString() {
		return jsonString;
	}
}

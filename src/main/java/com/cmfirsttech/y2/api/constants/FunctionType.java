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

public enum FunctionType {
	CRTOBJ(FunctionTypeGroup.DATABASE),
	CHGOBJ(FunctionTypeGroup.DATABASE),
	DLTOBJ(FunctionTypeGroup.DATABASE),
	RTVOBJ(FunctionTypeGroup.DATABASE),
	DFNRPTFMT(FunctionTypeGroup.DEVICE),
	DFNSCRFMT(FunctionTypeGroup.DEVICE),
	DSPRCD(FunctionTypeGroup.DEVICE),
	DSPRCD2(FunctionTypeGroup.DEVICE),
	DSPRCD3(FunctionTypeGroup.DEVICE),
	PMTRCD(FunctionTypeGroup.DEVICE),
	EDTRCD(FunctionTypeGroup.DEVICE),
	EDTRCD2(FunctionTypeGroup.DEVICE),
	EDTRCD3(FunctionTypeGroup.DEVICE),
	DSPFIL(FunctionTypeGroup.DEVICE),
	EDTFIL(FunctionTypeGroup.DEVICE),
	SELRCD(FunctionTypeGroup.DEVICE),
	DSPTRN(FunctionTypeGroup.DEVICE),
	EDTTRN(FunctionTypeGroup.DEVICE),
	PRTFIL(FunctionTypeGroup.DEVICE),
	PRTOBJ(FunctionTypeGroup.DEVICE),
	EXCINTFUN(FunctionTypeGroup.USER),
	EXCEXTFUN(FunctionTypeGroup.USER),
	EXCUSRPGM(FunctionTypeGroup.USER),
	EXCUSRSRC(FunctionTypeGroup.USER),
	SUM(FunctionTypeGroup.FUNCTION_FIELD),
	MAX(FunctionTypeGroup.FUNCTION_FIELD),
	MIN(FunctionTypeGroup.FUNCTION_FIELD),
	CNT(FunctionTypeGroup.FUNCTION_FIELD),
	DRV(FunctionTypeGroup.FUNCTION_FIELD),
	USR(FunctionTypeGroup.FUNCTION_FIELD),
	SNDERRMSG(FunctionTypeGroup.MESSAGE),
	SNDINFMSG(FunctionTypeGroup.MESSAGE),
	SNDCMPMSG(FunctionTypeGroup.MESSAGE),
	SNDSTSMSG(FunctionTypeGroup.MESSAGE),
	RTVMSG(FunctionTypeGroup.MESSAGE),
	EXCMSG(FunctionTypeGroup.MESSAGE),
	ADD(FunctionTypeGroup.BUILT_IN),
	COMMIT(FunctionTypeGroup.BUILT_IN),
	COMPUTE(FunctionTypeGroup.BUILT_IN),
	CONCAT(FunctionTypeGroup.BUILT_IN),
	CVTVAR(FunctionTypeGroup.BUILT_IN),
	DATE_DETAILS(FunctionTypeGroup.BUILT_IN),
	DATE_INCREMENT(FunctionTypeGroup.BUILT_IN),
	DIV(FunctionTypeGroup.BUILT_IN),
	DIV_WITH_REMAINDER(FunctionTypeGroup.BUILT_IN),
	DURATION(FunctionTypeGroup.BUILT_IN),
	ELAPSED_TIME(FunctionTypeGroup.BUILT_IN),
	EXIT_PROGRAM(FunctionTypeGroup.BUILT_IN),
	MODULO(FunctionTypeGroup.BUILT_IN),
	MOVE(FunctionTypeGroup.BUILT_IN),
	MOVE_ALL(FunctionTypeGroup.BUILT_IN),
	MOVE_ARRAY(FunctionTypeGroup.BUILT_IN),
	MULT(FunctionTypeGroup.BUILT_IN),
	QUIT(FunctionTypeGroup.BUILT_IN),
	ROLLBACK(FunctionTypeGroup.BUILT_IN),
	RTVCND(FunctionTypeGroup.BUILT_IN),
	RTVFLDINF(FunctionTypeGroup.BUILT_IN),
	SET_CURSOR(FunctionTypeGroup.BUILT_IN),
	SUB(FunctionTypeGroup.BUILT_IN),
	SUBSTRING(FunctionTypeGroup.BUILT_IN),
	TIME_DETAILS(FunctionTypeGroup.BUILT_IN),
	TIME_INCREMENT(FunctionTypeGroup.BUILT_IN);
	
	public final FunctionTypeGroup group;
	@JsonValue
	public final String jasonValue;

	private FunctionType(FunctionTypeGroup group) {
		this.group = group;
		StringBuilder jsonBuilder = new StringBuilder();
		switch (group) {
		case BUILT_IN:
			jsonBuilder.append('*');
			break;

		default:
			break;
		}
		jsonBuilder.append(name().replace('_', ' '));
		jasonValue = jsonBuilder.toString();
	}
	
	public static Optional<FunctionType> fromJsonValue(String s) {
		return Arrays.stream(FunctionType.values()).filter(l -> s.equals(l.jasonValue)).findAny();
	}

	@Override
	public String toString() {
		return jasonValue;
	}
	
}


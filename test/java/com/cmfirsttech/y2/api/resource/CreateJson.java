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
package com.cmfirsttech.y2.api.resource;

import java.util.ArrayList;
import java.util.Optional;

import com.cmfirsttech.y2.api.constants.ObjectType;
import com.cmfirsttech.y2.api.model.internal.ModelListQuery;
import com.cmfirsttech.y2.api.model.internal.ModelQueries;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;

public class CreateJson {

	public static void main(String[] args) throws JsonProcessingException {
		ObjectMapper mapper = new ObjectMapper();
		mapper.registerModule(new Jdk8Module());
		ModelQueries modelQueries = new ModelQueries();
		modelQueries.queries = new ArrayList<>();
		ModelListQuery query = new ModelListQuery();
		query.object = Optional.of("xx");
		query.objectType = Optional.of(ObjectType.ACP);
		query.owner = Optional.of("yy");
		modelQueries.queries.add(query);
		query = new ModelListQuery();
		query.owner = Optional.empty();
		query.objectType = Optional.empty();
		query.owner = Optional.empty();
		modelQueries.queries.add(query);
		String json = mapper.writeValueAsString(modelQueries);
		
		System.out.println(json);
	}

}

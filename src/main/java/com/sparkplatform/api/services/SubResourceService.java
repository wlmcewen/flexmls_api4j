//
//  Copyright (c) 2013 Financial Business Systems, Inc. All rights reserved.
//
//  Licensed under the Apache License, Version 2.0 (the "License");
//  you may not use this file except in compliance with the License.
//  You may obtain a copy of the License at
//
//  http://www.apache.org/licenses/LICENSE-2.0
//
//  Unless required by applicable law or agreed to in writing, software
//  distributed under the License is distributed on an "AS IS" BASIS,
//  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
//  See the License for the specific language governing permissions and
//  limitations under the License.
//

package com.sparkplatform.api.services;

import com.sparkplatform.api.core.Client;
import com.sparkplatform.api.models.Base;

public abstract class SubResourceService<T extends Base> extends BaseService<T> {

	private String prefix = "";

	public SubResourceService(Client c, String prefix) {
		super(c);
		this.prefix = prefix;
	}

	@Override
	public String getPath() {
		return getPrefix() + getSubResourcePath();
	}
	
	public String getPrefix() {
		return prefix;
	}

	public void setPrefix(String prefix) {
		this.prefix = prefix;
	}

	public abstract String getSubResourcePath();

}
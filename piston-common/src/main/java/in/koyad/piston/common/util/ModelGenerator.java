/*
 * Copyright (c) 2012-2017 Shailendra Singh <shailendra_01@outlook.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package in.koyad.piston.common.util;

import in.koyad.piston.common.basic.exception.FrameworkException;

/**
* Generic class for converting Form to Model and vice versa.
*
* @author Shailendra Singh
* @since 2.4
*/
public class ModelGenerator {
	
	private static final LogUtil LOGGER = LogUtil.getLogger(ModelGenerator.class);
	
	public static <F, M> M getModelFromForm(F form, Class<M> modelClass) throws FrameworkException {
		M model = null;
		try {
			model = modelClass.newInstance();
			BeanPropertyUtils.copyProperties(model, form);
		} catch (Exception ex) {
			LOGGER.logException(ex);
			throw new FrameworkException("Error occured whilte creating model object from form object.");
		}
		return model;
	}

	public static <F, M> F getFormFromModel(Class<F> formClass, M model) throws FrameworkException {
		F form = null;
		try {
			form = formClass.newInstance();
			BeanPropertyUtils.copyProperties(form, model);
		} catch (Exception ex) {
			LOGGER.logException(ex);
			throw new FrameworkException("Error occured whilte creating form object from model object.");
		}
		return form;
	}
}
